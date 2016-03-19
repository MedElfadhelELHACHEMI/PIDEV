/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.models.enums.Difficulte;
import java.io.File;
import java.util.Objects;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author haikal
 */
public class CoursContainer extends Pane {

    private RotateTransition rt;
    private Label views;
    private Label uploadDate;
    private Label nom;
    private static boolean pointer2 = false;
    private static boolean pointer1 = false;
    private static FadeTransition OldTra;
    private static ImageView OldQuizCh;
    private static ImageView OldQuizNch;
    private ImageView cours;
    private ImageView quizCh;
    private ImageView quizNotCh;
    private ImageView viewIcon;

    private Text text;
    private ProgressBar level;
    private FadeTransition ft;

    public CoursContainer(int nbVues, String Date, String nomCours, String description, Difficulte niveau, String coursPic, int x, int y) {
        super();
        setPrefSize(200, 252);
        setLayoutX(x);
        setLayoutY(y);
        setStyle("-fx-background-color: white;"
                + "  -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
        addNodes(nbVues, Date, description, nomCours, niveau, coursPic);

    }

    private void addNodes(int nbVues, String Date, String description, String nomCours, Difficulte niveau, String coursPic) {
        views = new Label("  " + nbVues + "  Views");

        uploadDate = new Label("  " + Date + "  ");
        nom = new Label(nomCours);
        nom.setStyle("\n"
                + "    -fx-font-family: Impact;\n"
                + "    -fx-font-style: bold;\n"
                + "    -fx-font-size: 18px;\n"
                + "      \n"
                + "");
        text = new Text(description);
        text.setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
        level = new ProgressBar();
        if (niveau == Difficulte.DIFFICILE) {
            level.setProgress(1.0);
            level.setStyle("-fx-accent: red; ");
        } else if (niveau == Difficulte.NORMALE) {
            level.setProgress(0.7);
            level.setStyle("-fx-accent: yellow; ");
        } else if (niveau == Difficulte.NORMALE) {
            level.setProgress(0.2);
            level.setStyle("-fx-accent: green; ");
        }
        cours = new ImageView(new Image(new File(coursPic).toURI().toString()));
//        cours.setOnMouseExited(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                pointer1=true ;
//                     System.out.println("exit"+pointer1+"  "+pointer2);
//                if(pointer1==true&&pointer2==true){
//                    System.out.println("2222");
//                if (!Objects.isNull(ft)) {
//                    System.out.println("not null");
//                    ft.setToValue(1);
//                    ft.play();
//                    quizCh.setVisible(false);
//                }
//
//            }}
//
//        });
        cours.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (ft != null) {

                    OldTra.setToValue(1);
                    OldTra.play();
                    OldQuizCh.setVisible(false);
                    OldQuizNch.setVisible(false);

                }

                ft = new FadeTransition(Duration.seconds(0.1));
                OldTra = ft;
                OldQuizCh =quizCh ;
                OldQuizNch = quizNotCh;
                ft.setNode(cours);
                ft.setCycleCount(1);
                ft.setToValue(0.4);
                ft.play();
                quizCh.setVisible(true);
                quizNotCh.setVisible(true);
            }

        });
        viewIcon = new ImageView(new Image("/com/images/view-cours.png"));
        quizCh = new ImageView(new Image("/com/images/quiz.png"));
        quizNotCh = new ImageView(new Image("/com/images/quizentren.png"));
//        quizCh.setOnMouseExited(new EventHandler<MouseEvent>() {
//           
//
//            @Override
//            public void handle(MouseEvent event) {
//                if(pointer1)
//                { if (!Objects.isNull(ft)) {
//                    System.out.println("not null");
//                    ft.setToValue(1);
//                    ft.play();
//                    quizCh.setVisible(false);
//                }
//            }}
//        });
        quizCh.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                rt = new RotateTransition(Duration.seconds(1), quizCh);
                rt.setCycleCount(1);
                rt.setByAngle(360);
                rt.play();
            }
        });

        quizNotCh.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                rt = new RotateTransition(Duration.seconds(1), quizNotCh);
                rt.setCycleCount(1);
                rt.setByAngle(360);
                rt.play();
            }
        });
        quizCh.setFitHeight(60);
        quizCh.setFitWidth(60);
        quizCh.setLayoutX(20);
        quizCh.setCursor(Cursor.HAND);
        quizCh.setLayoutY(50);
        quizNotCh.setFitHeight(50);
        quizNotCh.setFitWidth(50);
        quizNotCh.setCursor(Cursor.HAND);
        quizNotCh.setLayoutX(100);
        quizNotCh.setLayoutY(50);
        quizCh.setVisible(false);
        quizNotCh.setVisible(false);
        cours.setFitWidth(200);
        cours.setFitHeight(150);
        cours.setLayoutX(0);
        cours.setLayoutY(0);

        nom.setPrefSize(200, 17);
        nom.setLayoutX(0);
        nom.setLayoutY(150);

        text.setLayoutX(0);
        text.setLayoutY(190);

        viewIcon.setFitWidth(15);
        viewIcon.setFitHeight(15);
        viewIcon.setLayoutX(0);
        viewIcon.setLayoutY(199);

        views.setPrefSize(173, 15);
        views.setLayoutX(28);
        views.setLayoutY(195);
        views.setStyle("\n"
                + "    \n"
                + "    -fx-font-style: italic;\n"
                + "    -fx-font-size: 15px;\n"
                + "      \n"
                + "");
        uploadDate.setPrefSize(200, 17);
        uploadDate.setLayoutX(0);
        uploadDate.setLayoutY(214);
        uploadDate.setStyle("\"\\n\" +\n"
                + "\"    \\n\" +\n"
                + "\"    -fx-font-style: italic;\\n\" +\n"
                + "\"    -fx-font-size: 15px;\\n\" +\n"
                + "\"      \\n\" +\n"
                + "\"");
        level.setPrefSize(200, 17);
        level.setLayoutX(0);
        level.setLayoutY(240);

        getChildren().addAll(cours, nom, text, viewIcon, views, uploadDate, level, quizCh, quizNotCh);

    }

    private void showIcons() {
        quizCh.setVisible(true);
    }
}
