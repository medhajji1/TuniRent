/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.entity.Contrat;
import tn.esprit.entity.Paiement;
import tn.esprit.services.ContratService;
import tn.esprit.services.PaiementService;
import mailing.Mailing;

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
    @FXML
    private Button paiementButton;
    @FXML
    private Button pdfButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ContratService cs = new ContratService();
         List<Contrat> list =  cs.getAll();
        ObservableList liste = FXCollections.observableList(list);
        
        idContratColumn.setCellValueFactory(new PropertyValueFactory<>("idContract"));
       idReservationColumn.setCellValueFactory(new PropertyValueFactory<>("idReservation"));

        idProprietaireColumn.setCellValueFactory(new PropertyValueFactory<>("idProprietaire"));
        idLocataireColumn.setCellValueFactory(new PropertyValueFactory<>("idLocataire"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        motifColumn.setCellValueFactory(new PropertyValueFactory<>("motif"));
        contratTable.setItems(liste);
    }    

    @FXML
    private void ajouterContrat(ActionEvent event) {

           List<String> errors = new ArrayList<>();

    // Validate idReservation
    String idReservationText = idReservationField.getText();
    if (idReservationText.isEmpty()) {
        errors.add("idReservation is required");
    } else if (!idReservationText.matches("\\d+")) {
        errors.add("idReservation should contain only digits");
    }

    // Validate idProprietaire
    String idProprietaireText = idProprietaireField.getText();
    if (idProprietaireText.isEmpty()) {
        errors.add("idProprietaire is required");
    } else if (!idProprietaireText.matches("\\d+")) {
        errors.add("idProprietaire should contain only digits");
    }

    // Validate idLocataire
    String idLocataireText = idLocataireField.getText();
    if (idLocataireText.isEmpty()) {
        errors.add("idLocataire is required");
    } else if (!idLocataireText.matches("\\d+")) {
        errors.add("idLocataire should contain only digits");
    }

    // Validate motif
    String motif = motifField.getText();
    if (motif.isEmpty()) {
        errors.add("motif is required");
    } else if (!motif.matches("[a-zA-Z0-9 ]+")) {
        errors.add("motif should contain only letters, digits, and spaces");
    }

    if (!errors.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input validation failed");
        alert.setHeaderText("Please fix the following errors:");
        alert.setContentText(String.join("\n", errors));
        alert.show();
        return;
    }

    // Parse input values
    int idReservation = 0;
    int idProprietaire = 0;
    int idLocataire = 0;
    try {
        idReservation = Integer.parseInt(idReservationText);
    } catch (NumberFormatException e) {
        errors.add("idReservation should contain only digits");
    }
    try {
        idProprietaire = Integer.parseInt(idProprietaireText);
    } catch (NumberFormatException e) {
        errors.add("idProprietaire should contain only digits");
    }
    try {
        idLocataire = Integer.parseInt(idLocataireText);
    } catch (NumberFormatException e) {
        errors.add("idLocataire should contain only digits");
    }

    if (!errors.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input validation failed");
        alert.setHeaderText("Please fix the following errors:");
        alert.setContentText(String.join("\n", errors));
        alert.show();
        return;
    }

    // Create Contrat object and save to database

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

        // Mailing.mailing("zayani.fedi@esprit.tn");

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
        
      
    idReservationField.clear();
    idProprietaireField.clear();
    idLocataireField.clear();
    motifField.clear();
    idContratField.clear();
    idReservationField_2.clear();
    idProprietaireField_2.clear();
    idLocataireField_2.clear();
    motifField_2.clear();
    
     
        
        
    }
    @FXML
    private void selectPaiement(MouseEvent event) {
        
          Object selectedItem = contratTable.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
        return; // No row selected, do nothing
    }
    // Cast the selected row data to a Paiement object
    Contrat selectedContrat = (Contrat) selectedItem;
    
    // Set the values of the corresponding TextFields in the UI
   idContratField.setText(String.valueOf(selectedContrat.getIdContract()));
    idReservationField_2.setText(String.valueOf(selectedContrat.getIdReservation()));
    idProprietaireField_2.setText(String.valueOf(selectedContrat.getIdProprietaire()));
    idLocataireField_2.setText(String.valueOf(selectedContrat.getIdLocataire()));
    motifField_2.setText(selectedContrat.getMotif());
      
        
        
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
 clearFields(event);
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


        
        
         Object selectedItem = contratTable.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
        return; // No row selected, do nothing
    }
    // Cast the selected row data to a Paiement object
    Contrat selectedContrat = (Contrat) selectedItem;
            ContratService ps = new ContratService();
        ps.supprimer(selectedContrat.getIdContract());

    List<Contrat> list =  ps.getAll();
        //List<Paiement> paiements = service.getAll();
        //    System.out.println(paiements);
        ObservableList liste = FXCollections.observableList(list);
        contratTable.setItems(liste);
        
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
        }}

    @FXML
    private void effectuerPaiement(ActionEvent event) throws IOException {
         Object selectedItem = contratTable.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
        return; // No row selected, do nothing
    }
    // Cast the selected row data to a Paiement object
    Contrat selectedContrat = (Contrat) selectedItem;
            ContratService ps = new ContratService();
        //ps.supprimer(selectedContrat.getIdContract());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("paiement.fxml"));
        Parent root = loader.load();
        PaiementController paiementController = loader.getController();
        System.out.println(String.valueOf(selectedContrat.getIdContract()));
        paiementController.setIdContrat(String.valueOf(selectedContrat.getIdContract()) );
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void genererContrat(ActionEvent event) {
        Object selectedItem = contratTable.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
        return; // No row selected, do nothing
    }
    // Cast the selected row data to a Paiement object
    Contrat selectedContract = (Contrat) selectedItem;

    try (FileOutputStream outputStream = new FileOutputStream(new File("contract.pdf"))) {
        // Create a new iText document
        Document document = new Document();

        // Create a PDF writer to write the document to the output stream
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        // Open the document and add a paragraph with the contract details
        document.open();
        document.add(new Paragraph("Location Contract"));
        document.add(new Paragraph("ID Contract: " + selectedContract.getIdContract()));
        document.add(new Paragraph("ID Reservation: " + selectedContract.getIdReservation()));
        document.add(new Paragraph("ID Locataire: " + selectedContract.getIdLocataire()));
        document.add(new Paragraph("ID Proprietaire: " + selectedContract.getIdProprietaire()));
        document.add(new Paragraph("Date: " + selectedContract.getDate().toString()));
        document.add(new Paragraph("Motif: " + selectedContract.getMotif()));

        // Close the document
        document.close();

        // Open the generated PDF in the default PDF viewer
        Desktop.getDesktop().open(new File("contract.pdf"));
    } catch (IOException | DocumentException ex) {
        // Show an error message if there was a problem generating or opening the PDF
        ex.printStackTrace();
        return;
    }
    }
}
        
        
        
        
        
        /*ContratService cs = new ContratService();
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
   
    }*/
    

