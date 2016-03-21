/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.views.controllers;

import com.controllers.IServiceFormateurs;
import com.controllers.IServiceFormateursImpl;
import com.models.entities.Cours;
import com.models.entities.Formateur;
import com.models.entities.Log;
import com.models.entities.ScoreUtilisateur;
import com.models.entities.SessionCours;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Interval;

/**
 * FXML Controller class
 *
 * @author haikal
 */
public class DashboardCoachFXMLController implements Initializable {

    List<SessionCours> listCoursStat = new ArrayList<>();
    @FXML
    private Pane p1;
    @FXML
    private Label viewThisWeek;
    @FXML
    private Label viewsLastWeek;
    @FXML
    private Pane p2;
    @FXML
    private Label logThidWeek;
    @FXML
    private Label legLastWeek;
    @FXML
    private Label totalLog;
    @FXML
    private Pane p3;
    @FXML
    private Label totRank;
    @FXML
    private Label myRank;
    @FXML
    private Pane p4;
    @FXML
    private Pane p5;
    @FXML
    private BarChart<String, Long> barChart;
    @FXML
    private LineChart<String, Long> LineChart;
    private static final String JANUARY = "JANUARY";
    private static final String FEBRUARY = "FEBRUARY";
    private static final String MARCH = "MARCH";
    private static final String APRIL = "APRIL";
    private static final String MAY = "MAY";
    private static final String JUNE = "JUNE";
    private static final String JULY = "JULY";
    private static final String AUGUST = "AUGUST";
    private static final String SEPTEMBER = "SEPTEMBER";
    private static final String OCTOBER = "OCTOBER";
    private static final String NOVEMBER = "NOVEMBER";
    private static final String DECEMBER = "DECEMBER";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateStatViews();
        generateStatLogin();
        generateStatRank();
        generateBarChart();
        generateLineChart();

    }

    private void generateStatViews() {
        IServiceFormateurs formateurs = new IServiceFormateursImpl();
        listCoursStat = formateurs.getListCoursConsulted(CurrentUser.getId());
        LocalDate now = LocalDate.now();
        LocalDate start = now.with(DayOfWeek.MONDAY);
        LocalDate end = now.with(DayOfWeek.SUNDAY);
        LocalDate start0 = start.minusDays(7);
        System.out.println("[  " + start.toString() + " , " + end.toString() + "  ]");
        System.out.println("[  " + start.minusDays(7) + " , " + start.toString() + "  ]");
        int i = 0;
        int j = 0;
        Interval interval = new Interval(new DateMidnight(start.getYear(), start.getMonth().getValue(), start.getDayOfMonth()), new DateMidnight(end.getYear(), end.getMonth().getValue(), end.getDayOfMonth()));
        Interval interval2 = new Interval(new DateMidnight(start0.getYear(), start0.getMonth().getValue(), start0.getDayOfMonth()), new DateMidnight(start.getYear(), start.getMonth().getValue(), start.getDayOfMonth()));

        for (SessionCours se : listCoursStat) {
            // System.out.println( LocalDate.now().getDayOfWeek().compareTo(se.getDate_session().toLocalDate().getDayOfWeek()));
            System.out.println(se.getDate_session().toLocalDate().toString());
            if (!interval.contains(new DateTime(se.getDate_session().toLocalDate().getYear(), se.getDate_session().toLocalDate().getMonthValue(), se.getDate_session().toLocalDate().getDayOfMonth(), 0, 0, 0))) {
                i++;

            } else if (!interval2.contains(new DateTime(se.getDate_session().toLocalDate().getYear(), se.getDate_session().toLocalDate().getMonthValue(), se.getDate_session().toLocalDate().getDayOfMonth(), 0, 0, 0))) {
                j++;
            }
        }
        viewThisWeek.setText(i + "");
        viewsLastWeek.setText(j + "");
    }

    private void generateStatLogin() {
        IServiceFormateurs formateurs = new IServiceFormateursImpl();
        List<Log> log = formateurs.getLoginUtilisateur(CurrentUser.getId());

        long count;
        count = log.stream()
                .filter(logg -> logg.getTache().equals("CONNECTED"))
                .count();
        totalLog.setText(count + "");
        LocalDate now = LocalDate.now();
        LocalDate start = now.with(DayOfWeek.MONDAY);
        LocalDate end = now.with(DayOfWeek.SUNDAY);
        LocalDate start0 = start.minusDays(7);
        Interval interval = new Interval(new DateMidnight(start.getYear(), start.getMonth().getValue(), start.getDayOfMonth()), new DateMidnight(end.getYear(), end.getMonth().getValue(), end.getDayOfMonth()));
        Interval interval2 = new Interval(new DateMidnight(start0.getYear(), start0.getMonth().getValue(), start0.getDayOfMonth()), new DateMidnight(start.getYear(), start.getMonth().getValue(), start.getDayOfMonth()));

        int i = 0;
        int j = 0;
        for (Log logger : log) {
            if (!interval.contains(new DateTime(logger.getDateTache().toLocalDate().getYear(), logger.getDateTache().toLocalDate().getMonthValue(), logger.getDateTache().toLocalDate().getDayOfMonth(), 0, 0, 0))) {
                i++;

            } else if (!interval2.contains(new DateTime(logger.getDateTache().toLocalDate().getYear(), logger.getDateTache().toLocalDate().getMonthValue(), logger.getDateTache().toLocalDate().getDayOfMonth(), 0, 0, 0))) {
                j++;
            }
        }
        logThidWeek.setText(i + "");
        legLastWeek.setText(j + "");
    }

    private void generateStatRank() {
        IServiceFormateurs formateurs = new IServiceFormateursImpl();
        int i = formateurs.afficherNombreFormateurs();
        totRank.setText(i + "");
        int rank = formateurs.getRankFormateur(CurrentUser.getId());
        myRank.setText(rank + "");
    }

    private void generateBarChart() {
        IServiceFormateurs formateurs = new IServiceFormateursImpl();
        Map<String, Long> map = formateurs.getValuesByMonthViews();
        barChart.setTitle("Views this year");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Months");
        yAxis.setLabel("Number of views");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Total");
        series1.getData().add(new XYChart.Data(JANUARY, map.get("JANUARY")));
        series1.getData().add(new XYChart.Data(FEBRUARY, map.get("FEBRUARY")));
        series1.getData().add(new XYChart.Data(MARCH, map.get("MARCH")));
        series1.getData().add(new XYChart.Data(APRIL, map.get("APRIL")));
        series1.getData().add(new XYChart.Data(MAY, map.get("MAY")));
        series1.getData().add(new XYChart.Data(JUNE, map.get("JUNE")));
        series1.getData().add(new XYChart.Data(JULY, map.get("JULY")));
        series1.getData().add(new XYChart.Data(AUGUST, map.get("AUGUST")));
        series1.getData().add(new XYChart.Data(SEPTEMBER, map.get("SEPTEMBER")));
        series1.getData().add(new XYChart.Data(OCTOBER, map.get("OCTOBER")));
        series1.getData().add(new XYChart.Data(NOVEMBER, map.get("NOVEMBER")));
        series1.getData().add(new XYChart.Data(DECEMBER, map.get("DECEMBER")));

        barChart.getData().add(series1);

    }

    private void generateLineChart() {
        IServiceFormateurs formateurs = new IServiceFormateursImpl();
       List<ScoreUtilisateur> map = formateurs.getTop5Utilisateur();
        barChart.setTitle("Top Students");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Total");
        System.out.println("------------"+map.size()+"");
        for (ScoreUtilisateur scc : map) {
            System.out.println(scc);
            series1.getData().add(new XYChart.Data(scc.getNom(),scc.getNumbreOfSubs()));
        }
        LineChart.getData().add(series1);
          
    }

}
