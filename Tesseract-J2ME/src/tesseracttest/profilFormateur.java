/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesseracttest;

import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Form;
import com.sun.lwuit.Graphics;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextField;
import com.sun.lwuit.animations.Transition3D;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.contactless.ndef.NDEFRecordType;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author haikal
 */
public class profilFormateur extends Form implements Runnable {

    private Label nom;
    private Label prenom;
    private Label score;
    private Label adresseMail;
    private TextField nomTF;
    private TextField prenomTF;
    private Label scoreTF;
    private Label org;
    private Label orgTF;
    private TextField adressTF;
    private Button b1 = new Button("SUBMIT");
    private Button b2 = new Button("BACK");
    private Command ok = new Command("SUBMIT");
    private Command back = new Command("BACK");
    private Image font;
    private Formateur[] formateurs;
    Image image;

    public profilFormateur() {

        nom = new Label("First name");
        nom.getStyle().setBgTransparency(0);
        prenom = new Label("Family Name");
        prenom.getStyle().setBgTransparency(0);
        adresseMail = new Label("Mail");
        adresseMail.getStyle().setBgTransparency(0);
        score = new Label();
        org = new Label();
        //score.getStyle().setBgTransparency(0);
        score.getStyle().setBgTransparency(0);
        org.getStyle().setBgTransparency(0);
        getMenuBar().getStyle().setBgTransparency(0);
        nom.getStyle().setBackgroundType(Byte.parseByte("1"));
        nomTF = new TextField();
        prenomTF = new TextField();
        adressTF = new TextField();
        scoreTF = new Label();
        org = new Label();
        addComponent(nom);
        nomTF.setLabelForComponent(nom);
        addComponent(nomTF);
        addComponent(prenom);
        prenomTF.setLabelForComponent(prenom);
        addComponent(prenomTF);
        addComponent(adresseMail);
        adressTF.setLabelForComponent(adresseMail);
        addComponent(adressTF);
        addComponent(score);
        addComponent(org);
//        scoreTF.setLabelForComponent(score);
//        addComponent(scoreTF);
//     //   addComponent(score);
//        orgTF.setLabelForComponent(org);
        // addComponent(orgTF);
        b1.setX(getWidth() / 4);
        b1.setY(getHeight() - 100);
        b2.setX(getWidth() / 2);
        b2.setY(getHeight() - 100);
        addCommand(back);
        addCommand(ok);
        addCommandListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (ae.getCommand() == back) {
                    AccueilProfil aa = new AccueilProfil();
                    aa.setTransitionOutAnimator(Transition3D.createRotation(1000, false));
                    aa.show();
                } else if (ae.getCommand() == ok) {
                    updateFormateur();

                }
            }

        });
        Thread thread = new Thread(this);
        thread.start();
        repaint();

    }

    private void updateFormateur() {
        System.out.println("nom=" + nomTF.getText() + "&prenom=" + prenomTF.getText() + "&mail=" + adressTF.getText()+"&id="+CurrentUser.getId());
        try {
            HttpConnection connection;

            connection = (HttpConnection) Connector.open("http://localhost/Business/updateFormateur.php?nom=" + nomTF.getText() + "&prenom=" + prenomTF.getText() + "&mail=" + adressTF.getText()+"&id="+CurrentUser.getId());

            DataInputStream dis;

            dis = connection.openDataInputStream();
            int ascii;
            StringBuffer sb = new StringBuffer();

            while ((ascii = dis.read()) != -1) {

                sb.append((char) ascii);

            }
            if (sb.toString().equals("O")) {

                Alert a = new Alert("Successefully modified", sb.toString(), null, AlertType.CONFIRMATION);
                a.setTimeout(3000);

                Midlet.INSTANCE.disp.setCurrent(a, new HomeCanvas(""));
            }

        } catch (IOException ex) {
            Alert a = new Alert("Error","", null, AlertType.CONFIRMATION);
            a.setTimeout(3000);
            Midlet.INSTANCE.disp.setCurrent(a);
        }

    }

    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        getMenuBar().getStyle().setBgTransparency(0);
        score.getStyle().setBgTransparency(0);
        org.getStyle().setBgTransparency(0);
        //      score.setY(400);
        //score.setX(getWidth()-score.getWidth());
    }

    public void paintBackground(Graphics g) {
        super.paintBackground(g);
        score.getStyle().setBgTransparency(0);
        org.getStyle().setBgTransparency(0);
        //    score.setY(400);
        //       score.setX(getWidth()-score.getWidth());
        g.setColor(0xa6dae8);
        g.fillRect(0, 0, getWidth(), getHeight());
        getMenuBar().getStyle().setBgTransparency(0);
        try {
            font = Image.createImage("/tesseracttest/images/bgstat.png");
            g.drawImage(font, 0, 0);
            g.setColor(255);
            if (image != null) {
                g.drawImage(image, 200, 0);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        try {
            FormateurHandler handler = new FormateurHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection connection;

            connection = (HttpConnection) Connector.open("http://localhost/Business/getFormateurById.php?idUtilisateur=" + CurrentUser.getId());

            DataInputStream dis;

            dis = new DataInputStream(connection.openDataInputStream());
            parser.parse(dis, handler);

            formateurs = handler.getFormateurs();

            for (int i = 0; i < formateurs.length; i++) {
                nomTF.setText(formateurs[i].getNom());
                prenomTF.setText(formateurs[i].getPrenom());
                adressTF.setText(formateurs[i].getMail());
                score.setText("Your score is " + formateurs[i].getScore());

                org.setText(getOrganisation(formateurs[i].getIdOrgganisation()) + " is your Organisation");

            }
            repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    public void keyPressed(int keyCode) {
        super.keyPressed(keyCode); //To change body of generated methods, choose Tools | Templates.
        repaint();
    }

    private String getOrganisation(int idOrgganisation) {
        try {
            OrganismeHandler handler = new OrganismeHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection connection;

            connection = (HttpConnection) Connector.open("http://localhost/Business/selectOrgsById.php?idOrganisme=" + idOrgganisation);

            DataInputStream dis;

            dis = new DataInputStream(connection.openDataInputStream());
            parser.parse(dis, handler);

            Organisation[] organisations = handler.getOrganisme();

            for (int i = 0; i < formateurs.length; i++) {
                return organisations[i].getNom();
            }
            repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
        return "No organisation";
    }

}
