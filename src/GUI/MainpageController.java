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
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Yaadiii
 */
public class MainpageController implements Initializable {

    @FXML
    private Button acceuil;
    @FXML
    private Button voitures;
    @FXML
    private Button Recmpid;
    @FXML
    private Button profile;
    @FXML
    private Button todashboardid;
    @FXML
    private BorderPane bpid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void acceuil(ActionEvent event) {
    }

    @FXML
    private void voitures(ActionEvent event) {
    }

    @FXML
    private void Reclamationmp(ActionEvent event) {
        loadPage("AjouterReclamation");
    }

    @FXML
    private void profile(ActionEvent event) {
        loadPage("interfaceUser");
    }

    @FXML
    private void todashboard(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml")); 
                      try {
                        Parent root = loader.load();
                            todashboardid.getScene().setRoot(root);
                        } catch (IOException ex) {
                         System.out.println("error : "+ex.getMessage());                        
                        }
    }
       public void loadPage(String page)
    {
        Parent root =  null;
        try {
            root =FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(SidebarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bpid.setCenter(root);
        
    }
    
}
