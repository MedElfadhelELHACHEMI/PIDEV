/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.models.daos.interfaces.implementations.ImplCoursDAO;
import com.models.entities.Cours;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Bacem
 */
public class CoursesMCPItem extends HBox{
    
    VBox data;
    VBox img;
    VBox action;
    
    Image courseImg;
    ImageView courseImgView;
    Label courseTitle;
    Text courseDescription;
    Button show;
    Button acceptCourse;
    Button refuseCourse;
    
    Cours cour;
    ImplCoursDAO implCoursDAO;

    public CoursesMCPItem(Cours c, int i) {
        cour = new Cours();
        cour = c;
        data = new VBox();
        img = new VBox();
        action = new VBox();
        acceptCourse = new Button("");
        refuseCourse = new Button("");
        
        courseImg = new Image("file:"+c.getAffiche(), 100, 100, false, false);
        courseImgView = new ImageView(courseImg);
        courseTitle = new Label(cour.getNomCours());
        courseDescription = new Text(cour.getDescriptionCours());
        
        if(i == 0){
            show = new Button("");
            show.getStyleClass().add("show-video-btn");
        }
        else if(i == 1){
            show = new Button("");
            show.getStyleClass().add("show-content-btn");
        }
        img.getChildren().add(courseImgView);
        data.getChildren().addAll(courseTitle, courseDescription);
        action.getChildren().addAll(show, acceptCourse, refuseCourse);
        this.getChildren().addAll(img, data, action);
        courseDescription.setWrappingWidth(630);
        img.setPrefWidth(120);
        data.setMinWidth(630);
        action.setMinWidth(50);
        
        
        acceptCourse.getStyleClass().add("accept-btn");
        refuseCourse.getStyleClass().add("refuse-btn");
        
        courseTitle.getStyleClass().add("courseTitle-label");
        courseDescription.getStyleClass().add("courseDescription-text");
        
        
        this.getStyleClass().add("item");
        
        implCoursDAO = new ImplCoursDAO();
        acceptCourse.setOnAction((ActionEvent e) -> {
            implCoursDAO.accRefCourV1(cour,1);
        });
        refuseCourse.setOnAction((ActionEvent e) -> {
            implCoursDAO.accRefCourV1(cour,0);
        });
        show.setOnAction((ActionEvent e) -> {
            if(i==0){
                WebView webview = new WebView();
                webview.getEngine().load(cour.getVideo());
                webview.setPrefSize(640, 390);
                Stage st = new Stage(StageStyle.UTILITY);
                st.setScene(new Scene(webview));
                st.centerOnScreen();
                st.show();
            }
            else if(i==1){
                Stage st = new Stage(StageStyle.UTILITY);
                st.setScene(new Scene(new WebView()));
                st.centerOnScreen();
                st.show();
            }
        });
        
    }
    
    
    
}
