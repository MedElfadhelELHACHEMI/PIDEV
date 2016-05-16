/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.handler.MatiereHandler;
import com.entities.Apprenant;
import com.entities.Cours;
import com.entities.Matiere;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Ticker;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tesseract.Midlet;

/**
 *
 * @author Sameh
 */
public class ChercherCours  extends Form implements CommandListener ,Runnable {
 ImageItem imItemCours;
      ImageItem imItemLoad;
      ImageItem imItemcher;
   Apprenant currentUtilisateur; 
     Image image1;
     Image image2;
     Image image3;
     Image image4;
         HttpConnection httpConnection;    
    DataInputStream dataInputStream;    
    
   StringBuffer sb = new StringBuffer();

   Command cmdBack;
   Command cmdAff;
    private Command cmdChercher;
    private String imgUrl;
  private Matiere[] Matieres;
  
    Ticker tk = new Ticker("              Tesseract Coding          ");
    
     private ChoiceGroup cmatiere;
     String str;
 
 Form form = new Form("Infos Cours");
    public ChercherCours(String title,String str) {
        super(title);
        this.str=str;
        currentUtilisateur=Midlet.INSTANCE.ApprenantCurrent;
         try {
            image1 = Image.createImage("/img/cherch.png");
             imItemcher = new ImageItem("", image1, Item.LAYOUT_CENTER, "");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
          try {
            image4 = Image.createImage("/img/o.png");
            
           } catch (IOException ex) {
            ex.printStackTrace();
        }
           
     try {
            image3 = Image.createImage("/img/cr.png");
        

        } catch (IOException ex) {
            ex.printStackTrace();
        }
          cmatiere=new ChoiceGroup("Liste des Matiere :",Choice.POPUP);
       
           
            cmdChercher = new Command("Afficher", Command.OK, 0);
            cmdBack = new Command("Retourner", Command.BACK, 0);
                this.setTicker(tk);
                cmatiere.append("",null);
   this.append(imItemcher);
   this.append(cmatiere);
    this.addCommand(cmdBack);
     this.addCommand(cmdChercher);
     this.setCommandListener(this);
    Thread th = new Thread(this,"Matiere");
            th.start();  
     
    }

    public void commandAction(Command c, Displayable d) {
    if (c==cmdChercher){
   if (cmatiere.getSelectedIndex()==0){
    Midlet.INSTANCE.disp.setCurrent(new LSTCours("Please Wait",null,null,null));
   }
   if (cmatiere.getSelectedIndex()!=0){
      
       Matiere m=Matieres[cmatiere.getSelectedIndex()-1];
       
    Midlet.INSTANCE.disp.setCurrent(new LSTCours("Please Wait",m,null,null));   
   }
   
    }
    if (c==cmdBack){
       
    Midlet.INSTANCE.disp.setCurrent(new ProfilApprt(currentUtilisateur));        
    }
    }

    public void run() {
  if (Thread.currentThread().getName().equals("Matiere")){
        try {
            
            String str= "http://localhost/tesseractj2me/tesseract_php/getXMLMatire.php";  
          
            httpConnection = (HttpConnection)Connector.open(str);
            dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
           
                MatiereHandler matiereHandler = new MatiereHandler();
                 System.out.println("s<dwdxghfcjghfdsdqSDdsfgdfhjghkbjhghjcklhmjhgkcjxhwgwgFGdffsgfggfggggggggggggggggggggggggggggggggggggggggH"+dataInputStream.toString());
               parser.parse(dataInputStream, matiereHandler);
                
              Matieres= matiereHandler.getMatiere();
                for (int i=0;i<Matieres.length;i++){
            cmatiere.append(Matieres[i].getNomMatiere(), image4);
           }

    
 
      }catch (SAXException se) {
            Alert al = new Alert("Données incorrectes", "Nom d'utilisateur ou mot de passe incorrects!", null, AlertType.WARNING);
           Midlet.INSTANCE.disp.setCurrent(al);
         
            
        } catch (ConnectionNotFoundException ce){
            Alert al = new Alert("Connexion échouée", "Impossible de se connecter au serveur!", null, AlertType.ERROR);
           Midlet.INSTANCE.disp.setCurrent(al);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
          }
  
  
  
  
  
  
  
  
  
  
  
  
    }

 
}
