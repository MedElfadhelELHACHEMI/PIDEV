/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.handler.CoursHandler;
import com.entities.Apprenant;
import com.entities.Cours;
import com.entities.SessionCours;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Ticker;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tesseract.Midlet;

/**
 *
 * @author Sameh
 */
public class ListeMesBadge extends Form implements CommandListener, Runnable {

    SessionCours sessionCours;
    Cours currentCours;
    private Cours[] cours;
    Apprenant currentUtilisateur;
    Ticker tk = new Ticker("              Tesseract Coding          ");
    Command cmdBack;
    Command cmdBck;
    Image image3;
    String imgur;
    String nom;
    ImageItem imgItem;
    ImageItem imgItem2;
    HttpConnection httpConnection;
    DataInputStream dataInputStream;
    List lst;
    StringBuffer sb = new StringBuffer();
    Form form = new Form("Infos Badge");

    public ListeMesBadge(String title) {
        super(title);
        sessionCours = Midlet.INSTANCE.sessionCours;
        currentUtilisateur = Midlet.INSTANCE.ApprenantCurrent;
        try {
            image3 = Image.createImage("/img/c.png");

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
        this.append(imgItem2);
        lst = new List("Liste Badge", List.IMPLICIT);
        cmdBck = new Command("Retourner", Command.BACK, 0);
        cmdBack = new Command("Back", Command.BACK, 0);
        lst.addCommand(cmdBck);
        lst.setCommandListener(this);
        Thread th = new Thread(this, "Cours");
        th.start();

    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBck && d == lst) {
            Midlet.INSTANCE.disp.setCurrent(new ProfilApprt(currentUtilisateur));
        }
        if (c == List.SELECT_COMMAND) {

            form.deleteAll();
            currentCours = cours[lst.getSelectedIndex()];

            try {
                Image image1 = Image.createImage("/img/bdg.png");

                imgItem = new ImageItem(null, image1, ImageItem.LAYOUT_CENTER, null);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            form.setTicker(tk);
            form.append(imgItem);
            form.append("\n");
            form.append(showCours(lst.getSelectedIndex()));
            form.addCommand(cmdBack);

            form.setCommandListener(this);
            Midlet.INSTANCE.disp.setCurrent(form);
        }
        if (d == form && c == cmdBack) {
            Midlet.INSTANCE.disp.setCurrent(lst);
        }
    }

    public void run() {
        if (Thread.currentThread().getName().equals("Cours")) {
            try {
                String str = "http://localhost/tesseractj2me/tesseract_php/getXMLMesCourTerm.php?id=" + currentUtilisateur.getIdUtilisateur();
                System.out.println("fghjkl" + str);
                httpConnection = (HttpConnection) Connector.open(str);
                dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                CoursHandler coursHandler = new CoursHandler();
                parser.parse(dataInputStream, coursHandler);
                cours = coursHandler.getCours();

                if (cours.length > 0) {
                    this.deleteAll();
                    for (int i = 0; i < cours.length; i++) {
                        lst.append(cours[i].getNomCours(), image3);
                    }
                    Midlet.INSTANCE.disp.setCurrent(lst);
                }
            } catch (ConnectionNotFoundException ce) {
                Alert al = new Alert("Connexion échouée", "Impossible de se connecter au serveur!", null, AlertType.ERROR);
                Midlet.INSTANCE.disp.setCurrent(al);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String showCours(int i) {
        String res = "";
        if (cours.length > 0) {
            sb.append("Votre Nom : ");
            sb.append(currentUtilisateur.getNom());
            sb.append("\n");
            sb.append("Nom Cours: ");
            sb.append(cours[i].getNomCours());
            sb.append("\n");
            sb.append("Description : ");
            sb.append(cours[i].getDescriptionCours());
            sb.append("\n");
            sb.append("Difficulte : ");
            sb.append(cours[i].getDifficulte());

            res = sb.toString();
            sb = new StringBuffer("");
        }
        return res;
    }
}
