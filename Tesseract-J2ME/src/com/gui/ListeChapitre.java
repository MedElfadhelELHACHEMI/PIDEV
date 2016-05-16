/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.entities.Chapitre;
import com.entities.Cours;
import com.entities.SessionCours;
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Ticker;
import tesseract.Midlet;

/**
 *
 * @author Sameh
 */
class ListeChapitre extends List implements CommandListener {

    SessionCours[] sessionCours;
    Cours currentCours;
    Chapitre[] chapitres;
    StringBuffer sb = new StringBuffer();
    int ind;
    float chapterm;
    Image image3;
    Image image1;
    Ticker tk = new Ticker("              Tesseract Coding          ");
    Command cmd = new Command("Back", Command.SCREEN, 0);
    Command cmd2 = new Command("Back", Command.SCREEN, 0);
    Form form = new Form("Informations Chapitre");
    String nomCOurs;
    String imgur;
    ImageItem imgItem;

    public ListeChapitre(String title, int listType, SessionCours[] sessionCours, Chapitre[] chapitres, Cours currentCours, int ind, float chapterm) {
        super(title, listType);
        this.sessionCours = sessionCours;
        this.ind = ind;
        this.chapitres = chapitres;
        this.currentCours = currentCours;
        this.chapterm = chapterm;
        try {
            image3 = Image.createImage("/img/c.png");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setTicker(tk);
        addCommand(cmd);

        if (ind == 0) {
            this.setTitle("Liste Chapitre Non Terminer");
            for (int i = (int) chapterm; i < chapitres.length; i++) {
                append(chapitres[i].getNom(), image3);
            }
        }
        if (ind == 1) {
            this.setTitle("Liste Chapitre Terminer");
            for (int i = 0; i < (int) chapterm; i++) {
                append(chapitres[i].getNom(), image3);
            }
        }
        this.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == List.SELECT_COMMAND) {
            form.deleteAll();
            nomCOurs = currentCours.getNomCours();

            imgur = "/img/" + nomCOurs + ".jpg";

            try {
                image1 = Image.createImage(imgur);

                imgItem = new ImageItem(null, image1, ImageItem.LAYOUT_CENTER, null);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            form.setTicker(tk);
            form.append(imgItem);
            form.append("\n");
            form.append(showCours(this.getSelectedIndex()));
            form.addCommand(cmd2);
            form.setCommandListener(this);
            Midlet.INSTANCE.disp.setCurrent(form);
        }
        if (c == cmd2) {

            Midlet.INSTANCE.disp.setCurrent(this);
        }
        if (c == cmd) {
            Midlet.INSTANCE.disp.setCurrent(new StatistiqueCours(sessionCours, chapitres, currentCours));
        }
    }

    private String showCours(int i) {
        String res = "";
        if (chapitres.length > 0) {
            sb.append("Nom Chapitre : ");
            sb.append(chapitres[i].getNom());
            sb.append("\n");
            sb.append("\n");
            sb.append("Nom Cours : ");
            sb.append(nomCOurs);
            sb.append("\n");
            sb.append("\n");
            sb.append("Description Chapitre : ");
            sb.append(chapitres[i].getDescription());
            sb.append("\n");
            sb.append("\n");
            sb.append("Resumer : ");
            sb.append(chapitres[i].getResume());
            sb.append("\n");

            res = sb.toString();
            sb = new StringBuffer("");
        }
        return res;
    }

}
