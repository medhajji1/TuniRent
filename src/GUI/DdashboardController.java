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
                     if(dm != null){
                          try {
                              generatePDF(tab);
                          } catch (IOException ex) {
                              Logger.getLogger(DdashboardController.class.getName()).log(Level.SEVERE, null, ex);
                          } catch (COSVisitorException ex) {
                              Logger.getLogger(DdashboardController.class.getName()).log(Level.SEVERE, null, ex);
                          }
                     }
                      });
               
            Reponsebutton.setOnMouseClicked((MouseEvent event) -> {
                     dm = tab.getSelectionModel().getSelectedItem();
                     
                     if(dm != null)
                            {  FXMLLoader loader = new FXMLLoader(getClass().getResource("EnvoyerReponse.fxml"));
                               try {
                               Parent root = loader.load(); 
                                   EnvoyerReponseController rep = loader.getController();
                            rep.setelementtoupdate(dm);
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
        tab.setItems(observableList);
        
        tab.setItems(observableList);
         nid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("nom"));
       eid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("email"));
        numid.setCellValueFactory(new PropertyValueFactory<reclamation,Integer>("numtel"));
        sid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("sujet"));   
        mid.setCellValueFactory(new PropertyValueFactory<reclamation,String>("message")); 
        }  
    public void generatePDF(TableView<reclamation> table) throws IOException, COSVisitorException {
        PDDocument document = new PDDocument();
        PDFont font = PDType1Font.HELVETICA_BOLD;
        float fontSize = 12;
           // Set up PDF document and content stream
           document = new PDDocument();
           PDPage page = new PDPage();
           document.addPage(page);
           PDPageContentStream contentStream = new PDPageContentStream(document, page);
           // Set leading
           double leading = 14.5f;
           contentStream.setLeading(leading);
           // Set starting position
           double startX = 25;
           double startY = page.getMediaBox().getHeight() - leading;
           contentStream.newLineAtOffset( (float) startX,(float) startY);
           // Display column headers
           ObservableList<TableColumn<reclamation, ?>> columns = table.getColumns();
           for (TableColumn<reclamation, ?> column : columns) {
               String header = column.getText();
               contentStream.setFont(PDType1Font.HELVETICA, fontSize);
               contentStream.showText( (String) header);
               contentStream.newLineAtOffset((float) columns.get(0).getWidth(), 0);
           }
           contentStream.newLine();
           // Display table rows
           ObservableList<reclamation> items = table.getItems();
           for (reclamation item : items) {
               // Display cell values, handling null values
               String nom = item.getNom() != null ? item.getNom() : "";
               String email = item.getEmail() != null ? item.getEmail() : "";
               String numtel = item.getNumtel() != null ? item.getNumtel() : "";
               String sujet = item.getSujet() != null ? item.getSujet() : "";
               String message = item.getMessage() != null ? item.getMessage() : "";
               
               // Set font and font size for cell values
               contentStream.setFont(PDType1Font.HELVETICA, fontSize);
               contentStream.showText(nom);
                contentStream.newLineAtOffset((float) columns.get(0).getWidth(), 0);
                contentStream.showText(email);
                contentStream.newLineAtOffset((float) columns.get(1).getWidth(), 0);
                contentStream.showText(numtel);
                contentStream.newLineAtOffset((float) columns.get(2).getWidth(), 0);
                contentStream.showText(sujet);
                contentStream.newLineAtOffset((float) columns.get(3).getWidth(), 0);
                contentStream.showText(message);
                contentStream.newLineAtOffset((float) columns.get(4).getWidth(), 0);
                contentStream.newLine();
           }
           // Clean up resources
           contentStream.endText();
           document.save("table.pdf");
            document.close();
            }
}
            
    

