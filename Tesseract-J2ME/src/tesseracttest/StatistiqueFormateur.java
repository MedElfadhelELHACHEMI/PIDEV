/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesseracttest;

import com.sun.lwuit.Command;

import com.sun.lwuit.Form;
import com.sun.lwuit.Graphics;
import com.sun.lwuit.Image;
import com.sun.lwuit.List;
import com.sun.lwuit.MenuBar;
import com.sun.lwuit.animations.CommonTransitions;
import com.sun.lwuit.animations.Transition3D;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Ticker;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


class StatistiqueFormateur extends Form{
private Image font; 
Command  back ;
    Ticker ticker = new Ticker("aaa");
    public StatistiqueFormateur() {
               Timer timer = new Timer();
        timer.schedule(timerTask,5, 5);
     back = new Command("BACK");

          
          //getMenuStyle()
          getMenuBar().getStyle().setBgColor(0xa6dae8);
           addCommandListener(new ActionListener() {

         public void actionPerformed(ActionEvent ae) {
             if(ae.getCommand()==back){
             AccueilProfil aa = new AccueilProfil();
       aa.setTransitionOutAnimator(Transition3D.createRotation(1000, false));
       aa.show();
             
             }
              }
     });
        
 
//        List list = new List(listCours);
//        addComponent(list);
                   getMenuBar().getStyle().setBgTransparency(0);
                   }
int x = 0 ;
 int y = getLevelFormateur() ;
    TimerTask timerTask = new TimerTask() {

    public void run() {
      animations();
    }

   
};
     private void animations() {
         System.out.println("-------------"+x);
      x++;
      if(x>=y*36){
      timerTask.cancel();
      }
     
      repaint();
    }
    public void paint(Graphics g) {
      
        
    g.setColor(0xff0012);
    
       g.fillArc(0, getHeight()/2-getWidth()/2, getWidth() , getWidth(), 0, 360);
       g.setColor(0x0a006e);
       g.fillArc(0, getHeight()/2-getWidth()/2, getWidth(), getWidth(), 0   , x);
 g.setColor(0xffffff);
        g.drawString("Your score is "+y*36/3.6+" % ", getWidth()/2-60, getHeight()/2);
      
    }

    public void paintBackground(Graphics g) {
         g.setColor(0xa6dae8);
     g.fillRect(0, 0, getWidth() , getHeight());
        getMenuBar().getStyle().setBgTransparency(0);
      // getMenuBar().getStyle().setBgColor(0xa6dae8);
        try {
        font  = Image.createImage("/tesseracttest/images/bgstat.png");
        g.drawImage(font, 0, 0);
         g.setColor(255);
          
      //  g.drawRect(getWidth()/2, getHeight()/2, 50, 50);
    } catch (IOException ex) {
        ex.printStackTrace();
    }
     addCommand(back);
 
    }

    private int getLevelFormateur() {
     
       try {
            FormateurHandler handler = new FormateurHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection connection;

            connection = (HttpConnection) Connector.open("http://localhost/Business/getFormateurById.php?idUtilisateur=" + CurrentUser.getId());

            DataInputStream dis;

            dis = new DataInputStream(connection.openDataInputStream());
            parser.parse(dis, handler);

           Formateur[] formateurs = handler.getFormateurs();

            for (int i = 0; i < formateurs.length; i++) {
              return formateurs[i].getScore();

            }
        
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    return 0 ;
    
    }
    
}
