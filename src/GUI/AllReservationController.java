/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import gestion_voiture.entities.Voiture;
import gestion_voiture.entities.Reservation;
import gestion_voiture.entities.Category;
import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceReservation;
import gestion_voiture.services.ServiceVoiture;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author moham
 */
public class AllReservationController implements Initializable {
    
    ServiceVoiture sv = new ServiceVoiture();
    ServiceCategorie sc = new ServiceCategorie();
    ServiceReservation sr = new ServiceReservation();
    
    
    @FXML
    public TableView<Reservation> table;
    

    @FXML 
    public TableColumn<Reservation, String> voitureCol, ddCol, dfCol;
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchReservations();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm");
        voitureCol.setCellValueFactory(cell -> parseString(
            cell.getValue().getVoiture().getImmatriculation() + " ("  + cell.getValue().getVoiture().getCategorie().getMarque() + " " + cell.getValue().getVoiture().getCategorie().getModele() + " )"));
        ddCol.setCellValueFactory(cell -> parseString(formatter.format(cell.getValue().getDate_d())));
        dfCol.setCellValueFactory(cell -> parseString(formatter.format(cell.getValue().getDate_f())));
        
    }
    
    void fetchReservations() {
        ObservableList<Reservation> data = FXCollections.observableArrayList(sr.tout());
        table.setItems(data);
    }
    
    public void refresh() {
        ObservableList<Reservation> data = FXCollections.observableArrayList(sr.tout());
        table.setItems(data);
        table.refresh();
    }
    
    IntegerProperty parseInt(int id) {
        IntegerProperty v = new SimpleIntegerProperty(id);
        return v;
    }
    
    ReadOnlyStringWrapper parseString(String s) {
        return new ReadOnlyStringWrapper(s);
    }
    
    
    public void openCreate() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("create-reservation.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create Reservation");
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 700));
        stage.show();
    }
    
    public void openDelete() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("delete-reservation.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Delete Reservation");
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 700));
        stage.show();
    }
    
    public void openUpdate() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("update-reservation.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Update Reservation");
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 700));
        stage.show();
    }
}
