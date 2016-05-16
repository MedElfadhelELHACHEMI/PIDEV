/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.entities.Chapitre;
import com.entities.Cours;
import com.entities.SessionCours;
import java.io.DataInputStream;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.List;
import tesseract.Midlet;

/**
 *
 * @author Sameh
 */
public class StatistiqueCours extends Canvas implements CommandListener{
   SessionCours[] sessionCours; 
    Cours currentCours;
   Command cmdOk = new Command("ok", Command.SCREEN, 0);
   
     Chapitre[] chapitres;
     int[] data;
    int colors[] = {0xFF0000, 0xA9E969, 0x00FFFF, 0xC675EC, 0xC675EC, 0xA9E969, 0x008800, 0x00C400, 0x0088000};
  int score;
    float nbchap;
   float chapterm;
   int w = this.getWidth();
        int h = this.getHeight();
    float chapnn;
    int ind;
    int x2=w / 2 - 80;
         int y2=h / 2 + 70;
 public StatistiqueCours( SessionCours[] sessionCours, Chapitre[] chapitres, Cours currentCours){
     this.sessionCours=sessionCours;
     this.chapitres=chapitres;
     this.currentCours=currentCours;
      nbchap=chapitres.length;
     chapterm=sessionCours[0].getNbrchapitre();
    chapnn=nbchap-chapterm;
    if (chapterm==0){
     score=0;   
    } 
   else  if (chapterm==nbchap){
       score=100;  
     }
    
    else if (chapterm <nbchap/2){
      score=25;   
     }
   else if (chapterm==nbchap/2){
       score=50;  
         
     }
   else  if (chapterm >nbchap/2){
      score=75;   
     }
     
    
       this.addCommand(cmdOk);
        setCommandListener(this);
        
     
 }
    protected void paint(Graphics g) {
     g.setColor(0, 0, 0);
        
        g.fillRect(0, 0, w, h);
        //cercle Rouge
        g.setColor(255, 0, 0);
        g.fillArc(w / 2 - 90, h / 2 - 115, 180, 180, 0, 360);

        //reste en bleu
        g.setColor(0, 0, 255);
        g.fillArc(w / 2 - 90, h / 2 - 115, 180, 180, 90, 360 * score / 100);
        

        
        g.setColor(255, 255, 255);
        g.drawString("Cours : "+currentCours.getNomCours(), w / 2, h / 2 - 150, Graphics.TOP | Graphics.HCENTER);
        
        g.setColor(255, 0, 0);
        g.fillRect(w / 2 - 80, h / 2 + 70, 15, 15);

       
        g.setColor(255, 255, 255);
        g.drawString("Chapitre Non Terminer "+(int)chapnn, w / 2 - 50, h / 2 + 70, 0);

       
        g.setColor(0, 0, 255);
        g.fillRect(w / 2 - 80, h / 2 + 90, 15, 15);

         g.setColor(255, 255, 255);
        g.drawString("Chapitre Terminer :"+(int)chapterm, w / 2 - 50, h / 2 + 90, 0);
         g.setColor(255, 255, 0);
         g.drawRect(x2, y2 , 15,15);
    }

    public void commandAction(Command c, Displayable d) {
     if (c==cmdOk){
     Midlet.INSTANCE.disp.setCurrent(ChercherMesCours.lst);
     }
    }
    protected void keyPressed(int keyCode) {
        int key=getGameAction(keyCode);
        if (key==DOWN){
        if (y2==h / 2 + 70)  {
         y2=h / 2 + 90;
         repaint();
        }}
       if (key==UP){
           
         if (y2==h / 2 + 90)  {
         y2=h / 2 + 70;
         repaint();
        }   
       } 
      if (key==FIRE){
      if (y2==h / 2 + 70) {
           ind=0;
       Midlet.INSTANCE.disp.setCurrent(new ListeChapitre("Chapitre ",List.IMPLICIT,sessionCours,chapitres,currentCours,ind,chapterm));
      }
       if (y2==h / 2 + 90) {
           ind=1;
       Midlet.INSTANCE.disp.setCurrent(new ListeChapitre("Chapitre ",List.IMPLICIT,sessionCours,chapitres,currentCours,ind,chapterm));
      }
      }  
    }
}
