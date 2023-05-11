/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;
import gestion_voiture.entities.Category;
import javafx.fxml.Initializable;
import gestion_voiture.services.ServiceCategorie;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 *
 * @author moham
 */
public class AllcategoriesController implements Initializable {
    ServiceCategorie sc = new ServiceCategorie();
    
    @FXML
    public TableView<Category> table;
    
    @FXML 
    public TableColumn<Category, String> categoryMarqueCol;
    
    @FXML 
    public TableColumn<Category, String> categoryModeleCol;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchCategories();
        categoryMarqueCol.setCellValueFactory(cell -> parseString(cell.getValue().getMarque()));
        categoryModeleCol.setCellValueFactory(cell -> parseString(cell.getValue().getModele()));
    }
    
    void fetchCategories() {
        ObservableList<Category> data = FXCollections.observableArrayList(sc.tout());
        table.setItems(data);
    }
    
    public void refresh() {
        ObservableList<Category> data = FXCollections.observableArrayList(sc.tout());
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
        fxmlLoader.setLocation(getClass().getResource("create-categorie.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create Categorie");
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 700));
        stage.show();
    }
    
    public void openDelete() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("delete-category.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Delete Categorie");
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 700));
        stage.show();
    }
    
    public void openUpdate() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("update-category.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Update Categorie");
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 700));
        stage.show();
    }
}