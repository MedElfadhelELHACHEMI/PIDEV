package com.views.controllers;

import com.models.entities.Organisation;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OrganisationContainerNI extends Pane {

    private ImageView affiche;
    private Button invite;
    private Label nomOrganisation;
    private Label nbCoachOrganisation;

    public OrganisationContainerNI(Organisation organisation, int nbCoach, int x, int y) {
        super();
        setPrefHeight(270);
        setPrefWidth(200);
        setLayoutX(x);
        setLayoutY(y);
        genererComponents(organisation, nbCoach);
        setStyle("  -fx-background-color: white;\n"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n"
                + "");
    }

    private void genererComponents(Organisation organisation, int nb) {

        affiche = new ImageView(new Image(new File(organisation.getPhoto()).toURI().toString()));
        invite = new Button();
        nomOrganisation = new Label(organisation.getNom());
        nbCoachOrganisation = new Label("With " + nb + " Coachs");
        affiche.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println(organisation);
                try {
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/fxml/OrganisationMapFXML.fxml"));
                    fxmlLoader.load();
                    Parent root = fxmlLoader.getRoot();

                    Scene s = new Scene(root);
                    stage.setScene(s);
                    OrganisationMapFXMLController controller = fxmlLoader.getController();
                    controller.setOrganisation(organisation);
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        affiche.setFitHeight(150);
        affiche.setFitWidth(200);
        affiche.setLayoutX(0);
        affiche.setLayoutY(0);
        affiche.setCursor(Cursor.HAND);
        nomOrganisation.setPrefHeight(32);
        nomOrganisation.setPrefWidth(200);
        nomOrganisation.setLayoutX(0);
        nomOrganisation.setLayoutY(154);
        nomOrganisation.setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "  \n"
                + "    -fx-font-size: 15px;");
        nbCoachOrganisation.setPrefHeight(32);
        nbCoachOrganisation.setPrefWidth(200);
        nbCoachOrganisation.setLayoutX(0);
        nbCoachOrganisation.setLayoutY(190);
        nbCoachOrganisation.setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "  \n"
                + "    -fx-font-size: 15px;");

        invite.setPrefHeight(44);
        invite.setPrefWidth(200);
        invite.setLayoutX(0);
        invite.setLayoutY(226);
        invite.setStyle("-fx-background-color: #25c4cc");
        invite.setCursor(Cursor.HAND);
        invite.setGraphic(new ImageView(new Image("/com/images/add_org.png")));

        getChildren().addAll(affiche, nomOrganisation, nbCoachOrganisation, invite);
    }

}
