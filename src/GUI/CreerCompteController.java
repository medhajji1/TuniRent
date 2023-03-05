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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CreerCompteController implements Initializable {
     ServiceUtilisateur su = new ServiceUtilisateur() ;
    

    private Parent page;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField numeroTelephoneField;
    @FXML
    private TextField cinField;
    @FXML
    private PasswordField motDePassePasswordField;
    @FXML
    private Button creerButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws Exception {
        int cin = Integer.parseInt(cinField.getText());
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String password = motDePassePasswordField.getText();
        String telephone = numeroTelephoneField.getText();
        String type = "client";

         
        if(!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$") || telephone.length() != 8 || !telephone.matches("[0-9]+") || 
                !nom.matches("^[a-zA-Z]+$") || !prenom.matches("^[a-zA-Z]+$") || !password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$")){
            if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Email invalide");
                alert.setHeaderText(null);
                alert.setContentText("Votre email est invalide");
                alert.show();
                
                System.out.println(email);
            }else
            if (telephone.length() != 8 || !telephone.matches("[0-9]+")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Num tel invalide");
                alert.setHeaderText(null);
                alert.setContentText("Votre numéro de tel est invalide");
                alert.show();
            }else
            if (!nom.matches("^[a-zA-Z]+$")|| !prenom.matches("^[a-zA-Z]+$")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("nom ou prénom invalide");
                alert.setHeaderText(null);
                alert.setContentText("Votre nom et prenom doit contenir que des lettre !");
                alert.show();
            }else
            if(!password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$")){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("MDP invalide");
                alert.setHeaderText(null);
                alert.setContentText("Votre mdp doit contenir 8 caractéres avec un num et un caractere special");
                alert.show();
            }
        }else{
            
            Utilisateur utilisateur = new Utilisateur(cin, nom, prenom, email, password, telephone, type);

            

            int code = su.confirmationMail(email);
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Confirmation de l'inscription");

            Label instructionLabel = new Label("Nous avons envoyé un code au mail "+email+" :");
            TextField inputField = new TextField();


            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.add(instructionLabel, 0, 0, 2, 1);
            grid.add(inputField, 0, 1, 2, 1);

            dialog.getDialogPane().setContent(grid);
            ButtonType sendCodeButtonType = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(sendCodeButtonType, ButtonType.CANCEL);
            dialog.show();
            
            dialog.setResultConverter(dialogButton -> {


                if (dialogButton == sendCodeButtonType) {
                    String input = inputField.getText();
                        if (Integer.toString(code).equals(input)){
                            su.ajouter(utilisateur);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Confirmation");
                            alert.setHeaderText(null);
                            alert.setContentText("Utilisateur créé avec succès !");
                            alert.show();
                            try{
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            }catch(IOException ex){
                                
                            }
                           
     
  
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Code invalide");
                            alert.setHeaderText(null);
                            alert.setContentText("Votre code est invalide !");
                            alert.show();
                        }
                        
                }
                return null;
                });
        
        }
    }
    
}