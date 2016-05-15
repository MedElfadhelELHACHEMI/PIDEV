/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.entities.Evenement;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import tesseract.Midlet;

/**
 *
 * @author BoB
 */
public class Orgevents extends Canvas{
    int w = getWidth();
    int h = getHeight();
    HttpConnection hc;
    DataInputStream dis;
    Image photo;
    
    int i=0;
    Evenement[] events;
    Image choice;
    public Orgevents(Evenement events[]) {
        this.events=events;
        System.out.println(this.events[0].getAffiche());
        try {
            choice = Image.createImage("/com/gui/images/buttons/challenge_choice.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    public void getimage(Evenement e)
    {
        
      try {
          System.out.println("http://localhost/"+e.getAffiche());
            hc = (HttpConnection)Connector.open("http://localhost/"+e.getAffiche());
            
            dis = new DataInputStream(hc.openDataInputStream());
            this.photo = Image.createImage(dis);
            
            dis.close();
            hc.close();
        } catch (IOException ex) {
            System.out.println(""+ex.getMessage());
        }  
      
    }
    
    
    
    protected void paint(Graphics g) {
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE));
        this.getimage(events[i]);
        g.drawImage(this.photo, 0, 0, g.TOP|g.LEFT);
        g.drawString(events[i].getNom(), w / 2, (h - 20) / 8, g.HCENTER | g.BASELINE);
        g.drawString(events[i].getDescription(), w / 2, (h - 20) * 3 / 8, g.HCENTER | g.BASELINE);
        g.drawString(events[i].getDate(), w / 2, (h - 20) * 5 / 8, g.HCENTER | g.BASELINE);
        g.drawString(String.valueOf(events[i].getNbr_max()) , w / 2, (h - 20) * 7 / 8, g.HCENTER | g.BASELINE);
        g.drawImage(choice, 0, 268, g.TOP | g.LEFT);

        
    }
    protected void keyPressed(int keyCode) {
        int gameAction = getGameAction(keyCode);
        if (gameAction == DOWN) {

            i++;
            if (i >= events.length) {
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
        Midlet.INSTANCE.disp.setCurrent(new orgnewevent("New Event"));

        }
        else if (gameAction == FIRE)
        {
        Midlet.INSTANCE.disp.setCurrent(new orgupdateevent("update event", events[i]));    
        }
        repaint();
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
