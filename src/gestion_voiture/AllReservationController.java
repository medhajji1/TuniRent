/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;

import gestion_voiture.entities.Voiture;
import gestion_voiture.entities.Reservation;
import gestion_voiture.entities.Categorie;
import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceReservation;
import gestion_voiture.services.ServiceVoiture;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    
    @FXML 
    public TableColumn<Reservation, Number> idCol;
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchReservations();
        
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm");
                
        voitureCol.setCellValueFactory(cell -> parseString(
            cell.getValue().getVoiture().getImmatriculation() + " ("  + cell.getValue().getVoiture().getCategorie().getMarque() + " " + cell.getValue().getVoiture().getCategorie().getModele() + " )"));
        
        idCol.setCellValueFactory(cell -> parseInt(cell.getValue().getId()));
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
}
