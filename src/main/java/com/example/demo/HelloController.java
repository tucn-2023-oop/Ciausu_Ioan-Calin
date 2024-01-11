package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    public TextField singUpUserNameField;
    public Button loginButton;
    public Button signupButton;
    public PasswordField signUpPass2;
    public PasswordField signUpPassField;
    public Text signUpErrorText;
    public Text logInErrorText;

    @FXML
    private Label welcomeText;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passField;
    private Connection db;

    private String userName;

    public void setDb(Connection db) {
        this.db = db;
    }
    public void onLoginButtonClick(ActionEvent actionEvent) {
        userName = usernameField.getText();
        try (Statement statement = db.createStatement()) {
            // Example query
            String query = "SELECT pass FROM users WHERE username='" + userName+"'";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            if(resultSet.getString("pass").contentEquals( passField.getText())) {
                changeStage();
            }else {
                logInErrorText.setText("Wrong user name or password");
            }
        } catch (SQLException e) {
           // e.printStackTrace();
            logInErrorText.setText("Wrong user name or password");
        }
    }
    public void onSignupButtonClik(ActionEvent actionEvent) {
        userName = singUpUserNameField.getText();
        if(singUpUserNameField.getText().isEmpty()){
            signUpErrorText.setText("Insert user name");
            return;
        }
        if(! signUpPassField.getText().contentEquals(signUpPass2.getText())){
            signUpErrorText.setText("Passwords do not match");
            return;
        }
        try{
            Statement statement = db.createStatement();
            int succes = statement.executeUpdate(
                    "INSERT INTO users (username,pass) " +
                            "VALUES ('" + singUpUserNameField.getText() + "' , '" + signUpPassField.getText() +  "')"
            );
            changeStage();
        } catch (SQLException e) {
            signUpErrorText.setText("User name already in use");
        }
    }
    private void changeStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            UserView controller = fxmlLoader.getController();
            controller.setDb(db);
            controller.setUser(userName);
            Stage stage = (Stage) passField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch(IOException ignored){};
    }
}

