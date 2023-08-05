package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelos.Contacto;

public class vistaAgregarControlador implements Initializable {

    private Contacto modeloContactos = new Contacto();

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField telefono;
    @FXML
    private TextField direccion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void agregar() {
        modeloContactos.AgregarContacto(nombre.getText(), apellido.getText(), telefono.getText(), direccion.getText());
        Stage stage = (Stage) nombre.getScene().getWindow();
        stage.close();
    }

}
