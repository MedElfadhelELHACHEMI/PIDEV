/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.handler.CoursHandler;
import com.entities.Apprenant;
import com.entities.Cours;
import com.entities.Formateur;
import com.entities.Matiere;
import com.entities.Organisation;

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
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Ticker;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tesseract.Midlet;

/**
 *
 * @author Sameh
 */
public class LSTCours extends Form implements CommandListener, Runnable {

    Apprenant currentUtilisateur;
    Formateur f;
    private Cours[] cours;
    private Organisation o;
    Ticker tk = new Ticker("              Tesseract Coding          ");
    Command cmdBack;
    Command cmdinsc;
    Command cmdBck;
    Image image3;

    String imgur;
    String nom;
    ImageItem imgItem;
    ImageItem imgItem2;
    Cours currentCours;
    Image image1;
    Form form = new Form("Infos Cours");
    Matiere m;
    HttpConnection httpConnection;
    DataInputStream dataInputStream;
    List lst;
    StringBuffer sb = new StringBuffer();

    public LSTCours(String title, Matiere m, Formateur f, Organisation o) {
        super(title);
        this.m = m;
        this.f = f;
        this.o = o;
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
        lst = new List("Liste Cours", List.IMPLICIT);
        cmdinsc = new Command("Inscrire", Command.OK, 0);
        cmdBck = new Command("Retourner", Command.BACK, 0);
        cmdBack = new Command("Back", Command.BACK, 0);
        this.append(imgItem2);

        lst.addCommand(cmdBck);
        lst.addCommand(cmdinsc);
        lst.setCommandListener(this);
        if (f != null) {
            Thread th = new Thread(this, "CoursFor");
            th.start();
        } else if (o != null) {
            Thread th = new Thread(this, "CoursOrg");
            th.start();
        } else {
            Thread th = new Thread(this, "Cours");
            th.start();
        }

    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBck && d == lst) {
            if (f != null) {
                Midlet.INSTANCE.disp.setCurrent(new ChercherMembres("Chercher"));
            } else if (o != null) {
                Midlet.INSTANCE.disp.setCurrent(new ChercherMembres("Chercher"));

            } else {
                String str = "http://localhost/Tesseract/getXMLMatire.php";
                Midlet.INSTANCE.disp.setCurrent(new ChercherCours("Consulter Cours", str));
            }

        }

        if (c == List.SELECT_COMMAND) {

            form.deleteAll();
            currentCours = cours[lst.getSelectedIndex()];
            nom = cours[lst.getSelectedIndex()].getNomCours();
            imgur = "/img/" + nom + ".jpg";

            try {
                image1 = Image.createImage(imgur);

                imgItem = new ImageItem(null, image1, ImageItem.LAYOUT_CENTER, null);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            form.setTicker(tk);
            form.append(imgItem);
            form.append("\n");
            form.append(showCours(lst.getSelectedIndex()));
            form.addCommand(cmdBack);
//  form.addCommand(cmdinsc);
            form.setCommandListener(this);
            Midlet.INSTANCE.disp.setCurrent(form);
        }
        if (d == form && c == cmdBack) {
            Midlet.INSTANCE.disp.setCurrent(lst);
        }
        if (c == cmdinsc) {
            if (f != null) {
                Midlet.INSTANCE.disp.setCurrent(new SessionCoursForm("Session Cours", currentCours, null, f, null));
            } else if (o != null) {
                Midlet.INSTANCE.disp.setCurrent(new SessionCoursForm("Session Cours", currentCours, null, null, o));

            } else {
                Midlet.INSTANCE.disp.setCurrent(new SessionCoursForm("Session Cours", currentCours, m, null, null));
            }

        }
    }

    public void run() {
        if (Thread.currentThread().getName().equals("Cours")) {

            try {
                if (m == null) {
                    String str = "http://localhost/Tesseract/getXmlCourNon.php?id=" + currentUtilisateur.getIdUtilisateur();
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

                } else {

                    String str = "http://localhost/Tesseract/getXmlCourMat.php?id=" + m.getIdMatiere() + "&util=" + currentUtilisateur.getIdUtilisateur();

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
                }
            } catch (SAXException se) {
                Alert al = new Alert("Données incorrectes", "Liste vide!", null, AlertType.WARNING);
                Midlet.INSTANCE.disp.setCurrent(al);

            } catch (ConnectionNotFoundException ce) {
                Alert al = new Alert("Connexion échouée", "Impossible de se connecter au serveur!", null, AlertType.ERROR);
                Midlet.INSTANCE.disp.setCurrent(al);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (Thread.currentThread().getName().equals("CoursFor")) {
            try {

                String str = "http://localhost/Tesseract/getXMLCourFor.php?id=" + f.getIdUtilisateur() + "&util=" + currentUtilisateur.getIdUtilisateur();
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

            } catch (SAXException se) {
                Alert al = new Alert("Données Formateur incorrectes", "Liste vide!", null, AlertType.WARNING);
                Midlet.INSTANCE.disp.setCurrent(al);
            } catch (ConnectionNotFoundException ce) {
                Alert al = new Alert("Connexion échouée", "Impossible de se connecter au serveur!", null, AlertType.ERROR);
                Midlet.INSTANCE.disp.setCurrent(al);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (Thread.currentThread().getName().equals("CoursOrg")) {
            try {
                String str = "http://localhost/Tesseract/getXMLCourOrg.php?id=" + o.getIdOrganisation() + "&util=" + currentUtilisateur.getIdUtilisateur();

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

            } catch (SAXException se) {
                Alert al = new Alert("Données organisme incorrectes", "Liste vide!", null, AlertType.WARNING);
                Midlet.INSTANCE.disp.setCurrent(al);
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
            sb.append("Nom : ");
            sb.append(cours[i].getNomCours());
            sb.append("\n");
            sb.append("Description : ");
            sb.append(cours[i].getDescriptionCours());
            sb.append("\n");
            sb.append("Difficulte : ");
            sb.append(cours[i].getDifficulte());
            sb.append("\n");

            if (f != null) {
                sb.append("Nom Formateur: ");
                sb.append(f.getNomUtilisateur());
                sb.append("\n");
            }
            if (o != null) {
                sb.append("Nom Organisme: ");
                sb.append(o.getNom());
                sb.append("\n");
            }
            if (m == null) {
                sb.append("\n");
            } else {
                sb.append("Nom Matiere: ");
                sb.append(m.getNomMatiere());
                sb.append("\n");
            }
            res = sb.toString();
            sb = new StringBuffer("");
        }
        return res;
    }

}
