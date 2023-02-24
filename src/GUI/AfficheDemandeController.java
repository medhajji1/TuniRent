/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceReclamation;
import entities.reclamation;
import entities.reclamation.Category;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yadii
 */
public class AfficheDemandeController implements Initializable {

    @FXML
    private Label lblNom;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblNumtel;
    @FXML
    private Label lblSujet;
    @FXML
    private Label lblMessage;
    @FXML
    private Button cancelid;
    private int selectedReclamationId;
    @FXML
    private Label lblCat;
    @FXML
    private Button delete;
    @FXML
    private Label lblID;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setSelectedReclamationId(int selectedReclamationId) {
    this.selectedReclamationId = selectedReclamationId;
    }
    public void setSelectedReclamation(reclamation rec) {
    if (rec != null) {
        selectedReclamationId = rec.getId();
        setNom(rec.getNom());
        setEmail(rec.getEmail());
        setNumtel(rec.getNumtel());
        setSujet(rec.getSujet());
        setMessage(rec.getMessage());
        setMessage(rec.getMessage());
        setCategory(rec.getCategory());
    }
}
     public void setNom(String nom) {
                if (lblNom != null) {
                    lblNom.setText(nom);
                }
    }
        public void setId(int id) {
                if (lblID != null) {
                    lblID.setText(String.valueOf(id));
                }
    }
        public void setEmail(String email){
            if (lblEmail != null) {
                    lblEmail.setText(email);
                }
        }

        public void setNumtel(String numtel){
            if (lblNumtel != null) {
                    lblNumtel.setText(numtel);
                }
        }

                public void setSujet(String sujet){
             if (lblSujet != null) {
                 lblSujet.setText(sujet);
             }
                }

         public void setMessage(String message){
             if (lblMessage != null) {
                 lblMessage.setText(message);
                }
         }

        @FXML
        private void cancel(ActionEvent event) {
            try {
        // Load the FXML file of the new page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
        Parent root = loader.load();
        
        // Set the loaded page as the root of the current Scene
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
    }
            }
        

    public void setCategory(Category category) {
    if (lblCat != null) {
        lblCat.setText(category.name());
    }
}

    @FXML
    private void delete(ActionEvent event) {
        if (selectedReclamationId > 0) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir supprimer cette réclamation ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            ServiceReclamation service = new ServiceReclamation();
            //service.supprimer(selectedReclamationId);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ddashboard.fxml"));
            try {
                Parent root = loader.load();
                cancelid.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println("error : " + ex.getMessage());
            }
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner une réclamation à supprimer.", ButtonType.OK);
        alert.showAndWait();
    }
    }
}