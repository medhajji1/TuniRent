/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;
import gestion_voiture.entities.Categorie;
import javafx.fxml.Initializable;
import gestion_voiture.services.ServiceCategorie;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;


/**
 *
 * @author moham
 */
public class AllCategoriesController implements Initializable {
    ServiceCategorie sc = new ServiceCategorie();
    
    @FXML
    public TableView<Categorie> table;
    
    @FXML 
    public TableColumn<Categorie, Number> categoryIdCol;
    
    @FXML 
    public TableColumn<Categorie, String> categoryMarqueCol;
    
    @FXML 
    public TableColumn<Categorie, String> categoryModeleCol;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchCategories();
        categoryIdCol.setCellValueFactory(cell -> parseInt(cell.getValue().getId()));
        categoryMarqueCol.setCellValueFactory(cell -> parseString(cell.getValue().getMarque()));
        categoryModeleCol.setCellValueFactory(cell -> parseString(cell.getValue().getModele()));
    }
    
    void fetchCategories() {
        ObservableList<Categorie> data = FXCollections.observableArrayList(sc.tout());
        table.setItems(data);
    }
    
    public void refresh() {
        ObservableList<Categorie> data = FXCollections.observableArrayList(sc.tout());
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