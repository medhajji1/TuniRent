                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.Utilisateur;
import database.DataSource;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author abdelazizmezri
 */
public class ServiceUtilisateur implements IService<Utilisateur> {

    Connection cnx = DataSource.getInstance().getCnx();
        //-------------------------------encryption / decryption algorithme
    private static final String ALGORITHM = "AES";  
    private static final String secretKey="Karim_Gestion_User";

     //-------------------------------------Crypter mot de passe------------------------------------------------------------
     
     public static String cryptage(String plainText) throws Exception{
         
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] key = digest.digest(secretKey.getBytes(StandardCharsets.UTF_8));
        key = java.util.Arrays.copyOf(key, 16);
        SecretKeySpec secretKeyy = new SecretKeySpec(key, ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeyy);

        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
     }
     
         //-------------------------------------Decrypter mot de passe------------------------------------------------------------
     public static String decryptage(String encryptedText) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] key = digest.digest(secretKey.getBytes(StandardCharsets.UTF_8));
        key = java.util.Arrays.copyOf(key, 16);
        SecretKeySpec secretKeyy = new SecretKeySpec(key, ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeyy);

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(original);
     }     
     
     
    @Override
    public void ajouter(Utilisateur u) {
        try {
            String mdpCrypte=cryptage(u.getMotDePasse());
            String req = "INSERT INTO `utilisateur` (`CIN`,`nom`, `prenom`,`email`,`motDePasse`,`numeroTelephone`,`typeUtilisateur`) VALUES ('" + u.getCIN() + 
                    "', '" + u.getNom() + "', '" + u.getPrenom() + "', '" + u.getEmail() + "', '" + mdpCrypte + "', '" + u.getNumeroTelephone() + 
                    "','" + u.getTypeUtilisateur() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur creé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ajouter2(Utilisateur u) throws Exception {
        try {
            String mdpCrypte=cryptage(u.getMotDePasse());
            String req = "INSERT INTO `utilisateur` (`nom`, `prenom`, `email`, `motDePasse`, `numeroTelephone`,`typeUtilisateur`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(6, u.getTypeUtilisateur());
            ps.setString(5, u.getNumeroTelephone());
            ps.setString(4, mdpCrypte);
            ps.setString(3, u.getEmail());
            ps.setString(2, u.getPrenom());
            ps.setString(1, u.getNom());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int CIN) {
        try {
            String req = "DELETE FROM `utilisateur` WHERE CIN = " + CIN;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Utilisateur u) {
        String requeteUpdate = "UPDATE utilisateur SET nom=?, prenom=?, email=?, motDePasse=?, numeroTelephone=?,typeUtilisateur=? WHERE CIN=?";

            try {
                String mdpCrypte=cryptage(u.getMotDePasse());
                PreparedStatement st = cnx.prepareStatement(requeteUpdate);
                st.setString(1, u.getNom());
                st.setString(2, u.getPrenom());
                st.setString(3, u.getEmail());
                st.setString(4, mdpCrypte);
                st.setString(5, u.getNumeroTelephone());
                st.setString(6, u.getTypeUtilisateur());
                st.setInt(7, u.getCIN());
                st.executeUpdate();
                System.out.println("Utilisateur modifiée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Utilisateur> getAll() {
        ObservableList<Utilisateur>myList=FXCollections.observableArrayList();
        try { 
                String req = "SELECT * FROM utilisateur";
               PreparedStatement ps=cnx.prepareStatement(req);
                    ResultSet rs = ps.executeQuery(req);
              while(rs.next())
              {
                  Utilisateur R = new Utilisateur();
                  R.setCIN(rs.getInt("CIN"));
                  R.setNom(rs.getString("nom"));
                  R.setPrenom(rs.getString("prenom"));
                  R.setEmail(rs.getString("email"));
                  String mdpDeCrypte=decryptage(rs.getString("motDePasse"));
                  R.setMotDePasse(mdpDeCrypte);
                  R.setNumeroTelephone(rs.getString("numeroTelephone"));
                  R.setTypeUtilisateur(rs.getString("typeUtilisateur"));
                  myList.add(R);
              }
            } catch (SQLException ex) {
        System.err.println(ex.getMessage()); 
            } catch (Exception ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }

        return myList;
    }
    public void supprimer(Utilisateur rec) {
        
        try {
            String req = "DELETE from utilisateur where CIN="+rec.getCIN();
          PreparedStatement ps=cnx.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("utilisateur a ete supprimer !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public List<String> getEmail(){
        List<String> emails = new ArrayList<String>();

                 try {

                     String sql="SELECT `email` FROM utilisateur";
                             

                     PreparedStatement ps=cnx.prepareStatement(sql);
                                                  
                     ResultSet rs = ps.executeQuery();
                     while (rs.next()){
                         emails.add(rs.getString(1));
                       
                     }

                 } catch (SQLException ex) {
                     System.out.println(ex.getMessage());   
                 }    
                 return emails;

    }
    
        public List<String> getPass(String mail) throws Exception{
        List<String> pass = new ArrayList<String>();

                 try {
                     

                     String sql="SELECT `motDePasse` FROM utilisateur where email LIKE ?";
                             

                     PreparedStatement ps=cnx.prepareStatement(sql);
                     ps.setString(1, mail);                             
                     ResultSet rs = ps.executeQuery();
                     while (rs.next()){
                         String mdpDeCrypte=decryptage(rs.getString(1));
                         pass.add(mdpDeCrypte);
                        
                     }

                 } catch (SQLException ex) {
                     System.out.println(ex.getMessage());   
                 }    
                 return pass;

    }
    
    
    public int forgetPasswordMail(String mail) throws Exception{
        int min = 10000;    
        int max = 99999; 
        int code_random = (int)Math.floor(Math.random() * (max - min + 1) + min);
        String code="Votre code de verification est :  \n\n\n " +Integer.toString(code_random);
        new Maile().sendMail("Mot de passe oublié",code,mail);
        return code_random;
    }
    
    public int confirmationMail(String mail) throws Exception{
        int min = 10000;    
        int max = 99999; 
        int code_random = (int)Math.floor(Math.random() * (max - min + 1) + min);
        String code="Votre code de verification est :  \n\n\n " +Integer.toString(code_random);
        new Maile().sendMail("Vérification d'inscription",code,mail);
        return code_random;
    }
    
    
    public String getImage(Utilisateur u){
        String path="";
                         try {
                     

                     String sql="SELECT `image` FROM utilisateur where email LIKE ?";                             
                     PreparedStatement ps=cnx.prepareStatement(sql);
                     ps.setString(1, u.getEmail());                             
                     ResultSet rs = ps.executeQuery();
                     while (rs.next()){
                         path=rs.getString(1);
                        
                     }

                 } catch (SQLException ex) {
                     System.out.println(ex.getMessage());   
                 } 
        
        
        return path;
    }
        public void updateImage(String path, String email) {
        String requeteUpdate = "UPDATE utilisateur SET image=? WHERE email=?";

            try {
                
                PreparedStatement st = cnx.prepareStatement(requeteUpdate);
                st.setString(1, path);
                st.setString(2,email);

                st.executeUpdate();
                System.out.println("Utilisateur modifiée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
