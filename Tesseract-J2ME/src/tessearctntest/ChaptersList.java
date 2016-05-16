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
import tesseracttest.Midlet;

/**
 *
 * @author Noor
 */
public class ChaptersList extends List implements CommandListener, Runnable {

    Command back, view, addNew;
    Chapitre[] chapters;
    Cours cours;

    public ChaptersList(String title, int listType, Cours intCours) {
        super("Chapters List", List.IMPLICIT);
        view = new Command("View", Command.SCREEN, 0);
        back = new Command("Back", Command.BACK, 0);
        addNew = new Command("Add New", Command.SCREEN, 0);
        cours = intCours;
        Thread thread = new Thread(this);
        thread.start();

        addCommand(view);
        addCommand(back);
        addCommand(addNew);

        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == back) {
            try {
                Midlet.INSTANCE.disp.setCurrent(new CourseDetails("", cours));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else if (c == view) {

            Midlet.INSTANCE.disp.setCurrent(new ChapterDetails(getString(getSelectedIndex()), chapters[getSelectedIndex()], cours));

        } else if (c == addNew) {

            Midlet.INSTANCE.disp.setCurrent(new AddChapterForm("Add Chapter", cours));

        }
    }

    public void run() {

        try {
            ChapterHandler handler = new ChapterHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection connection;
            connection = (HttpConnection) Connector.open("http://localhost/Business/ChaptersList.php?courseId=" + cours.getIdCours());
            DataInputStream dis;
            dis = new DataInputStream(connection.openDataInputStream());
            parser.parse(dis, handler);
            chapters = handler.getChapters();
            for (int i = 0; i < chapters.length; i++) {
                insert(i, chapters[i].getNom(), null);
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
