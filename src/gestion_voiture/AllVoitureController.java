/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;
import gestion_voiture.entities.Voiture;
import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceVoiture;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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
    public TableColumn<Voiture, String> voitureImmatriculationCol, voiturecouleurCol, voitureCategorieCol, voitureimageCol;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchCategories();
        
        voiturekilometrageCol.setCellValueFactory(cell -> parseInt(cell.getValue().getKilometrage()));
        voitureImmatriculationCol.setCellValueFactory(cell -> parseString(cell.getValue().getImmatriculation()));
        voiturecouleurCol.setCellValueFactory(cell -> parseString(cell.getValue().getCouleur()));
        voitureCategorieCol.setCellValueFactory(cell -> parseString(
                cell.getValue().getCategorie().getId()+ " " +cell.getValue().getCategorie().getMarque() + " " + cell.getValue().getCategorie().getModele()
        ));
        voitureimageCol.setCellValueFactory(cell -> parseString(cell.getValue().getImage()));
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
    
    
    public void openCreate() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("create-voiture.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create Voiture");
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 700));
        stage.show();
    }
    
    public void openDelete() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("delete-voiture.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Delete Voiture");
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 700));
        stage.show();
    }
    
    public void openUpdate() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("update-voiture.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Update Voiture");
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 700));
        stage.show();
    }
}
