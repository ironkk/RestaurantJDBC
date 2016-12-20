/*https://gist.github.com/
 * Clase de Test
 */
package restaurant;

import java.sql.SQLException;
import java.util.List;
import model.Cocinero;
import persistence.RestaurantJDBC;

/**
 *
 * @author stucom
 */
public class Restaurant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RestaurantJDBC gestor = new RestaurantJDBC();
        try {
            System.out.println("Estableciendo conexión con la bbdd...");
            gestor.conectar();
            System.out.println("Conectado a la bbdd restaurant");
            Cocinero a = new Cocinero("Pepe El cocinero", "123456789", "hombre", 34, 2, "Postres");
            gestor.insertCocinero(a);
            System.out.println("Cocinero dado de alta.");
            List<Cocinero> todosCocineros = gestor.selectAllCocineros();
            System.out.println("Listado de cocineros");
            for (Cocinero c : todosCocineros) {
                System.out.println(c);
            }
            gestor.desconectar();
            System.out.println("Cerrada la conexión con la bbdd.");
        } catch (SQLException ex) {
            System.out.println("Error con la BBDD: "+ex.getMessage());
        } 
    }

}
