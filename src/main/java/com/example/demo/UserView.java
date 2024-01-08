package com.example.demo;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.input.MouseEvent;

        import java.net.URL;
        import java.sql.*;
        import java.util.ResourceBundle;

public class UserView implements Initializable{

    @FXML
    private TableColumn<Flight, Timestamp> arrivalTime;

    @FXML
    private TableColumn<Flight, String> crewLeader;

    @FXML
    private TableColumn<Flight, Timestamp> departureTime;

    @FXML
    private TableView<Flight> departures_table;

    @FXML
    private TableColumn<Flight, Integer> emptySeats;

    @FXML
    private TableColumn<Flight, Integer> flightId;

    @FXML
    private TableColumn<Flight, String> from;

    @FXML
    private TextField fromField;

    @FXML
    private TableColumn<Flight, String> plane;

    @FXML
    private Button reserve;

    @FXML
    private Button searchFlights;

    @FXML
    private TableColumn<Flight, String> to;

    @FXML
    private TextField toField;

    private Integer index;
    Connection db;

    public void setDb(Connection db) {
        this.db = db;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        to.setCellValueFactory(new PropertyValueFactory<Flight,String>("to"));
        from.setCellValueFactory(new PropertyValueFactory<Flight,String>("from"));
        departureTime.setCellValueFactory(new PropertyValueFactory<Flight,Timestamp>("departureTime"));
        arrivalTime.setCellValueFactory(new PropertyValueFactory<Flight,Timestamp>("arrivalTime"));
        plane.setCellValueFactory(new PropertyValueFactory<Flight,String>("plane"));
        crewLeader.setCellValueFactory(new PropertyValueFactory<Flight,String>("crewLeader"));
        emptySeats.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("emptySeats"));
        flightId.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("flightId"));
    }

    private String generateSearchSql(){
        String q = "SELECT * FROM flights";
        if(!(fromField.getText().isEmpty() & toField.getText().isEmpty()))
            q = q + " WHERE ";
        if(!fromField.getText().isEmpty()) {
            q = q + "destinationairport='" + fromField.getText() + "'";
            if(!toField.getText().isEmpty())
                q = q + " AND";
        }
        if(!toField.getText().isEmpty())
            q = q + "departureairport='"+toField.getText() + "'";
        return q;
    }
    @FXML
    public void searchClick(ActionEvent actionEvent) {
        ObservableList<Flight> data = FXCollections.observableArrayList();
        try {
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(generateSearchSql());
            while (resultSet.next()) {
                String to = resultSet.getString("destinationairport");
                String from = resultSet.getString("departureairport");
                Timestamp departureTime = resultSet.getTimestamp("departuretime");
                Timestamp arrivalTime = resultSet.getTimestamp("arrivaltime");
                int planeId = resultSet.getInt("planeid");
                int crewLeaderId = resultSet.getInt("crewid");
                int emptySeats = resultSet.getInt("empty_seats");
                int flightId = resultSet.getInt("flightId");

                //get plane and crewLeader name
                ResultSet resultSetPlane = db.createStatement().executeQuery("SELECT model FROM planes WHERE planeId=" + planeId);
                resultSetPlane.next();
                String plane = resultSetPlane.getString("model");

                ResultSet resultSetCrew = db.createStatement().executeQuery("SELECT name FROM employees WHERE employeeid=" + crewLeaderId);
                resultSetCrew.next();
                String crewLeader = resultSetCrew.getString("name");

                //set data
                data.add(new Flight(to, from, departureTime, arrivalTime, plane, crewLeader, emptySeats,flightId));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        departures_table.setItems(data);
    }

    public void newFlightSelected(MouseEvent mouseEvent) {
        index = departures_table.getSelectionModel().getSelectedIndex();
    }

    public void reseveClick(ActionEvent actionEvent) {

    }
}
