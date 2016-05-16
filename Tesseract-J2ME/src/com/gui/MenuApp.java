/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import java.io.IOException;
import javax.microedition.lcdui.*;
import tesseract.Midlet;

/**
 *
 * @author Bacem
 */
public class MenuApp extends Canvas{

    Image profile[]= new Image[2];
    Image chat[] = new Image[2];
    Image cours[] = new Image[2];
    
    int select = 0;
    int i=1;
    int j=0;
    int k=0;
    
    int w = getWidth();
    int h = getHeight();
    
    public MenuApp(){
        try {
            profile[0] = Image.createImage("/com/gui/images/icons/profile.png");
            profile[1] = Image.createImage("/com/gui/images/icons/selectedProfile.png");
            
            chat[0] = Image.createImage("/com/gui/images/icons/chatbox.png");
            chat[1] = Image.createImage("/com/gui/images/icons/selectedChatbox.png");
            
            cours[0] = Image.createImage("/com/gui/images/icons/book.png");
            cours[1] = Image.createImage("/com/gui/images/icons/selectedBook.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    protected void paint(Graphics g) {
        g.setColor(57,58,60);
        g.fillRect(0, 0, w, h);
        g.drawImage(profile[i], w/4, h/2, g.VCENTER|g.HCENTER);
        g.drawImage(chat[j], w/2, h/2, g.VCENTER|g.HCENTER);
        g.drawImage(cours[k], 3*w/4, h/2, g.VCENTER|g.HCENTER);
    }
    
    protected void keyPressed(int keyCode){
        int gameAction = getGameAction(keyCode);
        if(gameAction==RIGHT){
            select++;
            if(select==3){
                select=0;
            }
            changeSelection();
            repaint();
        }
        if(gameAction==LEFT){
            select--;
            if(select==-1){
                select=2;
            }
            changeSelection();
            repaint();
        }
        if(gameAction==FIRE){
            if(select==0){
                Midlet.INSTANCE.disp.setCurrent(new ProfilApprt(Midlet.INSTANCE.ApprenantCurrent));
            }
            else if(select==1){
                Midlet.INSTANCE.disp.setCurrent(this);
            }
            else if(select==2){
                Midlet.INSTANCE.disp.setCurrent(new ChercherCours("", ""));
            }
            
        }
    }

    private void changeSelection() {
        switch(select){
            case 0:
                i=1;
                j=k=0;
                break;
            case 1:
                j=1;
                i=k=0;
                break;
            case 2:
                k=1;
                i=j=0;
                break;
        }
    }
    
}
