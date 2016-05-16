package tessearctntest;

import com.sun.lwuit.Form;
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
import tesseracttest.HomeCanvas;
import tesseracttest.Midlet;

/**
 *
 * @author Noor
 */
public class CoursesCanvas extends Canvas implements CommandListener{

    private Image add;
    private Image edit;
    private Image agenda;
    int click = 2;
    private Command back = new Command("Back", Command.BACK, 0);
    public CoursesCanvas() {
        addCommand(back);
        setCommandListener(this);
    }

    protected void paint(Graphics g) {
         try {
            add = Image.createImage("/tesseracttest/images/addc.png");
            edit = Image.createImage("/tesseracttest/images/editc.png");
            agenda = Image.createImage("/tesseracttest/images/agendac.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (click == 1) {
                  InputStream in = getClass().getResourceAsStream("/audio/agenda.wav");
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
            g.drawImage(agenda, 0, 0, 0);
        }
        if (click == 2) {
             InputStream in = getClass().getResourceAsStream("/audio/edit_courses.wav");
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
            g.drawImage(edit, 0, 0, 0);
        }
        if (click == 3) {
             InputStream in = getClass().getResourceAsStream("/audio/add_courses.wav");
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
            g.drawImage(add, 0, 0, 0);
        }
        


    }
     protected void keyPressed(int keyCode) {
        int code = getGameAction(keyCode);
    if (code == RIGHT) {
        if (click==1)
        {click = 2;
            repaint();
            System.out.println("here");}
       else if (click==2)
        {click = 3;
            repaint();
            
        }
    }
    
    
     if (code == LEFT) {
        if (click==3)
        {click = 2;
            repaint();}
      else  if (click==2)
        {click = 1;
            repaint();
            
        }}
        if(code==FIRE){
        if(click==3){
            Midlet.INSTANCE.disp.setCurrent(new EventList("My Events",0));}
      
           else if(click==1){
                          Form f;
            try {
                f = new SubmitCourseForm("Submit Course"); 
                f.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
              
                }
           else if (click==2){
                Midlet.INSTANCE.disp.setCurrent(new CourseList("",0));
           }
        
          }
        }

    public void commandAction(Command c, Displayable d) {
   if(c==back){
   Midlet.INSTANCE.disp.setCurrent(new HomeCanvas("Home"));
   }
    }
     

}
