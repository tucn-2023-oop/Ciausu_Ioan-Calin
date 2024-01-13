package com.example.demo;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightDetailView {

    public Text flightIdField;
    public Text airlineField;
    public Text airplananeModelField;
    public Text airplaneCapacityField;
    public Text captainField;
    public Text captainContactField;
    public Text airlineContactField;
    private Connection db;
    private int flightId;

    public void setDb(Connection db){this.db = db;}
    public void setFlightId(int flightId){this.flightId = flightId;}
    public void updateContents(){
        try{
            ResultSet resultSetFlight = db.createStatement().executeQuery("SELECT * FROM flights WHERE flightid=" + flightId);
            resultSetFlight.next();
            int planeId = resultSetFlight.getInt("planeid");
            int pilotId = resultSetFlight.getInt("crewid");

            ResultSet resultSetPilot = db.createStatement().executeQuery("SELECT * FROM pilots WHERE pilotid=" + pilotId);
            resultSetPilot.next();
            String captainName = resultSetPilot.getString("name");
            String captainContact = resultSetPilot.getString("contacts");

            ResultSet resultSetPlane = db.createStatement().executeQuery("SELECT * FROM planes WHERE planeid=" + planeId);
            resultSetPlane.next();
            String planeModel = resultSetPlane.getString("model");
            int capacity = resultSetPlane.getInt("capacity");
            int airlineId = resultSetPlane.getInt("airlineid");

            ResultSet resultSetAirline= db.createStatement().executeQuery("SELECT * FROM airlinecompanies WHERE companyid=" + airlineId);
            resultSetAirline.next();
            String airlineName = resultSetAirline.getString("name");
            String airlineContact = resultSetAirline.getString("contactinformation");

            flightIdField.setText("Flight id: " + String.valueOf(flightId));
            airlineField.setText("Airline: " + airlineName);
            airlineContactField.setText("Contact: " + airlineContact);
            airplananeModelField.setText("Airplane model: " + planeModel);
            airplaneCapacityField.setText("Capacity: " + String.valueOf(capacity));
            captainField.setText("Captain: " + captainName);
            captainContactField.setText("Contact:" + captainContact);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void doneClick(ActionEvent actionEvent) {
        Stage stage = (Stage) captainField.getScene().getWindow();
        stage.close();
    }
}
