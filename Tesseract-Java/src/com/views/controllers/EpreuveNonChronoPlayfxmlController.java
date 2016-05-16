/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.models.entities.Chapitre;
import com.models.entities.Cours;
import com.models.entities.EpreuveEntrainement;
import com.models.entities.EpreuveFinal;
import com.models.entities.EpreuveObjectif;
import com.models.entities.HistoriqueQuestion;
import com.models.entities.Objectif;
import com.models.entities.Question;
import com.models.entities.Reponse;
import com.models.entities.SessionEpreuve;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class EpreuveNonChronoPlayfxmlController implements Initializable {
private Cours cours;
  @FXML
  private AnchorPane pane1;
   @FXML
    private Label numeroQuestionLabe;
    @FXML
    private RadioButton prop2RadioButton;
     @FXML
    private ImageView photoImageView;
       @FXML
    private ImageView imgbutton;
        @FXML
    private Button  validerButton;
          @FXML
    private RadioButton prop3RadioButton;
       @FXML
    private ToggleGroup prop;
        @FXML
    private Label numeroQuestionLabel;
      public Chapitre chapitre; 
        @FXML
    private RadioButton prop1RadioButton;
    private int index=0;
    private int numeroPropositionCorrecte;
     private List<Question> listeQuestion;
    private List<Reponse> listeReponse;
    private List<HistoriqueQuestion> listeRep;
     private int nombreQuestionCorrecte=0;
     private int rep;
     
    private int id_question;
        int idques;
      String typeDiffc;  
         @FXML
    private Label questionLabel;
           Question q;
   String type;
private List<EpreuveEntrainement> epreuveEntrainement;
private EpreuveObjectif epreuveObjectif;
@FXML
private Label tep;
 public void setEpreuveEntrainement(List<EpreuveEntrainement> epreuveEntrainement) {
        this.epreuveEntrainement = epreuveEntrainement;
          try {
          if(index==0){
          
                ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
          for(int i=0;i<epreuveEntrainement.size();i++){   
             if (epreuveEntrainement.get(i).getDifficulte().equals(typeDiffc)){ 
                listeQuestion=serviceApprenant.findQuestionByIdEpreuve(epreuveEntrainement.get(i).getId());}
          }
}
      if(index<listeQuestion.size())//1 2
        {
            System.out.println("index:"+index);
            q=listeQuestion.get(index);
          
            setId_question(listeQuestion.get(index).getId());

        }
      else{
       
      }  } catch (SQLException ex) {
                Logger.getLogger(EpreuvePlayFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
 public void setEpreuveObjectif(EpreuveObjectif epreuveObjectif) {
        this.epreuveObjectif = epreuveObjectif;
           if(index==0){
            try {
                ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
                listeQuestion=serviceApprenant.findQuestionByIdEpreuve(epreuveObjectif.getId());
            } catch (SQLException ex) {
                Logger.getLogger(EpreuveNonChronoPlayfxmlController.class.getName()).log(Level.SEVERE, null, ex);
            }
}
      if(index<listeQuestion.size())//1 2
        {
            System.out.println("index:"+index);
            q=listeQuestion.get(index);
          
            setId_question(listeQuestion.get(index).getId());

        }
      else{
       
      } 
    }

    public List<EpreuveEntrainement> getEpreuveEntrainement() {
        return epreuveEntrainement;
    }

    public EpreuveObjectif getEpreuveObjectif() {
        return epreuveObjectif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }

  

    public int getId_question() {
        return id_question;
    }
      public Cours getCours() {
        return cours;
    }
      public void setId_question(int id_question) {
         try {
        this.id_question = id_question;
         ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        listeReponse =serviceApprenant.findReponseByIdQuestion(id_question);
          numeroQuestionLabel.setText("Question N°"+(index+1)+"/"+listeQuestion.size());
         tep.setText("Epreuve "+type+" :");
         questionLabel.setText(listeQuestion.get(index).getQuestion());
          prop1RadioButton.setText(listeReponse.get(0).getReponse());
        prop2RadioButton.setText(listeReponse.get(1).getReponse());
        prop3RadioButton.setText(listeReponse.get(2).getReponse()); 
          if(listeReponse.get(0).getEtat().equals("true")) numeroPropositionCorrecte=1;
        else if(listeReponse.get(0).getEtat().equals("true"))numeroPropositionCorrecte=2;
        else numeroPropositionCorrecte=3;
          if(CurrentUser.getId()!=0)
                    {
                      int correct=0;
                       if(rep==numeroPropositionCorrecte)
                        {
                            correct=1;
                            nombreQuestionCorrecte++;  
                        }
                        if(index<listeQuestion.size())
                        {
                         HistoriqueQuestion hq=new HistoriqueQuestion();
                         hq.setId_his_question(index);
                         hq.setId_his_rep(rep);
                         hq.setCorrect(correct);
                         listeRep.add(hq);
                        }
                      
                       
                          
                                 
                        }
          
          } catch (SQLException ex) {
        Logger.getLogger(EpreuvePlayFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
      }
         public String getTypeDiffc() {
        return typeDiffc;
    }

    public void setTypeDiffc(String typeDiffc) {
        this.typeDiffc = typeDiffc;
    }
       public void setCours(Cours cours) {
    try {
        this.cours = cours;
        if (type.equals("Objectifs")){
       ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        Objectif objectif =serviceApprenant.searchOBJByChapitre(chapitre.getNumero(),chapitre.getId());
         List<EpreuveObjectif> eos=serviceApprenant.searchEpreuveOBJByObj(objectif.getId());
         for(int i=0;i<eos.size();i++){
          if (eos.get(i).getDifficulte().equals(typeDiffc)){
              setEpreuveObjectif(eos.get(i));
          }   
         }
           
        }
        else if (type.equals("Entrainement")){
          ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
         List<EpreuveEntrainement> entrainement=serviceApprenant.searchEpreuveEntrainementByCours(cours.getIdCours());
            setEpreuveEntrainement(entrainement);
        }
    } catch (SQLException ex) {
        Logger.getLogger(EpreuvePlayFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }

   
       
        
           
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listeReponse = new ArrayList<Reponse>();
    listeRep=new ArrayList<HistoriqueQuestion>();
    }
     @FXML
   void validerAction(ActionEvent event) throws IOException {  
     rep =0;
      if(prop1RadioButton.isSelected()) rep=1;
            else if(prop2RadioButton.isSelected())rep=2;
            else if(prop3RadioButton.isSelected()) rep=3;
         if(CurrentUser.getId()!=0)
                    {
                     int correct=0;
                       if(rep==numeroPropositionCorrecte)
                        {
                            correct=1;
                            nombreQuestionCorrecte++;  
                        }
                      if(index<listeQuestion.size())
                        {
                          HistoriqueQuestion hq=new HistoriqueQuestion();
                         hq.setId_his_question(index);
                         hq.setId_his_rep(rep);
                         hq.setCorrect(correct);
                         listeRep.add(hq);  
                        }
                      if (index==listeQuestion.size()-2){
                          imgbutton.setImage(new Image("/com/images/va.jpg"));
                          validerButton.setGraphic(imgbutton);
                        }
                      if(index==listeQuestion.size()-1)
                        {  try {
                              HistoriqueQuestion hq=new HistoriqueQuestion();
                              hq.setId_his_question(index);
                              hq.setId_his_rep(rep);
                              hq.setCorrect(correct);
                              listeRep.add(hq);
                              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ResultatEpreuveFinalFXML.fxml"));
                              
                              AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                              
                              ResultatEpreuveFinalFXMLController controller = fxmlLoader.<ResultatEpreuveFinalFXMLController>getController();
                              controller.setCours(cours);
                              controller.setType(type);
                             
                              controller.setListeQuestion(listeQuestion);
                            
                              controller.setListeRep(listeRep);
                              
                              pane1.getChildren().setAll(anchorPane);
                          } catch (IOException ex) {
                              Logger.getLogger(EpreuvePlayFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                          }
                        }
                       
                     }
          index++;
           prop1RadioButton.setSelected(false);
                    prop2RadioButton.setSelected(false);
                    prop3RadioButton.setSelected(false);  
          setEpreuveEntrainement(epreuveEntrainement);
   }
   
}
