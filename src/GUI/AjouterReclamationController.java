/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author yadii
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField np;
    @FXML
    private TextField mail;
    @FXML
    private TextField numtel;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea msg;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    private Pattern nomPattern = Pattern.compile("[a-zA-Z]+\\s[a-zA-Z]+");
    private Pattern emailPattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
    private Pattern sujetPattern = Pattern.compile("[a-zA-Z ]+");
    private Pattern messagePattern = Pattern.compile("[a-zA-Z ]+");
    private Pattern numtelPattern = Pattern.compile("\\d{8}");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        np.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!nomPattern.matcher(newValue).matches()) {
                np.setStyle("-fx-border-color: red;");
            } else {
                np.setStyle("");
            }
        });

        mail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!emailPattern.matcher(newValue).matches()) {
                mail.setStyle("-fx-border-color: red;");
            } else {
                mail.setStyle("");
            }
        });

        sujet.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!sujetPattern.matcher(newValue).matches()) {
                sujet.setStyle("-fx-border-color: red;");
            } else {
                sujet.setStyle("");
            }
        });

        numtel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!numtelPattern.matcher(newValue).matches()) {
                numtel.setStyle("-fx-border-color: red;");
            } else {
                numtel.setStyle("");
            }
        });
    }

    @FXML
    private void Addreclamation(ActionEvent event) {
        // Code pour ajouter la réclamation à la base de données
    }
    

    @FXML
    private void handleKeyType(javafx.scene.input.KeyEvent event) {
        boolean hasInvalidInput = !nomPattern.matcher(np.getText()).matches()
                || !emailPattern.matcher(mail.getText()).matches()
                || !sujetPattern.matcher(sujet.getText()).matches()
                || !messagePattern.matcher(msg.getText()).matches()
                || !numtelPattern.matcher(numtel.getText()).matches();
        btn.setDisable(hasInvalidInput);
    }
}