/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.handler.FormateurHandler;
import com.handler.MatiereHandler;
import com.entities.Apprenant;
import com.entities.Formateur;
import com.entities.Matiere;
import com.entities.Utilisateur;
import com.jappit.midmaps.googlemaps.GoogleMaps;
import com.jappit.midmaps.googlemaps.GoogleMapsCoordinates;
import com.jappit.midmaps.googlemaps.GoogleMapsMarker;
import com.jappit.midmaps.googlemaps.GoogleStaticMap;
import com.jappit.midmaps.googlemaps.GoogleStaticMapHandler;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tesseract.Midlet;

/**
 *
 * @author Sameh
 */
public class ProfilApprt extends Form implements GoogleStaticMapHandler, CommandListener, Runnable {

    Apprenant currentUtilisateur;
    GoogleMaps gMaps = null;
    GoogleStaticMap map = null;
    ImageItem mapItem;
    ImageItem imgItem;
    String imgUrl;
    private Matiere[] Matieres;
    Image img;
    double lat;
    double lon;
    private Utilisateur[] formateurs;
    private ImageItem imgItem2;
    private ImageItem imgItem3;
    Form loadingDialog = new Form("Please Wait");
    Form Confirmation = new Form("Confirmation");
    Ticker tk = new Ticker("              Tesseract Coding          ");
    HttpConnection httpConnection;
    DataInputStream dataInputStream;
    StringBuffer stringBuffer = new StringBuffer("");
    Command cmdEditInfo = new Command("Modifier", Command.SCREEN, 0);
    Command cmdBack = new Command("Déconnexion", Command.BACK, 0);
    Command cmdSuppr = new Command("Désactiver mon compte", Command.SCREEN, 0);
    Command cmdSearchMember = new Command("Chercher", Command.SCREEN, 0);
    Command cmdInsc = new Command("Inscrire Cours", Command.SCREEN, 0);
    Command cmdSuiv = new Command("Suivre Cours", Command.SCREEN, 0);
    Command cmdconf = new Command("Supprimer", Command.SCREEN, 0);
    Command cmdret = new Command("Annuler", Command.SCREEN, 0);
    Command cmdcons = new Command("Liste Cours", Command.SCREEN, 0);
    Command cmdBadge = new Command("Mes Badges", Command.SCREEN, 0);
    Command cmdMe = new Command("Meilleur Formateur", Command.SCREEN, 0);

    public ProfilApprt(Apprenant currentUtilisateur) {
        super("Mon compte");
        this.currentUtilisateur = currentUtilisateur;
        if (currentUtilisateur.getNom().equals("Sameh")) {
            imgUrl = "/img/sameh.jpg";
        } else if (currentUtilisateur.getNom().equals("Bacem")) {
            imgUrl = "/img/bacem.jpg";
        } else {
            imgUrl = "/img/profil.png";
        }
        try {
            img = Image.createImage(imgUrl);
            imgItem = new ImageItem(null, img, ImageItem.LAYOUT_CENTER, null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Image img2 = null;
        try {
            img2 = Image.createImage("/img/a.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imgItem2 = new ImageItem(null, img2, ImageItem.LAYOUT_CENTER, null);
        Image img3 = null;
        String ch = "";
        if (currentUtilisateur.getSexe().equals("F")) {
            ch = "/img/delF.png";

        } else {
            ch = "/img/delH.png";
        }
        try {
            img3 = Image.createImage(ch);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imgItem3 = new ImageItem(null, img3, ImageItem.LAYOUT_CENTER, null);
        Gauge gauge = new Gauge("Score", false, 100, currentUtilisateur.getScore());
        this.append(imgItem);

        this.append("\n\tLogin :   \t" + currentUtilisateur.getNomUtilisateur());
        this.append("\n\tNom :     \t" + currentUtilisateur.getNom());
        this.append("\n\tPrenom :  \t" + currentUtilisateur.getPrenom());
        this.append("\n\tEmail :   \t" + currentUtilisateur.getMail());
        this.append("\n\tDateNaissance :   \t" + currentUtilisateur.getDateNaissance());
        this.append("\n\tTelephone :   \t" + currentUtilisateur.getTel());
        this.append(gauge);
        this.append("\n\tAdresse : \t" + currentUtilisateur.getAdresse());
        this.setTicker(tk);
        if (GoogleMapsZoomCanvas.googCor != null) {
            createMap();
            mapItem = new ImageItem("Adresse en cours...", null, Item.LAYOUT_TOP, "Sample map");
            this.append("\n Ma Position :");
            this.append("\n");
            append(mapItem);
        }
        this.addCommand(cmdEditInfo);
        this.addCommand(cmdBack);
        this.addCommand(cmdSuppr);
        this.addCommand(cmdSearchMember);
        this.addCommand(cmdSuiv);
        this.addCommand(cmdcons);
        this.addCommand(cmdBadge);
        this.addCommand(cmdMe);
        this.setCommandListener(this);
    }

    public void GoogleStaticMapUpdated(GoogleStaticMap gsm) {
        System.out.println("map ok");

        mapItem.setImage(map.getImage());

        mapItem.setLabel("");
    }

    public void GoogleStaticMapUpdateError(GoogleStaticMap gsm, int i, String string) {
        System.out.println("map error: " + i + ", " + string);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdEditInfo) {
            Midlet.INSTANCE.disp.setCurrent(new EditInfoForm("Modification de mon compte"));
        }
        if (c == cmdBack) {
            Midlet.INSTANCE.disp.setCurrent(new SignIn());
        }
        if (c == cmdconf) {
            loadingDialog.append(imgItem2);
            Midlet.INSTANCE.disp.setCurrent(loadingDialog);
            Thread th = new Thread(this, "supp");
            th.start();
        }
        if (c == cmdSuppr) {
            Confirmation.append(imgItem3);
            Confirmation.addCommand(cmdconf);
            Confirmation.addCommand(cmdret);
            Confirmation.setCommandListener(this);
            Midlet.INSTANCE.disp.setCurrent(Confirmation);
        }
        if (c == cmdret) {
            Midlet.INSTANCE.disp.setCurrent(this);
        }
        if (c == cmdSearchMember) {

            Midlet.INSTANCE.disp.setCurrent(new ChercherMembres("Chercher"));
        }
        if (c == cmdcons) {
            String str = "http://localhost/tesseractj2me/tesseract_php/getXMLMatire.php";
            Midlet.INSTANCE.disp.setCurrent(new ChercherCours("Consulter Cours", str));

        }
        if (c == cmdSuiv) {
            Midlet.INSTANCE.disp.setCurrent(new ChercherMesCours("Chercher Mes Cours"));
        }
        if (c == cmdBadge) {
            Midlet.INSTANCE.disp.setCurrent(new ListeMesBadge("Please Wait"));
        }
        if (c == cmdMe) {
            loadingDialog.append(imgItem2);
            Midlet.INSTANCE.disp.setCurrent(loadingDialog);
            Thread th = new Thread(this, "Formateur");
            th.start();

        }
    }

    public void createMap() {

        double lat = GoogleMapsZoomCanvas.googCor.latitude;
        double lon = GoogleMapsZoomCanvas.googCor.longitude;
        gMaps = new GoogleMaps();

        map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);
        map.setHandler(this);
        map.setCenter(new GoogleMapsCoordinates(lat, lon));
        map.setZoom(15);

        GoogleMapsMarker redMarker = new GoogleMapsMarker(new GoogleMapsCoordinates(lat, lon));

        redMarker.setColor(GoogleStaticMap.COLOR_ORANGE);
        redMarker.setSize(GoogleMapsMarker.SIZE_MID);
        redMarker.setLabel('A');

        map.addMarker(redMarker);
        map.update();
    }

    public void run() {
        if (Thread.currentThread().getName().equals("supp")) {

            try {
                String str = "http://localhost/tesseractj2me/tesseract_php/suppUtilisateur.php?id=" + currentUtilisateur.getIdUtilisateur();
                httpConnection = (HttpConnection) Connector.open(str);
                dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
                StringBuffer sb = new StringBuffer();
                int ch;

                while ((ch = dataInputStream.read()) != -1) {
                    sb.append((char) ch);
                }
                if ("successfully".equals(sb.toString().trim())) {

                    Alert al = new Alert("Compte supprimé! ", "Votre compte a été supprimé avec succès!", null, AlertType.INFO);
                    al.setTimeout(9000);
                    Midlet.INSTANCE.disp.setCurrent(al);

                    Midlet.INSTANCE.disp.setCurrent(new SignIn());
                } else {
                    Alert al = new Alert("Echoue !");
                    Midlet.INSTANCE.disp.setCurrent(al);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        if (Thread.currentThread().getName().equals("Formateur")) {
            try {

                String str2 = "http://localhost/tesseractj2me/tesseract_php/getXMLMeilleurFormateur.php";
                System.out.println("fghjkl" + str2);
                httpConnection = (HttpConnection) Connector.open(str2);
                dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                FormateurHandler formateurHandler = new FormateurHandler();
                parser.parse(dataInputStream, formateurHandler);
                formateurs = formateurHandler.getUtilisateur();
                Formateur f = (Formateur) formateurs[0];
                System.out.println("dfqsffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff" + f.getScore());
                Midlet.INSTANCE.disp.setCurrent(new MeilleurFormateur(formateurs));
            } catch (SAXException se) {

            } catch (ConnectionNotFoundException ce) {

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }
}
