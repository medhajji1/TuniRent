/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.security.ntlm.Client;
import entities.Utilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javax.sql.DataSource;
import GUI.LoginController; 
import Services.ServiceUtilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class InterfaceUserController implements Initializable {
    ServiceUtilisateur su = new ServiceUtilisateur();
    Utilisateur u=null;

        @FXML
    private ImageView id_image;

    @FXML
    private Button button_changer;

    @FXML
    private Button button_modifier;

    @FXML
    private Button button_supprimer;

    @FXML
    private Button button_deconnecter;

    @FXML
    private TextField nom_id;

    @FXML
    private TextField prenom_id;

    @FXML
    private TextField email_id;

    @FXML
    private TextField cin_id;

    @FXML
    private TextField tel_id;

    @FXML
    private TextField type_id;

    @FXML
    private TextField mdp_id;;


     
    

    /**
     * Initializes the controller class.
     */
    @Override
     public void initialize(URL url, ResourceBundle rb) { 
       this.u=LoginController.getU();
       nom_id.setText(u.getNom());
       prenom_id.setText(u.getPrenom());
       email_id.setText(u.getEmail());
       cin_id.setText(Integer.toString(u.getCIN()));
       tel_id.setText(u.getNumeroTelephone());
       mdp_id.setText(u.getMotDePasse());
       type_id.setText(u.getTypeUtilisateur());
       String path=su.getImage(u);
       path = path.replaceAll("'", "");
       Image image;          
        File file = new File(path);
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                image = new Image(inputStream);
                id_image.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist");
            su.updateImage("src/images/default.png", u.getEmail());
            try {
                FileInputStream inputStream = new FileInputStream("src/images/default.png");
                image = new Image(inputStream);
                id_image.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        
     
    }





    @FXML
    void changerImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image TUNIRENT");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            String filePath = file.getAbsolutePath();
            Image image;  
            try {
                image = new Image(new FileInputStream(filePath));
                id_image.setImage(image);
                su.updateImage(filePath,u.getEmail());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InterfaceUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }

    @FXML
    void deconnecter(ActionEvent event) {
        try {              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"
                ));
            Parent page1 = loader.load();
            
            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

            } catch (IOException ex) {


                }     
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {
          FXMLLoader loader2 = new FXMLLoader(getClass().getResource("UpdateUtilisateurClient.fxml"));
                        Parent root2 = loader2.load(); 
                        Scene scene = new Scene(root2, 429, 489);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        Stage dashboardStage = (Stage) button_modifier.getScene().getWindow();
                        dashboardStage.close();
                            UpdateUtilisateurClientController update = loader2.getController();
                            update.setelementtoupdate(LoginController.getU());
    }

    @FXML
    void supprimer(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Vous étes sérieux ?");
        alert.setContentText("Voulez vous vraiment supprimer votre compte ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // User clicked OK button
            su.supprimer(u);
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email invalide");
            alert.setHeaderText(null);
            alert.setContentText("Votre email est invalide");
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
        } else {
            // User clicked Cancel button or closed the dialog
            alert.close();
        }
    }
    
}
