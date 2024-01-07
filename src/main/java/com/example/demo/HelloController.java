package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class HelloController {
    private Connection db;

    @FXML
    private Label welcomeText;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passField;

    public void setDb(Connection db) {
        this.db = db;
    }

    public void onSignupButtonClik(ActionEvent actionEvent) {
        passField.setVisible(true);
        welcomeText.setText("Signned in" + passField.getText());
    }

    public void onLoginButtonClick(ActionEvent actionEvent) {
        try (Statement statement = db.createStatement()) {
            // Example query
            String query = "SELECT pass FROM users WHERE username='" + usernameField.getText()+"'";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            if(resultSet.getString("pass").contentEquals( passField.getText()))
                welcomeText.setText("Authentication failed");
            else
                welcomeText.setText("Authentication successful");

        } catch (SQLException e) {
           // e.printStackTrace();
            welcomeText.setText("Authentication failed");
        }
    }

}

