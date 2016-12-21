/*
 * Clase encargada de la gestión con la BBDD
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cocinero;
import model.Plato;

/**
 *
 * @author stucom
 */
public class RestaurantJDBC {

    private Connection conexion;
    
    // Función que devuelve una lista con todos los datos de todos
    // los cocineros
    public List<Cocinero> selectAllCocineros() throws SQLException {
        List<Cocinero> cocineros = new ArrayList<>();
        String query = "select * from cocinero";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Cocinero c = new Cocinero();
            c.setNombre(rs.getString("nombre"));
            c.setEdad(rs.getInt("edad"));
            c.setEspecialidad(rs.getString("especialidad"));
            c.setExperiencia(rs.getInt("experiencia"));
            c.setSexo(rs.getString("sexo"));
            c.setTelefono(rs.getString("telefono"));
            cocineros.add(c);
        }
        rs.close();
        st.close();
        return cocineros;
    }
    
        public List<Plato> selectAllPlatos() throws SQLException {
        List<Plato> platos = new ArrayList<>();
        String query = "select * from plato";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Plato p = new Plato();
            p.setNombre(rs.getString("nombre"));
            p.setTipo(rs.getString("tipo"));
            p.setPrecio(rs.getDouble("precio"));
          //  p.setCocinero(rs.get("Cocinero"));
          
            platos.add(p);
        }
        rs.close();
        st.close();
        return platos;
    }
    
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
    
        public void insertPlato(Plato p) throws SQLException {
        String insert = "insert into plato values (?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        // Vamos dando valores a los interrogantes
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getTipo());
        ps.setDouble(3, p.getPrecio());
        ps.setString(4, p.getCocinero().getNombre());
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
