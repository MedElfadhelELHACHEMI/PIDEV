/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.entities.Apprenant;
import com.entities.Cours;
import com.entities.Formateur;
import com.entities.Matiere;
import com.entities.Organisation;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import tesseract.Midlet;

/**
 *
 * @author Sameh
 */
public class SessionCoursForm extends Form implements CommandListener, Runnable {
Cours c;
 Formateur f;

 private Organisation o;
 Ticker tk = new Ticker("              Tesseract Coding          ");
Apprenant currentUtilisateur;
Matiere m;
 HttpConnection httpConnection;    
    DataInputStream dataInputStream;  
     TextField tfApp;
    TextField tfCour;
      ImageItem imgItem2;
      Image image1;
       ImageItem imItemcher;
        ImageItem imItemLoad;
    Form loadingDialog = new Form("Please Wait");
       private Command cmddone = new Command("OK", Command.BACK, 0);
    private Command cmdCreate = new Command("Enregistre", Command.OK, 0);
    private Command cmdBack = new Command("Annuler", Command.BACK, 0);
    private Command cmdd = new Command("Ok", Command.OK, 0);
   
    public SessionCoursForm(String title,Cours c,Matiere m ,Formateur f,Organisation o) {
        super(title);
        this.c=c;
        this.m=m;
        this.o=o;
        this.f=f;
        currentUtilisateur=Midlet.INSTANCE.ApprenantCurrent;
          tfApp = new TextField("Votre Nom : ", currentUtilisateur.getNomUtilisateur(), 100, TextField.UNEDITABLE);
        tfCour = new TextField("Nom de Cours :",c.getNomCours(), 100, TextField.UNEDITABLE);
          try {
            image1 = Image.createImage("/img/k.png");
             imItemcher = new ImageItem("", image1, Item.LAYOUT_CENTER, "");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
             Image img2 = null;
        try {
           img2=Image.createImage("/img/a.png");
       } catch (IOException ex) {
           ex.printStackTrace();
       }
    imgItem2 = new ImageItem(null, img2, ImageItem.LAYOUT_CENTER, null);
       this.append(imItemcher);
       this.append(tfApp);
        this.append(tfCour);
        this.addCommand(cmdBack);
        this.addCommand(cmdCreate);
     
        setCommandListener(this);
    }

 

    public void commandAction(Command c, Displayable d) {
     if (c == cmdCreate) {
          loadingDialog.append(imgItem2);
            Midlet.INSTANCE.disp.setCurrent(loadingDialog);
           Thread th = new Thread(this,"ajout");
           th.start();}
     if (c==cmddone)
     {  if (f!= null){
       Midlet.INSTANCE.disp.setCurrent(new LSTCours("Please Wait",null,f,null));    
       } 
       else if (o!= null)
       {    Midlet.INSTANCE.disp.setCurrent(new LSTCours("Please Wait",null,null,o)); 
           
       }
       else { 
          Midlet.INSTANCE.disp.setCurrent(new LSTCours("Please Wait",m,null,null));  }
       
     }
     if(c==cmdd){
     Midlet.INSTANCE.disp.setCurrent(this);     
     }
     if (c==cmdBack){
          if (f!= null){
       Midlet.INSTANCE.disp.setCurrent(new LSTCours("Please Wait",null,f,null));    
       } 
       else if (o!= null)
       {    Midlet.INSTANCE.disp.setCurrent(new LSTCours("Please Wait",null,null,o)); 
           
       }
       else { 
          Midlet.INSTANCE.disp.setCurrent(new LSTCours("Please Wait",m,null,null));  }
     }
     }
    

    public void run() {
       if (Thread.currentThread().getName().equals("ajout")){
             try {
 String str= "http://localhost/tesseractj2me/tesseract_php/AjoutSessionCours.php?idcours="+c.getIdCours()+"&idapprt="+currentUtilisateur.getIdUtilisateur();
     
                 System.out.println("dqs<fghcjkghfgjjjjjjjjjjjjjjjjjjjjj"+str);
           httpConnection = (HttpConnection)Connector.open(str);
           dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
        StringBuffer sb = new StringBuffer();
                int ch;
                
                while ((ch = dataInputStream.read()) != -1) {                    
                    sb.append((char)ch);
                }
                
       if ("successfully added".equals(sb.toString().trim())) {
                  Alert al = new Alert("ajout avec succès!");
                  al.addCommand(cmddone);
                  al.setCommandListener(this);
                 Midlet.INSTANCE.disp.setCurrent(al);
                 
                    
                 
                 
                }else{
                    Alert al = new Alert("Erreur!");
                  al.addCommand(cmdd);
                  al.setCommandListener(this);
                 Midlet.INSTANCE.disp.setCurrent(al);
                }
       } catch (IOException ex) {
           ex.printStackTrace();
       }}
       }
    }

   

    
    

