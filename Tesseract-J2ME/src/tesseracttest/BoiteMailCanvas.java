
package tesseracttest;

import com.sun.lwuit.Command;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;


public class BoiteMailCanvas extends Canvas {

    private Image back;
    private Image msgs;
    private Image sendMail;
    int click = 1;

    public BoiteMailCanvas() {
     
        Disc = new javax.microedition.lcdui.Command("DISCONNECT", javax.microedition.lcdui.Command.BACK, 0);
        addCommand(Disc);
    }
    
private javax.microedition.lcdui.Command Disc ;
    protected void paint(Graphics g) {
        try {
            g.setColor(43, 88, 111);
            g.fillRect(0, 0, getWidth(), getHeight());
            back = Image.createImage("/tesseracttest/images/back.png");
            msgs = Image.createImage("/tesseracttest/images/msgs.png");
            sendMail = Image.createImage("/tesseracttest/images/sendmail.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("clik" + click);
        if (click == 1) {
              InputStream in = getClass().getResourceAsStream("/audio/my_invitations.wav");
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
            g.drawImage(msgs, 0, 0, 0);
        }
        if (click == 2) {
              InputStream in = getClass().getResourceAsStream("/audio/send_an_invitation.wav");
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
            g.drawImage(sendMail, 0, 0, 0);
        }
        if (click == 3) {
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
            g.drawImage(back, 0, 0, 0);
        }

    }

    protected void keyPressed(int keyCode) {
        int code = getGameAction(keyCode);
       
        if (code == UP) {
            if (click == 1) {
                click = 3;
                repaint();
            } else if (click == 2) {
                click = 1;
                repaint();
            } else if (click == 3) {
                click = 2;
                repaint();
            }

        } else if (code == DOWN) {

            if (click == 1) {

                click = 2;
                repaint();
            } else if (click == 2) {

                click = 3;
                repaint();
            } else if (click == 3) {

                click = 1;
                repaint();
            }

        } else if (code == FIRE) {

            if (click == 3) {
                Midlet.INSTANCE.disp.setCurrent(new HomeCanvas("Home"));
            } else if (click == 1) {

                Midlet.INSTANCE.disp.setCurrent(new InvitationsList("Invitations", 5));

            } else if (click == 2) {

              ChercherOrganisationForm chercherOrganisationForm = new ChercherOrganisationForm("Searching");
              chercherOrganisationForm.show();
            }

        }
    }

}



