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
        if(canLogIn(userName,passField.getText()))
            changeStage();
    }
    boolean canLogIn(String username,String pass){
        try{
            Statement statement = db.createStatement();
            String query = "SELECT pass FROM users WHERE username='" + username+"'";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            if(resultSet.getString("pass").contentEquals(pass)) {
                return true;
            }else {
                logInErrorText.setText("Wrong user name or password");
                return false;
            }
        } catch (SQLException e) {
            logInErrorText.setText("Wrong user name or password");
            return false;
        }
    }
    public void onSignupButtonClik(ActionEvent actionEvent) {
        userName = singUpUserNameField.getText();
       if(canSignUp(singUpUserNameField.getText(),signUpPassField.getText(),signUpPass2.getText()))
           changeStage();
    }

    boolean canSignUp(String username,String pass,String pass2){
        if(username.isEmpty()){
            signUpErrorText.setText("Insert user name");
            return false;
        }
        if(! pass.contentEquals(pass2)){
            signUpErrorText.setText("Passwords do not match");
            return false;
        }
        try{
            Statement statement = db.createStatement();
            int succes = statement.executeUpdate(
                    "INSERT INTO users (username,pass) " +
                            "VALUES ('" + username + "' , '" + pass +  "')"
            );
            return true;
        } catch (SQLException e) {
            signUpErrorText.setText("User name already in use");
            return false;
        }
    }
    private void changeStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            UserView controller = fxmlLoader.getController();
            controller.setDb(db);
            controller.setUser(userName);
            controller.refreshTickets();
            Stage stage = (Stage) passField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch(IOException ignored){};
    }
}

