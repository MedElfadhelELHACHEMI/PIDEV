/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.entities.Apprenant;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import tesseract.Midlet;

/**
 *
 * @author Sameh
 */
public class EditInfoForm extends Form implements Runnable, CommandListener{
private TextField tfPwd;
    private TextField tfNom;
    private TextField tfPrenom;
    private TextField tfEmail;
    private TextField tfAdresse;
    private TextField tftel;
    private TextField tflogin;
   DateField date=new DateField("Date de Naissance",DateField.DATE);
    Form loadingDialog = new Form("Please Wait");
   Ticker tk = new Ticker("              Tesseract Coding          ");
    ImageItem imgItem;
    Image img;
    String imgUrl;
     private ChoiceGroup cSexe;
      HttpConnection httpConnection;    
    DataInputStream dataInputStream;    
    StringBuffer stringBuffer = new StringBuffer("");  
    Apprenant currentUtilisateur;
    private ImageItem imgItem2;
     private Command cmdBack = new Command("Retourner", Command.BACK, 0);
    private Command cmdCreate = new Command("Modifier", Command.OK, 0);
    private Command cmddone = new Command("OK", Command.BACK, 0);
    private Command cmdd = new Command("Ok", Command.OK, 0);
    public EditInfoForm(String title) {
        super(title);
    currentUtilisateur=Midlet.INSTANCE.ApprenantCurrent;
     if (currentUtilisateur.getNom().equals("Sameh")) 
            imgUrl = "/img/sameh.jpg";
        else   if (currentUtilisateur.getNom().equals("Bacem")) 
            imgUrl = "/img/bacem.jpg";
        else  imgUrl="/img/profil.png";
       try {
            img =Image.createImage(imgUrl);
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
       tflogin = new TextField("Login :", "", 110, TextField.ANY);
        tfPwd = new TextField("Mot de passe :", "", 110, TextField.PASSWORD);
        tfNom = new TextField("Nom :", "", 110, TextField.ANY);
        tfPrenom = new TextField("Prenom :", "", 110, TextField.ANY);
        tfEmail = new TextField("Email :", "", 110, TextField.EMAILADDR);
        tfAdresse = new TextField("Adresse :", "", 150, TextField.ANY);
        tftel = new TextField("Telephone :", "", 150, TextField.NUMERIC);

        //Choix
        cSexe = new ChoiceGroup("Sexe:", Choice.POPUP);
        cSexe.append("M", null);
        cSexe.append("F", null);
//        System.out.println("qsdfghjklwdxgdhjkldsfdgfhjfsgdhjedfdghjedqsfdghrsrdthfgresttdfg"+currentUtilisateur.getSexe());
        if (currentUtilisateur.getSexe().equals("M") ) {
            cSexe.setSelectedIndex(0, true);
        } else if (currentUtilisateur.getSexe().equals("F")) {
            cSexe.setSelectedIndex(1, true);
        }
      tflogin.setString(currentUtilisateur.getNomUtilisateur());
      tfPwd.setString(currentUtilisateur.getMotDePass());
        tfNom.setString(currentUtilisateur.getNom());
        tfPrenom.setString(currentUtilisateur.getPrenom());
        tfEmail.setString(currentUtilisateur.getMail());
        tfAdresse.setString(currentUtilisateur.getAdresse());
         tftel.setString(""+currentUtilisateur.getTel());
       // date.setDate(currentUtilisateur.getDateNaissance());
      this.append(imgItem);
      this.append("\n");
      this.append(tflogin);
        this.append(tfPwd);
        this.append(tfNom);
        this.append(tfPrenom);
        this.append(cSexe);
        this.append(date);
        this.append(tftel);
        this.append(tfEmail);
        this.append(tfAdresse);
        this.setTicker(tk);

        //Commandes
        this.addCommand(cmdBack);
        this.addCommand(cmdCreate);
        this.setCommandListener(this);
    
    
    }

    public void run() {
    try {
        String str= "http://localhost/Tesseract/editUtilisateur.php?username="+tflogin.getString().trim()+"&password="+tfPwd.getString().trim()
                +"&nom="+tfNom.getString().trim() +"&prenom="+tfPrenom.getString().trim()+"&sexe="+cSexe.getString(cSexe.getSelectedIndex())+"&email="+tfEmail.getString().trim() +"&adresse="+tfAdresse.getString().trim()+"&telephone="+tftel.getString().trim()+ "&id=" +currentUtilisateur.getIdUtilisateur() ;
       
        System.out.println(str);
        httpConnection = (HttpConnection)Connector.open(str);
        dataInputStream = new DataInputStream(httpConnection.openDataInputStream());
        StringBuffer sb = new StringBuffer();
        int ch;
        
        while ((ch = dataInputStream.read()) != -1) {
            sb.append((char)ch);
        }
        if ("successfully".equals(sb.toString().trim())) {
                updateCompte();
                Alert al = new Alert("Compte modifié! ", "Votre compte a été modifié avec succès!", null, AlertType.INFO);
               
               al.addCommand(cmddone);
               al.setCommandListener(this);
              Midlet.INSTANCE.disp.setCurrent(al);
               
               
               
            } else {
                Alert al = new Alert("Données incorrectes !");
                al.addCommand(cmdd);
                  al.setCommandListener(this);
                 Midlet.INSTANCE.disp.setCurrent(al);
            }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }

    public void commandAction(Command c, Displayable d) {
     if (c == cmdCreate) {
            // this.deleteAll();
           loadingDialog.append(imgItem2);
            Midlet.INSTANCE.disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }

        if (c == cmdBack) {
            this.deleteAll();
           Midlet.INSTANCE.disp.setCurrent(new ProfilApprt(currentUtilisateur));
        }
        if(c==cmddone){
          Midlet.INSTANCE.disp.setCurrent(new ProfilApprt(currentUtilisateur));  
        }
        if (c==cmdd){
             Midlet.INSTANCE.disp.setCurrent(this);
         }
    }

    private void updateCompte() {
      this.currentUtilisateur.setNom(tfNom.getString());
        this.currentUtilisateur.setPrenom(tfPrenom.getString());
        this.currentUtilisateur.setMail(tfEmail.getString());
        this.currentUtilisateur.setMotDePass(tfPwd.getString());
        this.currentUtilisateur.setAdresse(tfAdresse.getString());
        this.currentUtilisateur.setSexe(cSexe.getString(cSexe.getSelectedIndex()));
        this.currentUtilisateur.setNomUtilisateur(tflogin.getString());
        this.currentUtilisateur.setTel(Integer.parseInt(tftel.getString()));
        
    }

   
   

   
    
}
