/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.entities.Organisation;
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
public class Orgprofile extends Canvas {
     int w = getWidth();
    int h = getHeight();
    
    HttpConnection hc;
    DataInputStream dis;
    
     
    
    String nom;
    String adresse;
    String email;
    String matricule;
    int telephone;
    Image photo;
    Image choice;
    Image hud;

    public Orgprofile(Organisation O) {
       nom=O.getNom();
       adresse=O.getAdress();
       email=O.getEmail();
       matricule=O.getMatricule();
       telephone=O.getTelephone();
        System.out.println(email);
        try {
            hc = (HttpConnection)Connector.open("http://localhost/"+O.getPhoto());
            dis = new DataInputStream(hc.openDataInputStream());
            photo = Image.createImage(dis);
            
            dis.close();
            hc.close();
        } catch (IOException ex) {
            System.out.println(""+ex.getMessage());
        }
         try {
            hud = Image.createImage("/com/gui/images/buttons/hud2.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
    }
    
    
    
    
    protected void paint(Graphics g) {
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE));
        g.setColor(57, 58, 60);
        g.fillRect(0, 0, w, h);
        g.setColor(94,94,94);
        g.drawImage(photo, 70, 30,g.TOP|g.LEFT);
        g.drawImage(hud, 0, 0,g.TOP|g.LEFT);
        g.drawString(nom, 30, 140, Graphics.LEFT|Graphics.BASELINE);
        g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        g.drawString(adresse, 30, 200, Graphics.LEFT|Graphics.BASELINE);
        g.drawString(email, 30, 235, Graphics.LEFT|Graphics.BASELINE);
        g.drawString(matricule, 30, 268, Graphics.LEFT|Graphics.BASELINE);
        g.drawString(String.valueOf(telephone),30, 300, Graphics.LEFT|Graphics.BASELINE);
        
        
    }
     protected void keyPressed(int keyCode) {
        int gameAction = getGameAction(keyCode);
        
       if(gameAction == LEFT)
        {
         Midlet.INSTANCE.disp.setCurrent(new OrganisationMenu());

        }
        
        repaint();
    }

    

    
    
}
