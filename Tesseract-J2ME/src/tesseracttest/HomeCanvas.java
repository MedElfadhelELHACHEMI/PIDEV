package tesseracttest;

import com.sun.lwuit.Display;
import com.sun.lwuit.animations.Transition3D;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import tessearctntest.CoursesCanvas;

/**
 *
 * @author Noor
 */
public  class HomeCanvas extends Canvas  implements CommandListener{

    private Image mail;
    private Image profile;
    private Image courses;
    int click = 2;
    Command signOut;

    public HomeCanvas(String coach) {
      
        InputStream in = getClass().getResourceAsStream("/audio/you_can_chose_something.wav");
        Player player = null;
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
        signOut = new Command("Sign Out", Command.OK, 0);
          addCommand(signOut);
        setCommandListener(this);
    }

    protected void paint(Graphics g) {

        try {
            mail = Image.createImage("/tesseracttest/images/left.png");
            profile = Image.createImage("/tesseracttest/images/up.png");
            courses = Image.createImage("/tesseracttest/images/right.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (click == 1) {
            g.drawImage(mail, 0, 0, 0);
        }
        if (click == 2) {
            g.drawImage(profile, 0, 0, 0);
        }
        if (click == 3) {
            g.drawImage(courses, 0, 0, 0);
        }

    }

    protected void keyPressed(int keyCode) {
        int code = getGameAction(keyCode);
        if (code == UP) {
            click = 2;
            repaint();
        }
        if (code == RIGHT) {
            click = 3;
            repaint();
        }
        if (code == LEFT) {
            click = 1;
            repaint();
        }
        if (code == FIRE) {
            if (click == 1) {
                Midlet.INSTANCE.disp.setCurrent(new BoiteMailCanvas());
            } else if (click == 2) {
                AccueilProfil ap = new AccueilProfil();

                ap.show();

            } else if (click == 3) {
                Midlet.INSTANCE.disp.setCurrent(new CoursesCanvas());
            }

        }

    }

    public void commandAction(Command c, Displayable d) {
        if (c == signOut) {
//            CurrentUser.setId(0);
//            CurrentUser.setRole(null);
        }
    }

}
