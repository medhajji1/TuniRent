/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import static sun.rmi.registry.RegistryImpl.getID;

/**
 * FXML Controller class
 *
 * @author maroo
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
    @FXML
    private Button supprimer;
    @FXML
    private Button Modifier;
    private int selectedReclamationId;
    
    
    
    public void setSelectedReclamationId(int selectedReclamationId) {
        this.selectedReclamationId = selectedReclamationId;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */   
       @Override
        public void initialize(URL url, ResourceBundle rb) {

        }
        public void setNom(String nom) {
                if (lblNom != null) {
                    lblNom.setText(nom);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
            try {
                Parent root = loader.load();
                //AjouterReclamationController SBC = loader.getController();
                //SBC.loadPage("AjouterReclamation");
                lblNom.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println("error : "+ex.getMessage());
            }
        }

    @FXML
            private void Supprimer(ActionEvent event) {
                   ServiceReclamation SR = new ServiceReclamation();
                     SR.supprimer(selectedReclamationId);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterReclamation.fxml"));
            try {
                Parent root = loader.load(); 
                AjouterReclamationController controller = loader.getController();
                lblNom.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println("error : "+ex.getMessage());                        
    }
}
            
    public void modifier(int selectedReclamationId, String nom, String email, String numtel, String sujet, String message) {
            ServiceReclamation SR = new ServiceReclamation();
            SR.modifier(selectedReclamationId, nom, email, numtel, sujet, message);
}
    @FXML
    private void Modifier(ActionEvent event) {
            int reclamationId = selectedReclamationId;
           String nom = lblNom.getText();
           String email = lblEmail.getText();
           String numtel = lblNumtel.getText();
           String sujet = lblSujet.getText();
           String message = lblMessage.getText();
           modifier(reclamationId, nom, email, numtel, sujet, message);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterReclamation.fxml"));
           try {
               Parent root = loader.load(); 
               AjouterReclamationController controller = loader.getController();
               lblNom.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.println("error : "+ex.getMessage());                        
           } 
    }
}