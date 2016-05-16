/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesseracttest;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author haikal
 */
class DetailsOrganismeForInvitation extends Form implements Runnable, CommandListener {

    private static String nom;
    private Organisation[] organisation;
    StringItem item1;
    StringItem item2;
    int id = 0;
    StringItem item3;
    StringItem item4;
    StringItem item5;
    StringBuffer buffer;
    Command back;
    Command invite;
    private StringBuffer sb;

    public DetailsOrganismeForInvitation(String string) {
        super(string);
        back = new Command("BACK", Command.BACK, 0);
        invite = new Command("INVT", Command.SCREEN, 0);
        setNom(string);
        addCommand(invite);
        addCommand(back);
        Thread thread = new Thread(this);
        thread.start();
    }

    public static void setNom(String nom) {
        DetailsOrganismeForInvitation.nom = nom;
    }

    public void run() {

        setCommandListener(this);
        try {
            OrganismeHandler handler = new OrganismeHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection connection;

            connection = (HttpConnection) Connector.open("http://localhost/Business/selectOrgsByNom.php?nom=" + nom);

            DataInputStream dis;

            dis = new DataInputStream(connection.openDataInputStream());
            parser.parse(dis, handler);

            organisation = handler.getOrganisme();
            System.out.println(organisation.length);
            for (int i = 0; i < organisation.length; i++) {

                item1 = new StringItem("Nom organisme  :", organisation[i].getNom());
                item2 = new StringItem("Adresse  :", organisation[i].getAdresse());
                item3 = new StringItem("Nombre des formateurs", String.valueOf(getNombreFormateur(organisation[i].getIdOrganisation())));
                item4 = new StringItem("Email", organisation[i].geteMail());
                id = organisation[i].getIdOrganisation();
                append(item1);
                append(item2);
                append(item3);
                append(item4);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    public int getNombreFormateur(int idOrganisme) throws ParserConfigurationException, SAXException, IOException {

        HttpConnection connection;

        connection = (HttpConnection) Connector.open("http://localhost/Business/nbFormateurOrg.php?id_organisation=" + idOrganisme);

        DataInputStream dis;

        dis = connection.openDataInputStream();
        int ascii;
        buffer = new StringBuffer();

        while ((ascii = dis.read()) != -1) {

            buffer.append((char) ascii);

        }

        return Integer.parseInt(buffer.toString().trim());

    }

    public void commandAction(Command c, Displayable d) {

        if (c == back) {
            ChercherOrganisationForm cof = new ChercherOrganisationForm("Organisms");
            cof.show();

        } else if (c == invite) {
            try {
                HttpConnection connection;
                System.out.println("    " + CurrentUser.getId() + "   " + id);
                connection = (HttpConnection) Connector.open("http://localhost/Business/verifInvitations.php?id_organisme=" + id + "&id_utilisateur=" + CurrentUser.getId());

                DataInputStream dis;

                dis = connection.openDataInputStream();
                int ascii;
                sb = new StringBuffer();

                while ((ascii = dis.read()) != -1) {

                    sb.append((char) ascii);

                }
                System.out.println(sb);

                if (sb.toString().equals("existe")) {

                    Alert a = new Alert("Invitation deja envoyée", sb.toString(), null, AlertType.CONFIRMATION);
                    a.setTimeout(3000);

                    Midlet.INSTANCE.disp.setCurrent(a, new ListOrganismeResearch(""));

                } else {
                    ascii = 0;
                    connection = (HttpConnection) Connector.open("http://localhost/Business/AjoutOrganisme.php?id_organisme=" + id + "&id_utilisateur=" + CurrentUser.getId() + "");
                    dis = connection.openDataInputStream();

                    sb = new StringBuffer();

                    while ((ascii = dis.read()) != -1) {

                        sb.append((char) ascii);

                    }
                    if (sb.toString().equals("O")) {

                        Alert a = new Alert("Invitation  envoyée", sb.toString(), null, AlertType.CONFIRMATION);
                        a.setTimeout(3000);

                        Midlet.INSTANCE.disp.setCurrent(a, new ListOrganismeResearch(""));
                    }

                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private int getOrganismeId(String nom) {

        return 1;
    }
}
