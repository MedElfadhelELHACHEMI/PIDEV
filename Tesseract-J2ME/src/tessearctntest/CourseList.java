package tessearctntest;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tesseracttest.CurrentUser;
import tesseracttest.Midlet;

/**
 *
 * @author Noor
 */
class CourseList extends List implements CommandListener, Runnable {

    Command back, view;
    Cours[] courses;

    public CourseList(String title, int listType) {
        super("Edit Courses", List.IMPLICIT);

        view = new Command("View", Command.SCREEN, 0);
        back = new Command("Back", Command.BACK, 0);

        Thread thread = new Thread(this);
        thread.start();

        addCommand(view);
        addCommand(back);

        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == back) {
            Midlet.INSTANCE.disp.setCurrent(new CoursesCanvas());

        } else if (c == view) {

            try {
                Midlet.INSTANCE.disp.setCurrent(new CourseDetails(getString(getSelectedIndex()), courses[getSelectedIndex()]));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    public void run() {
    
        try {
            CourseHandler handler = new CourseHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection connection;
            connection = (HttpConnection) Connector.open("http://localhost/Business/CoursesList.php?userId="+CurrentUser.getId());
            DataInputStream dis;
            dis = new DataInputStream(connection.openDataInputStream());
            parser.parse(dis, handler);
            courses = handler.getCourses();
            for (int i = 0; i < courses.length; i++) {
                   insert(i, courses[i].getNomCours(), null);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

}
