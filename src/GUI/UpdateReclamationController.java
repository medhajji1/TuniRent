/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceReclamation;
import entities.reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yadii
 */
public class UpdateReclamationController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNumtel;
    @FXML
    private TextField txtSujet;
    @FXML
    private TextArea txtMessage;
    @FXML
    private Button btnUpdate;

    private int selectedReclamationId;

    public void setSelectedReclamationId(int selectedReclamationId) {
        this.selectedReclamationId = selectedReclamationId;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void loadReclamation(int reclamationId) {
        // Load the attributes of the selected reclamation into the text fields
        ServiceReclamation SR = new ServiceReclamation();
        reclamation r = SR.getreclamation(reclamationId);
        txtNom.setText(r.getNom());
        txtEmail.setText(r.getEmail());
        txtNumtel.setText(r.getNumtel());
        txtSujet.setText(r.getSujet());
        txtMessage.setText(r.getMessage());
    }

    @FXML
    private void updateReclamation(ActionEvent event) throws IOException {
        // Update the reclamation in the database with the new attribute values
        String nom = txtNom.getText();
        String email = txtEmail.getText();
        String numtel = txtNumtel.getText();
        String sujet = txtSujet.getText();
        String message = txtMessage.getText();

        ServiceReclamation SR = new ServiceReclamation();
        SR.modifier(selectedReclamationId, nom, email, numtel, sujet, message);

        // Switch back to the AfficheDemande page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheDemande.fxml"));
        Parent root = loader.load();
        AfficheDemandeController afficheController = loader.getController();
        afficheController.setSelectedReclamationId(selectedReclamationId);
        afficheController.loadReclamation(selectedReclamationId);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}