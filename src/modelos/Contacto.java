package modelos;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;

public class Contacto {

    private Conectate bd;

    public void BuscarContactos(ComboBox resultado, String busqueda) {
        try {
            bd = new Conectate();
            Statement stmt = (Statement) bd.con.createStatement();
            ResultSet rs = (ResultSet) stmt.executeQuery("SELECT * FROM `contactos` WHERE `nombre` like \"%" + busqueda + "%\";");
            while (rs.next()) {
                resultado.getItems().add(rs.getString(1) + ": " + rs.getString(2) + " " + rs.getString(3));
            }
            bd.con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void ActualizarContacto(String seleccionado, String nombre, String apellido, String telefono, String direccion) {
        if (seleccionado != "") {
            try {
                bd = new Conectate();
                Statement stmt = (Statement) bd.con.createStatement();
                stmt.executeUpdate("UPDATE `contactos` SET `nombre`='" + nombre + "',`apellidos`='" + apellido + "',`telefono`='" + telefono + "',`direccion`='" + direccion + "'  WHERE id =" + seleccionado);
                bd.con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void AgregarContacto(String nombre, String apellido, String telefono, String direccion) {
        try {
            bd = new Conectate();
            Statement stmt = (Statement) bd.con.createStatement();
            String query = "INSERT INTO `contactos` (`nombre`, `apellidos`, `telefono`, `direccion`) VALUES ('"
                    + nombre + "', '" + apellido + "', '" + telefono + "', '" + direccion + "')";
            stmt.executeUpdate(query);
            bd.con.close();
            System.out.println("Contacto agregado exitosamente.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void EliminarContacto(String seleccionado) {
        if (seleccionado != "") {
            try {
                bd = new Conectate();
                Statement stmt = (Statement) bd.con.createStatement();
                stmt.executeUpdate("DELETE FROM `contactos` WHERE id =" + seleccionado);
                bd.con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public ArrayList<String> GetContacto(String seleccionado) {
        ArrayList<String> res = new ArrayList<>();
        String nombre = "";
        String apellido = "";
        String telefono = "";
        String direccion = "";
        try {
            bd = new Conectate();
            Statement stmt = (Statement) bd.con.createStatement();
            ResultSet rs = (ResultSet) stmt.executeQuery("SELECT * FROM `contactos` WHERE `id`=" + seleccionado);
            while (rs.next()) {
                nombre = rs.getString(2);
                apellido = rs.getString(3);
                telefono = rs.getString(4);
                direccion = rs.getString(5);
            }
            bd.con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        res.add(nombre);
        res.add(apellido);
        res.add(telefono);
        res.add(direccion);
        return res;
    }
}
