/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Yaadiii
 */
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.codec.binary.Base64;


public class Mail {
    private final Gmail service;
    public Mail() throws Exception {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

         service = new Gmail.Builder(HTTP_TRANSPORT, GsonFactory.getDefaultInstance(), getCredentials(HTTP_TRANSPORT,GsonFactory.getDefaultInstance()))
        .setApplicationName("Tunirent")
        .build();
          
    }
    
                
    
     private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, GsonFactory json_factory)
      throws IOException {
    
    File clientSecretsFile = new File("C:/Users/DELL/Desktop/java pi/TuniRent/src/Services/Gmail_credentials.json");
    InputStream inputStream = new FileInputStream(clientSecretsFile);
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(json_factory, new InputStreamReader(inputStream));

    // Build flow and trigger user authorization request.
    Set<String> scopes = Collections.singleton(GmailScopes.GMAIL_SEND);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            HTTP_TRANSPORT, json_factory, clientSecrets, scopes)
            .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
            .setAccessType("offline")
            .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(1234).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    //returns an authorized Credential object.
    return credential;
  }
     
  public void sendMail(String subject, String message, String receiver, File attachment) throws IOException, GeneralSecurityException, MessagingException {
    // Encode as MIME message
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);
    MimeMessage email = new MimeMessage(session);
    email.setFrom(new InternetAddress("mohamedhadji603@gmail.com"));
    email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(receiver));
    email.setSubject(subject);

    // Create the message body part for the text message
    MimeBodyPart textPart = new MimeBodyPart();
    textPart.setText(message);

    // Create the message body part for the PDF attachment
    MimeBodyPart pdfPart = new MimeBodyPart();
    pdfPart.attachFile(attachment);

    // Create a multipart message and add the text and PDF parts to it
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(textPart);
    multipart.addBodyPart(pdfPart);

    // Set the multipart message as the email content
    email.setContent(multipart);

    // Encode and wrap the MIME message into a gmail message
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    email.writeTo(buffer);
    byte[] rawMessageBytes = buffer.toByteArray();
    String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
    Message msg = new Message();
    msg.setRaw(encodedEmail);

    try {
        // Send the message
        msg = service.users().messages().send("me", msg).execute();
        System.out.println("Message sent: " + msg.toPrettyString());
    } catch (GoogleJsonResponseException e) {
        // TODO(developer) - handle error appropriately
        GoogleJsonError error = e.getDetails();
        if (error.getCode() == 403) {
            System.err.println(e.getDetails());
        } else {
            throw e;
        }
    }
}
  public void sendMail(String subject, String message, String receiver) throws IOException, GeneralSecurityException, MessagingException{

          
      

        // Encode as MIME message
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress("karim.chabchoub@esprit.tn"));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
        new InternetAddress(receiver));
        email.setSubject(subject);
        email.setText(message);


        // Encode and wrap the MIME message into a gmail message
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);

        try {
          // Create the draft message
          msg= service.users().messages().send("me", msg).execute();
          //System.out.println("message id : "+msg.getId());
         // System.out.println(msg.toPrettyString());

        } catch (GoogleJsonResponseException e) {
          // TODO(developer) - handle error appropriately
          GoogleJsonError error = e.getDetails();
          if (error.getCode() == 403) {
            System.err.println(e.getDetails());
          } else {
            throw e;
          }
        }
  }
}