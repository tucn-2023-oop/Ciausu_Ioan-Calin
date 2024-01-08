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

    Connection db;

    Timestamp t = new Timestamp(1,1,1,1,1,1,1);
    ObservableList<Flight> list = FXCollections.observableArrayList(
        new Flight("lol","lo",t,t,"sal","sal",2)
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        to.setCellValueFactory(new PropertyValueFactory<Flight,String>("to"));
        from.setCellValueFactory(new PropertyValueFactory<Flight,String>("from"));
        departureTime.setCellValueFactory(new PropertyValueFactory<Flight,Timestamp>("departureTime"));
        arrivalTime.setCellValueFactory(new PropertyValueFactory<Flight,Timestamp>("arrivalTime"));
        plane.setCellValueFactory(new PropertyValueFactory<Flight,String>("plane"));
        crewLeader.setCellValueFactory(new PropertyValueFactory<Flight,String>("crewLeader"));
        emptySeats.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("emptySeats"));
    }

    @FXML
    public void searchClick(ActionEvent actionEvent) {
        ObservableList<Flight> data = FXCollections.observableArrayList();
        try {
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM flights");


            while (resultSet.next()) {
                String to = resultSet.getString("destinationairport");
                String from = resultSet.getString("departureairport");
                Timestamp departureTime = resultSet.getTimestamp("departuretime");
                Timestamp arrivalTime = resultSet.getTimestamp("arrivaltime");
                String plane = resultSet.getString("planeid");
                String crewLeader = resultSet.getString("crewid");
                int emptySeats = resultSet.getInt("empty_seats");

                data.add(new Flight(to, from, departureTime, arrivalTime, plane, crewLeader, emptySeats));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        departures_table.setItems(data);
    }


    public void setDb(Connection db) {
        this.db = db;
    }

    public void newFlightSelected(MouseEvent mouseEvent) {
    }
}
