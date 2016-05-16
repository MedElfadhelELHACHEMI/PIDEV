/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesseracttest;

import com.sun.lwuit.Command;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Graphics;
import com.sun.lwuit.Image;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.animations.Transition3D;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/**
 *
 * @author haikal
 */
public class AccueilAnalytics extends Form {

    private Image stat;
    
   
    public AccueilAnalytics() {
 InputStream in = getClass().getResourceAsStream("/audio/analytics.wav");
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
        g.fillRect(0, 0, getWidth(), getHeight());
        try {
            stat = Image.createImage("/tesseracttest/images/statformateur.png");
            g.drawImage(stat, 0, 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        getMenuBar().getStyle().setBgColor(0xa6dae8);
    }

    public void paint(Graphics g) {

    }

    public void keyPressed(int keyCode) {
        int code = Display.getInstance().getGameAction(keyCode);
        if (code == Canvas.LEFT) {
            AccueilProfil aa = new AccueilProfil();
            aa.setTransitionOutAnimator(CommonTransitions.createFastSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 1000));
            aa.show();

        } else if (code == Canvas.RIGHT) {
            AcceuilBack aa = new AcceuilBack();
            aa.setTransitionOutAnimator(CommonTransitions.createFastSlide(TOP, true, 1000));
            aa.show();

        } else if (code == Canvas.FIRE) {
            StatistiqueFormateur aa = new StatistiqueFormateur();
            aa.setTransitionOutAnimator(Transition3D.createRotation(1000, true));
            aa.show();

        }
    }

}
