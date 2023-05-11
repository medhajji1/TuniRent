/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

/**
 *
 * @author moham
 */
public class MapViewController implements Initializable {
    
    @FXML 
    WebView map;
    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String mapUrl = "https://goo.gl/maps/E92HAwWS5119EqEo8";
        map.getEngine().load(mapUrl);
    }
}
