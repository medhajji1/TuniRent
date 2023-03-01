/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.FileOutputStream;
import Services.ServiceReclamation;
import bdd.bdd;
import entities.reclamation;
import entities.reponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.reclamation.SeverityLevel;
import javafx.scene.control.Label;
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
    @FXML
    private Button charts;
    ResultSet rs;
    Statement st;
    PreparedStatement pst;
    private Connection cnx;
    @FXML
    private Button Triseverity;
    
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
        numid.setCellValueFactory(new PropertyValueFactory<reclamation, Integer>("sujet"));
        sid.setCellValueFactory(new PropertyValueFactory<reclamation, String>("numtel"));
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
                        //pdf lena
                        pdfbtn.setOnMouseClicked((MouseEvent event) -> {
                            dm = tab.getSelectionModel().getSelectedItem();
                            if (dm != null) {
                                Document doc = new Document();
                                try {
                                    // Create a file chooser dialog
                                    FileChooser fileChooser = new FileChooser();
                                    fileChooser.setTitle("Save PDF File");

                                    // Set the initial file name and extension filters
                                    String defaultFilename = "Serviec Reclamation.pdf";
                                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
                                    fileChooser.setInitialFileName(defaultFilename);
                                    fileChooser.getExtensionFilters().add(extFilter);

                                    // Display the dialog and get the selected file
                                    File selectedFile = fileChooser.showSaveDialog(pdfbtn.getScene().getWindow());
                                    if (selectedFile != null) {
                                        // Generate the PDF and save it to the selected file
                                        PdfWriter.getInstance(doc, new FileOutputStream(selectedFile));
                                        doc.open();

                                        // Add a title to the PDF
                                        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.RED);
                                        doc.add(new Phrase("Liste des réclamations", titleFont));
                                        // Create a table to hold the data
                                        PdfPTable table = new PdfPTable(new float[] { 3, 3, 3, 3, 5 });
                                        table.setWidthPercentage(100f);
                                        table.getDefaultCell().setPadding(3);
                                        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                                        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

                                        // Add table headers
                                        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
                                        PdfPCell headerCell;
                                        headerCell = new PdfPCell(new Phrase("Nom et Prenom", headerFont));
                                        headerCell.setBackgroundColor(BaseColor.BLUE);
                                        table.addCell(headerCell);
                                        headerCell = new PdfPCell(new Phrase("Email", headerFont));
                                        headerCell.setBackgroundColor(BaseColor.BLUE);
                                        table.addCell(headerCell);
                                        headerCell = new PdfPCell(new Phrase("numero de telephone", headerFont));
                                        headerCell.setBackgroundColor(BaseColor.BLUE);
                                        table.addCell(headerCell);
                                        headerCell = new PdfPCell(new Phrase("Sujet", headerFont));
                                        headerCell.setBackgroundColor(BaseColor.BLUE);
                                        table.addCell(headerCell);
                                        headerCell = new PdfPCell(new Phrase("Message", headerFont));
                                        headerCell.setBackgroundColor(BaseColor.BLUE);
                                        table.addCell(headerCell);

                                        // Add table data
                                        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
                                            table.addCell(new Phrase(dm.getNom(), dataFont));
                                            table.addCell(new Phrase(dm.getEmail(), dataFont));
                                            table.addCell(new Phrase(dm.getNumtel(), dataFont));
                                            table.addCell(new Phrase(dm.getSujet(), dataFont));
                                            table.addCell(new Phrase(dm.getMessage(), dataFont));
                                        doc.add(table);
                                        doc.close();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
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
    @FXML
    private void charts(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PieChart.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            PieChartController pieChartController = loader.getController();
            pieChartController.loadPieChartData();

            stage.show();
        } catch (IOException ex) {
            System.out.println("Error loading PieChart.fxml: " + ex.getMessage());
        }
    }

    @FXML
    private void Triseverity(ActionEvent event) {
        try {
    cnx = bdd.getinstance().get_connection();
    String req = "SELECT * FROM reclamation ORDER BY severity_level ASC";
    pst = cnx.prepareStatement(req);
    ResultSet rs = pst.executeQuery();
    // Process the sorted result set
} catch (SQLException ex) {
    System.out.println("Error: " + ex.getMessage());
}
    }

    @FXML
    private void Triseverity(MouseEvent event) {
        try {
    cnx = bdd.getinstance().get_connection();
    String req = "SELECT * FROM reclamation ORDER BY severity_level ASC";
    pst = cnx.prepareStatement(req);
    ResultSet rs = pst.executeQuery();
    // Process the sorted result set
} catch (SQLException ex) {
    System.out.println("Error: " + ex.getMessage());
    }
    
            }
}