/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.Dao.InvitationDao;
import com.entities.Invitations;
import com.entities.Organisation;
import com.entities.Utilisateur;
import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import tesseract.Midlet;

/**
 *
 * @author BoB
 */
public class Orgcoaches extends Canvas{

    int i, j = 1;
    int w = getWidth();
    int h = getHeight();
    int decalage = 0;
    String[] coaches;
    Utilisateur[] coachess;
    Image choice;

    

    public Orgcoaches(Utilisateur coaches[]) {
        
        this.coachess=coaches;
        this.coaches = new String[coaches.length];
        for (i = 0; i < coaches.length; i++) {
            this.coaches[i] = coaches[i].getNom() + " " + coaches[i].getPrenom();
        }
        System.out.println(coaches.length);
        try {
            choice = Image.createImage("/com/gui/images/buttons/invites-choice.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    protected void keyPressed(int keyCode) {
        int gameAction = getGameAction(keyCode);
        decalage += 6;
        if (gameAction == DOWN) {
            if (decalage >= coaches.length - 6) {
                decalage = coaches.length - 6;
            }
        }
        if (gameAction == UP) {
            decalage -= 6;
            if (decalage <= 0) {
                decalage = 0;
            }
        }
        if(gameAction == FIRE)
        {
         Midlet.INSTANCE.disp.setCurrent(new OrganisationMenu());

        }
        if(gameAction == RIGHT)
        {
            InvitationDao id = new InvitationDao();
            Midlet.INSTANCE.disp.setCurrent(new Orginvitations(id.findByOrganisation(Midlet.INSTANCE.ApprenantCurrent.getOrganisation()), coachess));
        }
        repaint();
    }

    protected void paint(Graphics g) {
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        g.setColor(57, 58, 60);
        g.fillRect(0, 0, w, h);
        g.setColor(36, 199, 207);
        int max = 6;
        if (coaches.length < 6) {
            max = coaches.length;
        }
        for (i = decalage; i < decalage + max; i++) {
            g.drawString(coaches[i], w / 2, h * j / 6, Graphics.HCENTER | Graphics.BASELINE);
            j++;
        }
         g.drawImage(choice, 0, 268,g.TOP|g.LEFT);
    }

    

}
