/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.models.entities.Formateur;
import com.models.entities.Mail;
import com.models.entities.Organisation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import outils.MailConstruction;
import outils.Verif;

/**
 *
 * @author Sameh
 */
public class MailFXMLController implements Initializable{
   @FXML
    private JFXTextField mailRecp;
     @FXML
    private JFXPasswordField Pass;
      @FXML
    private JFXTextField mailDest;
       @FXML
    private JFXTextField sujet;
    @FXML
    private JFXTextField pj; 
     @FXML
    private TextArea contenue; 
     @FXML
    private Button btPJ;
        @FXML
    private Button  btEnvoyer;
        @FXML
    private Button   btretour;
        @FXML
    private AnchorPane pane1;
     String type;
     Formateur f;
     Organisation o;
        Mail mail = new Mail();
          URL url;

    public void setType(String type) {
        this.type = type;
        if (type.equals("f")){
    mailDest.setText(f.getMail());
    
   } 
        if (type.equals("fc")){
    mailDest.setText(f.getMail());
    
   }
     if (type.equals("o")){
    mailDest.setText(o.geteMail());
    
   }
    }

    public void setF(Formateur f) {
        this.f = f;
    }

    public void setO(Organisation o) {
        this.o = o;
    }

    public String getType() {
        return type;
    }

    public Formateur getF() {
        return f;
    }

    public Organisation getO() {
        return o;
    }
          
          
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    mailRecp.setText(CurrentUser.getUtilisateur().getMail());
    
    }
      @FXML
    public void SendMailActionPerformed(ActionEvent event) throws IOException{
    if(
          Verif.isValidEMail(mailDest.getText())
          && Pass.getText()!=""
          && sujet.getText()!=""
          && contenue.getText()!=""
         ){
        mail.setMailAddressRecipient("haikelmegrahi@gmail.com");
        mail.setPwd(Pass.getText().trim());
        mail.setMailAddressSender(mailRecp.getText());
        mail.setMailSubject(sujet.getText());
        String[] lines = contenue.getText().split("\\n");
        String msg="";
        for(String s:lines){
            msg = msg+s;
        }
        mail.setMailObject(msg); 
        MailConstruction mc = new MailConstruction();
        mc.getMailProperties();
         
              
        mc.getMailMessage( pj.getText(),mail);
        //DERNIERE ETAPE
        try{ 
            mc.SendMessage();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Votre message a été envoyé avec succès !", ButtonType.FINISH);
                            alert.show();
            
        }catch (MessagingException ex) {
                //Logger.getLogger(MailConstruction.class.getName()).log(Level.SEVERE, null, ex);
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Une exception a été déclanché lors de l'envoi du message !\n Votre email et/ou mot de passe sont-ils corrects ?", ButtonType.FINISH);
                            alert.show();
         mailDest.setText("");
          Pass.setText("");
           mailRecp.setText("");
            contenue.setText("");
             pj.setText("");
              sujet.setText("");
         }
        
       }else{
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Champs invalides ! ", ButtonType.FINISH);
                            alert.show();
                 
       }
           
    } 

   
    
    @FXML
    private void chercherPiece(ActionEvent event) {
     FileChooser fc = new FileChooser();
     fc.setTitle("researching");
      File file = fc.showOpenDialog(null);
         
        pj.setText(file.getAbsolutePath());
    }
     private AnchorPane loadNode(String addresse) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(addresse));
      

        return anchorPane;
    }
     public void setMain(Node node) {
 
        pane1.getChildren().setAll(node);

    }
       @FXML
      void retourAction(ActionEvent event) throws IOException {
            if (type.equals("f")){
      try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherOrgFormfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                ChercherOrgFormfxmlController controller = fxmlLoader.<ChercherOrgFormfxmlController>getController();
                 controller.setType("f");
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    
   } 
  else if (type.equals("o")){
      try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherOrgFormfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                ChercherOrgFormfxmlController controller = fxmlLoader.<ChercherOrgFormfxmlController>getController();
                 controller.setType("o");
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    
   }  else if (type.equals("fc")){
       try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/CoordoFormateurfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                CoordoFormateurfxmlController controller = fxmlLoader.<CoordoFormateurfxmlController>getController();
                 controller.setName(f.getNom());
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    
   }    
     else{  setMain(loadNode("/com/fxml/ApprenantAcceuil.fxml"));  }
    }
}
