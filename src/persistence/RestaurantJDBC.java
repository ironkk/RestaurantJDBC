/*
 * Clase encargada de la gestión con la BBDD
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Cocinero;

/**
 *
 * @author stucom
 */
public class RestaurantJDBC {

    private Connection conexion;
    
    // Función que inserta un cocinero en la bbdd
    public void insertCocinero(Cocinero c) throws SQLException {
        String insert = "insert into cocinero values (?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        // Vamos dando valores a los interrogantes
        ps.setString(1, c.getNombre());
        ps.setString(2, c.getTelefono());
        ps.setString(3, c.getSexo());
        ps.setInt(4, c.getEdad());
        ps.setInt(5, c.getExperiencia());
        ps.setString(6, c.getEspecialidad());
        // ejecutamos la consultas
        ps.executeUpdate();
        ps.close();
    }
    
    // Función que conecta con la bbdd
    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/restaurant";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }
    
    // Función que desconecta de la bbdd
    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    
}
