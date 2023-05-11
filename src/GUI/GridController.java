/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import gestion_voiture.entities.Voiture;
import gestion_voiture.services.ServiceCategorie;
import gestion_voiture.services.ServiceVoiture;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author moham
 */
public class GridController implements Initializable {
        ServiceVoiture sv = new ServiceVoiture();
    ServiceCategorie sc = new ServiceCategorie();
    
    @FXML GridPane grid;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }
    
    public void refresh() {
        grid.getChildren().clear();
        
        List<Voiture> data = sv.tout();
        
        grid.setHgap(10); 
        grid.setVgap(10);
            
        int x = 0, y = 0;
        for (int i = 0; i < data.size(); i++) {
            if (y == 3) {
                y = 0;
                x++;
            }
            
            Voiture v = data.get(i);
            
            try {
                VBox box = new VBox();
                
                ImageView imageView = new ImageView(new Image(new URL(v.getImage()).toExternalForm()));
                imageView.setFitWidth(300);
                imageView.setFitHeight(200);
                box.getChildren().add(imageView);
                
                String category = v.getCategorie().getMarque() + " " + v.getCategorie().getModele();
                box.getChildren().add(new Text(
                        v.getImmatriculation() + " (" + category + ")"
                ));
                box.getChildren().add(new Text(
                        "Kilometrage: " + v.getKilometrage() + "km, Couleur: " + v.getCouleur()
                ));
                
                GridPane.setConstraints(box, y, x, 1, 1, HPos.CENTER, VPos.CENTER);
                grid.getChildren().add(box);
                y++;
            } catch (Exception e) {
                System.out.println(e);
                continue;
            }
        }
        
    }
    
    public void map() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("map-view.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Find Us in Map");
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 700));
        stage.show();
    }
}
