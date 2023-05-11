/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import entities.Utilisateur;
import Services.ServiceUtilisateur;

public class DashboardController implements Initializable {

    @FXML
    private TableView<Utilisateur> tab;
    @FXML
    private TableColumn<Utilisateur, Integer> Cin;
    @FXML
    private TableColumn<Utilisateur, String> nid;
    @FXML
    private TableColumn<Utilisateur, String> pid;
    @FXML
    private TableColumn<Utilisateur, String> eid;
    @FXML
    private TableColumn<Utilisateur, String> numid;
    @FXML
    private TableColumn<Utilisateur, String> type;
    @FXML
    private TableColumn<Utilisateur, String> Aid;
    Utilisateur ut = null;
    ObservableList<Utilisateur>list  = FXCollections.observableArrayList();;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
                  ServiceUtilisateur CRUD =new ServiceUtilisateur();  
                   list=CRUD.getAll();
                   System.out.println(list);
        // TODO
        Cin.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("CIN"));
        nid.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
       pid.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
        eid.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
        numid.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("numeroTelephone"));   
        type.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("typeUtilisateur"));
             Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFoctory = (TableColumn<Utilisateur, String> param) -> {
            final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                      Button deletebutton = new Button("delete");
                       Button updatebutton = new Button("update");
                      
     
                      updatebutton.setOnMouseClicked((MouseEvent event) -> {
                     ut = tab.getSelectionModel().getSelectedItem();
                      System.out.println(ut);
                      
                        try {
                        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("UpdateUtilisateur.fxml"));
                        Parent root2 = loader2.load(); 
                        Scene scene = new Scene(root2, 429, 489);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        Stage dashboardStage = (Stage) updatebutton.getScene().getWindow();
                        dashboardStage.close();
                            UpdateUtilisateurController update = loader2.getController();
                            update.setelementtoupdate(ut);
                             
                        } catch (IOException ex) {
                          System.out.println("err "+ex.getMessage());
                        }
                      
                      
                      });
        
               deletebutton.setOnMouseClicked((MouseEvent event) -> {
                     ut = tab.getSelectionModel().getSelectedItem();
                     
                     if(ut != null)
                            {  
                               try {
                                   FXMLLoader loader3 = new FXMLLoader(getClass().getResource("supprimerutilisateur.fxml"));
                               Parent root3 = loader3.load(); 
                                   SupprimerutilisateurController CDC = loader3.getController();
                                    CDC.setdm(ut);
                                    CDC.settext("voulez vous supprimer Le compte de  : "+ut.getEmail());
                                    tab.getScene().setRoot(root3);
                               } catch (IOException ex) {
                                 System.out.println("error :"+ex.getMessage());
                               }
                     }
                      });
                       HBox managebtn = new HBox(updatebutton,deletebutton);
                       managebtn.setStyle("-fx-alignment:center");
                       HBox.setMargin(deletebutton, new Insets(2, 2, 0, 3));
                       deletebutton.setStyle("-fx-background-color:#ff1c1c;-fx-text-fill:white");
                       HBox.setMargin(updatebutton, new Insets(2, 3, 0, 2));
                       updatebutton.setStyle("-fx-background-color:#1dad00;-fx-text-fill:white");
                       
                       
                       
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
           
        };
        
        ///////////////////////////////////////////////////////////////////////////////////
        Aid.setCellFactory(cellFoctory);   
         tab.setItems(list);
         for (Utilisateur u : list) {
            System.out.println(u.getCIN() + " " + u.getNumeroTelephone());
    }
    
}
}