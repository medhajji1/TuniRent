/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceReponse;
import entities.reclamation;
import entities.reponse;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author yadii
 */
public class EnvoyerReponseController implements Initializable {

    @FXML
    private AnchorPane anchropane;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btn_ajout;
    reclamation d =null;
    @FXML
    private TextArea obj;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setdm(reclamation d){
 
         this.d=d;
    }
    public void setelementtoupdate(reclamation d){
        this.d=d;
    System.out.println("email :"+ d.toString());
    this.tfEmail.setText(d.getEmail());
    tfEmail.setEditable(false);
    }
    @FXML
private void ajout(ActionEvent event) throws SQLException {
    ServiceReponse rep = new ServiceReponse();
    if (obj.getText().isEmpty()) {
        Alert a = new Alert(Alert.AlertType.WARNING, "Tous les champs sont obligatoires", ButtonType.OK);
        a.showAndWait();
        return;
    }
    reponse p = new reponse(obj.getText());
    p.setRec(d); // Set the reclamation ID in the response object
    rep.ajouter(p);
    Alert a = new Alert(Alert.AlertType.INFORMATION, "Votre demande a été envoyée!", ButtonType.OK);
    a.showAndWait();
}
    
}
