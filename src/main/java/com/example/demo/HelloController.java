package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    public void onSignupButtonClik(ActionEvent actionEvent) {
        welcomeText.setText("Logged in");
    }

    public void onLoginButtonClick(ActionEvent actionEvent) {
        welcomeText.setText("Sign in");
    }
}

