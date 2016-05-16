/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.handler.ChapitreHandler;
import com.handler.CoursHandler;
import com.handler.SessionCoursHandler;
import com.entities.Apprenant;
import com.entities.Chapitre;
import com.entities.Cours;
import com.entities.Formateur;
import com.entities.Matiere;
import com.entities.Organisation;
import com.entities.SessionCours;


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
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import tesseract.Midlet;

/**
 *
 * @author Sameh
 */
public class ChercherMesCours extends Form implements CommandListener ,Runnable{
 private Formateur formateur;
   private Organisation organisation;
   String   imgur;
 Apprenant currentUtilisateur; 
 Cours currentCours;
 private Matiere Matieres;
  private ChoiceGroup cg;
   Ticker tk = new Ticker("              Tesseract Coding          ");
    Form loadingDialog = new Form("Please Wait");
     private Command cmdChercher;
  private Command cmdBack;
      HttpConnection httpConnection;    
    DataInputStream dataInputStream;    
    StringBuffer sb = new StringBuffer();
    private ImageItem imgItem;
    private String imgUrl;
    private Image img;
      private ImageItem imgItem2;
       private TextField tfNom;
        private Cours[] cours;
         public static  List lst;
            private Command cmdAff;
           Form form;
            String nom;
            Image image1;
            Image image3;
            SessionCours[] sessionCours;
             Chapitre[] chapitres;
    public ChercherMesCours(String title) {
        super(title);
        currentUtilisateur=Midlet.INSTANCE.ApprenantCurrent;
          try {
            image3 = Image.createImage("/img/c.png");
        

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            img = Image.createImage("/img/cherch2.png");
            imgItem = new ImageItem(null, img, ImageItem.LAYOUT_CENTER, null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        Image img2 = null;
        try {
           img2=Image.createImage("/img/a.png");
       } catch (IOException ex) {
           ex.printStackTrace();
       }
        imgItem2=new ImageItem(null, img2, ImageItem.LAYOUT_CENTER, null);
         cmdBack = new Command("Retourner", Command.BACK, 0);
        cmdChercher = new Command("Chercher", Command.OK, 0);
        cmdAff = new Command("Statistique", Command.OK, 0);
         tfNom = new TextField("\n\nNom     :", "", 110, TextField.ANY);
        lst = new List("Liste Mes Cours", List.IMPLICIT);
          cg = new ChoiceGroup("Critere :", Choice.POPUP);
         tfNom.setLayout(TextField.LAYOUT_CENTER);
        cg.append("                            ", null);
        cg.append("Formateur                   ", null);
        cg.append("Organisme                   ", null);
        cg.append("Matiere                   ", null);
        cg.setLayout(ChoiceGroup.LAYOUT_CENTER);
         this.append(imgItem);
        this.append(tfNom);
        this.append(cg);
         form = new Form("Informations Cours");
         this.addCommand(cmdBack);
        this.addCommand(cmdChercher);
        this.setCommandListener(this);
       this.setTicker(tk);
        lst.setTicker(tk);
        lst.addCommand(cmdBack);
        lst.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
     if (c == cmdBack && d == form) {
            form.deleteAll();
            Midlet.INSTANCE.disp.setCurrent(lst);
        }
        else if (c == cmdBack && d == lst) {
            form.deleteAll();
             Midlet.INSTANCE.disp.setCurrent(new ChercherMesCours("Chercher Mes Cours"));
    }
     else if (c == cmdBack) {
           Midlet.INSTANCE.disp.setCurrent(new ProfilApprt(currentUtilisateur));
        }
     else  if (c == cmdChercher) {
          form.deleteAll();
           loadingDialog.append(imgItem2);
            Midlet.INSTANCE.disp.setCurrent(loadingDialog);
            Thread th = new Thread(this, "recherche");
            th.start();
        }
      else if (c==List.SELECT_COMMAND){
      form.deleteAll();
           
            
          currentCours= cours[lst.getSelectedIndex()]; 
          nom=cours[lst.getSelectedIndex()].getNomCours();
           imgur = "/img/"+nom+".jpg";
             
         try {
                image1 = Image.createImage(imgur);
        
         imgItem = new ImageItem(null, image1, ImageItem.LAYOUT_CENTER, null);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
      form.setTicker(tk);
      form.append(imgItem);
      form.append("\n");
     form.append(showCours(lst.getSelectedIndex()));
     form.addCommand(cmdBack);
     form.addCommand(cmdAff);
     form.setCommandListener(this); 
      Midlet.INSTANCE.disp.setCurrent(form);
      }
    if (c==cmdAff && d==form){
      form.deleteAll();

            Midlet.INSTANCE.disp.setCurrent(loadingDialog);
            Thread th = new Thread(this, "session");
            th.start();   
    } 
    }

    public void run() {
      if (Thread.currentThread().getName().equals("recherche")) {
           
        if (cg.getSelectedIndex()==0){
         try {
            
              String str= "http://localhost/tesseractj2me/tesseract_php/getXmlMesCours.php?id=" + currentUtilisateur.getIdUtilisateur();
              System.out.println("fghjkl"+str);
              httpConnection = (HttpConnection)Connector.open(str);
              dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
              SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
              CoursHandler coursHandler = new CoursHandler();
              parser.parse(dataInputStream, coursHandler);
              cours = coursHandler.getCours();
              
              if (cours.length > 0) {
                  this.deleteAll();
                  for (int i = 0; i < cours.length; i++) {
                      lst.append(cours[i].getNomCours(), image3);
                  }
                  Midlet.INSTANCE.disp.setCurrent(lst);    
              
             }
               
     
                }catch (SAXException se) {
            Alert al = new Alert("Données", "Liste vide!", null, AlertType.WARNING);
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
         else if (cg.getSelectedIndex()==1){
               try {
            
              String str= "http://localhost/tesseractj2me/tesseract_php/getXMLMesCourFor.php?util=" + currentUtilisateur.getIdUtilisateur() + "&name=" +tfNom.getString().trim();
              System.out.println("fghjkl"+str);
              httpConnection = (HttpConnection)Connector.open(str);
              dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
              SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
              CoursHandler coursHandler = new CoursHandler();
              parser.parse(dataInputStream, coursHandler);
              cours = coursHandler.getCours();
              
              if (cours.length > 0) {
                  this.deleteAll();
                  for (int i = 0; i < cours.length; i++) {
                      lst.append(cours[i].getNomCours(), image3);
                  }
                  Midlet.INSTANCE.disp.setCurrent(lst);    
              
             }
               
     
                }catch (SAXException se) {
            Alert al = new Alert("Nom Formateur N existe pas", "Liste vide!", null, AlertType.WARNING);
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
         else if (cg.getSelectedIndex()==2){
              try {
            
              String str= "http://localhost/tesseractj2me/tesseract_php/getXMLMesCourOrg.php?util=" + currentUtilisateur.getIdUtilisateur() + "&name=" +tfNom.getString().trim();
              System.out.println("fghjkl"+str);
              httpConnection = (HttpConnection)Connector.open(str);
              dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
              SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
              CoursHandler coursHandler = new CoursHandler();
              parser.parse(dataInputStream, coursHandler);
              cours = coursHandler.getCours();
              
              if (cours.length > 0) {
                  this.deleteAll();
                  for (int i = 0; i < cours.length; i++) {
                      lst.append(cours[i].getNomCours(), image3);
                  }
                  Midlet.INSTANCE.disp.setCurrent(lst);    
              
             }
               
     
                }catch (SAXException se) {
            Alert al = new Alert("Nom Organisme N existe pas", "Liste vide!", null, AlertType.WARNING);
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
         else if (cg.getSelectedIndex()==3){
          try {
            
              String str= "http://localhost/tesseractj2me/tesseract_php/getXmlMesCourMat.php?util=" + currentUtilisateur.getIdUtilisateur() + "&name=" +tfNom.getString().trim();
              System.out.println("fghjkl"+str);
              httpConnection = (HttpConnection)Connector.open(str);
              dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
              SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
              CoursHandler coursHandler = new CoursHandler();
              parser.parse(dataInputStream, coursHandler);
              cours = coursHandler.getCours();
              
              if (cours.length > 0) {
                  this.deleteAll();
                  for (int i = 0; i < cours.length; i++) {
                      lst.append(cours[i].getNomCours(), image3);
                  }
                  Midlet.INSTANCE.disp.setCurrent(lst);    
              
             }
               
     
                }catch (SAXException se) {
            Alert al = new Alert("Nom Matiere N existe pas", "Liste vide!", null, AlertType.WARNING);
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
      
      if (Thread.currentThread().getName().equals("session")) {
        try {
            
              String str= "http://localhost/tesseractj2me/tesseract_php/getXMLSessionMesCours.php?util=" + currentUtilisateur.getIdUtilisateur() + "&id=" +currentCours.getIdCours();
              System.out.println("fghjkl"+str);
              httpConnection = (HttpConnection)Connector.open(str);
              dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
              SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
              SessionCoursHandler sessionCoursHandler  = new SessionCoursHandler ();
              parser.parse(dataInputStream, sessionCoursHandler );
              sessionCours = sessionCoursHandler .getSessionCours();
                String str2= "http://localhost/tesseractj2me/tesseract_php/getXMLChapitre.php?id=" +currentCours.getIdCours();
              System.out.println("fghjkl"+str2);
              httpConnection = (HttpConnection)Connector.open(str2);
              dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
              SAXParser parser1 = SAXParserFactory.newInstance().newSAXParser();
             ChapitreHandler chapitreHandler  = new ChapitreHandler ();
              parser.parse(dataInputStream, chapitreHandler );
              chapitres = chapitreHandler .getChapitre();
              
              System.out.println("dfgdggggggggggffflskksjgsjhkjgsnhgkjsghjmlgsjmlsgjsgfljgsfklgsj"+chapitres.length);
             if (sessionCours.length > 0) {
               Midlet.INSTANCE.sessionCours=sessionCours[0];
            Midlet.INSTANCE.disp.setCurrent(new StatistiqueCours(sessionCours,chapitres,currentCours));    
              
             }
               
     
                }catch (SAXException se) {
            Alert al = new Alert("Nom Matiere N existe pas", "Liste vide!", null, AlertType.WARNING);
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

    private String showCours(int i) {
     String res = "";
        if (cours.length > 0) {
       sb.append("Nom : ");
            sb.append(cours[i].getNomCours());
            sb.append("\n");
            sb.append("Description : ");
            sb.append(cours[i].getDescriptionCours());
            sb.append("\n");
            sb.append("Difficulte : ");
            sb.append(cours[i].getDifficulte());
            sb.append("\n");
           
           if (cg.getSelectedIndex()==1){
              sb.append("Nom Formateur: ");
                sb.append(tfNom.getString());
            sb.append("\n");   
           }
           if (cg.getSelectedIndex()==2){
             sb.append("Nom Organisme: ");
                sb.append(tfNom.getString());
            sb.append("\n");   
           }
            if (cg.getSelectedIndex()==0){
             sb.append("\n");    
            }
            else {
                sb.append("Nom Matiere: ");
                sb.append(tfNom.getString());
            sb.append("\n");}
        res = sb.toString();
        sb = new StringBuffer("");}
        return res;
        }
 
    
}
