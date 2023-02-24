/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceReclamation;
import entities.reclamation;
import entities.reclamation.Category;
import entities.reclamation.SeverityLevel;
import entities.reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

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
    ObservableList<reclamation> list = FXCollections.observableArrayList();
    @FXML
    private TextField search;
    @FXML
    private TableColumn<reclamation, LocalDateTime> date;
    @FXML
    private TableColumn<reclamation, reclamation.Status> status;
    @FXML
    private TableColumn<reclamation, reclamation.Category> cat;
    @FXML
    private TableColumn<reclamation, reclamation.SeverityLevel> severity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tab.setItems(list);
        ServiceReclamation CRUD = new ServiceReclamation();
        list = CRUD.getAll();

        // TODO
        nid.setCellValueFactory(new PropertyValueFactory<reclamation, String>("nom"));
        eid.setCellValueFactory(new PropertyValueFactory<reclamation, String>("email"));
        numid.setCellValueFactory(new PropertyValueFactory<reclamation, Integer>("numtel"));
        sid.setCellValueFactory(new PropertyValueFactory<reclamation, String>("sujet"));
        mid.setCellValueFactory(new PropertyValueFactory<reclamation, String>("message"));
        cat.setCellValueFactory(new PropertyValueFactory<reclamation, reclamation.Category>("category"));
        status.setCellValueFactory(new PropertyValueFactory<reclamation, reclamation.Status>("status"));
        severity.setCellValueFactory(new PropertyValueFactory<reclamation, reclamation.SeverityLevel>("severityLevel"));
        date.setCellValueFactory(new PropertyValueFactory<reclamation, LocalDateTime>("dateSubmitted"));
        Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>> cellFoctory = (TableColumn<reclamation, String> param) -> {
                        status.setCellFactory(column -> {
                 return new TableCell<reclamation, reclamation.Status>() {
                     @Override
                     protected void updateItem(reclamation.Status item, boolean empty) {
                         super.updateItem(item, empty);
                         if (empty || item == null) {
                             setText(null);
                             setStyle("");
                         } else {
                             setText(item.toString());
                             if (item == reclamation.Status.RESOLVED) {
                                 setStyle("-fx-text-fill: green;");
                             } else if (item == reclamation.Status.NEW) {
                                 setStyle("-fx-text-fill: lightblue;");
                             } else if (item == reclamation.Status.INPROGRESS) {
                                 setStyle("-fx-text-fill: orange;");
                             }
                             else if (item == reclamation.Status.CLOSED) {
                                 setStyle("-fx-text-fill: red;");
                             } else {
                                 setStyle("");
                             }
                         }
                     }
                 };
             });
           severity.setCellFactory(column -> {
                 return new TableCell<reclamation, reclamation.SeverityLevel>() {
                     @Override
                     protected void updateItem(reclamation.SeverityLevel item, boolean empty) {
                         super.updateItem(item, empty);
                         if (empty || item == null) {
                             setText(null);
                             setStyle("");
                         } else {
                             setText(item.toString());
                             if (item == reclamation.SeverityLevel.HIGH) {
                                 setStyle("-fx-text-fill: red;");
                             } else if (item == reclamation.SeverityLevel.LOW) {
                                 setStyle("-fx-text-fill: green;");
                             } else {
                                 setStyle("");
                             }
                         }
                     }
                 };
             });
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

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("updatereclamation.fxml"));
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
                                System.out.println("err " + ex.getMessage());
                            }

                        });

                        deletebutton.setOnMouseClicked((MouseEvent event) -> {
                            dm = tab.getSelectionModel().getSelectedItem();

                            if (dm != null) {
                                
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("supprimerDemande.fxml"));
                                try {
                                    
                                    Parent root = loader.load();
                                    SupprimerDemandeController CDC = loader.getController();
                                    CDC.setdm(dm);
                                    CDC.settext("voulez vous supprimer Le de demande de  : " + dm.getEmail());
                                    tab.getScene().setRoot(root);
                                } catch (IOException ex) {
                                    System.out.println("error :" + ex.getMessage());
                                }
                            }
                        });

                        Reponsebutton.setOnMouseClicked((MouseEvent event) -> {
                        dm = tab.getSelectionModel().getSelectedItem();
                        ServiceReclamation SM = new ServiceReclamation();
        if (dm != null) {
                        dm.setStatus(reclamation.Status.INPROGRESS); // set the status to in progress
                        dm.setSeverityLevel(reclamation.SeverityLevel.LOW); // set severity level to low
                        SeverityLevel severity = SeverityLevel.LOW;
                        Label label = new Label();
                        label.setStyle("-fx-text-fill: " + severity.getColor());
                        SM.update(dm); // update the record in the database
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EnvoyerReponse.fxml"));
        try {
            Parent root = loader.load();
            EnvoyerReponseController rep = loader.getController();
            rep.setelementtoupdate(dm);
            tab.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error :" + ex.getMessage());
        }
    }
});

                        HBox managebtn = new HBox(updatebutton, deletebutton, Reponsebutton, pdfbtn);
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
                } else if (reclamation.getNumtel().contains(lowerCaseFilter)) {
                    return true;
                } else if (reclamation.getCategory().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (reclamation.getStatus().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (reclamation.getSeverityLevel().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (reclamation.getDateSubmitted().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

    }

    private void TrieNom(ActionEvent event) throws SQLException {

        ServiceReclamation vs = new ServiceReclamation();

        ObservableList<reclamation> observableList = null;
        tab.setItems(observableList);
        nid.setCellValueFactory(new PropertyValueFactory<reclamation, String>("nom"));
        eid.setCellValueFactory(new PropertyValueFactory<reclamation, String>("email"));
        numid.setCellValueFactory(new PropertyValueFactory<reclamation, Integer>("numtel"));
        sid.setCellValueFactory(new PropertyValueFactory<reclamation, String>("sujet"));
        mid.setCellValueFactory(new PropertyValueFactory<reclamation, String>("message"));
        cat.setCellValueFactory(new PropertyValueFactory<reclamation, reclamation.Category>("category"));
        status.setCellValueFactory(new PropertyValueFactory<reclamation, reclamation.Status>("status"));
        severity.setCellValueFactory(new PropertyValueFactory<reclamation, reclamation.SeverityLevel>("severityLevel"));
        date.setCellValueFactory(new PropertyValueFactory<reclamation, LocalDateTime>("dateSubmitted"));
    }

}
