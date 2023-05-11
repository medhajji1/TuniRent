/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture;

import gestion_voiture.entities.Reservation;
import gestion_voiture.entities.Voiture;
import gestion_voiture.services.ServiceVoiture;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class StatController implements Initializable {

    @FXML
    private PieChart pie;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
