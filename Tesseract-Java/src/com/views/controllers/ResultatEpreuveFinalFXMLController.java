/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Cours;
import com.models.entities.HistoriqueQuestion;
import com.models.entities.Question;
import com.models.entities.Reponse;
import com.models.entities.SessionEpreuve;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class ResultatEpreuveFinalFXMLController implements Initializable {
private List<HistoriqueQuestion> listeRep;
 @FXML
    private Label score;
  @FXML
    private Label lCours;
   @FXML
    private Label name;
    @FXML
    private ImageView img;
     @FXML
    private Button   corVideo;
       @FXML
    private Button   corrText;
         @FXML
    private AnchorPane pane1;
          @FXML
    private Pane p;
     private List<Question> listeQuestion;
    private List<Reponse> listeReponse;      
         private Cours cours;
         private SessionEpreuve ep;
private int nombRepCorrect;
String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

  public List<Question> getListeQuestion() {
        return listeQuestion;
    }

    public List<Reponse> getListeReponse() {
        return listeReponse;
    }

    public void setListeQuestion(List<Question> listeQuestion) {
        this.listeQuestion = listeQuestion;
    }

    public void setListeReponse(List<Reponse> listeReponse) {
        this.listeReponse = listeReponse;
    }
    public Cours getCours() {
        return cours;
    }

    public SessionEpreuve getEp() {
        return ep;
    }

    public void setEp(SessionEpreuve ep) {
        this.ep = ep;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }
         
    public List<HistoriqueQuestion> getListeRep() {
        return listeRep;
    }

    public void setListeRep(List<HistoriqueQuestion> listeRep) {
        this.listeRep = listeRep;
        if (type.equals("Final")){
         ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
         boolean verif,verif2;
        nombRepCorrect=0;
     name.setText(CurrentUser.getUtilisateur().getNom());
     lCours.setText(cours.getNomCours());
     for(int i=0;i<listeRep.size();i++){
      if (listeRep.get(i).getCorrect()==1){
       nombRepCorrect++;   
      }   
     }
     if(nombRepCorrect==listeRep.size()){
            try {
                score.setText(" Your Score : 100% ");
                  ep.setNote(100);
                verif=serviceApprenant.modifierBadgeCours(cours, CurrentUser.getId());
                verif2=serviceApprenant.modifierSessionEpreuve(ep);
            } catch (SQLException ex) {
                Logger.getLogger(ResultatEpreuveFinalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }
     else if (nombRepCorrect==(listeRep.size()/2)){
      
       try {
                score.setText(" Your Score : 50% "); 
                  ep.setNote(50);
                verif=serviceApprenant.modifierBadgeCours(cours, CurrentUser.getId());
                verif2=serviceApprenant.modifierSessionEpreuve(ep);
            } catch (SQLException ex) {
                Logger.getLogger(ResultatEpreuveFinalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
     }
      else if (nombRepCorrect > (listeRep.size()/2)){
        
        try {
             score.setText(" Your Score : 80% "); 
               ep.setNote(80);
                verif=serviceApprenant.modifierBadgeCours(cours, CurrentUser.getId());
                verif2=serviceApprenant.modifierSessionEpreuve(ep);
            } catch (SQLException ex) {
                Logger.getLogger(ResultatEpreuveFinalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
     }
       else if (nombRepCorrect==0){
      
         try {
              score.setText(" Your Score : 0% "); 
                 img= new ImageView(new Image("/com/images/sor.jpg"));
                 img.setFitHeight(400);
                  img.setFitWidth(1056);
                    p.getChildren().add(img);
//                verif=serviceApprenant.modifierBadgeCours(cours, CurrentUser.getId());
                      ep.setNote(0);
                verif2=serviceApprenant.modifierSessionEpreuve(ep);
            } catch (SQLException ex) {
                Logger.getLogger(ResultatEpreuveFinalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
     }
      else if (nombRepCorrect<(listeRep.size()/2)){
       try {
              score.setText(" Your Score : 30% "); 
                 img= new ImageView(new Image("/com/images/sor.jpg"));
                 img.setFitHeight(400);
                  img.setFitWidth(1056);
                    p.getChildren().add(img);
//                verif=serviceApprenant.modifierBadgeCours(cours, CurrentUser.getId());
                    ep.setNote(30);
                verif2=serviceApprenant.modifierSessionEpreuve(ep);
            } catch (SQLException ex) {
                Logger.getLogger(ResultatEpreuveFinalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
     }}
        else if (type.equals("Entrainement")){
           
      
        nombRepCorrect=0;
        
     name.setText(CurrentUser.getUtilisateur().getNom());
     lCours.setText(cours.getNomCours());
     for(int i=0;i<listeRep.size();i++){
      if (listeRep.get(i).getCorrect()==1){
       nombRepCorrect++;   
      }   
     }
     if(nombRepCorrect==listeRep.size()){
      
                score.setText(" Your Score : 100% ");
                
             
          
       
    }
     else if (nombRepCorrect==(listeRep.size()/2)){
      

                score.setText(" Your Score : 50% "); 
              
             
         
     }
      else if (nombRepCorrect > (listeRep.size()/2)){
        
       
             score.setText(" Your Score : 80% "); 
             
           
         
     }
       else if (nombRepCorrect==0){
      
    
              score.setText(" Your Score : 0% "); 
                 img= new ImageView(new Image("/com/images/sor.jpg"));
                 img.setFitHeight(400);
                  img.setFitWidth(1056);
                    p.getChildren().add(img);
//                verif=serviceApprenant.modifierBadgeCours(cours, CurrentUser.getId());
                  
           
    
     }
      else if (nombRepCorrect<(listeRep.size()/2)){
  
              score.setText(" Your Score : 30% "); 
                 img= new ImageView(new Image("/com/images/sor.jpg"));
                 img.setFitHeight(400);
                  img.setFitWidth(1056);
                    p.getChildren().add(img);

                    
    
     }  
        }
     else if (type.equals("Objectifs")){
           
      
        nombRepCorrect=0;
        
     name.setText(CurrentUser.getUtilisateur().getNom());
     lCours.setText(cours.getNomCours());
     for(int i=0;i<listeRep.size();i++){
      if (listeRep.get(i).getCorrect()==1){
       nombRepCorrect++;   
      }   
     }
     if(nombRepCorrect==listeRep.size()){
      
                score.setText(" Your Score : 100% ");
                
             
          
       
    }
     else if (nombRepCorrect==(listeRep.size()/2)){
      

                score.setText(" Your Score : 50% "); 
              
             
         
     }
      else if (nombRepCorrect > (listeRep.size()/2)){
        
       
             score.setText(" Your Score : 80% "); 
             
           
         
     }
       else if (nombRepCorrect==0){
      
    
              score.setText(" Your Score : 0% "); 
                 img= new ImageView(new Image("/com/images/sor.jpg"));
                 img.setFitHeight(400);
                  img.setFitWidth(1056);
                    p.getChildren().add(img);
//                verif=serviceApprenant.modifierBadgeCours(cours, CurrentUser.getId());
                  
           
    
     }
      else if (nombRepCorrect<(listeRep.size()/2)){
  
              score.setText(" Your Score : 30% "); 
                 img= new ImageView(new Image("/com/images/sor.jpg"));
                 img.setFitHeight(400);
                  img.setFitWidth(1056);
                    p.getChildren().add(img);

                    
    
     }  
        }       
    }

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   @FXML
      void corVideoAction(ActionEvent event) throws IOException {
        
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/SolutionVideofxml.fxml"));
                              
      AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                              
      SolutionVideofxmlController controller = fxmlLoader.<SolutionVideofxmlController>getController();
 
          controller.setCours(cours);
         controller.setType(type);
       controller.setListeQuestion(listeQuestion);
        controller.setListeRep(listeRep);
     
        pane1.getChildren().setAll(anchorPane);   
      } 
    @FXML
      void retourAction(ActionEvent event) throws IOException {
      setMain(loadNode("/com/fxml/ApprenantAcceuil.fxml"));  
    }  
       private AnchorPane loadNode(String addresse) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(addresse));
      

        return anchorPane;
    }
     public void setMain(Node node) {
 
        pane1.getChildren().setAll(node);
     }  
      @FXML
      void corImageAction(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/SolutionImagefxml.fxml"));
                              
      AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                              
      SolutionImagefxmlController controller = fxmlLoader.<SolutionImagefxmlController>getController();
     controller.setType(type);
     controller.setListeQuestion(listeQuestion);
    
        controller.setListeRep(listeRep);
       
        pane1.getChildren().setAll(anchorPane);    
      } 
}
