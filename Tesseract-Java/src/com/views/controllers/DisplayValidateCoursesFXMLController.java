/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.IServiceFormateurs;
import com.controllers.IServiceFormateursImpl;
import com.jfoenix.controls.JFXCheckBox;
import com.models.entities.Cours;
import com.models.enums.Difficulte;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class DisplayValidateCoursesFXMLController implements Initializable {

    @FXML
    private Pane header;
    @FXML
    private AnchorPane body;
    @FXML
    private Pane paneCritere;
    @FXML
    private Button searchCourse;

    private ImageView searchIcon;
    private CoursContainer cc;
    private int X = 66;
    private int Y = 53;
    List<Cours> listCours = new ArrayList<>();
    @FXML
    private TextField searchField;
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
    private JFXCheckBox uplMonth;
    @FXML
    private JFXCheckBox uplYear;

    private Set<Cours> listCoursCritere = new HashSet<>();

    private boolean critere;
    private boolean critere1 = false;
    private boolean critere2 = false;
    private boolean critere8 = false;
    private boolean critere7 = false;
    private boolean critere6 = false;
    private boolean critere5 = false;
    private boolean critere4 = false;
    private boolean critere3 = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        searchIcon = new ImageView(new Image("/com/images/search-formateur.png"));

        searchCourse.setGraphic(searchIcon);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2));
        fadeTransition.setFromValue(0);
            fadeTransition.setToValue(0.9);
            fadeTransition.setNode(searchField);
            fadeTransition.setCycleCount(1);
             FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2));
        fadeTransition2.setFromValue(0);
            fadeTransition2.setToValue(0.9);
            fadeTransition2.setNode(searchCourse);
            fadeTransition2.setCycleCount(1);
            fadeTransition2.play();
            
        IServiceFormateurs isf = new IServiceFormateursImpl();

        generateContainersCourses(isf.getCoursesACCCoach(CurrentUser.getId()));

    }

    private void generateContainersCourses(List<Cours> lst) {
        X = 100;
        Y = 53;
        body.getChildren().clear();
        for (Cours cours : lst) {
            if (Y + 252 > body.getPrefHeight()) {
                body.setPrefHeight(Y + 260);
            }
            if (X >= 502) {
                X = 66;
                Y = Y + 281;
            }
            cc = new CoursContainer(X, cours.getUploadDate().toString(), cours.getNomCours(), cours.getDescriptionCours(), cours.getDifficulte(), cours.getAffiche(), X, Y);
            X = X + 324;
            TranslateTransition transition = new TranslateTransition(Duration.seconds(1.5));
            transition.setFromY(1000);
            transition.setToY(0);
            transition.setNode(cc);
            transition.setCycleCount(1);
            
            body.getChildren().add(cc);
                transition.play();
        }

        cc.getStylesheets().add("/com/styles/containercours.css");
    }

    @FXML
    private void searchCourse(ActionEvent event) {
        IServiceFormateurs isf = new IServiceFormateursImpl();
        listCours.clear();
        for (Cours cours : isf.getCoursesACCCoach(CurrentUser.getId())) {
            if (cours.getNomCours().contains(searchField.getText()) || (cours.getDescriptionCours().contains(searchField.getText()))) {
                listCours.add(cours);
            }
        }

        body.getChildren().clear();
        generateContainersCourses(listCours);
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
    private void clickMonth(ActionEvent event) {
        if (uplMonth.isSelected()) {
            critere7 = true;
        } else {
            critere7 = false;
        }
        searchByCrit();
    }

    @FXML
    private void clickYear(ActionEvent event) {
        if (uplYear.isSelected()) {
            critere8 = true;
        } else {
            critere8 = false;
        }
        searchByCrit();
    }

    private void searchByCrit() {
        IServiceFormateurs isf = new IServiceFormateursImpl();
        listCours.clear();
        listCours = isf.getCoursesACCCoach(CurrentUser.getId());
        if (critere1 == true) {
            for (Cours cours : listCours) {
                if (cours.getLanguage().equals("English")) {
                    listCoursCritere.add(cours);
                }
            }
        }
        if (critere2 == true) {
            for (Cours cours : listCours) {
                if (cours.getLanguage().equals("French")) {
                    listCoursCritere.add(cours);
                }
            }
        }
        if (critere3 == true) {
            for (Cours cours : listCours) {
                if (cours.getLanguage().equals("Arabic")) {
                    listCoursCritere.add(cours);
                }
            }
        }
        if (critere4 == true) {
            for (Cours cours : listCours) {
                if (cours.getDifficulte() == Difficulte.FACILE) {
                    listCoursCritere.add(cours);
                }
            }
        }
        if (critere5 == true) {
            for (Cours cours : listCours) {
                if (cours.getDifficulte() == Difficulte.NORMALE) {
                    listCoursCritere.add(cours);
                }
            }
        }
        if (critere6 == true) {
            for (Cours cours : listCours) {
                if (cours.getDifficulte() == Difficulte.DIFFICILE) {
                    listCoursCritere.add(cours);
                }
            }
        }
        if (critere7 == true) {
            for (Cours cours : listCours) {
                if (cours.getUploadDate().getMonth() == LocalDate.now().getMonth()) {
                    listCoursCritere.add(cours);
                }
            }
        }
        if (critere8 == true) {
            for (Cours cours : listCours) {
                if (cours.getUploadDate().getYear() == LocalDate.now().getYear() - 1) {
                    listCoursCritere.add(cours);
                }
            }
        }
        System.out.println(listCoursCritere.size());
        generateContainersCourses(new ArrayList<>(listCoursCritere));
        listCoursCritere.clear();
    }

}
