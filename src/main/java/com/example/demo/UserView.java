package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;

public class UserView {
    private Connection db;
    @FXML
    private TextField fromField;
    @FXML
    private TextField toField;
    @FXML
    private TableView departures_table;
    @FXML
    private Button searchFlights;
    @FXML
    private Button reserve;
    public void setDb(Connection db) {
        this.db = db;
    }

    public void newFlightSelected(MouseEvent mouseEvent) {

    }
}
