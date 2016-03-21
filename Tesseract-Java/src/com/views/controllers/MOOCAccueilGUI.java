package com.views.controllers;

import com.models.enums.Role;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MOOCAccueilGUI extends Stage {

    private AnchorPane paneMain = new AnchorPane();
    private double xOffset = 0;
    private double yOffset = 0;
    private double stage_width = 0;
    private double stage_height = 0;
    private double stage_x = 0;
    private double stage_y = 0;
    //menus
    private BorderPane root = new BorderPane();
    private BorderPane container = new BorderPane();
    private HBox h_racourci = new HBox();
    private HBox plt_menu = new HBox();
    private HBox tool_menu = new HBox();
    private VBox h_menu = new VBox();
    private HBox b_menu = new HBox();
    private VBox v_menu = new VBox();
    //plateform decoration buttons
    private Button b_close = new Button();
    private Button b_reduce = new Button();
    private Button b_maxsize = new Button();
    private Button btn_vmenu = new Button();
    private MenuButton user = new MenuButton("username");
    private Button btn_home = new Button();
    private Button btn_notif = new Button();
    private Button btn_mail = new Button();

    //_________________________________v_menu_____________________
    //apprennant____________________________
    //vbox  
    private VBox aprn_cours;
    private VBox aprn_epreuve;
    private VBox aprn_evnt_chall;
    private VBox aprn_fmt_org;
    private VBox aprn_forum;
    //Formateur section
    private VBox myProfile;
    private VBox myCourses;
    private VBox submitedCourses;
    private VBox careersManagment;

    private TitledPane tlpMyProfile;
    private TitledPane tlpMyCourses;
    private TitledPane tlpSubmitedCourses;
    private TitledPane tlpCareersManagment;

    private Button statistics;
    private Button myAccount;
    private Button myRank;

    private Button validatedCourses;
    private Button WaitingForValidation1;
    private Button WaitingForValidation2;
    private Button addCourse;

    private Button invitations;
    private Button Organisations;
    private Button chat;
    //**********************
    //button
    private Button aprn_mes_cours;
    private Button aprn_search_cours;
    private Button aprn_mes_epreuve;
    private Button aprn_search_frmt;
    private Button aprn_search_org;
    private Button aprn_event;
    private Button aprn_challenge;
    private Button aprn_forumb;
    private Button aprn_mes_sujet;
    //titled pane
    private TitledPane tlp_aprn_cours;
    private TitledPane tlp_aprn_epreuve;
    private TitledPane tlp_aprn_evnt_chall;
    private TitledPane tlp_aprn_fmt_org;
    private TitledPane tlp_aprn_forum;
        //____________________________________________

    //Formateur____________________________
    //vbox
    private VBox frm_cours;
    private VBox frm_organisme;

    //button
    private Button frm_new_cours;
    private Button frm_mes_cours;
    private Button frm_consult_cours;
    private Button frm_search_org;
    private Button frm_mes_invitations;
    private Button frm_forum;
    //titled pane
    private TitledPane tlp_frm_cours;
    private TitledPane tlp_frm_org;
    private TitledPane tlp_frm_forum;
        //____________________________________________

    //admin____________________________
    //vbox
    private VBox adm_log;
    private VBox adm_reclamation;
    private VBox adm_utilisateur;
    private VBox adm_organisme;
    private VBox adm_forum;

    //button
    private Button adm_logb;
    private Button adm_reclamationb;
    private Button adm_new_user;
    private Button adm_consult_users;
    private Button adm_new_org;
    private Button adm_consult_org;
    private Button adm_forum_ban;
    //titled pane
    private TitledPane tlp_adm_log;
    private TitledPane tlp_adm_reclamation;
    private TitledPane tlp_adm_users;
    private TitledPane tlp_adm_org;
    private TitledPane tlp_adm_forum;

    //____________________________________________
    private Accordion acrd_v_menu = new Accordion();

    public MOOCAccueilGUI() {
        System.out.println("--------------" + CurrentUser.getRole());
        Scene scene = new Scene(root, 1300, 700);
        scene.getStylesheets().add("/com/styles/Gui_style.css");
        initStyle(StageStyle.UNDECORATED);
        setScene(scene);
        show();
        init_stage();

        if (CurrentUser.getRole() == Role.FOR) {
            init_formateur();
        } else if (CurrentUser.getRole() == Role.APR) {
            init_apprenant();
        } else if (CurrentUser.getRole() == Role.ADM) {
            init_admin();
        } else if (CurrentUser.getRole() == Role.MCP) {
            init_MCP();

        } else if (CurrentUser.getRole() == Role.ORG) {
            init_ORG();
        }

        //stage.setMaximized(true);
        //ResizeHelper.addResizeListener(stage);
        //get default size and position
        stage_height = getHeight();
        stage_width = getWidth();
        stage_x = getX();
        stage_y = getY();
        setHeight(stage_height - 47);

        //plateform decoration button event
        b_close.setOnAction(e -> close());
        b_reduce.setOnAction(e -> setIconified(true));
        b_maxsize.setOnAction((ActionEvent e) -> {
            setWidth(stage_width);
            setHeight(stage_height - 47);
            setX(stage_x);
            setY(stage_y);
        });
        //button vmenu

        //set stage draggable
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
            System.out.println("--- v menu" + v_menu.getWidth() + "   " + v_menu.getHeight() + "  |||||   " + h_menu.getWidth() + "      height= " + h_menu.getHeight());
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            setX(event.getScreenX() - xOffset);
            setY(event.getScreenY() - yOffset);
        });
        //stage state listener
        maximizedProperty().addListener((ObservableValue<? extends Boolean> prop, Boolean oldValue, Boolean newValue) -> {
            System.out.println("Iconified? " + newValue);
            if (newValue == false) {
                System.out.println("xx");
            }
        });

        //btn vertical menu clicked
        btn_vmenu.setOnAction((ActionEvent e) -> {
            System.out.println("clicked  !" + v_menu.getWidth());
        });
        System.out.println(v_menu.getPrefWidth() + "     " + v_menu.getPrefHeight());
        System.out.println(h_menu.getPrefWidth() + "     " + h_menu.getPrefHeight());
    }

    public void init_stage() {
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
        Image image = new Image(getClass().getResourceAsStream("/com/images/user.png"), 60, 60, false, false);
        ImageView photo = new ImageView(image);
        Circle clip = new Circle(30, 30, 30);
        photo.setClip(clip);
        photo.getStyleClass().add("photo");
        Label user_name = new Label("username");
        user_name.getStyleClass().add("user_name");
        //grade
        Label user_grade = new Label("(Apprenant)", user_name);
        user_grade.getStyleClass().add("user_grade");

        user.setGraphic(photo);
        MenuItem deco = new MenuItem("deconnecter");
        deco.getStyleClass().add("user_menu_item");
        user.getItems().add(deco);
        user.getStyleClass().add("user_menu_button");
        //user.setText("username");
        paneMain.setPrefHeight(700);
        paneMain.setPrefWidth(1346);
        b_menu.getChildren().addAll(paneMain);

        //add nods
        h_menu.getChildren().addAll(plt_menu, tool_menu);
        v_menu.getChildren().addAll(btn_vmenu);
        //tool_menu.getChildren().add(header); 
        //add docoration plateform button
        plt_menu.setAlignment(Pos.TOP_RIGHT);
        plt_menu.getChildren().addAll(b_reduce, b_maxsize, b_close);
        tool_menu.setAlignment(Pos.CENTER_RIGHT);
        h_racourci.setAlignment(Pos.CENTER);
        h_racourci.getChildren().addAll(btn_mail, btn_notif, btn_home);
        tool_menu.getChildren().add(h_racourci);
        //accordion.getPanes().addAll(t1, t2, t3,t4,t5);
        //v_menu.getChildren().add(accordion);
        container.setTop(h_menu);
        root.setLeft(v_menu);
        root.setCenter(container);
        container.setCenter(b_menu);
        btn_home.setOnAction((ActionEvent event) -> {
            try {
                Stage stage = new Stage();
                Parent root1 = FXMLLoader.load(getClass().getResource("/com/fxml/LogInFXML.fxml"));
                Scene scene1 = new Scene(root1);
                stage.setScene(scene1);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
                hide();
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void init_apprenant() {
        //init_______________________________________________
        //button
        aprn_mes_cours = new Button("mes cours", null);
        aprn_search_cours = new Button("chercher cours", null);
        aprn_mes_epreuve = new Button("mes epreuves", null);
        aprn_search_frmt = new Button("formateur", null);
        aprn_search_org = new Button("organisme", null);
        aprn_event = new Button("evenement", null);
        aprn_challenge = new Button("challenge", null);
        aprn_forumb = new Button("forum", null);
        aprn_mes_sujet = new Button("mes sujet", null);
        //vbox
        aprn_cours = new VBox();
        aprn_epreuve = new VBox();
        aprn_evnt_chall = new VBox();
        aprn_fmt_org = new VBox();
        aprn_forum = new VBox();
        //titled pane

        tlp_aprn_cours = new TitledPane("Cours", aprn_cours);

        tlp_aprn_epreuve = new TitledPane("Epreuve", aprn_epreuve);
        tlp_aprn_evnt_chall = new TitledPane("Event & Challenge", aprn_evnt_chall);
        tlp_aprn_fmt_org = new TitledPane("Formateur & Organisme", aprn_fmt_org);
        tlp_aprn_forum = new TitledPane("Forum", aprn_forum);
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
        aprn_cours.getChildren().addAll(aprn_mes_cours, aprn_search_cours);
        aprn_epreuve.getChildren().addAll(aprn_mes_epreuve);
        aprn_evnt_chall.getChildren().addAll(aprn_event, aprn_challenge);
        aprn_fmt_org.getChildren().addAll(aprn_search_frmt, aprn_search_org);
        aprn_forum.getChildren().addAll(aprn_forumb, aprn_mes_sujet);

        acrd_v_menu.getPanes().addAll(tlp_aprn_cours, tlp_aprn_epreuve, tlp_aprn_evnt_chall, tlp_aprn_fmt_org, tlp_aprn_forum);
        v_menu.getChildren().addAll(new Label("Welcome " + CurrentUser.getUtilisateur().getNom()), new Label("Your eMail " + CurrentUser.getUtilisateur().getMail()), new Label(), new Label(), new Label(), new Label(), new Label(), acrd_v_menu);
    }

    public void init_formateur() {
        statistics = new Button("Dashboard", null);
        myAccount = new Button("My Account", null);

        validatedCourses = new Button("Validated courses", null);
        validatedCourses.setOnAction((event) -> {
            System.out.println("here");
            try {

                setMain(loadNode("/com/fxml/DisplayValidateCoursesFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        myAccount.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    setMain(loadNode("/com/fxml/MyAccountCoachFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        WaitingForValidation1 = new Button("Waiting for Validation1", null);
        WaitingForValidation2 = new Button("Waiting for Validation2", null);
        addCourse = new Button("Add course", null);

        invitations = new Button("My invitations", null);
        Organisations = new Button("Check Organisations", null);
        chat = new Button("Chat with coachs", null);

        /**
         * **************************
         */
        myProfile = new VBox();
        myCourses = new VBox();
        submitedCourses = new VBox();
        careersManagment = new VBox();
        /**
         * *************
         */
        tlpMyProfile = new TitledPane("My profile", myProfile);
        tlpMyCourses = new TitledPane("My courses", myCourses);
        tlpSubmitedCourses = new TitledPane("Submited courses", submitedCourses);
        tlpCareersManagment = new TitledPane("Career Managment", careersManagment);
        /**
         * ***************
         */
        statistics.getStyleClass().add("v_menu_btn");
        myAccount.getStyleClass().add("v_menu_btn");

        validatedCourses.getStyleClass().add("v_menu_btn");
        WaitingForValidation1.getStyleClass().add("v_menu_btn");
        WaitingForValidation2.getStyleClass().add("v_menu_btn");
        addCourse.getStyleClass().add("v_menu_btn");
        invitations.getStyleClass().add("v_menu_btn");
        Organisations.getStyleClass().add("v_menu_btn");
        chat.getStyleClass().add("v_menu_btn");

        /**
         * *******************
         */
        statistics.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
             try {

                    setMain(loadNode("/com/fxml/DashboardCoachFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        myProfile.getChildren().addAll(statistics, myAccount);
        myCourses.getChildren().addAll(validatedCourses);
        submitedCourses.getChildren().addAll(WaitingForValidation1, WaitingForValidation2, addCourse);
        careersManagment.getChildren().addAll(invitations, Organisations, chat);
        /**
         * ********************
         */
        acrd_v_menu.getPanes().addAll(tlpMyProfile, tlpMyCourses, tlpSubmitedCourses, tlpCareersManagment);
        v_menu.getChildren().addAll(new Label(), new Label(), new Label(), new Label(), new Label(), new Label(), new Label(), acrd_v_menu);

    }

    public void init_admin() {
        //button
        adm_logb = new Button(Application.STYLESHEET_MODENA, root);
        adm_reclamationb = new Button(Application.STYLESHEET_MODENA, root);
        adm_new_user = new Button(Application.STYLESHEET_MODENA, root);
        adm_consult_users = new Button(Application.STYLESHEET_MODENA, root);
        adm_new_org = new Button(Application.STYLESHEET_MODENA, root);
        adm_consult_org = new Button(Application.STYLESHEET_MODENA, root);
        adm_forum_ban = new Button(Application.STYLESHEET_MODENA, root);
        //vbox
        adm_log = new VBox();
        adm_reclamation = new VBox();
        adm_utilisateur = new VBox();
        adm_organisme = new VBox();
        adm_forum = new VBox();
        //titled pane
        tlp_adm_log = new TitledPane(Application.STYLESHEET_MODENA, container);
        tlp_adm_reclamation = new TitledPane(Application.STYLESHEET_MODENA, container);
        tlp_adm_users = new TitledPane(Application.STYLESHEET_MODENA, container);
        tlp_adm_org = new TitledPane(Application.STYLESHEET_MODENA, container);
        tlp_adm_forum = new TitledPane(Application.STYLESHEET_MODENA, container);
    }

    private void init_MCP() {

    }

    private void init_ORG() {

    }

    private AnchorPane loadNode(String addresse) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(addresse));

        return anchorPane;
    }

    public void setMain(Node node) {

        paneMain.getChildren().setAll(node);

    }
}
