package tesseracttest;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class InvitationsList extends List implements CommandListener, Runnable {

    Command details, back;
    Invitation[] invitations;
    Organisation[] organisations;

    public InvitationsList(String title, int i) {
        super(title, List.IMPLICIT);
        details = new Command("INFO", Command.SCREEN, 0);
        back = new Command("BACK", Command.BACK, 0);

        Thread thread = new Thread(this);
        thread.start();
        addCommand(details);
        addCommand(back);

        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == back) {
            Midlet.INSTANCE.disp.setCurrent(new BoiteMailCanvas());

        } else if (c == details) {

            Midlet.INSTANCE.disp.setCurrent(new DetailsOrganismeForm(getString(getSelectedIndex())));

        }

    }

    public void run() {
        try {
            InvitationHandler handler = new InvitationHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection connection;

            connection = (HttpConnection) Connector.open("http://localhost/Business/invitationFormateurById.php?idUtilisateur=" + CurrentUser.getId());

            DataInputStream dis;

            dis = new DataInputStream(connection.openDataInputStream());
            parser.parse(dis, handler);

            invitations = handler.getInvitations();

            for (int i = 0; i < invitations.length; i++) {
                System.out.println("START");
              
                addOrganisme(i,invitations[i].getIdOrganisation()) ;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    private void addOrganisme(int i , int idORganisme) throws IOException, SAXException, ParserConfigurationException {
      OrganismeHandler handler2 = new OrganismeHandler();
                SAXParser parser2 = SAXParserFactory.newInstance().newSAXParser();
                HttpConnection connection2;
                DataInputStream dis2;
                connection2 = (HttpConnection) Connector.open("http://localhost/Business/selectOrgsById.php?idOrganisme=" +idORganisme);

                dis2 = new DataInputStream(connection2.openDataInputStream());
                parser2.parse(dis2, handler2);
                organisations = handler2.getOrganisme();
                if (organisations.length == 1) {
                    insert(i, organisations[0].getNom(), null);
                  
                    organisations = null;
                }

    }
}
