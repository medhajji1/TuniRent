/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

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

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Get the parameter values and update the labels
        String nom = (String) rb.getObject("nom");
        String email = (String) rb.getObject("email");
        String numtel = (String) rb.getObject("numtel");
        String sujet = (String) rb.getObject("sujet");
        String message = (String) rb.getObject("message");
        setNom(nom);
        setEmail(email);
        setNumtel(numtel);
        setSujet(sujet);
        setMessage(message);
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
                lblNumtel.setText(sujet);
            }
    }

    public void setMessage(String message){
        if (lblMessage != null) {
                lblNumtel.setText(message);
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
}