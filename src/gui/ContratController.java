/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.input.MouseEvent;
import tn.esprit.entity.Contrat;
import tn.esprit.entity.Paiement;
import tn.esprit.services.ContratService;
import tn.esprit.services.PaiementService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ContratController implements Initializable {

    @FXML
    private TextField idReservationField;
    @FXML
    private TextField idProprietaireField;
    @FXML
    private TextField idLocataireField;
    @FXML
    private TextField motifField;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button clearButton;
    @FXML
    private TableColumn<?, ?> idContratColumn;
    @FXML
    private TableColumn<?, ?> idReservationColumn;
    @FXML
    private TableColumn<?, ?> idProprietaireColumn;
    @FXML
    private TableColumn<?, ?> idLocataireColumn;
    @FXML
    private TableColumn<?, ?> motifColumn;
    @FXML
    private TableColumn<?, ?> dateColumn;
    @FXML
    private TextField idContratField;
    @FXML
    private TextField idReservationField_2;
    @FXML
    private TextField idProprietaireField_2;
    @FXML
    private TextField idLocataireField_2;
    @FXML
    private TextField motifField_2;
    @FXML
    private Button modifierButton;
    @FXML
    private Button supprimerButton;
    
 //   ObservableList list ;
    @FXML
    private TableView<?> contratTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ContratService cs = new ContratService();
         List<Contrat> list =  cs.getAll();
        ObservableList liste = FXCollections.observableList(list);
        
        idContratColumn.setCellValueFactory(new PropertyValueFactory<>("idContrat"));
       idReservationColumn.setCellValueFactory(new PropertyValueFactory<>("idReservation"));

        idProprietaireColumn.setCellValueFactory(new PropertyValueFactory<>("idProprietaire"));
        idLocataireColumn.setCellValueFactory(new PropertyValueFactory<>("idLocataire"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        motifColumn.setCellValueFactory(new PropertyValueFactory<>("motif"));
        contratTable.setItems(liste);
    }    

    @FXML
    private void ajouterContrat(ActionEvent event) {

                    int idReservation = Integer.parseInt(idReservationField.getText());
                    int idProprietaire = Integer.parseInt(idProprietaireField.getText());
                    int idLocataire = Integer.parseInt(idLocataireField.getText());
            
            String motif = motifField.getText();
java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Contrat contrat = new Contrat(idReservation, idProprietaire, idLocataire, sqlDate, motif);
ContratService cs = new ContratService();
cs.ajouter(contrat);

            try {
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Added!");
                alert.show();
                
              
                
            } catch (Exception ee) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.show();
            }
        List<Contrat> list =  cs.getAll();
        ObservableList liste = FXCollections.observableList(list);
        contratTable.setItems(liste);
    }

    @FXML
    private void clearFields(ActionEvent event) {
    }

    @FXML
    private void selectPaiement(MouseEvent event) {
    }

    @FXML
    private void modifierPaiement(ActionEvent event) {
        

        
        Contrat selectedContrat = (Contrat) contratTable.getSelectionModel().getSelectedItem();
        
        if (selectedContrat != null) {
            int idContrat = Integer.parseInt(idContratField.getText());
                    int idReservation = Integer.parseInt(idReservationField_2.getText());
                    int idProprietaire = Integer.parseInt(idProprietaireField_2.getText());
                    int idLocataire = Integer.parseInt(idLocataireField_2.getText());
            
            String motif = motifField_2.getText();
java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        
        selectedContrat.setIdContract(idContrat);
        selectedContrat.setDate(sqlDate);
        selectedContrat.setIdLocataire(idLocataire);
        selectedContrat.setIdProprietaire(idProprietaire);
        selectedContrat.setIdReservation(idReservation);
        selectedContrat.setMotif(motif);
        
            System.out.println(selectedContrat);
        
        
        
ContratService cs = new ContratService();
cs.modifier(selectedContrat);
List<Contrat> list =  cs.getAll();
        //List<Paiement> paiements = service.getAll();
        //    System.out.println(paiements);
        ObservableList liste = FXCollections.observableList(list);
        contratTable.setItems(liste);
            System.out.println("sar lajout");

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
        }
        
    }

    @FXML
    private void supprimerPaiement(ActionEvent event) {

ContratService cs = new ContratService();
cs.supprimer(Integer.parseInt(idContratField.getText()));
List<Contrat> list =  cs.getAll();
        //List<Paiement> paiements = service.getAll();
        //    System.out.println(paiements);
        ObservableList liste = FXCollections.observableList(list);
        contratTable.setItems(liste);        
        // Clear the fields
     //   clearFields(event);
        
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Done");
            alert.setContentText("deleted!");
            alert.show();
        } catch (Exception ee) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.show();
        }
    // Set the values of the corresponding TextFields in the UI
   
    }
    
}
