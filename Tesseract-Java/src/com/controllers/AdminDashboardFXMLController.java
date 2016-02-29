package com.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminDashboardFXMLController implements Initializable {

    @FXML
    private PieChart courses;

    @FXML
    private TableView<?> log;

    @FXML
    private TableView<?> facebook;

    @FXML
    private NumberAxis accountsY;

    @FXML
    private CategoryAxis accountsX;

    @FXML
    private TableColumn<?, ?> loglist;

    @FXML
    private LineChart<?, ?> subscriptionschart;

    @FXML
    private TableView<?> twitterfeed;

    @FXML
    private NumberAxis subscriptionschartY;

    @FXML
    private CategoryAxis subscriptionschartX;

    @FXML
    private TableColumn<?, ?> facebooklist;

    @FXML
    private AreaChart<?, ?> accounts;

    @FXML
    private TableColumn<?, ?> twitterfeedlist;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
