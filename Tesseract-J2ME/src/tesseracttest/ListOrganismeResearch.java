package tesseracttest;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author haikal
 */
class ListOrganismeResearch extends List implements CommandListener{

    private String nom;
    private Command back;
    private Command afficherInf;

    public ListOrganismeResearch(String nom) {
        super("Organisms", List.IMPLICIT);
        setNom(nom);
        back = new Command("BACK", Command.BACK, 0);
        afficherInf= new Command("INFO", Command.SCREEN, 0);
        addCommand(back);
        addCommand(afficherInf);
        setCommandListener(this);
        GenerateList(nom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private void GenerateList(final String nom) {
        Thread d = new Thread(new Runnable() {
            private Organisation[] organisation;

            public void run() {

                try {
                    OrganismeHandler handler = new OrganismeHandler();
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                    HttpConnection connection;

                    connection = (HttpConnection) Connector.open("http://localhost/Business/selectOrgsByLikeNom.php?nom=" + nom);

                    DataInputStream dis;

                    dis = new DataInputStream(connection.openDataInputStream());
                    parser.parse(dis, handler);

                    organisation = handler.getOrganisme();
                    System.out.println(organisation.length);
                    for (int i = 0; i < organisation.length; i++) {

                        append(organisation[i].getNom(), null);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SAXException ex) {
                    ex.printStackTrace();

                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                }

            }
        });
        d.start();

    }

    public void commandAction(Command c, Displayable d) {
        System.out.println("action");
  if(c==back){
      System.out.println("here");
  ChercherOrganisationForm cof = new ChercherOrganisationForm("Organisation's name");
  cof.show();
  }
  else if(c==afficherInf){
       System.out.println("22");
  Midlet.INSTANCE.disp.setCurrent(new DetailsOrganismeForInvitation(getString(getSelectedIndex())));
  
  }
    
    }

}
