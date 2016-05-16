/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.*;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import tesseract.Midlet;

/**
 *
 * @author Bacem
 */
public class Animation extends Canvas implements CommandListener,Runnable{

    Image img[]=new Image[50];
    int index =0;
    int iteration =0;
    public Animation() {
        String s="";
        for(int i=0; i<50; i++){
            s = "/com/gui/images/video/"+i+".jpg";
            try {
                img[i]=Image.createImage(s);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        Thread th=new Thread(this,"anim");
        th.start();
        
         
    }

    protected void paint(Graphics g) {
        g.drawImage(img[index],getWidth()/2-10,getHeight()/2,g.VCENTER|g.HCENTER);
        iteration++;
        if (iteration%200==0){
            if(Thread.currentThread().getName().equals("anim")){
                Thread.currentThread().interrupt();
            }
            Midlet.INSTANCE.disp.setCurrent(new Welcome());
            System.out.println("w = "+getWidth());
            System.out.println("h = "+getHeight());
        }
    }

    public void commandAction(Command c, Displayable d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void run() {
        try {
            int x=-1;
            
            InputStream in = getClass().getResourceAsStream("splash.wav");
            Player player = Manager.createPlayer(in, "audio/x-wav");
            player.start();
            boolean wsel = false;
            while(true) {
                if(index==49){
                    wsel = true;
                }
                if (index==0){
                    wsel = false;
                }
                if(wsel){
                    index--;
                }
                else{
                    index++;
                }
                repaint();
                try {
                    Thread.sleep(20);
                }catch(Exception e){}
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }

   private void playFromResource() {
    try {
      
    } catch (Exception e) {
      return;
    }
  }
    
}
