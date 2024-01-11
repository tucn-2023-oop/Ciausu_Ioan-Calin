package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangePassView {
    public TextField singUpUserNameField;
    public PasswordField signUpPassField;
    public PasswordField signUpPass2;
    public Text errorMessage;

    private Connection db;
    private int userId;

    public void setDb(Connection db){this.db = db;}

    public void setUserId(int userId){this.userId = userId;}
    public void changeClick(ActionEvent actionEvent) {
        if(! signUpPassField.getText().contentEquals(signUpPass2.getText())){
            errorMessage.setText("Passwords do not match");
            return;
        }
        try{
            db.createStatement().executeUpdate(
                "UPDATE users SET pass='" + signUpPassField.getText() + "' WHERE userid=" + userId
            );
            Stage stage = (Stage) signUpPass2.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
            errorMessage.setText("Database error");
        }
    }
}
