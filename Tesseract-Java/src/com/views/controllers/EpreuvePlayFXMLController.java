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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class EpreuvePlayFXMLController implements Initializable {
private EpreuveFinal epreuveFinal;
private SessionEpreuve session;
private Cours cours;
  @FXML
  private AnchorPane pane1;
   @FXML
  private Pane pvol;
 @FXML
    private Label numeroQuestionLabel;
@FXML
    private Label tempLabel;
@FXML
    private Label tep;

    @FXML
    private RadioButton prop2RadioButton;

    @FXML
    private ImageView photoImageView;
     @FXML
    private ImageView imgvol;
     @FXML
    private ImageView imgbutton;
    @FXML
    private Button  validerButton;
    @FXML
    private RadioButton prop3RadioButton;
  @FXML
    private ToggleGroup prop;
    @FXML
    private RadioButton prop1RadioButton;
 @FXML
    private ProgressBar tempProgressBar;
    @FXML
    private Slider volumeSlider;
    private MediaPlayer son;
     private double volume;
      private int index=0;
       private int numeroSerie;
    private int numeroPropositionCorrecte;
    private int nombreQuestionCorrecte=0;
      private List<Question> listeQuestion;
    private List<Reponse> listeReponse;
    private List<HistoriqueQuestion> listeRep;
    private List<EpreuveEntrainement> entrainements;
    private int rep;
    private int id_question;
    private Timer timer;
    int idques;
         String typeDiffc;  
    private int tmpRestant=20;
    public Chapitre chapitre; 
 @FXML
    private Label questionLabel;
   Question q;
   String type;
private EpreuveEntrainement epreuveEntrainement;
private EpreuveObjectif epreuveObjectif;

    public void setEpreuveEntrainement(EpreuveEntrainement epreuveEntrainement) {
        this.epreuveEntrainement = epreuveEntrainement;
          try {
          if(index==0){
          
                ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
                listeQuestion=serviceApprenant.findQuestionByIdEpreuve(epreuveEntrainement.getId());
           
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

    public EpreuveEntrainement getEpreuveEntrainement() {
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
       public String getTypeDiffc() {
        return typeDiffc;
    }

    public void setTypeDiffc(String typeDiffc) {
        this.typeDiffc = typeDiffc;
    }

    public int getId_question() {
        return id_question;
    }
   
    
  public EpreuveFinal getEpreuveFinal() {
        return epreuveFinal;
    }

    public SessionEpreuve getSession() {
        return session;
    }

    public Cours getCours() {
        return cours;
    }

    public void setEpreuveFinal(EpreuveFinal epreuveFinal) {
    try {
        this.epreuveFinal = epreuveFinal;
         if(index==0){
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        listeQuestion=serviceApprenant.findQuestionByIdEpreuve(epreuveFinal.getId());}
      if(index<listeQuestion.size())//1 2
        {
            System.out.println("index:"+index);
            q=listeQuestion.get(index);
          
            setId_question(listeQuestion.get(index).getId());

        }
      else{
       
      } 
    } catch (SQLException ex) {
        Logger.getLogger(EpreuvePlayFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
        
    }
 public void setId_question(int id_question) {
    try {
        this.id_question = id_question;
        
        volume=volumeSlider.getValue()/100.0;
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
        File f = new File("src/son/son_question/1.mp3");
         String str = "file:/"+f.getAbsolutePath();
        str=str.replace("\\", "/"); 
         Media a;
         a = new Media(str);
          son = new MediaPlayer(a);
         son.play();
        son.setVolume(volume); 
         son.setOnEndOfMedia(new Runnable() {
        public void run() {
         tempProgressBar.setProgress(1);
         timer = new Timer();
          timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               Platform.runLater(new Runnable() {
                 public void run() {
               Double x=tempProgressBar.progressProperty().getValue()-0.05;
               tmpRestant--;
                  tempProgressBar.setProgress(x);
                  tempLabel.setText(tmpRestant+" s");
                     if(x<=0.5)
                    tempProgressBar.setBlendMode(BlendMode.GREEN);
                      if(x<=0.03)
                {rep =0;
                if(prop1RadioButton.isSelected()) rep=1;
                    else if(prop2RadioButton.isSelected())rep=2;
                    else if(prop3RadioButton.isSelected()) rep=3;
                 if(CurrentUser.getId()!=0)
                    {
                      ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
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
                        {
                          try {
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
                               controller.setEp(session);
                               controller.setListeQuestion(listeQuestion);
                              
                              controller.setListeRep(listeRep);
                              
                              
                              pane1.getChildren().setAll(anchorPane);
                          } catch (IOException ex) {
                              Logger.getLogger(EpreuvePlayFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                          }
                                 
                        }
                        
                        
                        
                        
                    }
                
                
    tempProgressBar.setBlendMode(BlendMode.SRC_OVER);
                    tempProgressBar.setProgress(-1);
                    timer.cancel();
                    index++;
                     
                    tmpRestant=20;
                    tempLabel.setText("20 s");
                    prop1RadioButton.setSelected(false);
                    prop2RadioButton.setSelected(false);
                    prop3RadioButton.setSelected(false);    
                } 
                     
             }
                });

            }
            }, 0, 1000);  
            //}, 0, 100);
        }
        });
        
    } catch (SQLException ex) {
        Logger.getLogger(EpreuvePlayFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
    }
    public void setSession(SessionEpreuve session) {
        this.session = session;
    }

    public void setCours(Cours cours) {
    try {
        this.cours = cours;
        if (type.equals("Final")){
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        EpreuveFinal epreuveFinal=serviceApprenant.searchEpreuveFinalByCours(cours.getIdCours());
         SessionEpreuve epreuve=new SessionEpreuve();
        epreuve.setId_epreuve(epreuveFinal.getId());
        epreuve.setId_utilisateur(CurrentUser.getId());
        setSession(epreuve);
        setEpreuveFinal(epreuveFinal);
        }
        else if (type.equals("Entrainement")){
          ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
          List<EpreuveEntrainement> epreuveEntrai=serviceApprenant.searchEpreuveEntrainementByCours(cours.getIdCours());
             for(int i=0;i<epreuveEntrai.size();i++){   
             if (epreuveEntrai.get(i).getDifficulte().equals(typeDiffc)){ 
               setEpreuveEntrainement(epreuveEntrai.get(i));}
          }
}   if (type.equals("Objectifs")){
       ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
        Objectif objectif =serviceApprenant.searchOBJByChapitre(chapitre.getNumero(),chapitre.getId());
         List<EpreuveObjectif> eos=serviceApprenant.searchEpreuveOBJByObj(objectif.getId());
         for(int i=0;i<eos.size();i++){
          if (eos.get(i).getDifficulte().equals(typeDiffc)){
              setEpreuveObjectif(eos.get(i));
          }   
         }
           
        }
            
        
    } catch (SQLException ex) {
        Logger.getLogger(EpreuvePlayFXMLController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      volumeSlider.valueProperty().addListener(new ChangeListener(){
            @Override public void changed(ObservableValue o, Object oldVal, Object newVal){
                double x1Val = (Double) newVal;

                son.setVolume(new Double(x1Val/100.0));
             if (x1Val==0.0){
              
              imgvol.setImage(new Image("/com/images/vol1.png"));
           
              pvol.getChildren().add(imgvol);
             }
              else{
               imgvol.setImage(new Image("/com/images/vol2.png"));
           
              pvol.getChildren().add(imgvol);  
             }
            } 
        });
         
         
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
                      ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
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
                              controller.setEp(session);
                              controller.setListeQuestion(listeQuestion);
                            
                              controller.setListeRep(listeRep);
                              
                              pane1.getChildren().setAll(anchorPane);
                          } catch (IOException ex) {
                              Logger.getLogger(EpreuvePlayFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                          }
                        } }
         son.stop();
        tempProgressBar.setBlendMode(BlendMode.SRC_OVER);
        tempProgressBar.setProgress(-1);
          if(tmpRestant!=20)
            timer.cancel();
        index++;
         tmpRestant=20;
        tempLabel.setText("20 s");
         prop1RadioButton.setSelected(false);
        prop2RadioButton.setSelected(false);
        prop3RadioButton.setSelected(false);
        
           setEpreuveFinal(epreuveFinal);
    }  
}
