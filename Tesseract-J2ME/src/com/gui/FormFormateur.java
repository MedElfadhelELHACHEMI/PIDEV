/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gui;

import com.entities.Cours;
import com.entities.Formateur;
import com.entities.Utilisateur;
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Ticker;

/**
 *
 * @author Sameh
 */
public class FormFormateur extends Form implements  CommandListener {
 private Command cmdAff;
    private Command cmdContact;
     private Command cmdBack;
     List lst;
     Command cmdinsc;
       Gauge gauge;
       Image img;
       StringBuffer sb = new StringBuffer();
      private Cours[] cours;
      private Utilisateur f; 
      private  Utilisateur[] formateur;
      ImageItem imgItem ;
       Ticker tk = new Ticker("              Tesseract Coding          ");
    public FormFormateur(String title,Utilisateur f,Cours[] cours,Utilisateur[] formateurs) {
        super(title);
        this.f=f;
        this.cours=cours;
        this.formateur=formateurs;
        cmdinsc = new Command("Inscrire", Command.OK, 0);
        cmdAff = new Command("Afficher Cours", Command.OK, 0);
        cmdContact = new Command("Contacter", Command.OK, 0);
        cmdBack = new Command("Retourner", Command.BACK, 0);
          String nom=null;
              String imgurl=null;
        int score =((Formateur)f).getScore();
     gauge = new Gauge("Score",false, 100,score ); 
      nom=f.getNomUtilisateur();
      imgurl = "/img/"+nom+".jpg";
      try {
          img = Image.createImage(imgurl);
             imgItem = new ImageItem(null, img, ImageItem.LAYOUT_CENTER, null);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
      this.setTicker(tk);
//      
      this.append(imgItem);
      this.append("\n");
      this.append(showCompte());
     this.append(gauge);
      this.addCommand(cmdBack);
      this.addCommand(cmdAff);
      this.addCommand(cmdContact);

//      lst.setTicker(tk);
//      lst.addCommand(cmdinsc);
//      lst.addCommand(cmdBack);
//      lst.setCommandListener(this);
      this.setCommandListener(this);
    }



    public void commandAction(Command c, Displayable d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String showCompte() {
         String res = "";
        
            
            sb.append("Nom : ");
            sb.append(f.getNomUtilisateur());
            sb.append("\n");
            sb.append("Addresse : ");
            sb.append(f.getAdresse());
            sb.append("\n");
            sb.append("Email : ");
            sb.append(f.getMail());
            sb.append("\n");
            sb.append("Telephone : ");
            sb.append(f.getTel());
           

       
        res = sb.toString();
        sb = new StringBuffer("");
        return res; 
    }

}
