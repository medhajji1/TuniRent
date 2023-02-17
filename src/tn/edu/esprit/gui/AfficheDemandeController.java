/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.gui;

import GUI.AjouterReclamationController;
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
        // TODO
    }    
    public void setNom(String nom){
        lblNom.setText(nom);
    }
    
    public void setEmail(String email){
        lblEmail.setText(email);
    }
public void setNumtel(String numtel){
        lblNumtel.setText(numtel);
    }
    
    public void setSujet(String sujet){
        lblSujet.setText(sujet);
    }
    public void setMessage(String message){
        lblMessage.setText(message);
    }


    @FXML
    private void cancel(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
                        try {
                        Parent root = loader.load(); 
                             AjouterReclamationController SBC = loader.getController();
                             SBC.loadPage("AjouterReclamation"); 
                           lblNom.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
        
    }
  
}
