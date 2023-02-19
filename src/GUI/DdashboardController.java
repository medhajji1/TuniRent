/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceReclamation;
import entities.reclamation;
import entities.reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author yadii
 */
public class DdashboardController implements Initializable {

   @FXML
    private TableView<reclamation> tab;
    @FXML
    private TableColumn<reclamation, String> nid;
    @FXML
    private TableColumn<reclamation, String> eid;
    @FXML
    private TableColumn<reclamation, Integer> numid;
    @FXML
    private TableColumn<reclamation, String> sid;
    @FXML
    private TableColumn<reclamation, String> mid;
    @FXML
    private TableColumn<reclamation, String> Aid;
    reclamation dm = null;
    reponse r = null;
 ObservableList<reclamation>list  = FXCollections.observableArrayList();;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              ServiceReclamation CRUD =new ServiceReclamation();  
       list=CRUD.getAll();
        
        // TODO
        nid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("nom"));
       eid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("email"));
        numid.setCellValueFactory(new PropertyValueFactory<reclamation,Integer>("numtel"));
        sid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("sujet"));   
        mid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("message"));        //Aid.setCellValueFactory(new Button("send email"));///////////////////////////////////
             Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>> cellFoctory = (TableColumn<reclamation, String> param) -> {
            final TableCell<reclamation, String> cell = new TableCell<reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                      Button deletebutton = new Button("delete");
                       Button updatebutton = new Button("update");
                       Button Reponsebutton = new Button("Reponder");
                       Button pdfbtn = new Button("PDF");
                      
     
                      updatebutton.setOnMouseClicked((MouseEvent event) -> {
                     dm = tab.getSelectionModel().getSelectedItem();
                      System.out.println(dm);
                      
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("updateReclamation.fxml"));
                        try {
                        Parent root = loader.load(); 
                        Scene scene = new Scene(root, 429, 489);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        Stage dashboardStage = (Stage) updatebutton.getScene().getWindow();
                        dashboardStage.close();
                            UpdateReclamationController update = loader.getController();
                            update.setelementtoupdate(dm);
                             tab.getScene().setRoot(root);
                        } catch (IOException ex) {
                          System.out.println("err "+ex.getMessage());
                        }
                      
                      
                      });
        
               deletebutton.setOnMouseClicked((MouseEvent event) -> {
                     dm = tab.getSelectionModel().getSelectedItem();
                     
                     if(dm != null)
                            {  FXMLLoader loader = new FXMLLoader(getClass().getResource("supprimerDemande.fxml"));
                               try {
                               Parent root = loader.load(); 
                                   SupprimerDemandeController CDC = loader.getController();
                                    CDC.setdm(dm);
                                    CDC.settext("voulez vous supprimer Le de demande de  : "+dm.getEmail());
                                    tab.getScene().setRoot(root);
                               } catch (IOException ex) {
                                 System.out.println("error :"+ex.getMessage());
                               }
                     }
                      });
                      
               pdfbtn.setOnMouseClicked((MouseEvent event) -> {
                     dm = tab.getSelectionModel().getSelectedItem();
                     String path="";
                     if(dm != null){}
                            
                     
                      });
            Reponsebutton.setOnMouseClicked((MouseEvent event) -> {
                     dm = tab.getSelectionModel().getSelectedItem();
                     
                     if(dm != null)
                            {  FXMLLoader loader = new FXMLLoader(getClass().getResource("EnvoyerReponse.fxml"));
                               try {
                               Parent root = loader.load(); 
                                   AfficheDemandeController CDC = loader.getController();
                                    //CDC.setdm(dm);
                                    tab.getScene().setRoot(root);
                               } catch (IOException ex) {
                                 System.out.println("error :"+ex.getMessage());
                               }
                     }
                      });
      
                      
                      
                        HBox managebtn = new HBox(updatebutton,deletebutton,Reponsebutton,pdfbtn);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deletebutton, new Insets(2, 2, 0, 3));
                        deletebutton.setStyle("-fx-background-color:#ff1c1c;-fx-text-fill:white");
                       HBox.setMargin(updatebutton, new Insets(2, 3, 0, 2));
                       updatebutton.setStyle("-fx-background-color:#1dad00;-fx-text-fill:white");
                       HBox.setMargin(Reponsebutton, new Insets(2, 4, 0, 1));
                       Reponsebutton.setStyle("-fx-background-color:#00c3ff;-fx-text-fill:white");
                       HBox.setMargin(pdfbtn, new Insets(2, 5, 0, 1));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        
        ///////////////////////////////////////////////////////////////////////////////////
        Aid.setCellFactory(cellFoctory);    
               
            
        
         FilteredList<reclamation> filteredList = new FilteredList<>(list, p -> true);
        tab.setItems(filteredList);
      search.textProperty().addListener((observable, oldValue, newValue) -> {
    filteredList.setPredicate((reclamation reclamation) -> {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();
            
        if (reclamation.getNom().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (reclamation.getEmail().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (reclamation.getSujet().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        } else if (reclamation.getMessage().toLowerCase().contains(lowerCaseFilter)) {
            return true;
        }else if (reclamation.getNumtel().contains(lowerCaseFilter)) {
            return true;
        }

        return false;
    });
        });  
      
    }    
    private void TrieNom(ActionEvent event) throws SQLException {
        
             ServiceReclamation vs=new ServiceReclamation();

        ObservableList<reclamation> observableList = null;
        //observableList = FXCollections.observableArrayList(vs.TrieNom());
        tab.setItems(observableList);
        
        tab.setItems(observableList);
         nid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("nom"));
       eid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("email"));
        numid.setCellValueFactory(new PropertyValueFactory<reclamation,Integer>("numtel"));
        sid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("sujet"));   
        mid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("message")); 
        }   
}
