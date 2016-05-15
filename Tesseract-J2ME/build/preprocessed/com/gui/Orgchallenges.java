/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.entities.Challenge;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
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
public class Orgchallenges extends Canvas {

    HttpConnection hc;
    DataInputStream dis;
    int i=0;
    int w = getWidth();
    int h = getHeight();
    Challenge[] challenges;
    Image backgroundmobile, backgrounddesktop,choice;

    

    public Orgchallenges(Challenge challenges[]) {
        
        this.challenges = challenges;
        System.out.println(this.challenges.length);
        try {
            hc = (HttpConnection) Connector.open("http://localhost/tesseractj2me/tesseract_php/Resources/mobile.png");
            dis = new DataInputStream(hc.openDataInputStream());
            backgroundmobile = Image.createImage(dis);

            dis.close();
            hc.close();
        } catch (IOException ex) {
            System.out.println("" + ex.getMessage());
        }
        try {
            hc = (HttpConnection) Connector.open("http://localhost/tesseractj2me/tesseract_php/Resources/desktop.png");
            dis = new DataInputStream(hc.openDataInputStream());
            backgrounddesktop = Image.createImage(dis);

            dis.close();
            hc.close();
        } catch (IOException ex) {
            System.out.println("" + ex.getMessage());
        }
        try {
            choice = Image.createImage("/com/gui/images/buttons/challenge_choice.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    protected void paint(Graphics g) {
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE));
        System.out.println(i);
        if (challenges[i].getTheme().equals("desktop")) {
            g.drawImage(backgrounddesktop, 0, 0, g.TOP | g.LEFT);
            g.setColor(36, 199, 207);
        } else if (challenges[i].getTheme().equals("mobile")) {
            g.setColor(57, 58, 60);
            g.drawImage(backgroundmobile, 0, 0, g.TOP | g.LEFT);

        }
        g.drawString(challenges[i].getNom(), w / 2, (h - 20) / 8, g.HCENTER | g.BASELINE);
        g.drawString(challenges[i].getDate(), w / 2, (h - 20) * 3 / 8, g.HCENTER | g.BASELINE);
        g.drawString(challenges[i].getDescription(), w / 2, (h - 20) * 5 / 8, g.HCENTER | g.BASELINE);
        g.drawString("Created By", w / 2, (h - 20) * 6 / 8, g.HCENTER | g.BASELINE);
        g.drawString(challenges[i].getUtilisateur().getNom() + " " + challenges[i].getUtilisateur().getPrenom(), w / 2, (h - 20) * 7 / 8, g.HCENTER | g.BASELINE);
        g.drawImage(choice, 10, 268, g.TOP | g.LEFT);
    }

    protected void keyPressed(int keyCode) {
        int gameAction = getGameAction(keyCode);
        if (gameAction == DOWN) {

            i++;
            if (i >= challenges.length) {
                i = 0;
            }

        } else if (gameAction == UP) {

            i--;
            if (i <= 0) {
                i = 0;
            }
        }
        else if(gameAction == LEFT)
        {
         Midlet.INSTANCE.disp.setCurrent(new OrganisationMenu());

        }
        else if (gameAction == RIGHT)
        {
        Midlet.INSTANCE.disp.setCurrent(new orgnewchallenge("New Challenge"));

        }
        else if (gameAction == FIRE)
        {
        Midlet.INSTANCE.disp.setCurrent(new orgupdatechallenge(challenges[i].getNom(), challenges[i]));    
        }
        repaint();
    }

    
}
