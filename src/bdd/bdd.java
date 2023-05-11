/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author maroo
 */
public class bdd {
    public static bdd bddtest;
    private Connection connection;
        private bdd(){
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tesr","root", "");
                System.out.println("*****connected to database*****");
            }  catch (SQLException e) {
                e.getMessage();
            }

          }
            

   public Connection get_connection(){
       return connection;
    }
   
   
   public static bdd getinstance (){
       if (bddtest==null){
           bddtest= new bdd();
       }
       return bddtest;
   }
   
 
   
}
