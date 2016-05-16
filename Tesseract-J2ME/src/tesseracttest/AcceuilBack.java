/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesseracttest;

import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Graphics;
import com.sun.lwuit.Image;
import com.sun.lwuit.animations.CommonTransitions;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/**
 *backprof.png
 * @author haikal
 */
public class AcceuilBack extends Form{
private Image back ;
    public AcceuilBack() {
        InputStream in = getClass().getResourceAsStream("/audio/back.wav");
                Player player=null;
                try {
                    player = Manager.createPlayer(in, "audio/x-wav");
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (MediaException ex) {
                    ex.printStackTrace();
                }
                try {
                    player.start();
                } catch (MediaException ex) {
                    ex.printStackTrace();
                }
    }

    public void paintBackground(Graphics g) {
             g.setColor(0xa6dae8);
     g.fillRect(0, 0, getWidth() , getHeight());try {
        back = Image.createImage("/tesseracttest/images/backprof.png");
        g.drawImage(back, 0, 0);
    } catch (IOException ex) {
        ex.printStackTrace();
    }
     getMenuBar().getStyle().setBgColor(0xa6dae8);
    }
    public void keyPressed(int keyCode) {
       int code = Display.getInstance().getGameAction(keyCode);
       if(code==Canvas.LEFT){
       AccueilAnalytics aa = new AccueilAnalytics();
      aa.setTransitionOutAnimator(CommonTransitions.createFastSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 1000));
       aa.show();
       
       }
       if(code == Canvas.FIRE){
       Midlet.INSTANCE.disp.setCurrent(new HomeCanvas(""));
       
       }
    }
}
