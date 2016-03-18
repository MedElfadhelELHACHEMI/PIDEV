
package com.views.controllers;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MOOCAccueilGUI extends Stage {

    double xOffset = 0;
    double yOffset = 0;
    double stage_width = 0;
    double stage_height = 0;
    double stage_x = 0;
    double stage_y = 0;
    //menus
    BorderPane root = new BorderPane();
    BorderPane container = new BorderPane();
    HBox h_racourci = new HBox();
    HBox plt_menu = new HBox();
    HBox tool_menu = new HBox();
    VBox h_menu = new VBox();
    HBox b_menu = new HBox();
    VBox v_menu = new VBox();
    //plateform decoration buttons
    Button b_close = new Button();
    Button b_reduce = new Button();
    Button b_maxsize = new Button();
    Button btn_vmenu = new Button();
    MenuButton user = new MenuButton("username");
    Button btn_home = new Button();
    Button btn_notif = new Button();
    Button btn_mail = new Button();

        //_________________________________v_menu_____________________
    //apprennant____________________________
    //vbox  
    VBox aprn_cours;
    VBox aprn_epreuve;
    VBox aprn_evnt_chall;
    VBox aprn_fmt_org;
    VBox aprn_forum;
    //button
    Button aprn_mes_cours;
    Button aprn_search_cours;
    Button aprn_mes_epreuve;
    Button aprn_search_frmt;
    Button aprn_search_org;
    Button aprn_event;
    Button aprn_challenge;
    Button aprn_forumb;
    Button aprn_mes_sujet;
    //titled pane
    TitledPane tlp_aprn_cours;
    TitledPane tlp_aprn_epreuve;
    TitledPane tlp_aprn_evnt_chall;
    TitledPane tlp_aprn_fmt_org;
    TitledPane tlp_aprn_forum;
        //____________________________________________

        //Formateur____________________________
    //vbox
    VBox frm_cours;
    VBox frm_organisme;

    //button
    Button frm_new_cours;
    Button frm_mes_cours;
    Button frm_consult_cours;
    Button frm_search_org;
    Button frm_mes_invitations;
    Button frm_forum;
    //titled pane
    TitledPane tlp_frm_cours;
    TitledPane tlp_frm_org;
    TitledPane tlp_frm_forum;
        //____________________________________________

        //admin____________________________
    //vbox
    VBox adm_log;
    VBox adm_reclamation;
    VBox adm_utilisateur;
    VBox adm_organisme;
    VBox adm_forum;

    //button
    Button adm_logb;
    Button adm_reclamationb;
    Button adm_new_user;
    Button adm_consult_users;
    Button adm_new_org;
    Button adm_consult_org;
    Button adm_forum_ban;
    //titled pane
    TitledPane tlp_adm_log;
    TitledPane tlp_adm_reclamation;
    TitledPane tlp_adm_users;
    TitledPane tlp_adm_org;
    TitledPane tlp_adm_forum;

        //____________________________________________
    Accordion acrd_v_menu = new Accordion();

    public MOOCAccueilGUI() {
        
           Scene scene=new Scene(root,1500, 800);
        scene.getStylesheets().add("/com/styles/Gui_style.css");
       initStyle(StageStyle.UNDECORATED);
        setScene(scene);
     show();
     init_stage();
     init_apprenant();
        
        //stage.setMaximized(true);
        //ResizeHelper.addResizeListener(stage);
        
        
        //get default size and position
        stage_height=getHeight();
        stage_width=getWidth();
        stage_x=getX();
        stage_y=getY();
        setHeight(stage_height-47);
        
        //plateform decoration button event
        b_close.setOnAction(e -> close());   
        b_reduce.setOnAction(e -> setIconified(true));
        b_maxsize.setOnAction((ActionEvent e) -> {
          setWidth(stage_width);
          setHeight(stage_height-47);
          setX(stage_x);
          setY(stage_y);
        });
        //button vmenu
        
        //set stage draggable
       root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
              System.out.println("clicked nik omék !"+v_menu.getWidth());
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            setX(event.getScreenX() - xOffset);
            setY(event.getScreenY() - yOffset);
        });
        //stage state listener
        maximizedProperty().addListener((ObservableValue<? extends Boolean> prop, Boolean oldValue, Boolean newValue) -> {
            System.out.println("Iconified? " + newValue);
            if(newValue==false){
                System.out.println("xx");
            }
        });
        
        
        //btn vertical menu clicked
        btn_vmenu.setOnAction((ActionEvent e)->{
            System.out.println("clicked  !"+v_menu.getWidth());
        });
    }
public void init_stage(){
        //t1.setAlignment(Pos.CENTER_RIGHT);
        //set css class
            //root
   
        root.getStyleClass().add("root");
            //tool menu button 
        b_close.getStyleClass().add("btn_close");
        b_reduce.getStyleClass().add("btn_reduce");
        b_maxsize.getStyleClass().add("btn_maxsize");
            //menu
        plt_menu.getStyleClass().add("plt_menu");
        tool_menu.getStyleClass().add("tool_menu");
        h_menu.getStyleClass().add("h_menu");
        v_menu.getStyleClass().add("v_menu");
        h_racourci.getStyleClass().add("h_racourci");
        btn_home.getStyleClass().add("btn_home");
        btn_notif.getStyleClass().add("btn_notif");
        btn_mail.getStyleClass().add("btn_mail");
        
        //set size
            //menu
        plt_menu.setPrefHeight(30);
        tool_menu.setPrefHeight(60);
        h_menu.setPrefHeight(90);
        h_racourci.setPrefHeight(60);
        h_racourci.setPrefWidth(300);
        v_menu.setPrefWidth(200);
        btn_home.setPrefSize(60, 60);
        btn_notif.setPrefSize(60, 60);
        btn_mail.setPrefSize(60, 60);
        
            //button
        b_close.setPrefSize(30, 30);
        b_reduce.setPrefSize(30, 30);
        b_maxsize.setPrefSize(30, 30);
        btn_vmenu.setPrefSize(200, 90);
        btn_vmenu.getStyleClass().add("btn_vmenu");
        //photode_profile
        Image image = new Image(getClass().getResourceAsStream("/com/images/user.png"),60,60,false,false);
        ImageView photo=new ImageView(image);
        Circle clip = new Circle(30, 30, 30);
        photo.setClip(clip);
        photo.getStyleClass().add("photo");
        Label user_name= new Label("username");
        user_name.getStyleClass().add("user_name");
        //grade
        Label user_grade= new Label("(Apprenant)",user_name);
        user_grade.getStyleClass().add("user_grade"); 
        
        user.setGraphic(photo);  
        MenuItem deco=new MenuItem("deconnecter");
        deco.getStyleClass().add("user_menu_item");
        user.getItems().add(deco);
        user.getStyleClass().add("user_menu_button");
        //user.setText("username");
        
        
        //add nods
        h_menu.getChildren().addAll(plt_menu,tool_menu);
        v_menu.getChildren().addAll(btn_vmenu);
        //tool_menu.getChildren().add(header); 
       //add docoration plateform button
        plt_menu.setAlignment(Pos.TOP_RIGHT);
        plt_menu.getChildren().addAll(b_reduce,b_maxsize,b_close);
        tool_menu.setAlignment(Pos.CENTER_RIGHT);
        h_racourci.setAlignment(Pos.CENTER);
        h_racourci.getChildren().addAll(btn_mail,btn_notif,btn_home);
        tool_menu.getChildren().add(h_racourci);
        //accordion.getPanes().addAll(t1, t2, t3,t4,t5);
        //v_menu.getChildren().add(accordion);
        container.setTop(h_menu);
        root.setLeft(v_menu);
        root.setCenter(container);
    }
    
    public void init_apprenant(){
        //init_______________________________________________
            //button
        aprn_mes_cours=new Button("mes cours", null);
        aprn_search_cours=new Button("chercher cours", null);
        aprn_mes_epreuve=new Button("mes epreuves", null);
        aprn_search_frmt=new Button("formateur", null);
        aprn_search_org=new Button("organisme", null);
        aprn_event=new Button("evenement", null);
        aprn_challenge=new Button("challenge", null);
        aprn_forumb=new Button("forum", null);
        aprn_mes_sujet=new Button("mes sujet", null);
                    //vbox
        aprn_cours=new VBox();
        aprn_epreuve=new VBox();
        aprn_evnt_chall=new VBox();
        aprn_fmt_org=new VBox();
        aprn_forum=new VBox();
            //titled pane
        
        tlp_aprn_cours=new TitledPane("Cours", aprn_cours);
        
        tlp_aprn_epreuve=new TitledPane("Epreuve", aprn_epreuve);
        tlp_aprn_evnt_chall=new TitledPane("Event & Challenge", aprn_evnt_chall);
        tlp_aprn_fmt_org=new TitledPane("Formateur & Organisme", aprn_fmt_org);
        tlp_aprn_forum=new TitledPane("Forum", aprn_forum);
        //size_______________________________________________
        //css________________________________________________
        aprn_mes_cours.getStyleClass().add("v_menu_btn");
        aprn_search_cours.getStyleClass().add("v_menu_btn");
        aprn_mes_epreuve.getStyleClass().add("v_menu_btn");
        aprn_search_frmt.getStyleClass().add("v_menu_btn");
        aprn_search_org.getStyleClass().add("v_menu_btn");
        aprn_event.getStyleClass().add("v_menu_btn");
        aprn_challenge.getStyleClass().add("v_menu_btn");
        aprn_forumb.getStyleClass().add("v_menu_btn");
        aprn_mes_sujet.getStyleClass().add("v_menu_btn");
        //apply nodes________________________________________
        aprn_cours.getChildren().addAll(aprn_mes_cours,aprn_search_cours);
        aprn_epreuve.getChildren().addAll(aprn_mes_epreuve);
        aprn_evnt_chall.getChildren().addAll(aprn_event,aprn_challenge);
        aprn_fmt_org.getChildren().addAll(aprn_search_frmt,aprn_search_org);
        aprn_forum.getChildren().addAll(aprn_forumb,aprn_mes_sujet);
        
        acrd_v_menu.getPanes().addAll(tlp_aprn_cours,tlp_aprn_epreuve,tlp_aprn_evnt_chall,tlp_aprn_fmt_org,tlp_aprn_forum);
        v_menu.getChildren().addAll(new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),new Label(),acrd_v_menu);
    }
    public void init_formateur(){
            //button
        frm_new_cours=new Button(Application.STYLESHEET_MODENA, root);
        frm_mes_cours=new Button(Application.STYLESHEET_MODENA, root);
        frm_consult_cours=new Button(Application.STYLESHEET_MODENA, root);
        frm_search_org=new Button(Application.STYLESHEET_MODENA, root);
        frm_mes_invitations=new Button(Application.STYLESHEET_MODENA, root);
        frm_forum=new Button(Application.STYLESHEET_MODENA, root);
                    //vbox
        frm_cours=new VBox();
        frm_organisme=new VBox();
            //titled pane
        tlp_frm_cours=new TitledPane(Application.STYLESHEET_MODENA, container);
        tlp_frm_org=new TitledPane(Application.STYLESHEET_MODENA, container);
        tlp_frm_forum=new TitledPane(Application.STYLESHEET_MODENA, container);
    }
    public void init_admin(){
            //button
        adm_logb=new Button(Application.STYLESHEET_MODENA, root);
        adm_reclamationb=new Button(Application.STYLESHEET_MODENA, root);
        adm_new_user=new Button(Application.STYLESHEET_MODENA, root);
        adm_consult_users=new Button(Application.STYLESHEET_MODENA, root);
        adm_new_org=new Button(Application.STYLESHEET_MODENA, root);
        adm_consult_org=new Button(Application.STYLESHEET_MODENA, root);
        adm_forum_ban=new Button(Application.STYLESHEET_MODENA, root);
            //vbox
        adm_log=new VBox();
        adm_reclamation=new VBox();
        adm_utilisateur=new VBox();
        adm_organisme=new VBox();
        adm_forum=new VBox();
            //titled pane
        tlp_adm_log=new TitledPane(Application.STYLESHEET_MODENA, container);
        tlp_adm_reclamation=new TitledPane(Application.STYLESHEET_MODENA, container);
        tlp_adm_users=new TitledPane(Application.STYLESHEET_MODENA, container);
        tlp_adm_org=new TitledPane(Application.STYLESHEET_MODENA, container);
        tlp_adm_forum=new TitledPane(Application.STYLESHEET_MODENA, container);
    }
    
}
