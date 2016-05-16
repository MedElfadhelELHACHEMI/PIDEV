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
import javax.microedition.lcdui.TextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class DetailsOrganismeForm extends Form implements Runnable, CommandListener {

    private static String nom;
    private Organisation[] organisation;
    StringItem item1;
    StringItem item2;
    StringItem item3;
    StringItem item4;
    StringItem item5;
    StringBuffer buffer;
    Command back;
    Command acc;
    Command ref;

    public DetailsOrganismeForm(String nom) {
        super(nom);
        setNom(nom);
        back = new Command("BACK", Command.BACK, 0);
        acc = new Command("ACC", Command.OK, 0);
        ref = new Command("REF", Command.OK, 0);
        addCommand(acc);
        addCommand(ref);
        addCommand(back);
        Thread thread = new Thread(this);
        thread.start();
    }

    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {

        DetailsOrganismeForm.nom = nom;
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

    public void commandAction(Command c, Displayable d) {
        if (c == back) {
            Midlet.INSTANCE.disp.setCurrent(new BoiteMailCanvas());
        }
        if (c == acc) {
            repondreInvitation(true);
            Midlet.INSTANCE.disp.setCurrent(new InvitationsList("Invitations", 5));
        }
        if (c == ref) {
            repondreInvitation(false);
            Midlet.INSTANCE.disp.setCurrent(new InvitationsList("Invitations", 5));
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
    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;

    private void repondreInvitation(boolean reponse) {
        if (reponse) {
            Thread thread = new Thread(new Runnable() {

                public void run() {
                    for (int i = 0; i < organisation.length; i++) {
                        int idO, idU;
                        if (nom.equals(organisation[i].getNom())) {
                            try {

                                idO = organisation[i].getIdOrganisation();
                                idU = CurrentUser.getId();
                                hc = (HttpConnection) Connector.open("http://localhost/Business/accepterInvitation.php?id_organisme=" + idO + "&id_utilisateur=" + idU);
                                dis = hc.openDataInputStream();
                                int ascii;
                                sb = new StringBuffer();

                                while ((ascii = dis.read()) != -1) {

                                    sb.append((char) ascii);

                                }

                                if (sb.toString().equals("O")) {
                                    hc = (HttpConnection) Connector.open("http://localhost/Business/affecterOrganismeUtilisateur.php?id=" + idU + "&idOrg=" + idO);
                                    dis = hc.openDataInputStream();
                                    ascii = 0;

                                    sb = new StringBuffer();

                                    while ((ascii = dis.read()) != -1) {

                                        sb.append((char) ascii);
                                        if (sb.toString().equals("O")) {
                                            Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                                            a.setTimeout(3000);
                                            Midlet.INSTANCE.disp.setCurrent(new InvitationsList("Invitations", 0));
                                        }
                                    }

                                }

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                        }
                    }
                }
            });
            thread.start();
        } else {
            Thread thread = new Thread(new Runnable() {

                public void run() {
                    for (int i = 0; i < organisation.length; i++) {
                        int idO, idU;
                        if (nom.equals(organisation[i].getNom())) {
                            try {

                                idO = organisation[i].getIdOrganisation();
                                idU = CurrentUser.getId();
                                hc = (HttpConnection) Connector.open("http://localhost/Business/refuserInvitation.php?id_organisme=" + idO + "&id_utilisateur=" + idU);
                                dis = hc.openDataInputStream();
                                int ascii;
                                sb = new StringBuffer();

                                while ((ascii = dis.read()) != -1) {

                                    sb.append((char) ascii);

                                }

                                if (sb.toString().equals("O")) {

                                    Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                                    a.setTimeout(3000);
                                    Midlet.INSTANCE.disp.setCurrent(new InvitationsList("Invitations", 0));

                                }

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                        }
                    }
                }
            }
            );
            thread.start();
        }
    }

}
