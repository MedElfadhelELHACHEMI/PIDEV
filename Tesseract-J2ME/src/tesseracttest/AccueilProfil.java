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
import com.sun.lwuit.Label;
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
public class AccueilProfil extends Form {

    private Image stat;
    private Image profil;
    private Image rightImg;
    private Label right;

    public AccueilProfil() {
        InputStream in = getClass().getResourceAsStream("/audio/profile.wav");
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

    public void paint(Graphics g) {

        try {
            rightImg = Image.createImage("/tesseracttest/images/rightflesh.png");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        right = new Label(rightImg);
        right.setY(getHeight() / 2);
        right.setX(getWidth() / 2);
        addComponent(right);

    }

    public void paintBackground(Graphics g) {
        g.setColor(0xa6dae8);
        g.fillRect(0, 0, getWidth(), getHeight());
        try {
            stat = Image.createImage("/tesseracttest/images/profill.png");

            g.drawImage(stat, 0, 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        getMenuBar().getStyle().setBgColor(0xa6dae8);

    }

    public void keyPressed(int keyCode) {
        int code = Display.getInstance().getGameAction(keyCode);
        if (code == Canvas.RIGHT) {
            AcceuilMap aa = new AcceuilMap();
            aa.setTransitionOutAnimator(CommonTransitions.createFastSlide(TOP, true, 1000));
            aa.show();

        }
        if (code == Canvas.FIRE) {
            profilFormateur formateur = new profilFormateur();
            formateur.setTransitionOutAnimator(Transition3D.createRotation(400, true));
            formateur.show();

        }
    }

}
