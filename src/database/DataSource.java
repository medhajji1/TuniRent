/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author abdelazizmezri
 */
public class DataSource {
    
    private Connection cnx;
    private static DataSource instance;
    
    private final String USER = "root";
    private final String PWD = "";
<<<<<<< HEAD
    private final String URL = "jdbc:mysql://localhost:3306/tesr";
=======
    private final String URL = "jdbc:mysql://localhost:3306/tunirent";
>>>>>>> a9f2272f143766b9ac53dfea451dfae668a5dc1c

    private DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DataSource getInstance() {
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
