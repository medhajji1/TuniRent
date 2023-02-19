/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceReclamation;
import entities.reclamation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.regex.Pattern;
import javafx.scene.control.TextFormatter;
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
    reclamation d =null; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Pattern charPattern = Pattern.compile("[a-zA-Z ]*"); // ken letter ou espace
    Pattern emailPattern = Pattern.compile(".+@.+\\..+"); // @ ou . 
    Pattern numtelPattern = Pattern.compile("[259]\\d{7}"); // 8 num ou yabda 2 5 9 
            txtNom.setTextFormatter(new TextFormatter<>(change -> {
                String newText = change.getControlNewText();
                if (charPattern.matcher(newText).matches()) {
                    return change;
                }
                return null;
            }));
            txtEmail.setTextFormatter(new TextFormatter<>(change -> {
                String newText = change.getControlNewText();
                if (emailPattern.matcher(newText).matches()) {
                    return change;
                }
                return null;
            }));

            // Set up text formatter for numtel
            txtNumtel.setTextFormatter(new TextFormatter<>(change -> {
                String newText = change.getControlNewText();
                if (numtelPattern.matcher(newText).matches()) {
                    return change;
                }
                return null;
    }));
    // Set up text formatter for sujet and message
    txtSujet.setTextFormatter(new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (newText.matches("[a-zA-Z]+")) {
            return change;
        }
        return null;
    }));
    txtMessage.setTextFormatter(new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (!newText.isEmpty() && newText.matches("[a-zA-Z]+")) {
            return change;
        }
        return null;
    }));
    }
    public void setelementtoupdate(reclamation d){
        this.d=d;
    System.out.println("les champs a etes modifier :"+ d.toString());
    this.txtNom.setText(d.getNom());
    this.txtEmail.setText(d.getEmail());
    this.txtNumtel.setText(d.getNumtel());
    this.txtSujet.setText(d.getSujet());
    this.txtMessage.setText(d.getMessage());
    }
    @FXML
    private void updateReclamation(ActionEvent event) throws SQLException {
        String nom = txtNom.getText();
            String email = txtEmail.getText();
            String numtel = txtNumtel.getText();
              String sujet = txtSujet.getText(); 
              String message = txtMessage.getText();
            ServiceReclamation SM =new ServiceReclamation(); 
            this.d.setNom(nom);
            this.d.setEmail(email);
            this.d.setNumtel(numtel);
            this.d.setSujet(sujet);
            this.d.setMessage(message);     
           SM.modifier(d);
           
    }

}