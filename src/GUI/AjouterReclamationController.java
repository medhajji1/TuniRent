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
    import entities.reclamation.Status;
    import java.io.IOException;
    import java.net.URL;
    import java.time.LocalDateTime;
    import java.util.ResourceBundle;
    import java.util.regex.Pattern;
import javafx.concurrent.Worker;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.ButtonType;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.Label;
    import javafx.scene.control.TextArea;
    import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
    import javafx.scene.web.WebView;

    /**
     * FXML Controller class
     *
     * @author yadii
     */
    public class AjouterReclamationController implements Initializable {

        @FXML
        private TextField np;
        @FXML
        private TextField mail;
        @FXML
        private TextField numtel;
        @FXML
        private TextField sujet;
        @FXML
        private TextArea msg;
        @FXML
        private Button btn;          
        /**
         * Initializes the controller class.
         * @param url
         * @param rb
         */
        private final Pattern nomPattern = Pattern.compile("[a-zA-Z]+\\s[a-zA-Z]+");
        private final Pattern emailPattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
        private final Pattern sujetPattern = Pattern.compile("[a-zA-Z0-9 ]+");
        private final Pattern messagePattern = Pattern.compile("[a-zA-Z0-9 ]+");
        private final Pattern numtelPattern = Pattern.compile("[259]\\d{7}");
        @FXML
        private ComboBox<String> category;
        private Category selectedCategory; 
            @FXML
            private WebView webview;
            WebEngine webEngine = null;     
        @Override
        public void initialize(URL url, ResourceBundle rb) {
        webEngine  = webview.getEngine();
        webEngine.load("https://www.google.com/recaptcha/api2/anchor?ar=1&k=6LfEaFkUAAAAAGnIJMG983t2JyYg0McK4CUuRAdk&co=aHR0cHM6Ly93d3cudXBsb2FkLTRldmVyLmNvbTo0NDM.&hl=fr&v=Nh10qRQB5k2ucc5SCBLAQ4nA&size=normal&cb=fy9vwi71q7so");
            category.getItems().addAll("qualité", "service", "facturation");
              btn.setDisable(true);
            np.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!nomPattern.matcher(newValue).matches()) {
                    np.setStyle("-fx-border-color: red;-fx-text-fill: red;");
                } else {
                    np.setStyle("-fx-text-fill: green;");
                }
                handleKeyType();
            });

            mail.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!emailPattern.matcher(newValue).matches()) {
                    mail.setStyle("-fx-border-color: red;-fx-text-fill: red;");
                } else {
                    mail.setStyle("-fx-text-fill: green;");
                }
                handleKeyType();
            });

            sujet.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!sujetPattern.matcher(newValue).matches()) {
                    sujet.setStyle("-fx-border-color: red;");
                } else {
                    numtel.setStyle("-fx-text-fill: green;");
                }
                handleKeyType();
            });

            msg.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!messagePattern.matcher(newValue).matches()) {
                    msg.setStyle("-fx-border-color: red;-fx-text-fill: red;");
                } else {
                    msg.setStyle("-fx-text-fill: green;");
                }
                handleKeyType();
            });

            numtel.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!numtelPattern.matcher(newValue).matches()) {
                    numtel.setStyle("-fx-border-color: red;-fx-text-fill: red;");
                } else {
                    numtel.setStyle("-fx-text-fill: green;");
                }
                handleKeyType();
            });
        }

        @FXML
    private void Addreclamation(ActionEvent event) throws IOException {
            ServiceReclamation sp = new ServiceReclamation();
            if (np.getText().isEmpty() || mail.getText().isEmpty() || numtel.getText().isEmpty()
                    || sujet.getText().isEmpty() || msg.getText().isEmpty() || category.getSelectionModel().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.WARNING, "Tous les champs sont obligatoires", ButtonType.OK);
                a.showAndWait();
                return;
            }
            this.selectedCategory = Category.valueOf(category.getSelectionModel().getSelectedItem().toUpperCase());
            reclamation p = new reclamation(np.getText(), mail.getText(), numtel.getText(), sujet.getText(), msg.getText(), selectedCategory);
            p.setStatus(Status.NEW);
            SeverityLevel severity = SeverityLevel.HIGH;
            Label label = new Label();
            label.setStyle("-fx-text-fill: " + severity.getColor());
            p.setSeverityLevel(SeverityLevel.HIGH);
            p.setDateSubmitted(LocalDateTime.now());
            sp.ajouter(p);
            System.out.println(p);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Votre demande a été envoyée!", ButtonType.OK);
            a.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheDemande.fxml"));
            Parent root = loader.load();
            np.getScene().setRoot(root);
            GUI.AfficheDemandeController apc = loader.getController();
            apc.setNom(np.getText());
            apc.setEmail(mail.getText());
            apc.setNumtel(numtel.getText());
            apc.setSujet(sujet.getText());
            apc.setMessage(msg.getText());
            apc.setCategory(selectedCategory);
        }

        @FXML
        private void handleKeyType() {
            boolean hasInvalidInput = !nomPattern.matcher(np.getText()).matches()
                    || !emailPattern.matcher(mail.getText()).matches()
                    || !sujetPattern.matcher(sujet.getText()).matches()
                    || !messagePattern.matcher(msg.getText()).matches()
                    || !numtelPattern.matcher(numtel.getText()).matches();
            btn.setDisable(hasInvalidInput);

        }

        }