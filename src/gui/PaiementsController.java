/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entity.Paiement;
import tn.esprit.services.PaiementService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PaiementsController implements Initializable {

    @FXML
    private TextField idContratField;
    @FXML
    private TextField montantField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField motifField;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button clearButton;
    @FXML
    private TableView<?> paiementTable;
    @FXML
    private TableColumn<?, ?> idPaiementColumn;
    @FXML
    private TableColumn<?, ?> idContratColumn;
    @FXML
    private TableColumn<?, ?> montantColumn;
    @FXML
    private TableColumn<?, ?> dateColumn;
    @FXML
    private TableColumn<?, ?> motifColumn;
    @FXML
    private TextField idPaiementField;
    @FXML
    private TextField idContratField_2;
    @FXML
    private TextField montantField_2;
    @FXML
    private TextField dateField_2;
    @FXML
    private TextField motifField_2;
    @FXML
    private Button modifierButton;
    @FXML
    private Button supprimerButton;
    
    private PaiementService service;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
        
        ObservableList list = null;

         
        try {
            list = service.getPaiementList();//TODO
        } catch (SQLException ex) {
            Logger.getLogger(PaiementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         idPaiementColumn.setCellValueFactory(new PropertyValueFactory<>("id paiement"));
         idContratColumn.setCellValueFactory(new PropertyValueFactory<>("id contrat"));
         montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
         dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
         motifColumn.setCellValueFactory(new PropertyValueFactory<>("motif"));
         paiementTable.setItems(list);
    }    

    @FXML
    private void ajouterPaiement(ActionEvent event) {
        try {
            int idContrat = Integer.parseInt(idContratField.getText());
            int montant = Integer.parseInt(montantField.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateField.getText()) ;
            String motif = motifField.getText();

            Paiement paiement = new Paiement(idContrat, montant, date, motif);
            service.ajouter(paiement);

            try {
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Addeded!");
                alert.show();
                
              
                
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
            ObservableList list = null;
            list = service.getPaiementList();//TODO
            paiementTable.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearFields(ActionEvent event) {
    }

    @FXML
    private void modifierPaiement(ActionEvent event) {
    }

    @FXML
    private void supprimerPaiement(ActionEvent event) {
    }
    
}
