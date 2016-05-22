/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.models.daos.interfaces.DAOFactory;
import com.models.daos.interfaces.IChapitreDAO;
import com.models.daos.interfaces.ICoursDAO;
import com.models.daos.interfaces.IEpreuveFinalDAO;
import com.models.daos.interfaces.IEpreuveObjectifDAO;
import com.models.daos.interfaces.IQuestionDAO;
import com.models.daos.interfaces.IReponseDAO;
import com.models.entities.Chapitre;
import com.models.entities.Cours;
import com.models.entities.Epreuve;
import com.models.entities.EpreuveFinal;
import com.models.entities.EpreuveObjectif;
import com.models.entities.Question;
import com.models.entities.Reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author noor
 */
public class WaitingForValidation2FXMLController implements Initializable {

    Alert alert;

    Label previewQuizz = new Label();
    boolean finalEpreuve = false;
    static int currentCourseId;
    static int currentChapterId;
    IChapitreDAO chapitreDAO;
    IEpreuveFinalDAO epreuveFinalDAO;
    IEpreuveObjectifDAO epreuveObjectifDAO;
    IQuestionDAO questionDAO;
    IReponseDAO reponseDAO;
    ICoursDAO coursDAO;
    List<Cours> Courses;
    final ScrollBar sc = new ScrollBar();
    List<Chapitre> Chapters = new ArrayList<Chapitre>();
    @FXML
    private AnchorPane body;
    @FXML
    private Label title;
    @FXML
    private VBox box;
    Button b;
    Label l;
    HBox p;
    @FXML
    private VBox editingCourse;
    @FXML
    private HBox controls;
    @FXML
    private AnchorPane chapterPane;
    @FXML
    private Label chapterTitle;
    @FXML
    private JFXTextField chapterTitleTF;
    @FXML
    private JFXTextField chapterVideoTF;
    @FXML
    private JFXTextArea descChapter;
    @FXML
    private JFXButton nextBt;
    @FXML
    private JFXButton cancelBt;
    @FXML
    private Pane RightPane;
    @FXML
    private JFXButton backBt;
    @FXML
    private JFXButton submit;
    @FXML
    private Label qnb;
    @FXML
    private JFXTextField question;
    @FXML
    private JFXTextField trueAnswer;
    @FXML
    private JFXTextField wrongAnswer1;
    @FXML
    private JFXTextField wrongAnswer2;
    @FXML
    private JFXTextField wrongAnswer3;
    @FXML
    private JFXButton addQ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controls.setLayoutX(400);

        sc.setLayoutX(body.getWidth() - sc.getWidth());
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(180);

        nextBt.setStyle(
                "   -fx-background-color: #0b8ba7; -fx-background-insets: 0,1,2; -fx-background-radius: 3,2,1;      -fx-text-fill: white ;    -fx-font-size: 18px;");
        cancelBt.setStyle(
                "   -fx-background-color: #9DA4A6; -fx-background-insets: 0,1,2; -fx-background-radius: 3,2,1;    -fx-text-fill: white ;    -fx-font-size: 18px;");
        submit.setStyle(
                "   -fx-background-color: #0b8ba7; -fx-background-insets: 0,1,2; -fx-background-radius: 3,2,1;      -fx-text-fill: white ;    -fx-font-size: 18px;");
        backBt.setStyle(
                "   -fx-background-color: #9DA4A6; -fx-background-insets: 0,1,2; -fx-background-radius: 3,2,1;    -fx-text-fill: white ;    -fx-font-size: 18px;");
        addQ.setStyle(
                "   -fx-background-color: white; -fx-background-insets: 0,1,2; -fx-background-radius: 3,2,1;    -fx-text-fill: #9DA4A6 ;    -fx-font-size: 18px;");

        chapterPane.setVisible(false);

        coursDAO = DAOFactory.getCoursDAO();
        try {
            Courses = coursDAO.getCoursValid2EnAttente(CurrentUser.getId());
        } catch (SQLException ex) {
            Logger.getLogger(WaitingForValidation2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Courses.stream().forEach(c -> {
            b = new Button("Edit");
            b.setOnAction((ActionEvent event) -> {
                try {
                    EditAction(c.getIdCours());
                } catch (Exception ex) {
                    Logger.getLogger(WaitingForValidation2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            l = new Label();
            p = new HBox();
            l.setStyle("-fx-text-fill: #032149 ; -fx-font-size: 16px; ");
            l.setText(c.getNomCours());
            l.setPrefWidth(400);
            p.setPrefSize(500, 35);
            b.setStyle("   -fx-background-color: #0B686E; -fx-background-insets: 1,1,2; -fx-background-radius: 3,2,1;    -fx-padding: 3 30 3 30;  -fx-text-fill: white ;    -fx-font-size: 16px;");
            p.getChildren().addAll(l, b);
            box.getChildren().add(p);

        });

        // TODO
    }

    private EventHandler<ActionEvent> EditAction(int idCours) throws SQLException {
        currentCourseId = idCours;
        box.setVisible(false);
        editingCourse.setVisible(true);
        Button chapterBT = new Button("Add Chapter");
        chapterBT.setOnAction((ActionEvent event) -> EditChapter(new Chapitre()));
        Button FinalBT = new Button("Add Final Quizz");
        FinalBT.setDisable(false);
        FinalBT.setOnAction((ActionEvent event) -> {
            finalEpreuve = true;
            QuizzAction(event);
        });
        Button submitBT = new Button("Submit Course ");
        submitBT.setOnAction((ActionEvent event) -> {
            try {
                submitAction(event);
            } catch (IOException ex) {
                Logger.getLogger(WaitingForValidation2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        chapterBT.setStyle("   -fx-background-color: #0B686E; -fx-background-insets: 1,1,2; -fx-background-radius: 3,2,1;    -fx-padding: 3 30 3 30;  -fx-text-fill: white ;    -fx-font-size: 16px;");
        FinalBT.setStyle("   -fx-background-color: white; -fx-background-insets: 1,1,2; -fx-background-radius: 3,2,1;    -fx-padding: 3 30 3 30;  -fx-text-fill: #0B686E ;    -fx-font-size: 16px;");
        submitBT.setStyle("   -fx-background-color: #0B686E; -fx-background-insets: 1,1,2; -fx-background-radius: 3,2,1;    -fx-padding: 3 30 3 30;  -fx-text-fill: white ;    -fx-font-size: 16px;");
        controls.getChildren().addAll(chapterBT, FinalBT, submitBT);
        chapitreDAO = DAOFactory.getChapitreDAO();
        Chapters = chapitreDAO.findChapitreByIdCours(currentCourseId);
        Chapters.stream().forEach(c -> {
            b = new Button("Edit");
            b.setOnAction((ActionEvent event) -> EditChapter(c));
            l = new Label();
            p = new HBox();
            l.setStyle("-fx-text-fill: #032149 ; -fx-font-size: 16px; ");
            l.setText(c.getNom());
            l.setPrefWidth(400);
            p.setPrefSize(500, 35);
            b.setStyle("   -fx-background-color: #587a8f; -fx-background-insets: 1,1,2; -fx-background-radius: 3,2,1;    -fx-padding: 3 30 3 30;  -fx-text-fill: white ;    -fx-font-size: 16px;");
            p.getChildren().addAll(l, b);
            editingCourse.getChildren().add(p);

        });

        return null;
    }

    private void EditChapter(Chapitre c) {
        title.setVisible(false);
        editingCourse.setVisible(false);
        chapterPane.setVisible(true);
        if (c != null) {
            chapterTitleTF.setText(c.getNom());
            chapterVideoTF.setText(c.getResume());
            descChapter.setText(c.getDescription());
        }

    }

    @FXML
    private void QuizzAction(ActionEvent event) {
        title.setVisible(false);
        editingCourse.setVisible(false);
        chapterPane.setVisible(true);
        ScrollPane pane = new ScrollPane();
        pane.setPrefSize(300, 300);
        pane.setLayoutX(620);
        pane.setLayoutY(100);
        previewQuizz.setPrefSize(400, 350);
        pane.setContent(previewQuizz);
        RightPane.getChildren().add(pane);
        RightPane.setLayoutX(0);
        Chapitre c = new Chapitre();
        c.setIdCours(currentCourseId);
        c.setNom(chapterTitleTF.getText());
        c.setResume(chapterVideoTF.getText());
        c.setDescription(descChapter.getText());
        chapitreDAO = DAOFactory.getChapitreDAO();
        chapitreDAO.addChapitre(c);
        c = chapitreDAO.searchChapitre(chapterTitle.getText(), currentCourseId);
        currentChapterId = c.getId();
    }

    @FXML
    private void BackAction(ActionEvent event) {
        editingCourse.setVisible(true);
        chapterPane.setVisible(false);

    }

    @FXML
    private void backAction(ActionEvent event) {
        RightPane.setLayoutX(1100);
    }

    @FXML
    private void submitAction(ActionEvent event) throws IOException {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Submission");
        alert.setHeaderText("Once data submitted , you will not be able to edit it ");
        alert.setContentText("Are you sure you want to submit ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            body.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/com/fxml/WaitingForValidation2FXML.fxml")));
            finalEpreuve = false;
        }
    }

    @FXML
    private void addQuestion() throws SQLException {

        Epreuve ef = new EpreuveFinal();

        if (finalEpreuve) {

            ef.setIdCours(currentCourseId);
            epreuveFinalDAO = DAOFactory.getEpreuveFinalDAO();
            epreuveFinalDAO.createEpreuveFinal((EpreuveFinal) ef);
            ef = epreuveFinalDAO.searchEpreuveFinalByCours(currentCourseId);

        } else {
            ef = new EpreuveObjectif();
            ef.setIdCours(currentCourseId);
            ef.setIdChapitre(currentChapterId);
            epreuveObjectifDAO = DAOFactory.getEpreuveObjectifDAO();
            epreuveObjectifDAO.createEpreuveObjectif((EpreuveObjectif) ef);
            ef = epreuveObjectifDAO.searchEpreuveObjectifByObjId(currentChapterId);

        }
        Question q = new Question();
        q.setQuestion(question.getText());
        q.setIdEpreuve(ef.getId());
        questionDAO = DAOFactory.getQuestionDAO();
        questionDAO.createQuestion(q);
        q = questionDAO.findQuestionByQuestion(question.getText());
        Reponse reponse = new Reponse();
        reponse.setIdQuestion(q.getId());
        reponse.setEtat("true");
        reponse.setReponse(trueAnswer.getText());
        reponseDAO = DAOFactory.getReponseDAO();
        reponseDAO.createReponse(reponse);

        reponse = new Reponse();
        reponse.setIdQuestion(q.getId());
        reponse.setEtat("false");
        reponse.setReponse(wrongAnswer1.getText());
        reponseDAO.createReponse(reponse);

        reponse = new Reponse();
        reponse.setIdQuestion(q.getId());
        reponse.setEtat("false");
        reponse.setReponse(wrongAnswer2.getText());
        reponseDAO.createReponse(reponse);

        reponse = new Reponse();
        reponse.setIdQuestion(q.getId());
        reponse.setEtat("false");
        reponse.setReponse(wrongAnswer3.getText());
        reponseDAO.createReponse(reponse);
        previewQuizz.setText(previewQuizz.getText() + "\n " + question.getText() + "\n " + "    " + trueAnswer.getText() + "\n " + "    " + wrongAnswer1.getText() + "\n " + "    " + wrongAnswer2.getText() + "\n " + "    " + wrongAnswer3.getText());

        question.setText("");
        trueAnswer.setText("");
        wrongAnswer1.setText("");
        wrongAnswer2.setText("");
        wrongAnswer3.setText("");

    }

}
