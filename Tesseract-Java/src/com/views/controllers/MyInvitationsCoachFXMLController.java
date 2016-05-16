/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.IServiceFormateurs;
import com.controllers.IServiceFormateursImpl;
import com.models.entities.Invitation;
import com.models.entities.Organisation;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class MyInvitationsCoachFXMLController implements Initializable {

    List<Invitation> listInvitation;
    int x = 35;
    int y = 38;
    @FXML
    private VBox body;
    @FXML
    private AnchorPane header01;
    @FXML
    private TextField txtfsearch;
    @FXML
    private Button btchercher;
    @FXML
    private Button preced;
    @FXML
    private Button next;
    @FXML
    private Label pagination;
    public int page = 1;
    public int nbLimitPages;
    public  int nbElement = 0;
    int i = 0;
    @FXML
    private AnchorPane goupe;
    @FXML
    private AnchorPane body02;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preced.setDisable(true);
        IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
        listInvitation = iServiceFormateurs.afficherInvitationEnAttente(CurrentUser.getId());
        nbLimitPages = (int) ((listInvitation.size() / 4) + 1);
        if (nbLimitPages == 1) {
            next.setDisable(true);
        }
        for (i = nbElement; i < nbElement + 4; i++) {
            if (Objects.isNull(listInvitation.get(i))) {
                break;
            }
            nbElement++;
            System.out.println(nbElement);
            InvitationContainerCoach coach = new InvitationContainerCoach(listInvitation.get(i), x, y,goupe,body02);
            x = x + 278;
            body.getChildren().addAll(coach);
            if (nbElement >= 4) {
                System.out.println("enter ");
                break;
            }
        }
//        for (Invitation invitation : listInvitation) {
//
//        }
        pagination.setText(page + " - " + (int) ((listInvitation.size() / 4) + 1) + " of " + (int) ((listInvitation.size() / 4) + 1));
        pagination.setStyle("-fx-font-weight: bold;    -fx-font-size:10;-fx-text-fill:#25c4cc;");

    }

    @FXML
    private void chercherInvitation(ActionEvent event) {
         next.setDisable(true);
              preced.setDisable(true);
        body.getChildren().clear();
        IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
        listInvitation = iServiceFormateurs.afficherInvitationEnAttente(CurrentUser.getId());
        nbLimitPages = (int) ((listInvitation.size() / 4) + 1);
        if (nbLimitPages == 1) {
           
        }
        for (i = 0; i < 4 ; i++) {
            if (Objects.isNull(listInvitation.get(i))) {
                break;
            }
            IServiceFormateurs iServiceFormateurs2 = new IServiceFormateursImpl();
            //   Organisation organisation = DAOFactory.getOrganisationDAO().getOrganisationByid(invitation.getIdOrganisation());
            Organisation organisation = iServiceFormateurs2.getOragnisationById(listInvitation.get(i).getIdOrganisation());
            System.out.println("---or"+organisation);
            if (organisation.getNom().toLowerCase().contains(txtfsearch.getText().toLowerCase())) {
                System.out.println(txtfsearch.getText());
                System.out.println("--eeeeeeeeee-or"+organisation);
                InvitationContainerCoach coach = new InvitationContainerCoach(listInvitation.get(i), x, y,goupe,body02);
                x = x + 278;
                body.getChildren().addAll(coach);
             
            }
//        for (Invitation invitation : listInvitation) {
//
//        }
            pagination.setText(page + " - " + (int) ((listInvitation.size() / 4) + 1) + " of " + (int) ((listInvitation.size() / 4) + 1));
            pagination.setStyle("-fx-font-weight: bold;    -fx-font-size:10;-fx-text-fill:#25c4cc;");
        }
    }

    @FXML
    private void preced(ActionEvent event) {

        page--;
        nbElement = nbElement - 4;
        body.getChildren().clear();
        System.out.println("beu3  " + i);
        System.out.println(page+" acccccccc");
        if (page == 1) {
 System.out.println(page+" xxxxxxx");
            preced.setDisable(true);
   next.setDisable(false);
        } else {

            preced.setDisable(false);
            next.setDisable(false);
        }
        IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
        listInvitation = iServiceFormateurs.afficherInvitationEnAttente(CurrentUser.getId());
        nbLimitPages = (int) ((listInvitation.size() / 4) + 1);
        System.out.println("nb element" + nbElement + "  " + (nbElement - 1));
        for (i = (nbElement - 1); i <= nbElement + 5; i++) {
            System.out.println("i var"+i);
            if (Objects.isNull(listInvitation.get(i))) {
                break;
            }
            nbElement++;
            System.out.println(nbElement);
            InvitationContainerCoach coach = new InvitationContainerCoach(listInvitation.get(i), x, y,goupe,body02);
            x = x + 278;
            body.getChildren().addAll(coach);
            if (nbElement >= 4) {
                System.out.println("enter ");
                break;
            }
        }
        for (Invitation invitation : listInvitation) {

        }
        pagination.setText(page + " - " + (int) ((listInvitation.size() / 4) + 1) + " of " + (int) ((listInvitation.size() / 4) + 1));
        pagination.setStyle("-fx-font-weight: bold;    -fx-font-size:10;-fx-text-fill:#25c4cc;");
    }

    @FXML
    private void next(ActionEvent event) {
        System.out.println("pagee " + page + "   nbLimi" + nbLimitPages);
        System.out.println("");
        if (page + 1 >= nbLimitPages) {

            preced.setDisable(false);
        }

        if (page + 1 == nbLimitPages) {

            next.setDisable(true);
        }
        body.getChildren().clear();
        page++;
        IServiceFormateurs iServiceFormateurs = new IServiceFormateursImpl();
        listInvitation = iServiceFormateurs.afficherInvitationEnAttente(CurrentUser.getId());
        nbLimitPages = (int) ((listInvitation.size() / 4) + 1);

        for (i = nbElement; i < nbElement + 4; i++) {
            if (Objects.isNull(listInvitation.get(i))) {
                break;
            }
            nbElement++;
            System.out.println(nbElement);
            InvitationContainerCoach coach = new InvitationContainerCoach(listInvitation.get(i), x, y,goupe,body02);
            x = x + 278;
            body.getChildren().addAll(coach);
            if (nbElement >= 4) {
                System.out.println("enter ");
                break;
            }
        }
        for (Invitation invitation : listInvitation) {

        }
        pagination.setText(page + " - " + (int) ((listInvitation.size() / 4) + 1) + " of " + (int) ((listInvitation.size() / 4) + 1));
        pagination.setStyle("-fx-font-weight: bold;    -fx-font-size:10;-fx-text-fill:#25c4cc;");
    }

    public void testPage() {
        if (nbLimitPages == page) {
            next.setDisable(true);

        }
        if (page == 1) {
            preced.setDisable(true);
        }

    }
}
