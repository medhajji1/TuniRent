/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Yaadiii
 */
public class WebController implements Initializable {

    @FXML
    private WebView webview;
    WebEngine webEngine = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     webEngine  = webview.getEngine();
     webEngine.load("https://www.google.com/recaptcha/api2/anchor?ar=1&k=6LfEaFkUAAAAAGnIJMG983t2JyYg0McK4CUuRAdk&co=aHR0cHM6Ly93d3cudXBsb2FkLTRldmVyLmNvbTo0NDM.&hl=fr&v=Nh10qRQB5k2ucc5SCBLAQ4nA&size=normal&cb=fy9vwi71q7so");
    }
}
