/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.connexion3a17.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karra
 */
public class MyConnection {
    String url="jdbc:mysql://localhost:3306/esprit3a17";
    String login="root";
    String pwd="";
    private Connection MyConexBd;
    private static MyConnection instance;

    private MyConnection() {
        try {
         MyConexBd = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getMyConexBd() {
        return MyConexBd;
    }
    
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
         return instance;
    }
    
    
    
}


    

