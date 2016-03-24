package com.views.controllers;

import com.controllers.IServiceFormateurs;
import com.controllers.IServiceFormateursImpl;
import com.models.daos.interfaces.DAOFactory;
import com.models.entities.Invitation;
import com.models.entities.Organisation;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class InvitationContainerCoach extends Pane {

    private Circle profil;

    private ImageView ok;
    private ImageView no;
    private Label l;

    public InvitationContainerCoach(Invitation invitation, int x, int y) {
        IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
        //   Organisation organisation = DAOFactory.getOrganisationDAO().getOrganisationByid(invitation.getIdOrganisation());
        Organisation organisation = iServiceFormateurs.getOragnisationById(invitation.getIdOrganisation());
        setStyle("-fx-background-color: white;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"
                + "-fx-font-family: Letter Gothic Std;"
                + "-fx-font-size: 10px;");
        setPrefSize(222, 349);
        setLayoutX(x);
        setLayoutY(y);
        generateCompo(organisation, invitation);

    }

    private void generateCompo(Organisation organisation, Invitation invitation) {
        System.out.println(organisation);
        profil = new Circle(111);
        l = new Label(organisation.getNom());
        l.setStyle("-fx-font-family: Impact;"
                + "-fx-font-size: 18px;");
        ok = new ImageView(new Image("/com/images/yes-inv.png"));
        no = new ImageView(new Image("/com/images/no.png"));
        System.out.println("here");
        profil.setFill(new ImagePattern(new Image(new File(organisation.getPhoto()).toURI().toString())));
        profil.setCursor(Cursor.HAND);
        ok.setFitHeight(64);
        ok.setFitWidth(82);

        ok.setLayoutX(13);
        ok.setLayoutY(271);
        ok.setCursor(Cursor.HAND);
        no.setFitHeight(64);
        no.setFitWidth(82);
        no.setCursor(Cursor.HAND);

        no.setLayoutX(127);
        no.setLayoutY(271);

        l.setPrefSize(162, 30);
        l.setLayoutX(30);
        l.setLayoutY(234);
        profil.setLayoutX(111);
        profil.setLayoutY(112);
          profil.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
       
          try {
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/fxml/OrganismeInformationForCoachFXML.fxml"));
                    fxmlLoader.load();
                    Parent root = fxmlLoader.getRoot();

                    Scene s = new Scene(root);
                    stage.setScene(s);
                    OrganismeInformationForCoachFXMLController controller = fxmlLoader.getController();
                    controller.setOrganisation(organisation);
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
                
                
            }
          
          
          });
             ok.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                     IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
              boolean test =  iServiceFormateurs.accteperInvitation(invitation);
              if(test){
                     Alert alert = new Alert(Alert.AlertType.WARNING, "Invitation accepted", ButtonType.OK);
            alert.show();
              }else {
                 Alert alert = new Alert(Alert.AlertType.WARNING, "No", ButtonType.OK);
            alert.show();
              }
            }
          
          
          });
                no.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                      IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
              boolean test =   iServiceFormateurs.refuserInvitation(invitation);
                if(test){
                       Alert alert = new Alert(Alert.AlertType.WARNING, "Invitation refused", ButtonType.OK);
            alert.show();
              }else {
                       Alert alert = new Alert(Alert.AlertType.WARNING, "No", ButtonType.OK);
            alert.show();
              
              }
            }
          
          
          });

        getChildren().addAll(ok, no, profil, l);
    }

}
