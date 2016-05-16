package com.views.controllers;

import com.models.daos.interfaces.INotificationDAO;
import com.models.daos.interfaces.IUtilisateurDAO;
import com.models.daos.interfaces.implementations.ImplNotificationDAO;
import com.models.daos.interfaces.implementations.ImplUtilisateurDAO;
import com.models.entities.Notification;
import com.models.enums.Role;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
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

    private Button btn_vmenu = new Button();
    private MenuButton user = new MenuButton("username");
    private Button btn_home = new Button();
    private Button btn_notif = new Button();
    private Button btn_mail = new Button();

    // MCP
    private VBox aprn_profil;
    private VBox aprn_epreuve;
    private VBox aprn_evnt_chall;
    private VBox aprn_fmt_org;
    private VBox aprn_cherch;
    private VBox aprn_MesC;
    private VBox aprn_avv;
    private VBox aprn_recl;
    private VBox aprn_avis;
    //Formateur section
    //buttons
    private Button MCP_new_courses;
    private Button MCP_content_courses;
    private Button MCP_va_coach;
    private Button MCP_ac_coach;
    private Button Mo_V_courses;
    private Button Mo_V_coaches;
    //titled pane
    private TitledPane tlpMCPCourses;
    private TitledPane tlpMCPCoaches;
    private TitledPane tlpMCPStats;
    //VBOX
    private VBox MCP_Courses;
    private VBox MCP_Coaches;
    private VBox MCP_Stats;
    private static boolean valeur = true;
    //_________________________________v_menu_____________________
    //apprennant____________________________
    //vbox  
    private VBox aprn_cours;

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
    private Button reclaims;
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
    private Button aprn_search_cours_matiere;
    private Button aprn_search_cours_Formateur;
    private Button aprn_search_cours_Organisme;
    private Button aprn_search_cours_Nom;
    private Button aprn_mes_epreuve_Entrainement;
    private Button aprn_mes_epreuve_Finale;
    private Button aprn_mes_epreuve_Objectifs;
    private Button aprn_search_frmt;
    private Button aprn_search_org;
    private Button aprn_event;
    private Button aprn_challenge;
    private Button aprn_mes_cours_NN_terminer;
    private Button aprn_mes_cours_terminer;
    private Button aprn_consl_profil;
    private Button aprn_mod_profil;
    private Button aprn_avvcment_tous;
    private Button aprn_avvcment_ep;
    private Button aprn_recl_admin;
    private Button aprn_avis_cours;
    private Button aprn_Formateurn;
    private Button aprn_mes_event;
    private Button aprn_mes_challenge;
    private Button mes_badge;
    //titled pane
    private TitledPane tlp_aprn_cours;
    private TitledPane tlp_aprn_epreuve;
    private TitledPane tlp_aprn_evnt_chall;
    private TitledPane tlp_aprn_fmt_org;
    private TitledPane tlp_aprn_forum;
    //____________________________________________

    private TitledPane tlp_aprn_MesCours;
    private TitledPane tlp_aprn_cher_cour;

    private TitledPane tlp_aprn_Avannc;
    private TitledPane tlp_aprn_Relm;
    private TitledPane tlp_aprn_Evn_Chal;
    private TitledPane tlp_aprn_avis;
    private TitledPane tlp_aprn_eprv;
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
    Pane notifPane;
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
        btn_notif.setOnMouseClicked((MouseEvent event) -> {
            if (valeur) {
                notifPane = createPane();
                container.getChildren().add(notifPane);

            } else if (valeur == false) {
                container.getChildren().remove(notifPane);
            }

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
        plt_menu.getChildren().addAll(b_reduce, b_close);
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
        mes_badge = new Button("Mes Badges", null);
        aprn_mes_event = new Button("Chercher Evenement", null);
        aprn_mes_challenge = new Button("Chercher Challenge", null);
        aprn_avvcment_ep = new Button("Avancement Epreuve", null);
        aprn_mes_cours_NN_terminer = new Button("Cours En Cours", null);
        aprn_mes_cours_terminer = new Button("Cours Terminer", null);
        aprn_Formateurn = new Button("Meilleur Formateur", null);
        aprn_search_cours_matiere = new Button("Matiere", null);
        aprn_search_cours_Formateur = new Button("Formateur", null);
        aprn_search_cours_Organisme = new Button("Organisme", null);
        aprn_search_cours_Nom = new Button("Cours", null);
        aprn_mes_epreuve_Entrainement = new Button("Epreuve Entrainement", null);
        aprn_mes_epreuve_Finale = new Button("Epreuve Finale", null);
        aprn_mes_epreuve_Objectifs = new Button("Epreuve Objectifs", null);
        aprn_search_frmt = new Button("formateur", null);
        aprn_search_org = new Button("organisme", null);
        aprn_event = new Button("Evenement", null);
        aprn_challenge = new Button("Challenge", null);
        aprn_consl_profil = new Button("Consulter", null);
        aprn_mod_profil = new Button("Modifier", null);
        aprn_avvcment_tous = new Button("Avancement Cours", null);
        aprn_recl_admin = new Button("Reclamation", null);
        aprn_avis_cours = new Button("Commenter Cours", null);
        aprn_mes_cours_terminer.setOnAction((event) -> {
            System.out.println("here");
            try {

                setMain(loadNode("/com/fxml/ListCoursTerminerFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        aprn_recl_admin.setOnAction((event) -> {
            try {

                setMain(loadNode("/com/fxml/ReclamationInsertfxml.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_Formateurn.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/StatFormateurfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                StatFormateurfxmlController controller = fxmlLoader.<StatFormateurfxmlController>getController();

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_search_org.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherOrgFormfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ChercherOrgFormfxmlController controller = fxmlLoader.<ChercherOrgFormfxmlController>getController();
                controller.setType("o");

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_mes_challenge.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChallengEvenementfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ChallengEvenementfxmlController controller = fxmlLoader.<ChallengEvenementfxmlController>getController();
                controller.setType("c");

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_mes_event.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChallengEvenementfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ChallengEvenementfxmlController controller = fxmlLoader.<ChallengEvenementfxmlController>getController();
                controller.setType("e");

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_challenge.setOnAction((event) -> {
            try {

                setMain(loadNode("/com/fxml/ListeChallangefxml.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_event.setOnAction((event) -> {
            try {

                setMain(loadNode("/com/fxml/ListeEvenementfxml.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_search_frmt.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherOrgFormfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ChercherOrgFormfxmlController controller = fxmlLoader.<ChercherOrgFormfxmlController>getController();
                controller.setType("f");

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_search_cours_Nom.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherCoursfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ChercherCoursfxmlController controller = fxmlLoader.<ChercherCoursfxmlController>getController();
                controller.setType("c");

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_search_cours_Organisme.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherCoursfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ChercherCoursfxmlController controller = fxmlLoader.<ChercherCoursfxmlController>getController();
                controller.setType("o");

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_search_cours_Formateur.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherCoursfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ChercherCoursfxmlController controller = fxmlLoader.<ChercherCoursfxmlController>getController();
                controller.setType("f");

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_search_cours_matiere.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChercherCoursfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ChercherCoursfxmlController controller = fxmlLoader.<ChercherCoursfxmlController>getController();
                controller.setType("m");

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        aprn_avis_cours.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/CommCoursFXML.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                CommCoursFXMLController controller = fxmlLoader.<CommCoursFXMLController>getController();
                controller.setId_User(CurrentUser.getId());

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        btn_mail.setOnAction((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/MailXML.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                MailFXMLController controller = fxmlLoader.<MailFXMLController>getController();

                controller.setType("k");

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        btn_notif.setOnAction((event) -> {

            try {

                setMain(loadNode("/com/fxml/ListNotificationFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        aprn_consl_profil.setOnAction((event) -> {

            try {

                setMain(loadNode("/com/fxml/ProfilApprenantFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        aprn_mod_profil.setOnAction((event) -> {

            try {

                setMain(loadNode("/com/fxml/ApprenantUpdateFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        aprn_mes_epreuve_Finale.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChoixCoursEpFinal.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ChoixCoursEpFinalController controller = fxmlLoader.<ChoixCoursEpFinalController>getController();
                controller.setType("Final");
                controller.setId_User(CurrentUser.getId());

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        aprn_avvcment_ep.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ListCoursAvancementfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ListCoursAvancementfxmlController controller = fxmlLoader.<ListCoursAvancementfxmlController>getController();
                controller.setType("ep");
                controller.setId_User(CurrentUser.getId());

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        aprn_avvcment_tous.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/AvancementCoursfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                AvancementCoursfxmlController controller = fxmlLoader.<AvancementCoursfxmlController>getController();
                controller.setType("cr");

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        aprn_mes_cours_NN_terminer.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/SuivreCoursfxml.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                SuivreCoursfxmlController controller = fxmlLoader.<SuivreCoursfxmlController>getController();

                controller.setId_User(CurrentUser.getId());

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        aprn_mes_epreuve_Entrainement.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChoixCoursEpFinal.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ChoixCoursEpFinalController controller = fxmlLoader.<ChoixCoursEpFinalController>getController();
                controller.setType("Entrainement");
                controller.setId_User(CurrentUser.getId());

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        mes_badge.setOnAction((event) -> {

            try {

                setMain(loadNode("/com/fxml/ListeBadgefxml.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        aprn_mes_epreuve_Objectifs.setOnAction((event) -> {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fxml/ChoixCoursEpFinal.fxml"));

                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

                ChoixCoursEpFinalController controller = fxmlLoader.<ChoixCoursEpFinalController>getController();
                controller.setType("Objectifs");
                controller.setId_User(CurrentUser.getId());

                paneMain.getChildren().setAll(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        //vbox
        aprn_profil = new VBox();
        aprn_epreuve = new VBox();
        aprn_cherch = new VBox();
        aprn_MesC = new VBox();
        aprn_evnt_chall = new VBox();
        aprn_fmt_org = new VBox();
        aprn_avv = new VBox();
        aprn_recl = new VBox();
        aprn_avis = new VBox();
        //titled pane

        tlp_aprn_cours = new TitledPane("Mon Profil", aprn_profil);
        tlp_aprn_MesCours = new TitledPane("Mes Cours", aprn_MesC);
        tlp_aprn_cher_cour = new TitledPane("Chercher Cours", aprn_cherch);
        tlp_aprn_fmt_org = new TitledPane("Formateur & Organisme", aprn_fmt_org);
        tlp_aprn_Avannc = new TitledPane("Avancement", aprn_avv);
        tlp_aprn_Relm = new TitledPane("Reclamation", aprn_recl);
        tlp_aprn_Evn_Chal = new TitledPane("Event & Challenge", aprn_evnt_chall);
        tlp_aprn_avis = new TitledPane("Avis Cours", aprn_avis);
        tlp_aprn_eprv = new TitledPane("Epreuve", aprn_epreuve);

        //size_______________________________________________
        //css________________________________________________
        aprn_consl_profil.getStyleClass().add("v_menu_btn");
        aprn_mes_epreuve_Entrainement.getStyleClass().add("v_menu_btn");
        aprn_mes_epreuve_Finale.getStyleClass().add("v_menu_btn");
        aprn_mes_epreuve_Objectifs.getStyleClass().add("v_menu_btn");
        aprn_search_frmt.getStyleClass().add("v_menu_btn");
        aprn_search_org.getStyleClass().add("v_menu_btn");
        aprn_event.getStyleClass().add("v_menu_btn");
        aprn_challenge.getStyleClass().add("v_menu_btn");
        aprn_mes_cours_NN_terminer.getStyleClass().add("v_menu_btn");
        aprn_mes_cours_terminer.getStyleClass().add("v_menu_btn");
        aprn_search_cours_matiere.getStyleClass().add("v_menu_btn");
        aprn_search_cours_Formateur.getStyleClass().add("v_menu_btn");
        aprn_search_cours_Organisme.getStyleClass().add("v_menu_btn");
        aprn_search_cours_Nom.getStyleClass().add("v_menu_btn");
        aprn_consl_profil.getStyleClass().add("v_menu_btn");
        aprn_mod_profil.getStyleClass().add("v_menu_btn");
        aprn_avvcment_tous.getStyleClass().add("v_menu_btn");
        aprn_recl_admin.getStyleClass().add("v_menu_btn");
        aprn_avis_cours.getStyleClass().add("v_menu_btn");
        aprn_Formateurn.getStyleClass().add("v_menu_btn");
        aprn_avvcment_ep.getStyleClass().add("v_menu_btn");
        aprn_mes_challenge.getStyleClass().add("v_menu_btn");
        aprn_mes_event.getStyleClass().add("v_menu_btn");
        mes_badge.getStyleClass().add("v_menu_btn");
        //apply nodes________________________________________
        aprn_MesC.getChildren().addAll(aprn_mes_cours_NN_terminer, aprn_mes_cours_terminer);
        aprn_epreuve.getChildren().addAll(aprn_mes_epreuve_Objectifs, aprn_mes_epreuve_Entrainement, aprn_mes_epreuve_Finale);
        aprn_evnt_chall.getChildren().addAll(aprn_event, aprn_challenge, aprn_mes_challenge, aprn_mes_event);
        aprn_fmt_org.getChildren().addAll(aprn_search_frmt, aprn_search_org, aprn_Formateurn);
        aprn_avv.getChildren().addAll(aprn_avvcment_tous, aprn_avvcment_ep);
        aprn_cherch.getChildren().addAll(aprn_search_cours_matiere, aprn_search_cours_Formateur, aprn_search_cours_Organisme, aprn_search_cours_Nom);
        aprn_recl.getChildren().addAll(aprn_recl_admin);
        aprn_avis.getChildren().addAll(aprn_avis_cours);
        aprn_profil.getChildren().addAll(aprn_consl_profil, aprn_mod_profil, mes_badge);
        acrd_v_menu.getPanes().addAll(tlp_aprn_cours, tlp_aprn_MesCours, tlp_aprn_cher_cour, tlp_aprn_fmt_org, tlp_aprn_Avannc, tlp_aprn_Evn_Chal, tlp_aprn_eprv, tlp_aprn_avis, tlp_aprn_Relm);
        v_menu.getChildren().addAll(new Label("Welcome " + CurrentUser.getUtilisateur().getNom()), new Label("Your eMail " + CurrentUser.getUtilisateur().getMail()), new Label(), new Label(), new Label(), new Label(), new Label(), acrd_v_menu);

    }

    public void init_formateur() {
//        try {
//            ImageView profimImg = new ImageView();
//            profimImg.setFitHeight(50);
//            profimImg.setFitWidth(50);
//            profimImg.setImage(new Image(new File(CurrentUser.getUtilisateur().getPhoto()).toURI().toString()));
//        } catch (Exception e) {
//
//            Alert alert = new Alert(Alert.AlertType.WARNING, "No Picture", ButtonType.OK);
//            alert.show();
//        }
        Circle circle = new Circle(50);
        circle.setLayoutX(50);
        circle.setLayoutY(50);
//        circle.setFill(new ImagePattern(new Image(new File(CurrentUser.getUtilisateur().getPhoto()).toURI().toString())));
        circle.setStyle(" -fx-border-top-style: solid;\n"
                + "    -fx-border-color: black;");
        Label l = new Label(CurrentUser.getUtilisateur().getNomUtilisateur());
        Label l2 = new Label("as a Coach");
        l.getStyleClass().add("nom_user");
        VBox b = new VBox(circle);
        b.setPrefSize(214.0, 161);

        b.setPadding(new Insets(0, 0, 0, 50));
        VBox b2 = new VBox(l, l2);
        b.setPrefSize(214.0, 130);

        b2.setPadding(new Insets(0, 0, 0, 70));

        l.setStyle("-fx-font-size:14 ; -fx-text-fill:"
                + "    -fx-font-size:10;");
        l2.setStyle("-fx-font-weight: bold;    -fx-font-size:10;-fx-text-fill:#25c4cc;");
        v_menu.getChildren().addAll(new Label(), b, b2);
        statistics = new Button("Dashboard", null);
        myAccount = new Button("My Account", null);
        reclaims = new Button("Reclaims", null);
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
         addCourse.setOnAction((ActionEvent event) -> {
            try {
                setMain(loadNode("/com/fxml/addCourseFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button sendRec = new Button("Send a reclaim", null);
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
        VBox recl = new VBox();
        /**
         * *************
         */
        tlpMyProfile = new TitledPane("My profile", myProfile);
        tlpMyCourses = new TitledPane("My courses", myCourses);
        tlpSubmitedCourses = new TitledPane("Submitted courses", submitedCourses);
        tlpCareersManagment = new TitledPane("Career Managment", careersManagment);
        TitledPane tlpRecl = new TitledPane("Send reclaim", recl);
        /**
         * ***************
         */
        tlpMyCourses.setStyle(" -fx-font-weight: bold;\n"
                + "    -fx-font-size:14;");
        tlpSubmitedCourses.setStyle(" -fx-font-weight: bold;\n"
                + "    -fx-font-size:14;");
        tlpMyProfile.setStyle(" -fx-font-weight: bold;\n"
                + "    -fx-font-size:14;");
        tlpCareersManagment.setStyle(" -fx-font-weight: bold;\n"
                + "    -fx-font-size:14;");
        tlpRecl.setStyle(" -fx-font-weight: bold;\n"
                + "    -fx-font-size:14;");
        statistics.getStyleClass().add("v_menu_btn");
        myAccount.getStyleClass().add("v_menu_btn");
        sendRec.getStyleClass().add("v_menu_btn");
        validatedCourses.getStyleClass().add("v_menu_btn");
        WaitingForValidation1.getStyleClass().add("v_menu_btn");
        WaitingForValidation2.getStyleClass().add("v_menu_btn");
        addCourse.getStyleClass().add("v_menu_btn");
        invitations.getStyleClass().add("v_menu_btn");
        Organisations.getStyleClass().add("v_menu_btn");
        chat.getStyleClass().add("v_menu_btn");
        reclaims.getStyleClass().add("v_menu_btn");

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
        Organisations.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    setMain(loadNode("/com/fxml/CoachBrowseOrganisationsFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        invitations.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    setMain(loadNode("/com/fxml/MyInvitationsCoachFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        myProfile.getChildren().addAll(statistics, myAccount);
        myCourses.getChildren().addAll(validatedCourses);
        submitedCourses.getChildren().addAll(WaitingForValidation1, WaitingForValidation2, addCourse);
        careersManagment.getChildren().addAll(invitations, Organisations, chat);
        recl.getChildren().addAll(sendRec);
        /**
         * ********************
         */
        acrd_v_menu.getPanes().addAll(tlpMyProfile, tlpMyCourses, tlpSubmitedCourses, tlpCareersManagment, tlpRecl);
        v_menu.getChildren().addAll(new Label(), acrd_v_menu);

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
        System.out.println("iniiiiiiiiitMCP");
        MCP_new_courses = new Button("Validate Course", null);
        MCP_content_courses = new Button("Validate Content", null);
        MCP_va_coach = new Button("Validate Coach", null);
        MCP_ac_coach = new Button("Accept Coach", null);
        Mo_V_courses = new Button("Courses Views", null);
        Mo_V_coaches = new Button("Coaches Ratings", null);

        MCP_new_courses.getStyleClass().add("v_menu_btn");
        MCP_content_courses.getStyleClass().add("v_menu_btn");
        MCP_va_coach.getStyleClass().add("v_menu_btn");
        MCP_ac_coach.getStyleClass().add("v_menu_btn");
        Mo_V_courses.getStyleClass().add("v_menu_btn");
        Mo_V_coaches.getStyleClass().add("v_menu_btn");

        MCP_Courses = new VBox();
        MCP_Coaches = new VBox();
        MCP_Stats = new VBox();

        tlpMCPCourses = new TitledPane("Courses", MCP_Courses);
        tlpMCPCoaches = new TitledPane("Coaches", MCP_Coaches);
        tlpMCPStats = new TitledPane("Statistics", MCP_Stats);

        MCP_Courses.getChildren().addAll(MCP_new_courses, MCP_content_courses);
        MCP_Coaches.getChildren().addAll(MCP_va_coach, MCP_ac_coach);
        MCP_Stats.getChildren().addAll(Mo_V_courses, Mo_V_coaches);

        MCP_new_courses.setOnAction((event) -> {
            System.out.println("here");
            try {

                setMain(loadNode("/com/fxml/ValidateCourseFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        MCP_content_courses.setOnAction((event) -> {
            System.out.println("here");
            try {

                setMain(loadNode("/com/fxml/ValidateContentFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        MCP_va_coach.setOnAction((event) -> {
            System.out.println("here");
            try {

                setMain(loadNode("/com/fxml/ValidateCoachFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        MCP_ac_coach.setOnAction((event) -> {
            System.out.println("here");
            try {

                setMain(loadNode("/com/fxml/AcceptCoachFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        Mo_V_courses.setOnAction((event) -> {
            System.out.println("here");
            try {

                setMain(loadNode("/com/fxml/CoursesViewsFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        Mo_V_coaches.setOnAction((event) -> {
            System.out.println("here");
            try {

                setMain(loadNode("/com/fxml/CoachesRatingsFXML.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        acrd_v_menu.getPanes().addAll(tlpMCPCourses, tlpMCPCoaches, tlpMCPStats);
        v_menu.getChildren().addAll(new Label(CurrentUser.getUtilisateur().getNomUtilisateur()), new Label(), new Label(), acrd_v_menu);

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

    public Pane createPane() {
        System.out.println("create");
        Pane p1 = new Pane();
        ImageView im = new ImageView(new Image("/com/images/triangle.png"));
        p1.setPrefSize(228, 323);
        Pane p2 = new Pane(im);
        p2.setPrefSize(20, 15);
        im.setFitHeight(20);
        im.setFitWidth(15);
        Pane p3 = new Pane();

        p3.setPrefSize(228, 307);
        p3.setLayoutX(-150);
        p3.setLayoutY(0);
        AnchorPane ap = new AnchorPane();

        ap.setPrefSize(228, 307);
        ScrollPane pane = new ScrollPane(ap);
        ap.setStyle("-fx-background-color:white;");
        pane.setStyle("-fx-border-color: #25c4cc ; -fx-border-width:2px;");
        p3.getChildren().add(pane);
        p3.setLayoutY(15);
        p1.setLayoutX(940);
        p1.setLayoutY(75);
        initialize(ap);
        p1.getChildren().addAll(p2, p3);
        return p1;
    }

    private void initialize(AnchorPane ap) {
//        System.out.println("init");
//        int x = 0;
//        int y = 0;
//        INotificationDAO o = new ImplNotificationDAO();
//        List<Notification> lstN= new ArrayList<>();
//        try {
//            System.out.println("user id"+CurrentUser.getUtilisateur().getIdUtilisateur());
//            lstN = o.displayNotificationByUserId2(CurrentUser.getUtilisateur().getIdUtilisateur());
//            System.out.println(lstN);
//        } catch (SQLException ex) {
//            Logger.getLogger(MOOCAccueilGUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Pane caseNo = new Pane();
//        ImageView img = new ImageView();
//        Label l = new Label();
//        Text t = new Text();
//   System.out.println("list long"+lstN.size());
//        for (Notification lstN1 : lstN) {
//            System.out.println("list long"+lstN.size());
//            caseNo.setPrefSize(228, 95);
//            caseNo.setLayoutX(0);
//            caseNo.setLayoutY(y);
//            img.setFitHeight(86);
//            img.setFitWidth(101);
//            img.setLayoutX(5);
//            img.setLayoutY(5);
//            l.setPrefSize(73, 17);
//            l.setLayoutX(114);
//            l.setLayoutY(14);
//            t.setLayoutX(114);
//            t.setLayoutX(53);
//                  try {
//          
//            img.setFitHeight(50);
//            img.setFitWidth(50);
//            img.setImage(new Image(new File(CurrentUser.getUtilisateur().getPhoto()).toURI().toString()));
//        } catch (NullPointerException e) {
//
//            Alert alert = new Alert(Alert.AlertType.WARNING, "No Picture", ButtonType.OK);
//            alert.show();
//        }
//        Circle circle = new Circle(50);
//        circle.setLayoutX(50);
//        circle.setLayoutY(50);
//        circle.setFill(new ImagePattern(new Image(new File(CurrentUser.getUtilisateur().getPhoto()).toURI().toString())));
//        circle.setStyle(" -fx-border-top-style: solid;\n"
//                + "    -fx-border-color: black;");
//        
//        t.setText(lstN1.getNotification());
//        t.setStyle("  -fx-font-size:20px; -fx-text-fill:#25c4cc; -fx-font-weight: bold;");
//        l.setText(lstN1.getDateNotification().toString());
//        caseNo.setStyle("-fx-background-color: #4f4f4f");
//            System.out.println(lstN1);
//            caseNo.getChildren().addAll(l, t, img);
//            
//            ap.getChildren().add(caseNo);
//            y = y + 106;
      //  }

    }
}
