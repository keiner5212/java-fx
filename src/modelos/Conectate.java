package modelos;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

public class Conectate {
    private String driver = "com.mysql.jdbc.Driver";
    private String cadenaConexion = "jdbc:mysql://127.0.0.1/agenda";
    private String usuario = "root";
    private String contraseña = "";
    public Connection con;

    public Conectate() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(cadenaConexion, usuario, contraseña);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido establecer una conexión con la BD: " + e.getMessage());
        }
    }
}