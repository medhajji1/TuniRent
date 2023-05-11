/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceUtilisateur;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class UpdateUtilisateurController implements Initializable {
    
    
    Utilisateur u =null; 

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField numeroTelephoneField;

    @FXML
    private Button creerButton2;

    @FXML
    private TextField cinField;

    @FXML
    private PasswordField motDePassePasswordField;

    @FXML
    private RadioButton adminRadioButton;

    @FXML
    private RadioButton clientRadioButton;

    @FXML
    void updateU(ActionEvent event)throws SQLException, IOException {
        
            String nom = nomField.getText();
            String email = emailField.getText();
            String numtel = numeroTelephoneField.getText();
            String mdp = motDePassePasswordField.getText();
            String prenom = prenomField.getText(); 
            String cin = cinField.getText();          
            String type = "";
            if (adminRadioButton.isSelected()) {
                type = "admin";
            } else if (clientRadioButton.isSelected()) {
                type = "client"; 
            }
            System.out.println("aa");
            ServiceUtilisateur SM =new ServiceUtilisateur(); 
            this.u.setNom(nom);          
            //this.u.setEmail(email);
            //this.u.setPrenom(prenom);
            //this.u.setMotDePasse(mdp);
            //this.u.setNumeroTelephone(numtel);
            //int cinValue = Integer.parseInt(cin);
            //u.setCIN(cinValue);
            //this.u.setTypeUtilisateur(type);
            //SM.modifier(u);
           
    }
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setelementtoupdate(Utilisateur d){
        this.u=u;
    System.out.println("les champs a etes modifier :"+ d.toString());
    this.nomField.setText(d.getNom());
    this.prenomField.setText(d.getPrenom());
    this.emailField.setText(d.getEmail());
    this.motDePassePasswordField.setText(d.getMotDePasse());
    this.numeroTelephoneField.setText(d.getNumeroTelephone());
    cinField.setText(String.valueOf(d.getCIN()));
    }


    
}
