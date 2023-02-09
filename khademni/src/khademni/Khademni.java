/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package khademni;

import java.sql.Connection;
import khademni.utils.MyConnection;

/**
 *
 * @author mikea
 */
public class Khademni {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Connection myconn =MyConnection.getInstance().getConnexion();
    }
    
}
