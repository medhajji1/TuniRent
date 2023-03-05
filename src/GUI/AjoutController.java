/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import entities.Utilisateur;
import Services.ServiceUtilisateur;

public class AjoutController implements Initializable {

    @FXML
    private TextField cinField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField motDePassePasswordField;

    @FXML
    private TextField numeroTelephoneField;
    
    @FXML
    private RadioButton adminRadioButton;
    
    @FXML
    private RadioButton clientRadioButton;
    
    @FXML
    private Button switchSceneButton;

    @FXML
    private Button creerButton;

     ServiceUtilisateur su = new ServiceUtilisateur() ;
    
    

    public void initialize() {
       
        ToggleGroup typeGroup = new ToggleGroup();
        adminRadioButton.setToggleGroup(typeGroup);
        clientRadioButton.setToggleGroup(typeGroup);
        adminRadioButton.setSelected(false);
        clientRadioButton.setSelected(true);
    }

    @FXML
    public void ajouter() {
        int cin = Integer.parseInt(cinField.getText());
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String password = motDePassePasswordField.getText();
        String telephone = numeroTelephoneField.getText();
        String type = "";
        if (adminRadioButton.isSelected()) {
        type = "admin";
        } else if (clientRadioButton.isSelected()) {
        type = "client"; 
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
        throw new IllegalArgumentException("Le format de l'adresse email est invalide");
        }
        if (telephone.length() != 8 || !telephone.matches("[0-9]+")) {
        throw new IllegalArgumentException("Le numéro de téléphone doit être strictement égal à 8 et ne contenir que des chiffres");
        }
    // code pour ajouter l'utilisateur à la base de données
        }

        Utilisateur utilisateur = new Utilisateur(cin, nom, prenom, email, password, telephone, type);

        su.ajouter(utilisateur);
        clearFields();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur créé avec succès !");
        alert.showAndWait();
        
        System.out.println(emailField.getText());
    }

    private void clearFields() {
        cinField.clear();
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        motDePassePasswordField.clear();
        numeroTelephoneField.clear();
        adminRadioButton.setSelected(false);
        clientRadioButton.setSelected(true);
    }
    
    
    
    
    @FXML
    private void switchToSMA(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("C:/Users/DELL/Documents/PIDev/src/main/java/gui/user/utDashboard.fxml"));
        System.out.println(root);                           
    Scene scene = new Scene(root);
    Stage stage = (Stage) switchSceneButton.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
