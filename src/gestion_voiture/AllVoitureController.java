/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;

import gestion_voiture.entities.Categorie;
import gestion_voiture.entities.Voiture;
import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceVoiture;
import java.net.URL;
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
public class AllVoitureController implements Initializable {
    ServiceVoiture sv = new ServiceVoiture();
    ServiceCategorie sc = new ServiceCategorie();
    
    
    @FXML
    public TableView<Voiture> table;
    
    @FXML 
    public TableColumn<Voiture, Number> voiturekilometrageCol;
    
    @FXML 
    public TableColumn<Voiture, String> voitureImmatriculationCol, voiturecouleurCol, voitureCategorieCol;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchCategories();
        
        voiturekilometrageCol.setCellValueFactory(cell -> parseInt(cell.getValue().getKilometrage()));
        voitureImmatriculationCol.setCellValueFactory(cell -> parseString(cell.getValue().getImmatriculation()));
        voiturecouleurCol.setCellValueFactory(cell -> parseString(cell.getValue().getCouleur()));
        voitureCategorieCol.setCellValueFactory(cell -> parseString(
                cell.getValue().getCategorie().getId()+ " " +cell.getValue().getCategorie().getMarque() + " " + cell.getValue().getCategorie().getModele()
        ));
    }
    
    void fetchCategories() {
        ObservableList<Voiture> data = FXCollections.observableArrayList(sv.tout());
        table.setItems(data);
    }
    
    public void refresh() {
        ObservableList<Voiture> data = FXCollections.observableArrayList(sv.tout());
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
