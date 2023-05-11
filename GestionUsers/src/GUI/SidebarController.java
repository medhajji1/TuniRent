/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yadii
 */
public class SidebarController implements Initializable {

    @FXML
    private Button backid;
    @FXML
    private Button prodif;
    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;
    @FXML
    private Button voiture;
    @FXML
    private Button paiment;
    @FXML
    private Button Reclamationid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void tolandingpage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout.fxml"));
                        Parent root = loader.load(); 
                        Scene scene = new Scene(root, 429, 489);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        Stage retour = (Stage) backid.getScene().getWindow();
                        retour.close();
    }

    @FXML
    private void profiles(ActionEvent event) {
        loadPage("dashboard");
    }


    @FXML
    private void Reclamation(ActionEvent event) {
        loadPage("ddashboard");
    }

    @FXML
    private void Voiture(ActionEvent event) {
    }

    @FXML
    private void paiment(ActionEvent event) {
    }
    public void loadPage(String page)
    {
        Parent root =  null;
        try {
            root =FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GUI.SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
        
    }
}