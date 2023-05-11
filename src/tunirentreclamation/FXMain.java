/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunirentreclamation;

import Services.ServiceUtilisateur;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author yadii
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
<<<<<<< HEAD
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/mainpage.fxml"));
=======
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/login.fxml"));
>>>>>>> a9f2272f143766b9ac53dfea451dfae668a5dc1c
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TuniRent");
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
        launch(args);
    }
    
}
