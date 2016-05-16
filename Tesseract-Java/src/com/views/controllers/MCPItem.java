/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.models.daos.interfaces.IFormateurDAO;
import com.models.daos.interfaces.implementations.ImplCoursDAO;
import com.models.daos.interfaces.implementations.ImplFormateurDAO;
import com.models.daos.interfaces.implementations.ImplMatiereDAO;
import com.models.entities.Cours;
import com.models.entities.Formateur;
import com.qoppa.pdf.PDFException;
import com.qoppa.pdfViewerFX.PDFViewer;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Bacem
 */
public class MCPItem extends HBox {

    VBox data;
    Image img;
    ImageView photo;
    Label lb_name;
    Label lb_mail;
    Label lb_adress;
    Label lb_courses;
    Label lb_datens;
    Label lb_tel;

    VBox tools;
    HBox tools_btn;
    Button btn_add;
    Button btn_view;
    Formateur formateur;

    VBox courses;
    PieChart chart;
    Button btn_cv;
    Button btn_close;

    VBox info;
    
    PDFViewer m_PDFViewer = new PDFViewer();

    public MCPItem(Formateur f, String type) throws SQLException {
        formateur = new Formateur();
        formateur = f;
        if (type == "display") {
    //nodes_____________________________________________________________________
    /*data=new VBox();
             img= new Image("file:"+f.getPhoto(), 80, 80, false, false);
             photo = new ImageView(img);
             Circle clip = new Circle(40, 40, 40);
             photo.setClip(clip);
             lb_name=new Label(f.getNom()+"  "+f.getPrenom());
             lb_mail=new Label(f.getMail());
             lb_adress=new Label(f.getAdresse());    
             ImplCoursDAO dao =new ImplCoursDAO();
             lb_courses=new Label("    000"+dao.findCoursByIdFromateur(f.getIdUtilisateur()).size());
             //size_____________________________________________________________________
             this.setPrefSize(1195, 90);
             lb_courses.setPrefWidth(250);
             //css
             this.getStyleClass().add("item");
             lb_name.getStyleClass().add("name");
             lb_mail.getStyleClass().add("data_mail");
             lb_adress.getStyleClass().add("data_adress");
             lb_courses.getStyleClass().add("nbrc");
             HBox.setMargin(data, new Insets(3, 0, 3, 10));
             //appl node
             data.getChildren().addAll(lb_name,lb_mail,lb_adress);
             this.getChildren().addAll(photo,data);
    
             }else if(type=="add"){
        
             data=new VBox();
             img= new Image("file:"+f.getPhoto(), 80, 80, false, false);
             photo = new ImageView(img);
             Circle clip = new Circle(40, 40, 40);
             photo.setClip(clip);
             lb_name=new Label(f.getNom()+"  "+f.getPrenom());
             lb_mail=new Label(f.getMail());
             lb_adress=new Label(f.getAdresse());    
             ImplCoursDAO dao =new ImplCoursDAO();
             lb_courses=new Label("    000"+dao.findCoursByIdFromateur(f.getIdUtilisateur()).size());
             btn_add=new Button("add");
             btn_view=new Button("view");
             tools_btn=new HBox(btn_add,btn_view);
             tools=new VBox(tools_btn,lb_courses);
             //size_____________________________________________________________________
             this.setPrefSize(1195, 90);
             lb_courses.setPrefWidth(250);
             data.setPrefWidth(400);
             //css
             this.getStyleClass().add("item");
             lb_name.getStyleClass().add("name");
             lb_mail.getStyleClass().add("data_mail");
             lb_adress.getStyleClass().add("data_adress");
             lb_courses.getStyleClass().add("nbrc");
             btn_add.getStyleClass().add("btn_add");
             btn_view.getStyleClass().add("btn_view");
             HBox.setMargin(data, new Insets(3, 0, 3, 120));
             HBox.setMargin(btn_view, new Insets(0, 0, 0, 20));
             //appl node
             data.getChildren().addAll(lb_name,lb_mail,lb_adress);
             this.getChildren().addAll(photo,data,tools); 
             //event
             btn_add.setOnAction((ActionEvent e)->{
             AdminOrganisation adm=new AdminOrganisation();
             adm=(AdminOrganisation)CurrentUser.getUtilisateur();
             ImplInvitationDAO dv=new ImplInvitationDAO();
             java.sql.Date d = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
             Invitation i=new Invitation(f.getIdUtilisateur(),adm.getOrganisationn().getIdOrganisation() ,"fo", "ATT", d);
             try {
             dv.createInvitation(i);
             OrganismeCoachesInterface oc=(OrganismeCoachesInterface)OrganismeCurrentInterface.getNd();
             oc.getOrganismeCoachInterface().LoadCoaches(-1, -1);
             oc.getOrganismeCoachRequestInterface().LoadCoaches(-1, -1);
             oc.getOrganismeNewCoachInterface().LoadCoaches(-1, -1,"");
             } catch (SQLException ex) {
             Logger.getLogger(MCPItem.class.getName()).log(Level.SEVERE, null, ex);
             }
        
         
             });
    
             }else if(type=="display_info"){
             btn_close=new Button();
             btn_cv=new Button("   CV");
             courses=new VBox();
             data=new VBox();
             img= new Image("file:"+f.getPhoto(), 100, 100, false, false);
             photo = new ImageView(img);
             lb_name=new Label(f.getNom()+"  "+f.getPrenom());
             lb_mail=new Label(f.getMail());
             lb_adress=new Label(f.getAdresse());    
             lb_datens=new Label(f.getDateNaissance().toString());
             lb_tel=new Label(""+f.getTel());
             ImplCoursDAO dao =new ImplCoursDAO();
             lb_courses=new Label("   Courses    000"+dao.findCoursByIdFromateur(f.getIdUtilisateur()).size());
             //size_____________________________________________________________________
             this.setPrefSize(1195, 300);
             lb_courses.setPrefWidth(250);
             data.setPrefWidth(400);
             btn_close.setPrefSize(30, 30);
             //css
             this.getStyleClass().add("item");
             lb_name.getStyleClass().add("name");
             lb_mail.getStyleClass().add("data_mail");
             lb_adress.getStyleClass().add("data_adress");
             lb_courses.getStyleClass().add("nbrc");
             lb_datens.getStyleClass().add("data_date");
             lb_tel.getStyleClass().add("data_tel");
             btn_cv.getStyleClass().add("btn_cv");
             btn_close.getStyleClass().add("btn_close");
             HBox.setMargin(data, new Insets(3, 0, 3, 100));
             VBox.setMargin(lb_mail, new Insets(10, 0, 0, 0));
             VBox.setMargin(lb_adress, new Insets(10, 0, 0, 0));
             VBox.setMargin(lb_datens, new Insets(10, 0, 0, 0));
             VBox.setMargin(lb_tel, new Insets(10, 0, 0, 0));
             VBox.setMargin(btn_cv, new Insets(10, 0, 0, 0));
             //appl node
             courses.getChildren().add(lb_courses);
             LoadCoachCourses();
             data.getChildren().addAll(lb_name,lb_mail,lb_adress,lb_datens,lb_tel,btn_cv);
             this.getChildren().addAll(photo,data,courses); 
             CoachChart();
    
             //events____________________________________________________________________
             btn_cv.setOnAction((ActionEvent e)->{
             PDFViewer m_PDFViewer = new PDFViewer();
             try {
             m_PDFViewer.loadPDF("C:\\Users\\Choukou_Tracker\\Downloads\\ORM et CRUD.pdf");
             } catch (PDFException ex) {
             Logger.getLogger(MCPItem.class.getName()).log(Level.SEVERE, null, ex);
             }
             Stage st=new Stage(StageStyle.UTILITY);
             st.setScene(new Scene(m_PDFViewer));
             st.centerOnScreen();
             st.show();
             });
             btn_close.setOnAction((ActionEvent e)->{
             //animation____________
             ScaleTransition ft = new ScaleTransition(Duration.millis(500), OrganismeCurrentInterface.getNd().getTop());
             ft.setFromY(1);
             ft.setFromX(1);
             ft.setToY(0);
             ft.setToX(0);
             ft.setCycleCount(1);
             ft.setAutoReverse(true);
             ft.play();
             //____________________
             Timeline timeline = new Timeline(new KeyFrame(
             Duration.millis(500),
             ae -> {
             OrganismeCurrentInterface.getNd().setTop(null);
             }
             ));
             timeline.play();
                
             });
             }else if(type=="request"){
             AdminOrganisation adm=new AdminOrganisation();
             adm=(AdminOrganisation)CurrentUser.getUtilisateur();
    
             VBox odata=new VBox();
             VBox fdata=new VBox();
             info=new VBox();
    
             Image imgf= new Image("file:"+f.getPhoto(), 80, 80, false, false);
             ImageView photof = new ImageView(imgf);
             Circle clipf = new Circle(40, 40, 40);
             photof.setClip(clipf);
    
             Label lb_fname=new Label(f.getNom()+"  "+f.getPrenom());
    
             fdata.getChildren().addAll(photof,lb_fname);
             //size_____________________________________________________
             info.setMinWidth(200);
             fdata.setPrefWidth(120);
    
             //css_____________________________________________________
             this.getStyleClass().add("item");
             lb_fname.getStyleClass().add("req_info");
             // lb_oname.getStyleClass().add("req_info");
             odata.setPadding(new Insets(2, 5, 0, 5));
             fdata.setPadding(new Insets(2, 5, 0, 5));
    
    
    
             this.getChildren().addAll(fdata,info);
             */
        } else if (type == "joinRequest") {
            VBox fdata = new VBox();
            info = new VBox();

            Image imgf = new Image("file:" + f.getPhoto(), 80, 80, false, false);
            ImageView photof = new ImageView(imgf);
            Circle clipf = new Circle(40, 40, 40);
            photof.setClip(clipf);

            Label lb_fname = new Label(f.getNom() + "  " + f.getPrenom());

            fdata.getChildren().addAll(photof, lb_fname);
            //size_____________________________________________________
            info.setMinWidth(300);
            fdata.setPrefWidth(120);

            //css_____________________________________________________
            this.getStyleClass().add("item");
            lb_fname.getStyleClass().add("courseTitle-label");
            // lb_oname.getStyleClass().add("req_info");
            fdata.setPadding(new Insets(2, 5, 0, 5));

            this.getChildren().addAll(fdata, info);

            Button btn_accept = new Button("Accept");
            Button btn_refuse = new Button("Refuse");
            Button btn_cv = new Button("CV");
            /*btn_accept.setPrefWidth(180);
            btn_refuse.setPrefWidth(180);
            btn_cv.setPrefWidth(120);*/
            btn_accept.getStyleClass().add("accept-btn");
            btn_refuse.getStyleClass().add("refuse-btn");
            btn_cv.getStyleClass().add("cv-btn");
            info.getChildren().addAll(btn_cv, btn_accept, btn_refuse);
            //info.setPadding(new Insets(0, 0, 0, 10));

            IFormateurDAO implFormateurDAO = new ImplFormateurDAO();
            
            //events______________________________________________
            btn_accept.setOnAction((ActionEvent e) -> {
                implFormateurDAO.accRefFormateur(f, 1);
            });

            btn_refuse.setOnAction((ActionEvent e) -> {
                implFormateurDAO.accRefFormateur(f, 0);

            });
            btn_cv.setOnAction((ActionEvent e)->{
                try {
                    m_PDFViewer.loadPDF("‪\\com\\cv\\a.pdf");
                } catch (PDFException ex) {
                    java.util.logging.Logger.getLogger(MCPItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
               Stage st=new Stage(StageStyle.UTILITY);
               st.setScene(new Scene(m_PDFViewer));
               st.centerOnScreen();
               st.show();
            });

        }

    }

    public Button getBtn_view() {
        return btn_view;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void LoadCoachCourses() throws SQLException {
        ImplCoursDAO dao = new ImplCoursDAO();
        ImplMatiereDAO mdao = new ImplMatiereDAO();
        for (Cours c : dao.findCoursByIdFromateur(formateur.getIdUtilisateur())) {
            Label l = new Label(c.getNomCours() + "  ");
            Label m = new Label(mdao.findMatiereById(c.getIdMatiere()).getNomMatiere());
            l.getStyleClass().add("name");
            m.getStyleClass().add("lb_mat");
            courses.getChildren().add(new HBox(l, m));

        }

    }/*
     public void CoachChart() throws SQLException{
     ImplCoursDAO dao=new ImplCoursDAO();
     ImplSessionCoursDAO sdao=new ImplSessionCoursDAO();
     List lst=new ArrayList();
     for(Cours c : dao.findCoursByIdFromateur(formateur.getIdUtilisateur())){
     System.out.println(c.getNomCours()+"   "+sdao.getSessionCoursbyCours(c.getIdCours()).size());
     lst.add(new PieChart.Data(c.getNomCours(), sdao.getSessionCoursbyCours(c.getIdCours()).size()));
     }
     ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(lst);
     chart = new PieChart(pieChartData);
                
     this.getChildren().add(chart);
     this.getChildren().add(btn_close);
     }*/
    /*
     public void setInvit(Invitation inv){
    
     Label lb_dt=new Label(inv.getDateInvitation().toString());
     lb_dt.getStyleClass().add("req_info_cnt");
     System.out.println("etat : "+inv.getEtat());
        
     if (inv.getEtat().equals("ATT") && inv.getSens().equals("fo") ) {
     Button btn_accept = new Button("accept");
     Button btn_refuse = new Button("Refuse");
     btn_accept.setPrefWidth(180);
     btn_refuse.setPrefWidth(180);
     btn_accept.getStyleClass().add("btn_accpt");
     btn_refuse.getStyleClass().add("btn_refuse");
     info.getChildren().addAll(lb_dt, btn_accept, btn_refuse);
     info.setPadding(new Insets(0, 0, 0, 10));
            
     //events______________________________________________
     btn_accept.setOnAction((ActionEvent e)->{
     try {
     inv.setEtat("ACC");
     ImplInvitationDAO idao=new ImplInvitationDAO();
     ImplFormateurDAO fdao=new ImplFormateurDAO();
     idao.updateInvitation(inv);
     fdao.joinOrganisation(inv.getIdUtilisateur(), inv.getIdOrganisation());
     OrganismeCoachesInterface oc=(OrganismeCoachesInterface)OrganismeCurrentInterface.getNd();
     oc.getOrganismeCoachInterface().LoadCoaches(-1, -1);
     oc.getOrganismeCoachRequestInterface().LoadCoaches(-1, -1);
     oc.getOrganismeNewCoachInterface().LoadCoaches(-1, -1,"");
     } catch (SQLException ex) {
     Logger.getLogger(MCPItem.class.getName()).log(Level.SEVERE, null, ex);
     }
                
                
     });
            
     btn_refuse.setOnAction((ActionEvent e)->{
     try {
     inv.setEtat("REF");
     ImplInvitationDAO idao=new ImplInvitationDAO();
     idao.updateInvitation(inv);
                    
     OrganismeCoachesInterface oc=(OrganismeCoachesInterface)OrganismeCurrentInterface.getNd();
     oc.getOrganismeCoachInterface().LoadCoaches(-1, -1);
     oc.getOrganismeCoachRequestInterface().LoadCoaches(-1, -1);
     oc.getOrganismeNewCoachInterface().LoadCoaches(-1, -1,"");
     } catch (SQLException ex) {
     Logger.getLogger(MCPItem.class.getName()).log(Level.SEVERE, null, ex);
     }
                
     });
            
     }
     else if(inv.getEtat().equals("ATT") && inv.getSens().equals("of")){
     Label etat=new Label("Pending ...");
     Button btn_cancel = new Button("cancel request");
            
     //csss____________________________________________________
     etat.getStyleClass().add("wait");
     btn_cancel.getStyleClass().add("btn_refuse");
     btn_cancel.setPrefWidth(180);
            
     info.getChildren().addAll(etat , btn_cancel);
            
     btn_cancel.setOnAction((ActionEvent e)->{
     try {
     ImplInvitationDAO idao=new ImplInvitationDAO();
     ImplFormateurDAO fdao=new ImplFormateurDAO();
     idao.deleteInvitation(inv);
                    
     OrganismeCoachesInterface oc=(OrganismeCoachesInterface)OrganismeCurrentInterface.getNd();
     oc.getOrganismeCoachInterface().LoadCoaches(-1, -1);
     oc.getOrganismeCoachRequestInterface().LoadCoaches(-1, -1);
     oc.getOrganismeNewCoachInterface().LoadCoaches(-1, -1,"");
     } catch (SQLException ex) {
     Logger.getLogger(MCPItem.class.getName()).log(Level.SEVERE, null, ex);
     }
     });
            
            
     }else if(inv.getEtat().equals("ACC") ){
     Label etat=new Label("Accepted");
     Button btn_cancel = new Button("Kick from team");
            
     //csss____________________________________________________
     etat.getStyleClass().add("acc");
     btn_cancel.getStyleClass().add("btn_refuse");
     btn_cancel.setPrefWidth(180);
            
     info.getChildren().addAll(etat ,lb_dt, btn_cancel);
            
     btn_cancel.setOnAction((ActionEvent e)->{
     try {
     ImplInvitationDAO idao=new ImplInvitationDAO();
     ImplFormateurDAO fd=new ImplFormateurDAO();
     idao.deleteInvitation(inv);
     fd.QuitOrganisation(formateur);
                    
                    
     OrganismeCoachesInterface oc=(OrganismeCoachesInterface)OrganismeCurrentInterface.getNd();
     oc.getOrganismeCoachInterface().LoadCoaches(-1, -1);
     oc.getOrganismeCoachRequestInterface().LoadCoaches(-1, -1);
     oc.getOrganismeNewCoachInterface().LoadCoaches(-1, -1,"");
     } catch (SQLException ex) {
     Logger.getLogger(MCPItem.class.getName()).log(Level.SEVERE, null, ex);
     }
     });
            
            
     }
        
     }*/

}
