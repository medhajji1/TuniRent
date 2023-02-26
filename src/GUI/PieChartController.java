/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import bdd.bdd;
import entities.reclamation.Status;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
    
/**
 * FXML Controller class
 *
 * @author Yaadiii
 */
public class PieChartController implements Initializable {
    ResultSet rs;
    Statement st;
    PreparedStatement pst;
    private Connection cnx;
    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
     public void loadPieChartData() {
        int newCount = countStatus(Status.NEW);
        int openCount = countStatus(Status.OPEN);
        int inProgressCount = countStatus(Status.INPROGRESS);
        int closedCount = countStatus(Status.CLOSED);
        int resolvedCount = countStatus(Status.RESOLVED);
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("New", newCount),
                        new PieChart.Data("Open", openCount),
                        new PieChart.Data("In Progress", inProgressCount),
                        new PieChart.Data("Closed", closedCount),
                        new PieChart.Data("resolved", resolvedCount)
                );
        pieChart.setData(pieChartData);
    }
     public int countStatus(Status status) {
     try {
        cnx = bdd.getinstance().get_connection();
        String req = "SELECT COUNT(*) AS count FROM reclamation WHERE status = ?";
        pst = cnx.prepareStatement(req);
        pst.setString(1, status.toString());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("count");
            return count;
        }
        return 0;
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
        return 0;
    }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
