package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passField;
    public void onSignupButtonClik(ActionEvent actionEvent) {
        welcomeText.setText("Logged in");
    }

    public void onLoginButtonClick(ActionEvent actionEvent) {
        welcomeText.setText("Sign in");
    }

}

