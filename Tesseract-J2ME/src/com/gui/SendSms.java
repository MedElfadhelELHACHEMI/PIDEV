/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.entities.Formateur;
import com.entities.Organisation;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import tesseract.Midlet;

/**
 *
 * @author Sameh
 */
public class SendSms extends Form implements CommandListener,Runnable {
private Formateur formateur;
    private Organisation organisation;
     TextField toWhom;
       TextField message;
       Alert alert;
       Command send,exit;
       MessageConnection clientConn;
       Form compose;
       int num;
        Ticker tk = new Ticker("              Tesseract Coding          ");
    public SendSms(Object object) {
        super("SMS");
        if (object instanceof Formateur){
        formateur=(Formateur) object; 
        num=formateur.getTel();}
        else{
        organisation=(Organisation) object;
        num=organisation.getTel();
        }
      compose=new Form("Compose Message");
            toWhom=new TextField("A","123456790",10,TextField.PHONENUMBER);
            message=new TextField("Message","",600,TextField.ANY);
            send=new Command("Envoyer",Command.SCREEN,1);
            exit=new Command("Précédent",Command.EXIT,0);
            this.setTicker(tk);
            this.append(toWhom);
            this.append(message);
            this.addCommand(send);
            this.addCommand(exit);
            this.setCommandListener(this);  
    }

    public void commandAction(Command c, Displayable d) {
    if(c==exit) {
                 Midlet.INSTANCE.disp.setCurrent(ChercherMembres.form);
            }
            if(c==send) {
              Thread th=new Thread(this);
               th.start(); 
      
    }}

    public void run() {
     String mno=toWhom.getString();
                  String msg=message.getString();
                  if(mno.equals("")) {
                        alert = new Alert("Alert");
                        alert.setString("Enter Mobile Number!!!");
                        alert.setTimeout(2000);
                        Midlet.INSTANCE.disp.setCurrent(alert);
                  }
                  else {
                        try {
                        clientConn=(MessageConnection)Connector.open("sms://"+mno);
                        alert = new Alert("Alert");
                        alert.setString("cv!!!");
                        alert.setTimeout(2000);
                        Midlet.INSTANCE.disp.setCurrent(alert);
                        }
                        catch(Exception e) {
                              alert = new Alert("Alert");
                              alert.setString("Unable to connect to Station because of network problem");
                              alert.setTimeout(2000);
                             Midlet.INSTANCE.disp.setCurrent(alert);
                        }
                        try {
                              TextMessage textmessage = (TextMessage) clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
                              textmessage.setAddress("sms://"+mno);
                              textmessage.setPayloadText(msg);
                              clientConn.send(textmessage);
                        }
                        catch(Exception e)
                        {
                              Alert alert=new Alert("Alert","",null,AlertType.INFO);
                              alert.setTimeout(Alert.FOREVER);
                              alert.setString("Unable to send");
                             Midlet.INSTANCE.disp.setCurrent(alert);
                        }
                  }
    }

    
    
}
