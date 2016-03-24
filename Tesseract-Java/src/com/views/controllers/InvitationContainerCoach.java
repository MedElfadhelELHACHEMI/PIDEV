package com.views.controllers;

import com.controllers.IServiceFormateurs;
import com.controllers.IServiceFormateursImpl;
import com.models.daos.interfaces.DAOFactory;
import com.models.entities.Invitation;
import com.models.entities.Organisation;
import java.io.File;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

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

        getChildren().addAll(ok, no, profil, l);
    }

}
