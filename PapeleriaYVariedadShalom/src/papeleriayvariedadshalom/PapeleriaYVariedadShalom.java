/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package papeleriayvariedadshalom;

import Connection.SQLConnection;
import View.VistaCategorias;

/**
 *
 * @author Acer
 */
public class PapeleriaYVariedadShalom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*SQLConnection conexion = new SQLConnection();
        conexion.getConectarDB();*/
        VistaCategorias objetoVista = new VistaCategorias();
        objetoVista.setVisible(true);
    }
    
}
