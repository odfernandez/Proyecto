/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Connection.SQLConnection;
import java.sql.*;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class Categoria {
    //Declaramos los atributos que conforman la clase 'Categoría'
    private int idCategoria;
    private String descripcion;
    private boolean estado;
   
    
    public int getIdCategoria() {
        return idCategoria;
    }

    //Creamos los Getters and Setters
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    //Crearemos las funciones que permitirán realizar las funciones básicas del CRUD
   
    //Método MostrarCategorias 
    //Carga todos los registros guardaados en la DB.
    public void mostrarCategorias (JTable paramTablaTotalCategorias ){
        SQLConnection connection = new SQLConnection();
        Statement st;
        ResultSet rs;
        
        
        DefaultTableModel tableModel = new DefaultTableModel();
        
        String sql = "";
        
        //Definimos los nombres de las columnas de la tabla donde mostraremos las categorias
        tableModel.addColumn("Id");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Estado");
        
        //Le seteamos los nombres de las columnas de la tabla donde se mostrara las categorias
        paramTablaTotalCategorias.setModel(tableModel);
        
        sql = "Select * FROM Categorias";
        
        String [] datos = new String[3];
        
        try {
            st = connection.getConectarDB().createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                tableModel.addRow(datos);
            }
            
            paramTablaTotalCategorias.setModel(tableModel);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: "+ e.toString());
        }
    }
    
    
    //Método Registrar Categorias
    public void registrarCategorias ( JTextField paramDescripcion, JCheckBox paramEstado){
        
        //Se le asignan los valores que se capturan del formulario
        //setIdCategoria(Integer.parseInt(paramId.getText()));
        setDescripcion(paramDescripcion.getText());
        setEstado(paramEstado.isFocusable());
        
        //Creamos una instancia de la clase SQLConnection para tener acceso a DB
        SQLConnection connection = new SQLConnection();
        
        //Asignamos la consulta sql a una variable tipo String y en los valores colocamos ? 
        //para indicar que los valores son dinámicos
        String sql = "insert into Categorias (descripcion,estado) values (?,?);";
        
        try {
            //Creamos una variable de clase CallableStatement para preparar la consulta sql
            CallableStatement cs = connection.getConectarDB().prepareCall(sql);
            

            //Capturamos los parametros, y al hacerlo de esta forma lo manejamos de forma práctica al ser dinámicos
            
            //cs.setInt(1, getIdCategoria());
            cs.setString(1, getDescripcion());
            cs.setBoolean(2, getEstado());
            
            //Cargamos los datos del formulario y ejecutamos la sentencia sql y realizamos el registro en DB
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: "+e.toString());
        }
        
        
    }
    
    
    //Método Seleccionar Categoria
    public void seleccionarCategoria (JTable paramTablaCategorias, JTextField paramId, JTextField paramDescripcion, JCheckBox paramEstado){
        try {
            //Asignamos el valor numerico de las filas de tabla para hacer validaciones
            int row = paramTablaCategorias.getSelectedRow();
            
            if (row >= 0) {
                paramId.setText(paramTablaCategorias.getValueAt(row, 0).toString());
                paramDescripcion.setText(paramTablaCategorias.getValueAt(row, 1).toString());
                paramEstado.setSelected(paramTablaCategorias.getVerifyInputWhenFocusTarget());
            }else {
                JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: "+e.toString());
        }
    }

    //Método Modificar Categorias
    public void modificarCategorias (JTextField paramId, JTextField paramDescripcion, JCheckBox paramEstado){
        
        //Se le asignan los valores que se capturan del formulario
        setIdCategoria(Integer.parseInt(paramId.getText()));
        setDescripcion(paramDescripcion.getText());
        setEstado(paramEstado.isSelected());
        
        //Creamos una instancia de la clase SQLConnection para tener acceso a DB
        SQLConnection connection = new SQLConnection();
        
        //Asignamos la consulta sql a una variable tipo String y en los valores colocamos ? 
        //para indicar que los valores son dinámicos
        String sql = "update Categorias set descripcion = ?, estado = ? where Categorias.id = ?;";
        
        try {
            //Creamos una variable de clase CallableStatement para preparar la consulta sql
            CallableStatement cs = connection.getConectarDB().prepareCall(sql);
            

            //Capturamos los parametros, y al hacerlo de esta forma lo manejamos de forma práctica al ser dinámicos
            cs.setString(1, getDescripcion());
            cs.setBoolean(2, getEstado());
            cs.setInt(3, getIdCategoria());
            
            //Cargamos los datos del formulario y ejecutamos la sentencia sql
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se modificó correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: "+e.toString());
        } 
    }
    
    
    //Método Eliminar Categorias
     public void EliminarCategorias (JTextField paramId){
        
        //Se le asignan los valores que se capturan del formulario
        setIdCategoria(Integer.parseInt(paramId.getText()));
        
        //Creamos una instancia de la clase SQLConnection para tener acceso a DB
        SQLConnection connection = new SQLConnection();
        
        //Asignamos la consulta sql a una variable tipo String y en los valores colocamos ? 
        //para indicar que los valores son dinámicos
        String sql = "delete from Categorias where Categorias.id = ?;";
        
        try {
            //Creamos una variable de clase CallableStatement para preparar la consulta sql
            CallableStatement cs = connection.getConectarDB().prepareCall(sql);
            
            //Capturamos los parametros, y al hacerlo de esta forma lo manejamos de forma práctica al ser dinámicos
            cs.setInt(1, getIdCategoria());
            
            //Cargamos los datos del formulario y ejecutamos la sentencia sql
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se Eliminó correctamente");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: "+e.toString());
        } 
    }
}
