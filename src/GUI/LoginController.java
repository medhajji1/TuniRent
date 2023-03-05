/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.Maile;
import Services.ServiceUtilisateur;
import com.mysql.jdbc.Connection;
import entities.Utilisateur;
import java.io.IOException;
import static java.lang.Class.forName;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LoginController implements Initializable {
private static Utilisateur u ;   
    
    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtMotDePasse;

    @FXML
    private Button btnok;
    
    @FXML
    private Button btnok1;
    @FXML
    private Hyperlink forgetPass_id;
    
    ServiceUtilisateur su = new ServiceUtilisateur();

    @FXML
    void forgetPass(ActionEvent event) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Forgot Password");

        Label instructionLabel = new Label("SVP confirmer votre email:");
        TextField inputField = new TextField();


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(instructionLabel, 0, 0, 2, 1);
        grid.add(inputField, 0, 1, 2, 1);

        dialog.getDialogPane().setContent(grid);
        ButtonType sendCodeButtonType = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(sendCodeButtonType, ButtonType.CANCEL);
        dialog.show();

        dialog.setResultConverter(dialogButton -> {
            
            
            if (dialogButton == sendCodeButtonType) {
                
                
                String input = inputField.getText();
                List<String> emails = su.getEmail();
                System.out.println(emails);
                for (int i = 0; i < emails.size(); i++) {
                    if (emails.get(i) .equals(input)){
                        try {
                            String mail= (String) emails.get(i);
                            
                            int code = su.forgetPasswordMail((String)emails.get(i));
                            
                            Dialog<ButtonType> dialog2 = new Dialog<>();
                            dialog2.setTitle("Confirmation de code");
                            Label instructionLabel2 = new Label("Nous avons envoyé un code au mail "+emails.get(i)+":");
                            TextField inputField2 = new TextField();
                            
                            
                            GridPane grid2 = new GridPane();
                            grid2.setHgap(10);
                            grid2.setVgap(10);
                            grid2.add(instructionLabel2, 0, 0, 2, 1);
                            grid2.add(inputField2, 0, 1, 2, 1);
                            
                            dialog2.getDialogPane().setContent(grid2);
                            ButtonType sendCodeButtonType2 = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
                            dialog2.getDialogPane().getButtonTypes().addAll(sendCodeButtonType2, ButtonType.CANCEL);
                            dialog.close();
                            dialog2.show();
                            
                            dialog2.setResultConverter(dialogButton2 -> {
                                String input2 = inputField2.getText();
                                if (dialogButton2 == sendCodeButtonType2 && input2.equals(Integer.toString(code)) ) {
                                    //code confirmer
                                    List pass;
                                    try {
                                        pass = su.getPass(mail);
                                        new Maile().sendMail("Votre mot de passe","Votre mot de passe est : \n\n\n\n\n\n\n\n"+(String)pass.get(0),mail);
                                    } catch (Exception ex) {
                                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                }
                                return null;
                            });
                            
                            
                            
                            break;
                        } catch (Exception ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);                     
                        }
                    }
                }     
            }
            return null;
            });
    }
    Connection con ;
    PreparedStatement pst; 
    ResultSet rs ; 

    public static Utilisateur getU() {
        return u;
    }
    
    
    
    @FXML
    
void login(ActionEvent event) throws Exception {

    String email = txtEmail.getText();
    String motDePasseDecrypter = txtMotDePasse.getText();
    String motDePasseCrypter=su.cryptage(motDePasseDecrypter);

    if (email.equals("") && motDePasseDecrypter.equals("")) {
        JOptionPane.showMessageDialog(null, "UserName or Password Blank");

    } else {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/utilisateurs", "root", "");

            pst = con.prepareStatement("select * from utilisateur where email=? and motDePasse=?");

            pst.setString(1, email);
            pst.setString(2, motDePasseCrypter);

            rs = pst.executeQuery();

            if (rs.next()) {
                // Récupérer le type d'utilisateur à partir de la base de données
                String userType = rs.getString("typeUtilisateur");
                int CIN = rs.getInt("CIN");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String uemail = rs.getString("email");
                String umotDePasse = rs.getString("motDePasse");
                String numeroTelephone = rs.getString("numeroTelephone");
                
                u.setCIN(CIN);
                u.setNom(nom);
                u.setPrenom(prenom);
                u.setEmail(uemail);
                u.setMotDePasse(motDePasseDecrypter);
                u.setNumeroTelephone(numeroTelephone);
               
                u.setTypeUtilisateur(userType); 
                System.out.println(u);
                if (userType.equals("client")) {
                    // Rediriger vers la page interfaceUser.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceUser.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else if (userType.equals("admin")) {
                    // Rediriger vers la page dashboard.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    // Si le type d'utilisateur n'est pas client ou admin, afficher un message d'erreur
                    JOptionPane.showMessageDialog(null, "Type d'utilisateur non valide.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Login failed");
                txtEmail.setText("");
                txtMotDePasse.setText("");
                txtEmail.requestFocus();;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u = new Utilisateur() ; 
        // TODO
    }
    
    @FXML
    void creerCompte(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("creerCompte.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
}
    

