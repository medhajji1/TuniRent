/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import tn.esprit.entity.Paiement;
import tn.esprit.services.PaiementService;
import mailing.Mailing;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PaiementController implements Initializable {

    @FXML
    private TextField idContratField;
    @FXML
    private TextField montantField;
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
    private TextField dateField_2;
    @FXML
    private TextField motifField_2;
    @FXML
    private Button modifierButton;
    @FXML
    private Button supprimerButton;
    
        private PaiementService service;
        ObservableList list ;
    @FXML
    private TextField emailField;
    @FXML
    private TextField emailField1;
    @FXML
    private TableColumn<?, ?> emailColumn;
    
    private int idContrat;

    public int getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(String idContrat) {
        idContratField.setText(idContrat); 
    }



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PaiementService ps = new PaiementService();
         System.out.println("azerty");
         List<Paiement> list =  ps.getAll();
        //List<Paiement> paiements = service.getAll();
        //    System.out.println(paiements);
        ObservableList liste = FXCollections.observableList(list);
        paiementTable.setItems(liste);
        System.out.println(liste);
        idContratField.setText(String.valueOf(idContrat));
        
        
        
        
        idPaiementColumn.setCellValueFactory(new PropertyValueFactory<>("idPaiement"));
        idContratColumn.setCellValueFactory(new PropertyValueFactory<>("idContrat"));
        montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        motifColumn.setCellValueFactory(new PropertyValueFactory<>("motif"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

   //      paiementTable.setItems(list);
      
    }    

    @FXML
private void ajouterPaiement(ActionEvent event) {
    String idContratText = idContratField.getText();
    String montantText = montantField.getText();
    String motif = motifField.getText();
    String email = emailField.getText();
    List<String> errors = new ArrayList<>();

    // Validate input fields
    if (idContratText.isEmpty()) {
        errors.add("Veuillez entrer l'ID du contrat.");
    } else {
        try {
            int idContrat = Integer.parseInt(idContratText);
        } catch (NumberFormatException e) {
            errors.add("L'ID du contrat doit être un entier.");
        }
    }
    if (montantText.isEmpty()) {
        errors.add("Veuillez entrer le montant du paiement.");
    } else {
        try {
            int montant = Integer.parseInt(montantText);
            if (montant <= 0) {
                errors.add("Le montant doit être supérieur à zéro.");
            }
        } catch (NumberFormatException e) {
            errors.add("Le montant doit être un entier.");
        }
    }
    if (motif.isEmpty()) {
        errors.add("Veuillez entrer le motif du paiement.");
    }
    if (email.isEmpty()) {
        errors.add("Veuillez entrer l'adresse email.");
    } else if (!email.matches("\\S+@\\S+\\.\\S+")) {
        errors.add("L'adresse email n'est pas valide.");
    }

    if (!errors.isEmpty()) {
        // Display error messages
        String message = String.join("\n", errors);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Des erreurs ont été trouvées dans le formulaire.");
        alert.setContentText(message);
        alert.showAndWait();
        return;
    }

    // Create new Paiement object
    int idContrat = Integer.parseInt(idContratText);
    int montant = Integer.parseInt(montantText);
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    Paiement paiement = new Paiement(idContrat, montant, sqlDate, motif);
    paiement.setEmail(email);
    PaiementService ps = new PaiementService();
    ps.ajouter(paiement);
    List<Paiement> list =  ps.getAll();
    ObservableList liste = FXCollections.observableList(list);
    paiementTable.setItems(liste);

    // Display success message and send email
    try {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setContentText("Le paiement a été ajouté avec succès.");
        alert.show();
        Mailing.mailing(email, montant);
    } catch (Exception ee) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur est survenue lors de l'envoi de l'email.");
        alert.setContentText(ee.getMessage());
        alert.showAndWait();
    }
}
    @FXML
    private void clearFields(ActionEvent event) {
            idContratField.clear();
            emailField.clear();
            emailField1.clear();
    montantField.clear();
    motifField.clear();
    idPaiementField.clear();
    idContratField_2.clear();
    montantField_2.clear();
    motifField_2.clear();

    }

    @FXML
private void modifierPaiement(ActionEvent event) {
    // Get the selected Paiement object from the table
    Paiement selectedPaiement = (Paiement) paiementTable.getSelectionModel().getSelectedItem();
    
    if (selectedPaiement != null) {
        // Update the properties of the Paiement object with the new values entered in the text fields
        int idContrat = Integer.parseInt(idContratField_2.getText());
        int montant = Integer.parseInt(montantField_2.getText());
        String motif = motifField_2.getText();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        String email = emailField1.getText();
        
        selectedPaiement.setIdContrat(idContrat);
        selectedPaiement.setMontant(montant);
        selectedPaiement.setDate(sqlDate);
        selectedPaiement.setMotif(motif);
        selectedPaiement.setEmail(email);
        
        
        
        
        
        
        
        // Call the modifier method of the PaiementService to update the Paiement object in the database
        PaiementService ps = new PaiementService();
        ps.modifier(selectedPaiement);
        
         System.out.println("azerty");
         List<Paiement> list =  ps.getAll();
        //List<Paiement> paiements = service.getAll();
        //    System.out.println(paiements);
        ObservableList liste = FXCollections.observableList(list);
        paiementTable.setItems(liste);
        
        // Clear the fields
        clearFields(event);
        
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Done");
            alert.setContentText("Updated!");
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
                 Object selectedItem = paiementTable.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
        return; // No row selected, do nothing
    }
    // Cast the selected row data to a Paiement object
    Paiement selectedPaiement = (Paiement) selectedItem;
            PaiementService ps = new PaiementService();
        ps.supprimer(selectedPaiement.getIdPaiement());

    List<Paiement> list =  ps.getAll();
        //List<Paiement> paiements = service.getAll();
        //    System.out.println(paiements);
        ObservableList liste = FXCollections.observableList(list);
        paiementTable.setItems(liste);
        
        // Clear the fields
        clearFields(event);
        
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Done");
            alert.setContentText("Updated!");
            alert.show();
        } catch (Exception ee) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.show();
        }
    // Set the values of the corresponding TextFields in the UI
   
      
    }
    

    @FXML
    private void selectPaiement(MouseEvent event) {
            Object selectedItem = paiementTable.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
        return; // No row selected, do nothing
    }
    // Cast the selected row data to a Paiement object
    Paiement selectedPaiement = (Paiement) selectedItem;
    
    // Set the values of the corresponding TextFields in the UI
    idPaiementField.setText(String.valueOf(selectedPaiement.getIdPaiement()));
    idContratField_2.setText(String.valueOf(selectedPaiement.getIdContrat()));
    montantField_2.setText(String.valueOf(selectedPaiement.getMontant()));
    motifField_2.setText(selectedPaiement.getMotif());
    emailField1.setText(selectedPaiement.getEmail());
      
    }
    
}
