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
class EventList extends List implements CommandListener, Runnable {

    Command back, details;
    Evenement[] events;

    public EventList(String title, int listType) {
        super(title, List.IMPLICIT);
        details = new Command("Details", Command.SCREEN, 0);
        back = new Command("Back", Command.BACK, 0);

        Thread thread = new Thread(this);
        thread.start();
        addCommand(details);
        addCommand(back);

        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == back) {
            Midlet.INSTANCE.disp.setCurrent(new CoursesCanvas());

        } 
        else if (c == details) {

            try {
                Midlet.INSTANCE.disp.setCurrent(new EventDetails(getString(getSelectedIndex()),events[getSelectedIndex()]));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    public void run() {
        try {
            EventHandler handler = new EventHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection connection;
            connection = (HttpConnection) Connector.open("http://localhost/Business/coachEvents.php");
            DataInputStream dis;
            dis = new DataInputStream(connection.openDataInputStream());
            parser.parse(dis, handler);
            events = handler.getEvents();
            for (int i = 0; i < events.length; i++) {
                   insert(i, events[i].getNom(), null);
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
