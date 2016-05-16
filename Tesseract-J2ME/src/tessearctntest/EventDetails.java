package tessearctntest;

import com.sun.lwuit.Label;
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author Noor
 */
class EventDetails extends Form {
    Command join;
    ImageItem imgItem;
    StringItem details=null;
    

    public EventDetails(String title, Evenement event) throws IOException {
        super(title);
        join=new Command("Join",Command.OK, 0);
        addCommand(join);
        
        
     imgItem=new ImageItem("", Image.createImage(event.getAffiche()), 0, title);
     append(imgItem);
        
        
       details=new StringItem("", " Event id = " + event.getIdEvenement() +
                " \n Event Name : " + event.getNom() +
                
                "\n Description : " + event.getDescription() + 
                "\n Takes place at " + event.getDateEvenement() +
                "\n Remaining places " +event.getNbrMax());
       //get host name (org) + fix picture + add join cmd 
      // rod l'image URI 
        
        append(details);
    }
}
