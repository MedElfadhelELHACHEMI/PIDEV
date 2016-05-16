/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outils;


import com.models.entities.Mail;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author MohamedAmine
 */
public class MailConstruction {
    
    URL url;
    Properties properties;
    String mailPart ;
    Session session;
    Message message;
    
    
    
  
    
    public void getMailProperties(){ 
            this.properties = new Properties();
            this.properties.put("mail.smtp.auth", "true");
            this.properties.put("mail.smtp.starttls.enable", "true");
            this.properties.put("mail.smtp.host", "smtp.gmail.com");
            
            this.properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            this.properties.put("mail.smtp.port", "587");   
    }
    
    
    
    public void getMailMessage(String pieceJointe,final Mail mail){
        try {
            this.session = Session.getInstance(this.properties, 
                    new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail.getMailAddressSender(), mail.getPwd());}});
            this.message = new MimeMessage(this.session);
            InternetAddress	recipient   = new InternetAddress(mail.getMailAddressRecipient());
            this.message.setRecipient(Message.RecipientType.TO, recipient);
            this.message.setSubject(mail.getMailSubject());
 
            /**
             * Partie 1: Le texte
             */
            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(mail.getMailObject());
  // On regroupe les deux dans le message
            MimeMultipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
            /**
             * Le fichier joint
             */
          if (pieceJointe!=""){
            MimeBodyPart mbp2 = new MimeBodyPart();
            DataSource source = new FileDataSource(pieceJointe);
           mbp2.setDataHandler(new DataHandler(source));
           mbp2.setFileName(pieceJointe);
            mp.addBodyPart(mbp2);
           }
          
         
            this.message.setContent(mp);
        } catch (MessagingException ex) {
            Logger.getLogger(MailConstruction.class.getName()).log(Level.SEVERE, null, ex);
        } 
     
    }
    
    public void SendMessage() throws MessagingException {
    
        Transport.send(this.message);
        
    }
       
    
    
    
}
