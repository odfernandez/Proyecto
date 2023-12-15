/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

/**
 *
 * @author Acer
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SQLConnection {
    
    //Creamos una variable de tipo Connection
    Connection sqlConnection;
    
    //Sobrecargamos el constructor añadiendo la conexión a la base de datos.
    public SQLConnection(){
        //Declaramos las variables a utilizar en el proceso de conexión a DB.
        String host = "localhost";
        String puerto = "5432";
        String nameDB = "db_pruebas";
        String username = "postgres";
        String pass = "Sawada280390$";
        String driver = "org.postgresql.Driver";
        
        String dataBaseUrl = "jdbc:postgresql://" + host + ":" + puerto + "/" + nameDB + "?useSSL=false";
        
        try {
            Class.forName(driver);
            //Obteneos la coenxión a la DB por medio DriverManager y su método getConnection y la
                //asignamos a la variable sqlConnection
                sqlConnection = DriverManager.getConnection(dataBaseUrl,username,pass);
                //System.out.println("Base de Datos Conectada");
                JOptionPane.showMessageDialog(null, "Se conecto a la base de datos");
        } catch (Exception e) {
            //System.out.println("Base de Datos NO Conectada, Verifique la conexión");
            JOptionPane.showMessageDialog(null, "ERROR"+e.toString());
        }  
    }
    
    public Connection getConectarDB (){
        return sqlConnection;
    }
}
