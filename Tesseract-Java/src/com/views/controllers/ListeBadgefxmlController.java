/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.ServiceApprenant;
import com.controllers.ServiceApprenantsIpl;
import com.itextpdf.text.DocumentException;
import com.models.entities.Cours;
import com.models.entities.Evenement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.outils.PdfBadge;
import com.outils.PdfListNotification;

/**
 * FXML Controller class
 *
 * @author Sameh
 */
public class ListeBadgefxmlController implements Initializable {
 @FXML
    public TilePane  body;
     @FXML
    public  AnchorPane pane1;
     private ImageView imgfo; 
    private ImageView imgfobut;
     List<Cours> listC = new ArrayList<>(); 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
        ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
       List<Cours> list=serviceApprenant.getListeMesCoursTerminer(CurrentUser.getId());
       
             generateContainersCourses(list); 

//        quizCh = new ImageView(new Image("/com/images/com.png"));
    } catch (SQLException ex) {
        Logger.getLogger(ListCoursTerminerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
   
       
    }
    }    

    private void generateContainersCourses(List<Cours> list) {
      Button[] buttons = new Button[100];
         Label[] Name = new Label[100];
    Label[] Namecours = new Label[100];
     Label[] NameMatiere = new Label[100];
       ServiceApprenant serviceApprenant=new ServiceApprenantsIpl();
     for(int i=0;i<list.size();i++){
          try {
              String NameMa=serviceApprenant.getNameMatiere(list.get(i));
              imgfo=new ImageView(new Image("/com/images/badge.jpg"));
              imgfo.setFitHeight(125);
              imgfo.setFitWidth(180);
              imgfo.setLayoutX(10);
              imgfo.setLayoutY(14);
              imgfobut=new ImageView(new Image("/com/images/pdf.png"));
              imgfobut.setFitHeight(46);
              imgfobut.setFitWidth(57);
              imgfobut.setLayoutX(0);
              imgfobut.setLayoutY(0);
              Name[i]=new Label(""+CurrentUser.getUtilisateur().getNom()+"&"+CurrentUser.getUtilisateur().getPrenom());
              Name[i].setLayoutX(45);
              Name[i].setLayoutY(153);
              Name[i].setStyle("\n"
                      + "    -fx-font-family: Impact;\n"
                      + "    -fx-font-style: bold;\n"
                      + "    -fx-font-size: 18px;\n"
                      + "      \n"
                      + "");
        NameMatiere[i]=new Label(""+NameMa);
NameMatiere[i].setPrefSize(150, 23);
NameMatiere[i].setLayoutX(58);
NameMatiere[i].setLayoutY(184);
NameMatiere[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
             
                + "      \n"
                + "");  
    Namecours[i]=new Label(""+list.get(i).getNomCours());
Namecours[i].setPrefSize(150, 23);
Namecours[i].setLayoutX(88);
Namecours[i].setLayoutY(214);
Namecours[i].setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                
                + "      \n"
                + ""); 
buttons[i] = new Button(""+i,imgfobut);
buttons[i].setFont(new Font(0.1));
buttons[i].minHeight(37);
buttons[i].minWidth(64);
buttons[i].setLayoutX(68);
buttons[i].setLayoutY(243);
 VBox vboxMeals = new VBox(10);
  vboxMeals.setSpacing(1000);
        Pane p=new Pane();
       p.setPrefSize(200,305);
            p.setStyle("-fx-background-color: white;"
                + "  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
            
    p.getChildren().addAll(imgfo,Name[i], NameMatiere[i],Namecours[i],buttons[i] );
    vboxMeals.getChildren().addAll(p);
    buttons[i].setOnAction(new EventHandler<ActionEvent>() {
       @Override
            public void handle(ActionEvent event) { 
                Button b = (Button) event.getSource();
                 FileChooser fileChooser = new FileChooser();
     fileChooser.setTitle("Export en PDF");
      File file = fileChooser.showSaveDialog(null);
      if (file != null) {
                    try {
                        PdfBadge pdf = new PdfBadge(file.getPath(),list.get(Integer.parseInt(b.getText())).getNomCours());
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Message");
                        alert2.setContentText("Message: Exportation terminé avec succés.");
                        Stage stage5 = (Stage) alert2.getDialogPane().getScene().getWindow();
                        stage5.getIcons().add(new Image(this.getClass().getResource("/com/images/btn_vmenu_hover.png").toString()));
                        alert2.showAndWait();
                    } catch (DocumentException ex) {
                        Logger.getLogger(ListeBadgefxmlController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ListeBadgefxmlController.class.getName()).log(Level.SEVERE, null, ex);
                    }
      }
            }});
      body.getChildren().add(vboxMeals);   
          } catch (SQLException ex) {
              Logger.getLogger(ListeBadgefxmlController.class.getName()).log(Level.SEVERE, null, ex);
          }
      
     }
     
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
}
