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
import com.entities.Utilisateur;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Ticker;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tesseract.Midlet;

/**
 *
 * @author Sameh
 */
public class MeilleurFormateur extends Canvas implements CommandListener, Runnable {

    int colors[] = {0xFF0000, 0xA9E969, 0x00FFFF, 0xC675EC, 0x008800, 0xA9E969, 0x008800, 0x00C400, 0x0088000};
    private Cours[] cours;
    Apprenant currentUtilisateur;
    HttpConnection httpConnection;
    DataInputStream dataInputStream;
    Utilisateur u;
    int w = this.getWidth();
    int h = this.getHeight();
    Command cmdOk = new Command("ok", Command.SCREEN, 0);
    int[] data = {90, 50, 80, 40, 100};
    String[] datadata = {"sameh", "hamza", "nour", "Haikel", "Skon"};
    private double d2r = Math.PI / 180.0; // Degrees to radians.

    int xx;
    int yy;
    int yyy;
    int xx2 = 20;

    int yy2 = 210;
    int yyy2 = 210;
    private Formateur f;
    private Formateur f2;
    private ImageItem imgItem2;

    Form loadingDialog = new Form("Please Wait");
    Ticker tk = new Ticker("              Tesseract Coding          ");
    private Utilisateur[] formateurs;

    public MeilleurFormateur(Utilisateur[] formateurs) {
        this.addCommand(cmdOk);
        Image img2 = null;
        try {
            img2 = Image.createImage("/img/a.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imgItem2 = new ImageItem(null, img2, ImageItem.LAYOUT_CENTER, null);
        loadingDialog.append(imgItem2);
        currentUtilisateur = Midlet.INSTANCE.ApprenantCurrent;
        this.formateurs = formateurs;
        setCommandListener(this);

    }

    protected void paint(Graphics g) {
        int width = g.getClipWidth();
        int height = g.getClipHeight();

        g.setColor(0, 0, 0);
        g.fillRect(0, 0, width, height);

        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            f = (Formateur) formateurs[i];
            sum += f.getScore();
        }
        int deltaAngle = 360 * 100 / sum / 100;
        int x = 20;
        int y = 20;

        int diameter;

        if (width > height) {
            diameter = height - y * 2;
        } else {
            diameter = width - x * 2;
        }

        int startAngle = 0;
        xx = 20;

        yy = 210;
        yyy = 210;

        Random randomGenerator = new Random();

        for (int i = 0; i < data.length; i++) {
            f = (Formateur) formateurs[i];
            g.setColor(colors[i]);
            g.fillArc(20, 5, diameter, diameter, startAngle, deltaAngle * f.getScore());
            /**
             * ****************************
             */

            if (i < 4) {
                g.fillRect(xx, yy, 10, 10);
                g.drawString(f.getNomUtilisateur(), xx + 20, yy, Graphics.TOP | Graphics.LEFT);
                yy += 20;

            } else {
                g.fillRect(xx + 100, yyy, 10, 10);
                g.drawString(f.getNomUtilisateur(), xx + 120, yyy, Graphics.TOP | Graphics.LEFT);
                yyy += 20;
            }

            /**
             * ****************************
             */
            g.setColor(0, 0, 0);

            g.drawArc(20, 5, diameter, diameter, startAngle, deltaAngle * f.getScore());
            startAngle += deltaAngle * f.getScore();
        }
        g.setColor(255, 255, 255);
        g.drawRect(xx2, yy2, 10, 10);

    }

    public void commandAction(Command c, Displayable d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void run() {
        if (Thread.currentThread().getName().equals("Formateur")) {
            try {

                String str = "http://localhost/tesseractj2me/tesseract_php/getXMLCourFor.php?id=" + f.getIdUtilisateur() + "&util=" + currentUtilisateur.getIdUtilisateur();
                System.out.println("fghjkl" + str);
                httpConnection = (HttpConnection) Connector.open(str);
                dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                CoursHandler coursHandler = new CoursHandler();
                parser.parse(dataInputStream, coursHandler);
                cours = coursHandler.getCours();
                Midlet.INSTANCE.disp.setCurrent(new FormFormateur("Info Formateur", u, cours, formateurs));

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
    }

    protected void keyPressed(int keyCode) {
        int key = getGameAction(keyCode);
        int i = 0;
        if (key == DOWN) {
            if (xx2 == 20 && yy2 <= 250 && i < 4) {
                yy2 += 20;
                i++;

                repaint();
            } else {
                yy2 = 210;
                xx2 = 120;

                repaint();
            }
        }
        if (key == UP) {

            if (xx2 == 20 && yy2 >= 230) {
                yy2 -= 20;
                repaint();
            }

            if (xx2 == 120 && yy2 == 210) {
                yy2 = 270;
                xx2 = 20;
                repaint();
            }
        }
        if (key == LEFT) {
            if (xx2 == 120 && yy2 == 210) {
                yy2 = 210;
                xx2 = 20;
                repaint();
            }
        }
        if (key == RIGHT) {

            yy2 = 210;
            xx2 = 120;
            repaint();

        }
        if (key == FIRE) {
            if (xx2 == 20 && yy2 == 210) {
                u = formateurs[0];
                Midlet.INSTANCE.disp.setCurrent(loadingDialog);
                Thread th = new Thread(this, "Formateur");
                th.start();
            } else if (xx2 == 20 && yy2 == 230) {
                u = formateurs[1];
                Midlet.INSTANCE.disp.setCurrent(loadingDialog);
                Thread th = new Thread(this, "Formateur");
                th.start();
            } else if (xx2 == 20 && yy2 == 250) {
                u = formateurs[2];
                Midlet.INSTANCE.disp.setCurrent(loadingDialog);
                Thread th = new Thread(this, "Formateur");
                th.start();
            } else if (xx2 == 20 && yy2 == 270) {
                u = formateurs[3];
                Midlet.INSTANCE.disp.setCurrent(loadingDialog);
                Thread th = new Thread(this, "Formateur");
                th.start();
            } else if (xx2 == 120 && yy2 == 210) {
                u = formateurs[4];

                Midlet.INSTANCE.disp.setCurrent(loadingDialog);
                Thread th = new Thread(this, "Formateur");
                th.start();
            }
        }
    }

}
