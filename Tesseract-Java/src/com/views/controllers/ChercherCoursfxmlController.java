/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.jfoenix.controls.JFXCheckBox;
import com.models.entities.Cours;
import com.models.entities.Formateur;
import com.models.entities.Matiere;
import com.models.entities.Organisation;
import com.models.enums.Difficulte;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class ChercherCoursfxmlController implements Initializable {
   @FXML
    private ComboBox searchField;
    @FXML
    private JFXCheckBox langEng;
    @FXML
    private JFXCheckBox langFr;
    @FXML
    private JFXCheckBox langArb;
    @FXML
    private JFXCheckBox lvlBeg;
    @FXML
    private JFXCheckBox lvlMed;
    @FXML
    private JFXCheckBox lvlHard;
        @FXML
    private Pane header;
    @FXML
    private TilePane body;
    @FXML
    private Pane paneCritere;
     @FXML
    private AnchorPane pane1;
     @FXML
    private Button searchCourse;
     Formateur fC;
     @FXML  
private ImageView img;
        List<Cours> listcritCours = new ArrayList<>();
       private ImageView searchIcon;
   @FXML
    private Pane pimg; 
  private String type; 
   private ObservableList<String> NameData;  
  List<Cours> listCours = new ArrayList<>();
    private ImageView  imgcours;
        
        private ImageView  imgc;
        private ImageView  imgvue;
         
    private boolean critere1 = false;
    private boolean critere2 = false;
  
    private boolean critere6 = false;
    private boolean critere5 = false;
    private boolean critere4 = false;
    private boolean critere3 = false;
    Organisation oC;
  @FXML
    private Button   btretour;
    public Formateur getfC() {
        return fC;
    }

    public void setfC(Formateur fC) {
        this.fC = fC;
    }

    public String getType() {
        return type;
    }

    public void setoC(Organisation oC) {
        this.oC = oC;
    }

    public Organisation getoC() {
        return oC;
    }

    public void setType(String type) {
        this.type = type;
     ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();  
    try { 
  
    if(type.equals("m")){
            
    
    List<Matiere> list=serviceApprenant.getAllMatiere();
      NameData = FXCollections.observableArrayList();
  for(int i=0;i<list.size();i++){
   NameData.add(list.get(i).getNomMatiere());
  } 
  searchField.getItems().addAll(NameData);
  
    }  
    if(type.equals("f")){
    
    List<Formateur> list=serviceApprenant.getAllFormateurs();
      NameData = FXCollections.observableArrayList();
  for(int i=0;i<list.size();i++){
   NameData.add(list.get(i).getNom());
  } 
  searchField.getItems().addAll(NameData);    
    } 
     if(type.equals("o")){
  
    List<Organisation> list=serviceApprenant.getAllOrganisation();
      NameData = FXCollections.observableArrayList();
  for(int i=0;i<list.size();i++){
   NameData.add(list.get(i).getNom());
  } 
  searchField.getItems().addAll(NameData);      
    } 
   if(type.equals("c")){
   
    List<Cours> list=serviceApprenant.findAllCours(CurrentUser.getId());
      NameData = FXCollections.observableArrayList();
  for(int i=0;i<list.size();i++){
   NameData.add(list.get(i).getNomCours());
  } 
  searchField.getItems().addAll(NameData);      
    }
     if(type.equals("fC")){
          listCours.clear();
        
      btretour.setVisible(true);
      searchField.getEditor().setText(fC.getNom());
      listCours=serviceApprenant.chercherCoursByNameFormateur(fC.getNom(),CurrentUser.getId());
         if (listCours.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Pas de cours  ", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
 searchField.getEditor().setText("");
        if (alert.getResult() == ButtonType.OK) {
      listCours=serviceApprenant.findAllCours(CurrentUser.getId());  
       generatepane(listCours);
        }}
         else{
           generatepane(listCours);   
         }      
      }
       if(type.equals("oC")){
          listCours.clear();
     btretour.setVisible(true);   
      
      searchField.getEditor().setText(oC.getNom());
      listCours=serviceApprenant.chercherCoursByNameOrganisme(oC.getNom(),CurrentUser.getId());
         if (listCours.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Pas de cours  ", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
 searchField.getEditor().setText("");
        if (alert.getResult() == ButtonType.OK) {
      listCours=serviceApprenant.findAllCours(CurrentUser.getId());  
       generatepane(listCours);
        }}
         else{
           generatepane(listCours);   
         }      
      }
     
    } catch (SQLException ex) {
                Logger.getLogger(ChercherCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           searchIcon = new ImageView(new Image("/com/images/search-formateur.png"));
           
           searchCourse.setGraphic(searchIcon);
           ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
           listCours=serviceApprenant.findAllCours(CurrentUser.getId());   
           generatepane(listCours);
       } catch (SQLException ex) {
           Logger.getLogger(ChercherCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }    
   @FXML
    private void searchCourse(ActionEvent event) {
     try {      
       if(type.equals("m")){
        listCours.clear();
        
      ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
      listCours=serviceApprenant.chercherCoursByNameMatiere(searchField.getEditor().getText().toString(),CurrentUser.getId());
         if (listCours.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Nom matiere n'existe pas ", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
         searchField.getEditor().setText("");
        if (alert.getResult() == ButtonType.OK) {
      listCours=serviceApprenant.findAllCours(CurrentUser.getId());  
       generatepane(listCours);
        }}
         else{
           generatepane(listCours);   
         }  
       } 
      if(type.equals("f")){
          listCours.clear();
        
      ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
      listCours=serviceApprenant.chercherCoursByNameFormateur(searchField.getEditor().getText().toString(),CurrentUser.getId());
         if (listCours.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Nom Formateur n'existe pas ", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
 searchField.getEditor().setText("");
        if (alert.getResult() == ButtonType.OK) {
      listCours=serviceApprenant.findAllCours(CurrentUser.getId());  
       generatepane(listCours);
        }}
         else{
           generatepane(listCours);   
         }      
      }  
      if(type.equals("o")){
            listCours.clear();
        
      ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
      listCours=serviceApprenant.chercherCoursByNameOrganisme(searchField.getEditor().getText().toString(),CurrentUser.getId());
         if (listCours.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Nom Organisation n'existe pas ", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
 searchField.getEditor().setText("");
        if (alert.getResult() == ButtonType.OK) {
      listCours=serviceApprenant.findAllCours(CurrentUser.getId());  
       generatepane(listCours);
        }}
         else{
           generatepane(listCours);   
         }   
      }
       if(type.equals("c")){
             listCours.clear();
        
      ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
     
    Cours c=serviceApprenant.chercherCoursByNameCours(searchField.getEditor().getText().toString(),CurrentUser.getId());
    listCours.add(c);
         if (listCours.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Nom Cours n'existe pas ", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
 searchField.getEditor().setText("");
        if (alert.getResult() == ButtonType.OK) {
      listCours=serviceApprenant.findAllCours(CurrentUser.getId());  
       generatepane(listCours);
        }}
         else{
           generatepane(listCours);   
         }   
       }
       
       
       } catch (SQLException ex) {
                Logger.getLogger(ChercherCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }  
     @FXML
    private void clickEng(ActionEvent event) {
         if (langEng.isSelected()) {
            critere1 = true;
        } else {
            critere1 = false;
        }
        searchByCrit();   
    }
      @FXML
    private void clickFren(ActionEvent event) {
          if (langFr.isSelected()) {
            critere2 = true;
        } else {
            critere2 = false;
        }
        searchByCrit();   
    }
     @FXML
    private void clickArabic(ActionEvent event) {
        if (langArb.isSelected()) {
            critere3 = true;
        } else {
            critere3 = false;
        }
        searchByCrit();   
    }
        @FXML
    private void clickBeginner(ActionEvent event) {
        if (lvlBeg.isSelected()) {
            critere4 = true;
        } else {
            critere4 = false;
        }
        searchByCrit();   
    }
       @FXML
    private void clickMeduim(ActionEvent event) {
        if (lvlMed.isSelected()) {
            critere5 = true;
        } else {
            critere5 = false;
        }
        searchByCrit();   
    }
      @FXML
    private void clickHard(ActionEvent event) {
       if (lvlHard.isSelected()) {
            critere6 = true;
        } else {
            critere6 = false;
        }
        searchByCrit();   
    }
     @FXML
      void retourAction(ActionEvent event) throws IOException {
       if(type.equals("fC")){
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
        if(type.equals("oC")){
            try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherOrgFormfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                ChercherOrgFormfxmlController controller = fxmlLoader.<ChercherOrgFormfxmlController>getController();
                 controller.setType("o");
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
      }

    private void generatepane(List<Cours> listCours) {
    body.getChildren().clear();
      Button[] buttons = new Button[100];
     Label[] nameCours= new Label[100];
    Label[] nameMatiere = new Label[100];
    Label[] nameFormateur = new Label[100];
    Label[] nameDiff = new Label[100];
    Label[] nameNombvue = new Label[100];
     ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
    
   try {   
  for(int i=0;i<listCours.size();i++){
 String nMatiere=serviceApprenant.getNameMatiere(listCours.get(i));
 String nFormateur=serviceApprenant.getNameFormateur(listCours.get(i));
 int nbvue=serviceApprenant.afficherNombreVue(listCours.get(i));
   
 imgcours = new ImageView(new Image(new File(listCours.get(i).getAffiche()).toURI().toString()));
 imgcours.setFitHeight(121);
 imgcours.setFitWidth(209);
 imgcours.setLayoutX(15);
 imgcours.setLayoutY(0);
imgvue = new ImageView(new Image("/com/images/view-cours.png"));
imgvue.setFitWidth(27);
imgvue.setFitHeight(31);
imgvue.setLayoutX(40);
imgvue.setLayoutY(241);
imgc = new ImageView(new Image("/com/images/ain.png"));
imgc.setFitHeight(45);
imgc.setFitWidth(155);
nameCours[i]=  new Label("    Cours: "+listCours.get(i).getNomCours());
nameCours[i].setLayoutX(40);
nameCours[i].setLayoutY(131);
nameCours[i].setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
nameMatiere[i]=  new Label("    Matiere: "+nMatiere);
nameMatiere[i].setLayoutX(40);
nameMatiere[i].setLayoutY(157);
nameMatiere[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
nameFormateur[i]=  new Label("    Formateur: "+nFormateur);
nameFormateur[i].setLayoutX(40);

nameFormateur[i].setLayoutY(186);
nameFormateur[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
nameDiff[i]=  new Label("    Difficulté: "+listCours.get(i).getDifficulte());
nameDiff[i].setLayoutX(40);
nameDiff[i].setLayoutY(215);
nameDiff[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
nameNombvue[i]=  new Label("    nbVue: "+nbvue);
nameNombvue[i].setLayoutX(80);
nameNombvue[i].setLayoutY(246);
nameNombvue[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
buttons[i] = new Button(""+i,imgc);
buttons[i].setFont(new Font(0.1));
buttons[i].setLayoutX(52);
buttons[i].setLayoutY(282);
 VBox vboxMeals = new VBox(10);
  vboxMeals.setSpacing(1000);
        Pane p=new Pane();
       p.setPrefSize(250,338);
            p.setStyle("-fx-background-color: white;"
                + "  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
            
    p.getChildren().addAll(imgcours,imgvue,nameDiff[i],nameCours[i],nameMatiere[i],nameNombvue[i],nameFormateur[i],buttons[i]);
    vboxMeals.getChildren().addAll(p);
     buttons[i].setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) { 
            Button b = (Button) event.getSource();
            try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/InscrireCoursfxml.fxml")); 
                 
               AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
              
                InscrireCoursfxmlController controller = fxmlLoader.<InscrireCoursfxmlController>getController();
                 controller.setType(type);
                 controller.setCours(listCours.get(Integer.parseInt(b.getText())));
               
              
               
                 pane1.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            
            
            }});
  body.getChildren().add(vboxMeals);   
  }   
    } catch (SQLException ex) {
              Logger.getLogger(ChercherCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
          }   
    }

    private void searchByCrit() {
       try {
           listCours.clear();
           ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();  
           listCours=serviceApprenant.findAllCours(CurrentUser.getId());
             if (critere1 == true) {
            for (Cours cours : listCours) {
                if (cours.getLanguage().equals("English")) {
                    listcritCours.add(cours);
                }
            }
            }
           
             if (critere2 == true) {
            for (Cours cours : listCours) {
                if (cours.getLanguage().equals("French")) {
                    listcritCours.add(cours);
                }
            }
        }
        if (critere3 == true) {
            for (Cours cours : listCours) {
                if (cours.getLanguage().equals("Arabic")) {
                    listcritCours.add(cours);
                }
            }
        }
        if (critere4 == true) {
            for (Cours cours : listCours) {
                if (cours.getDifficulte() == Difficulte.FACILE) {
                    listcritCours.add(cours);
                }
            }
        }
        if (critere5 == true) {
            for (Cours cours : listCours) {
                if (cours.getDifficulte() == Difficulte.NORMALE) {
                    listcritCours.add(cours);
                }
            }
        }
        if (critere6 == true) {
            for (Cours cours : listCours) {
                if (cours.getDifficulte() == Difficulte.DIFFICILE) {
                    listcritCours.add(cours);
                }
            }
        }
          if (listcritCours.isEmpty()){
          generatepane(listCours);  
        }
        else{ generatepane(listcritCours);  }
          
         listcritCours.clear();  
           
       } catch (SQLException ex) {
           Logger.getLogger(ChercherCoursfxmlController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

}
