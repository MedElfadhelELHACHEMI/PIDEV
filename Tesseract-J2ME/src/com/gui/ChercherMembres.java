/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.handler.FormateurHandler;
import com.handler.OrganisationHandler;
import com.entities.Apprenant;
import com.entities.Formateur;
import com.entities.Organisation;
import com.entities.Utilisateur;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
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
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
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
public class ChercherMembres extends Form implements Runnable, CommandListener, ItemStateListener  {
private ImageItem imgItem;
public static Form form;

    private String imgUrl;
    private Image img;
    private Image imgUser;
    private Command cmdBack;
    private Command cmdChercher;
    private Command cmdAff;
    private Command cmdContact;
    private Formateur formateur;
     Gauge gauge;
    private Organisation organisation;
     Form loadingDialog = new Form("Please Wait");
     private Command cmdd = new Command("Ok", Command.OK, 0);
     private ImageItem imgItem2;
   Ticker tk = new Ticker("              Tesseract Coding          ");
    private ChoiceGroup cg;
    private TextField tfNom;
      Apprenant currentUtilisateur;
       private  Utilisateur[] formateurs;
        private Organisation[] Organisations;
          List lst;
          
          HttpConnection httpConnection;    
    DataInputStream dataInputStream;    
    
          StringBuffer sb = new StringBuffer();
    public ChercherMembres(String title) {
        super(title);
      
        currentUtilisateur=Midlet.INSTANCE.ApprenantCurrent;
        imgUrl = "/img/search1.png";
        try {
            img = Image.createImage("/img/search1.png");
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
        cmdAff = new Command("Afficher Cours", Command.OK, 0);
        cmdContact = new Command("Contacter", Command.OK, 0);
       
        
         tfNom = new TextField("\n\nNom du membre:", "", 110, TextField.ANY);
        lst = new List("Resultats de la recherche", List.IMPLICIT);
        
        //Choix
        cg = new ChoiceGroup("Membre :", Choice.POPUP);
        cg.setPreferredSize(50, 5);
        cg.append("Formateur                   ", null);
        cg.append("Organisme                   ", null);
        this.append(imgItem);
        this.append(tfNom);
        this.append(cg);
         form = new Form("Informations compte");
         this.addCommand(cmdBack);
        this.addCommand(cmdChercher);
        this.setCommandListener(this);
        this.setItemStateListener(this);
      this.setTicker(tk);
      lst.setTicker(tk);
        lst.addCommand(cmdBack);
        lst.addCommand(cmdAff);
        lst.setCommandListener(this);
    }

   

    

    public void commandAction(Command c, Displayable d) {
      if (c == cmdBack && d == form) {
            form.deleteAll();
            Midlet.INSTANCE.disp.setCurrent(lst);
        }
      else if (c == cmdBack && d == lst) {
            form.deleteAll();
             Midlet.INSTANCE.disp.setCurrent(new ChercherMembres("Chercher"));
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
           form.addCommand(cmdBack);
           form.addCommand(cmdContact);
            form.setCommandListener(this);
             String nom=null;
              String imgurl=null;
            if (cg.getSelectedIndex()==0) {
                int score =((Formateur)formateurs[lst.getSelectedIndex()]).getScore();
            gauge=new Gauge("Score",false, 100,score );
           nom=formateurs[lst.getSelectedIndex()].getNomUtilisateur();
            imgurl = "/img/"+nom+".jpg";}
            else {
               nom=Organisations[lst.getSelectedIndex()].getNom();  
                imgurl = "/img/"+nom+".png";}
             
          
            
            try {
                img = Image.createImage(imgurl);
                imgItem = new ImageItem(null, img, ImageItem.LAYOUT_CENTER, null);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            form.setTicker(tk);
            form.append(imgItem);
            form.append("\n");
            form.append(showCompte(lst.getSelectedIndex()));
            form.append(gauge);
           Midlet.INSTANCE.disp.setCurrent(form);    
     }
     else  if (c == cmdAff && d==lst) {
       if (cg.getSelectedIndex()==0){
          formateur=(Formateur) formateurs[lst.getSelectedIndex()] ; 
          Midlet.INSTANCE.disp.setCurrent(new LSTCours("Please Wait",null,formateur,null));
         }
         else {
           organisation=Organisations[lst.getSelectedIndex()];
           Midlet.INSTANCE.disp.setCurrent(new LSTCours("Please Wait",null,null,organisation));
         }
      }
      
     if (c==cmdContact && d==form){
         if (cg.getSelectedIndex()==0){
          formateur=(Formateur) formateurs[lst.getSelectedIndex()] ; 
          Midlet.INSTANCE.disp.setCurrent(new SendSms(formateur));
         }
         else {
           organisation=Organisations[lst.getSelectedIndex()];
           Midlet.INSTANCE.disp.setCurrent(new SendSms(organisation));
         }
         
     }
      if (c==cmdd){
             Midlet.INSTANCE.disp.setCurrent(new ChercherMembres("Chercher"));
         }
    }
    public void itemStateChanged(Item item) {
        if (item == tfNom) {
            if ((tfNom.getString().length() % 2) != 0) {
                imgUrl = "/img/search2.png";
            } else {
                imgUrl = "/img/search1.png";
            }
            String str = tfNom.getString();
            deleteAll();
            revalidate();
            tfNom.setString(str);
           Midlet.INSTANCE.disp.setCurrentItem(tfNom);}
    }

    private String showCompte(int i) {
        String res = "";
        if (cg.getSelectedIndex()==0) {
            
            sb.append("Nom : ");
            sb.append(formateurs[i].getNom());
            sb.append("\n");
            sb.append("Addresse : ");
            sb.append(formateurs[i].getAdresse());
            sb.append("\n");
            sb.append("Email : ");
            sb.append(formateurs[i].getMail());
            sb.append("\n");
            sb.append("Telephone : ");
            sb.append(formateurs[i].getTel());
           

        }
        else {
         sb.append("Nom : ");
            sb.append(Organisations[i].getNom());
            sb.append("\n");
            sb.append("Addresse : ");
            sb.append(Organisations[i].getAdress());
            sb.append("\n");
            sb.append("Email : ");
            sb.append(Organisations[i].getEmail());  
            sb.append("\n");
            sb.append("Telephone : ");
            sb.append(Organisations[i].getTelephone());
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res; 
    }

    private void revalidate() {
       try {
            img = Image.createImage(imgUrl);
            imgItem = new ImageItem(null, img, ImageItem.LAYOUT_CENTER, null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        tfNom = new TextField("\n\nNom du membre:", "", 110, TextField.ANY);

        //Choix
        cg = new ChoiceGroup("Membre :", Choice.POPUP);
        cg.append("Formateur                   ", null);
        cg.append("Organisme                   ", null);

        cg.setPreferredSize(50, 5);
        this.append(imgItem);
        this.append(tfNom);
        this.append(cg);
        this.addCommand(cmdBack);
        this.setCommandListener(this);
        this.setItemStateListener(this);
    }
  public void run() {
     if (Thread.currentThread().getName().equals("recherche")) {
         if (cg.getSelectedIndex()==0){
        try {
           
            String str= "http://localhost/tesseractj2me/tesseract_php/getXmlRechercheFor.php?name=" +tfNom.getString().trim();
            System.out.println("fghjkl"+str);
            httpConnection = (HttpConnection)Connector.open(str);     
            dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
             SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
              FormateurHandler formateurHandler = new FormateurHandler();
               parser.parse(dataInputStream, formateurHandler);
              formateurs=  formateurHandler.getUtilisateur();
              System.out.println(formateurHandler.getUtilisateur()[0].toString());
               if ( formateurs.length > 0) {
                    for (int i = 0; i <  formateurs.length; i++) {
                        try {
                            if (i % 2 == 0) {
                                imgUser = Image.createImage("/img/user.png");
                            } else {
                                imgUser = Image.createImage("/img/user2.png");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        lst.append( formateurs[i].getNom()+" "+formateurs[i].getPrenom(), imgUser);
 Midlet.INSTANCE.disp.setCurrent(lst);
                    }
               }
               
        } catch (SAXException se) {
            Alert al = new Alert("Données incorrectes", "Formateur n'existe pas!", null, AlertType.WARNING);
           al.addCommand(cmdd);
                  al.setCommandListener(this);
                 Midlet.INSTANCE.disp.setCurrent(al);;
       
            
        } catch (ConnectionNotFoundException ce){
          
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
     
     
         }
 }
         
      else if (cg.getSelectedIndex()==1){
        try {
         String str= "http://localhost/tesseractj2me/tesseract_php/getXmlRechercheOrg.php?name=" + tfNom.getString().trim();
            
            httpConnection = (HttpConnection)Connector.open(str);     
            dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
             SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
           OrganisationHandler organisationrHandler = new OrganisationHandler();
               parser.parse(dataInputStream, organisationrHandler);
              Organisations= organisationrHandler.getOrganisation();
               if ( Organisations.length > 0) {
                    for (int i = 0; i <  Organisations.length; i++) {
                        try {
                            if (i % 2 == 0) {
                                imgUser = Image.createImage("/img/user.png");
                            } else {
                                imgUser = Image.createImage("/img/user2.png");
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        lst.append( Organisations[i].getNom(), imgUser);
                       Midlet.INSTANCE.disp.setCurrent(lst);
                    }
                 }
               
       }catch (SAXException se) {
            Alert al = new Alert("Données incorrectes", "Organisation n'existe pas!", null, AlertType.WARNING);
           al.addCommand(cmdd);
                  al.setCommandListener(this);
                 Midlet.INSTANCE.disp.setCurrent(al);
       
            
        } catch (ConnectionNotFoundException ce){
          
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
     
     
         }}
  
     
     }
    
    
} 
}    
    

//                }
    
