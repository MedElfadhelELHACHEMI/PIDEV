package com.views.controllers;

import com.controllers.IServiceFormateurs;
import com.controllers.IServiceFormateursImpl;
import com.models.daos.interfaces.DAOFactory;
import com.models.entities.Invitation;
import com.models.entities.Organisation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class InvitationContainerCoach extends Pane {

    static boolean vis = true;
    private Circle profil;
    List<Pane> list = new ArrayList<>();
    private ImageView ok;
    private ImageView no;
    private Label l;
Pane exPane ;
    public InvitationContainerCoach(Invitation invitation, int x, int y, AnchorPane body, AnchorPane bb) {
        IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
        //   Organisation organisation = DAOFactory.getOrganisationDAO().getOrganisationByid(invitation.getIdOrganisation());
        Organisation organisation = iServiceFormateurs.getOragnisationById(invitation.getIdOrganisation());
        setStyle("-fx-background-color: white;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"
                + ""
                + ""
                + "-fx-border-color: white #f4f4f4 #f4f4f4 white;-fx-border-width:2px;");
        setPrefSize(222, 100);
        setLayoutX(x);
        setLayoutY(y);
        generateCompo2(organisation, invitation, body, bb);

    }

    private void generateCompo(Organisation organisation, Invitation invitation) {

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
        profil.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

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
        ok.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
                boolean test = iServiceFormateurs.accteperInvitation(invitation);
                if (test) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Invitation accepted", ButtonType.OK);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No", ButtonType.OK);
                    alert.show();
                }
            }

        });
        no.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
                boolean test = iServiceFormateurs.refuserInvitation(invitation);
                if (test) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Invitation refused", ButtonType.OK);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No", ButtonType.OK);
                    alert.show();

                }
            }

        });

        getChildren().addAll(ok, no, profil, l);
    }

    private void generateCompo2(Organisation organisation, Invitation invitation, AnchorPane b, AnchorPane bb) {

        Pane p = new Pane();
        ImageView i1 = new ImageView();
        ImageView i2 = new ImageView();
        ImageView i3 = new ImageView();
        ImageView i4 = new ImageView();

        Button b1 = new Button("Accept");
        Button b2 = new Button("Refuse");
        Label l1 = new Label();
        Label l2 = new Label();
        Label l3 = new Label();

        i1.setFitHeight(117);
        i1.setFitWidth(153);
        i1.setLayoutX(20);
        i2.setLayoutY(0);

        p.setPrefSize(400, 117);
        p.setLayoutX(248);
        p.setLayoutY(0);

//        i2.setFitHeight(27);
//        i2.setFitWidth(23);
//        i2.setLayoutX(3);
//        i2.setLayoutY(9);
        i3.setFitHeight(15);
        i3.setFitWidth(15);
        i3.setLayoutX(70);
        i3.setLayoutY(39);

        i4.setFitHeight(15);
        i4.setFitWidth(15);
        i4.setLayoutX(70);
        i4.setLayoutY(68);

        l1.setPrefSize(195, 26);
        l1.setLayoutX(89);
        l1.setLayoutY(9);

        l2.setPrefSize(195, 26);
        l2.setLayoutX(91);
        l2.setLayoutY(35);

        l3.setPrefSize(195, 26);
        l3.setLayoutX(91);
        l3.setLayoutY(60);

        b1.setPrefSize(101, 20);
        b1.setLayoutX(841);
        b1.setLayoutY(10);

        b2.setPrefSize(101, 20);
        b2.setLayoutX(841);
        b2.setLayoutY(60);
        b2.setStyle("-fx-background-color:#25c4cc;");
        b1.setStyle("-fx-background-color:#25c4cc;");
        i3.setImage(new Image("/com/images/mailorg.png"));
        l1.setText(organisation.getNom());
        l1.setStyle("-fx-font-weight: bold;    -fx-font-size:17;-fx-text-fill:#25c4cc;");
        l2.setText(organisation.geteMail());
        l2.setStyle("-fx-font-weight: bold;    -fx-font-size:13;-fx-text-fill:#9ba6a5;");
        l3.setStyle("-fx-font-weight: bold;    -fx-font-size:13;-fx-text-fill:#9ba6a5;");
        l3.setText(organisation.getAdresse());
        i4.setImage(new Image("/com/images/maporg.png"));
        try {

            i1.setFitHeight(100);
            i1.setFitWidth(100);
            i1.setImage(new Image(new File(organisation.getPhoto()).toURI().toString()));
        } catch (NullPointerException e) {

            Alert alert = new Alert(Alert.AlertType.WARNING, "No Picture", ButtonType.OK);
            alert.show();
        }
        b1.getStyleClass().add("bt-accep");
        b2.getStyleClass().add("bt-refuse");
        b1.setCursor(Cursor.HAND);
        b2.setCursor(Cursor.HAND);
        i1.setCursor(Cursor.HAND);
        i1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                 Pane map = new Pane();
                 list.add(map);
                 System.out.println(list);
                 exPane=map;
                System.out.println("  viewww   "+vis);
                System.out.println(exPane);
                  final WebView browser = new WebView();
                    final WebEngine webEngine = browser.getEngine();
                if (vis) {
                    vis = false;
                    b.setLayoutX(500);
                   
                    map.setPrefSize(500, 520);
map.setLayoutX(35);map.setLayoutY(35);
                    map.setStyle(""
                            + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"
                            + ""
                            + ""
                            + "-fx-border-color: white #f4f4f4 #f4f4f4 white;-fx-border-width:2px;");
                    System.out.println(map);
                    String replace = organisation.getAdresse().replace(' ', '+');
                    String replace2 = organisation.getNom().replace(' ', '+');
                    System.out.println("" + replace2 + "+" + replace);
                    browser.setPrefHeight(520);
                    browser.setPrefWidth(500);
                    System.out.println(organisation.getAdresse());
                    webEngine.load("https://www.google.tn/maps/place/" + replace2 + "+" + replace);
    //    System.out.println(browser.getEngine().getDocument().getDocumentElement().toString()); 

                    //add the web view to the scene
                    map.getChildren().add(browser);
                    bb.getChildren().add(map);
                } else if (vis == false) {
                    for (Pane list1 : list) {
                        System.out.println(list1);
                        list1.setVisible(false);
                    }
                    b.getChildren().remove(exPane);
                    System.out.println(exPane);
                    exPane.setVisible(false);
                 browser.getEngine().load(null);
                    b.setLayoutX(0);
                  browser.setVisible(false);
                    exPane.getChildren().clear();
                    exPane.setVisible(false);
                    vis = true;
                }
//                    Stage stage = new Stage();
//                    FXMLLoader fxmlLoader = new FXMLLoader();
//                    fxmlLoader.setLocation(getClass().getResource("/com/fxml/OrganisationMapFXML.fxml"));
//                    fxmlLoader.load();
//                    Parent root = fxmlLoader.getRoot();
//
//                    Scene s = new Scene(root);
//                    stage.setScene(s);
//                    OrganisationMapFXMLController controller = fxmlLoader.getController();
//                    controller.setOrganisation(organisation);
//                    stage.show();

            }
        });
   b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
                boolean test = iServiceFormateurs.accteperInvitation(invitation);
                if (test) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Invitation accepted", ButtonType.OK);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No", ButtonType.OK);
                    alert.show();
                }
            }

        });
        b2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
                boolean test = iServiceFormateurs.refuserInvitation(invitation);
                if (test) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Invitation refused", ButtonType.OK);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No", ButtonType.OK);
                    alert.show();

                }
            }

        });
        Circle circle = new Circle(100);
        circle.setLayoutX(100);
        circle.setLayoutY(100);
        circle.setFill(new ImagePattern(new Image(new File(CurrentUser.getUtilisateur().getPhoto()).toURI().toString())));
        p.getChildren().addAll(l1, l2, l3, i3, i4);
        getChildren().addAll(i1, p, b1, b2);
    }

}
