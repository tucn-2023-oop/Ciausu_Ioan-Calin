package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {

    HelloController controller;
    Connection db;

    void setUp() {
        dbConnection conector = new dbConnection();
        db = conector.connectionDb("airport","postgres","12345678");
        controller = new HelloController();
        controller.signUpErrorText = new Text();
        controller.logInErrorText = new Text();
        controller.setDb(db);
    }

    @Test
    void testLogInCorectCombo(){
        setUp();
        String username = "John";
        String pass = "hello";
        assertTrue(controller.canLogIn(username,pass));
    }

    @Test
    void testLogInIncorectCombo(){
        setUp();
        String username = "John";
        String pass = "hell";
        assertFalse(controller.canLogIn(username,pass));
    }

    @Test
    void testSignUp(){
        setUp();
        Random rand = new Random();
        String username;
        String pass = "00";
        do {
            username = String.valueOf(rand.nextInt());
        }while(controller.canLogIn(username,pass));

        //String pass = "hello";
    }
}