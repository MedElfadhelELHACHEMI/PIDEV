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
public class Welcome extends Canvas implements CommandListener, Runnable{

    int w = getWidth();
    int h = getHeight();
    Image logo;
    Image label;
    int i=0;
    boolean hide = false;
    public Welcome(){
        try {
            logo = Image.createImage("/com/gui/images/logo.png");
            label = Image.createImage("/com/gui/images/label.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Thread th=new Thread(this);
        th.start();
    }
    protected void paint(Graphics g) {
        g.setColor(57,58,60);
        g.fillRect(0, 0, w, h);
        g.drawImage(logo, w/2, (h-20)/3, g.VCENTER|g.HCENTER);
        if(!hide){
            g.drawImage(label, w/2, 2*(h-20)/3, g.VCENTER|g.HCENTER);
        }
    }
    protected void keyPressed(int keyCode){
        if (keyCode!=0){
            Midlet.INSTANCE.disp.setCurrent(new SignIn());
        }
    }
    public void commandAction(Command c, Displayable d) {
    }
    public void run() {
        while(true){
            hide=!hide;
            repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
