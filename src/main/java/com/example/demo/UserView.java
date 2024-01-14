package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class UserView implements Initializable{

    public Button changePassButton;
    public Text fullFlightText;
    @FXML
    private TableView<Flight> departures_table;
    @FXML
    private TableColumn<Flight, Timestamp> arrivalTime;
    @FXML
    private TableColumn<Flight, Integer> emptySeats;
    @FXML
    private TableColumn<Flight, Timestamp> departureTime;
    @FXML
    private TableColumn<Flight, Integer> flightId;
    @FXML
    private TableColumn<Flight, String> from;
    @FXML
    private TableColumn<Flight, String> plane;
    @FXML
    private TableColumn<Flight, String> to;



    @FXML
    private TableView<Ticket> ticketsTable;
    @FXML
    private TableColumn<Ticket,String> from1;
    @FXML
    private TableColumn<Ticket,String> to1;
    @FXML
    private TableColumn<Ticket,Timestamp> departureTime1;
    @FXML
    private TableColumn<Ticket,Integer> ticketId1;
    @FXML
    private TableColumn<Ticket,Integer> seat1;

    @FXML
    private Button reserve;
    @FXML
    private Button searchFlights;
    @FXML
    private Button logOutButton;
    @FXML
    private TextField toField;
    @FXML
    private TextField fromField;
    @FXML
    private Text userNameText;

    private Integer index;
    private Ticket selectedFlightTicket;
    Connection db;
    Integer userId;
    String userName;

    public void setDb(Connection db) {
        this.db = db;
    }

    public void setUser(String userName){
        this.userName = userName;
        try{
            ResultSet resultSet = db.createStatement().executeQuery("SELECT userid FROM users WHERE username='" + userName +"'");
            resultSet.next();
            userId = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userNameText.setText(userName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        to.setCellValueFactory(new PropertyValueFactory<Flight,String>("to"));
        from.setCellValueFactory(new PropertyValueFactory<Flight,String>("from"));
        departureTime.setCellValueFactory(new PropertyValueFactory<Flight,Timestamp>("departureTime"));
        arrivalTime.setCellValueFactory(new PropertyValueFactory<Flight,Timestamp>("arrivalTime"));
        plane.setCellValueFactory(new PropertyValueFactory<Flight,String>("plane"));
        //crewLeader.setCellValueFactory(new PropertyValueFactory<Flight,String>("crewLeader"));
        emptySeats.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("emptySeats"));
        flightId.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("flightId"));

        from1.setCellValueFactory(new PropertyValueFactory<Ticket,String>("from1"));
        to1.setCellValueFactory(new PropertyValueFactory<Ticket,String>("to1"));
        departureTime1.setCellValueFactory(new PropertyValueFactory<Ticket,Timestamp>("departureTime1"));
        ticketId1.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("ticketId1"));
        seat1.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("seat1"));

    }

    private String generateSearchSql(){
        String q = "SELECT * FROM flights";
        if(!(fromField.getText().isEmpty() & toField.getText().isEmpty()))
            q = q + " WHERE ";
        if(!fromField.getText().isEmpty()) {
            q = q + "destinationairport='" + fromField.getText() + "'";
            if(!toField.getText().isEmpty())
                q = q + " AND ";
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
                int flightId = resultSet.getInt("flightId");

                //get plane and crewLeader name
                ResultSet resultSetPlane = db.createStatement().executeQuery("SELECT model,capacity FROM planes WHERE planeId=" + planeId);
                resultSetPlane.next();
                String plane = resultSetPlane.getString("model");
                int capacity = resultSetPlane.getInt("capacity");

                ResultSet resultSetCrew = db.createStatement().executeQuery("SELECT name FROM pilots WHERE pilotid=" + crewLeaderId);
                resultSetCrew.next();
                String crewLeader = resultSetCrew.getString("name");

                int emptySeats = capacity - getNoOfOccupiedSeats(flightId);

                //set data
                data.add(new Flight(to, from, departureTime, arrivalTime, plane, crewLeader, emptySeats,flightId));
            }

        } catch (SQLException ignore) {
            //throw new RuntimeException(ex);
        }
        departures_table.setItems(data);
    }

    int getNoOfOccupiedSeats(int flightId){
        try{
            ResultSet resultSet = db.createStatement().executeQuery("SELECT COUNT(*) FROM tickets WHERE flightid ="+ flightId);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            return 0;
        }
    }

    public void newFlightSelected(MouseEvent mouseEvent) {
        index = departures_table.getSelectionModel().getSelectedIndex();
        if(index>=0) {
            selectedFlightTicket = new Ticket();
            selectedFlightTicket.flightId1 = flightId.getCellData(index);
            selectedFlightTicket.userId1 = userId;
            //selectedFlightTicket.seatNumber1 = getEmptySeat(selectedFlightTicket.flightId1);
            selectedFlightTicket.from1 = from.getCellData(index);
            selectedFlightTicket.to1 = to.getCellData(index);
            selectedFlightTicket.departureTime1 = departureTime.getCellData(index);
        }
    }

    public void reserveClick(ActionEvent actionEvent) {
        if (selectedFlightTicket != null) {
            selectedFlightTicket.seatNumber1 = getEmptySeat(selectedFlightTicket.flightId1);
            if(selectedFlightTicket.seatNumber1 == -1) {
                fullFlightText.setText("Selected flight is full");
                return;
            } else
                fullFlightText.setText("");
            try {
                db.createStatement().executeUpdate(
                        "INSERT INTO tickets (flightid,seatnumber,ticketprice,userid)" +
                                "VALUES (" + selectedFlightTicket.flightId1 + "," + selectedFlightTicket.seatNumber1 +","+ 100 +","+ userId + ")"
                );
                //db.createStatement().executeUpdate("INSERT INTO tickets (flightid,seatnumber,ticketprice,username) VALUES (100,1,100,'John')");
            } catch (SQLException e) {
                System.out.println("Reservation error");
                e.printStackTrace();
            }
        }
        refreshTickets();
    }

    int getEmptySeat(int flightId){
        ArrayList<Integer> occupiedSeats = new ArrayList<Integer>();
        int capacity;
        try{
            ResultSet resultSetTicket = db.createStatement().executeQuery("SELECT seatnumber FROM tickets WHERE flightid ="+ flightId);
            while(resultSetTicket.next()){
                occupiedSeats.add(resultSetTicket.getInt("seatnumber"));
            }

            ResultSet resultSetPlane = db.createStatement().executeQuery("SELECT planeid FROM flights WHERE flightid ="+ flightId);
            resultSetPlane.next();
            int planeId = resultSetPlane.getInt("planeid");

            ResultSet resultSetCapacity = db.createStatement().executeQuery("SELECT capacity FROM planes WHERE planeid =" + planeId);
            resultSetCapacity.next();
            capacity = resultSetCapacity.getInt("capacity");
        } catch (SQLException e) {
            return -1;
        }
        if(occupiedSeats.size() >= capacity)
            return -1;
        int a=0;
        do{
            a++;
        }while(occupiedSeats.contains(a));
        return a;
    }


    public void refreshTickets() {
        ObservableList<Ticket> data = FXCollections.observableArrayList();
        try {
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tickets WHERE userid="+userId);
            while (resultSet.next()) {
                int tickedId = resultSet.getInt("ticketid");
                int flightId = resultSet.getInt("flightid");
                int seatNumber = resultSet.getInt("seatnumber");

                ResultSet resultSetFlight = db.createStatement().executeQuery("SELECT * FROM flights WHERE flightid=" + flightId);
                resultSetFlight.next();
                String departureAirport = resultSetFlight.getString("departureairport");
                String destinationAirport = resultSetFlight.getString("destinationairport");
                Timestamp departureTime = resultSetFlight.getTimestamp("departuretime");

                //set data
                data.add(new Ticket(userId,tickedId,flightId,seatNumber,destinationAirport,departureAirport,departureTime));
            }

        } catch (SQLException ex) {
            System.out.println("Refresh error");
            throw new RuntimeException(ex);
        }
        ticketsTable.setItems(data);
    }

    private int ticketToDeleteId;
    private int indexToDelete;
    public void delteTicketSelected(MouseEvent mouseEvent) {
        indexToDelete = ticketsTable.getSelectionModel().getSelectedIndex();
        if(indexToDelete>=0)
            ticketToDeleteId = ticketId1.getCellData(indexToDelete);
    }

    public void deleteTicketClick(ActionEvent actionEvent) {
        try {
            if(indexToDelete >= 0)
                db.createStatement().executeUpdate(
                        "DELETE FROM tickets WHERE ticketid=" + ticketToDeleteId
                );
        }catch(SQLException e){
            e.printStackTrace();
        }
        refreshTickets();
    }

    public void logOutClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        HelloController controller = fxmlLoader.getController();
        controller.setDb(db);
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void changePassClick(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("change_pass_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ChangePassView controller = fxmlLoader.getController();
            controller.setDb(db);
            controller.setUserId(userId);
            stage.setScene(scene);
            stage.show();
        }catch(IOException ignored){};
    }

    public void getInfoClick(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("flight_detail_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            FlightDetailView controller = fxmlLoader.getController();
            controller.setDb(db);
            controller.setFlightId(selectedFlightTicket.flightId1);
            controller.updateContents();
            stage.setScene(scene);
            stage.show();
        }catch(IOException ignored){};
    }
}
