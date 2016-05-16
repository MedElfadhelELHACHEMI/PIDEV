/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesseracttest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;


public class SplashFormateur extends Canvas {

    Image walk;
    Image stop;
    int x = -50;
    int y = getHeight();

    public SplashFormateur() {
        Timer timer = new Timer();
        timer.schedule(timerTask, 5, 5);

    }
    TimerTask timerTask = new TimerTask() {

        public void run() {
            walk();
        }

    };
    boolean move = true;
    boolean nomove = false;

    private void walk() {
        if (nomove == true) {
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        nomove = false;
        if (move) {
            x++;
        } else {
            x--;
        }

        if (x == getWidth() - 80) {
            Midlet.INSTANCE.disp.setCurrent(new HomeCanvas("Home"));
            timerTask.cancel();
            move = false;
        } else if (x == -80) {

            move = true;
        } else if (x == getWidth() / 2 - 50) {
            nomove = true;
        }
        repaint();
    }

    protected void paint(Graphics g) {
        try {
            g.setColor(0xa6dae8);
            g.fillRect(0, 0, getWidth(), getHeight());
            if (nomove == false) {
                System.out.println("here");
                walk = Image.createImage("/tesseracttest/images/formateur.png");
            } else if (nomove == true) {
                InputStream in = getClass().getResourceAsStream("/audio/welcome_coatch.wav");
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
                walk = Image.createImage("/tesseracttest/images/formateur2.png");
                g.drawImage(Image.createImage("/tesseracttest/images/welcome.png"), 0, 0, 0);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        g.drawImage(walk, x, y - walk.getHeight(), 0);

    }

}
