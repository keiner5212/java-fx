package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelos.Contacto;

public class vistaPControlador implements Initializable {

    @FXML
    private Button search;
    @FXML
    private Button eliminar;
    @FXML
    private Button editar;

    @FXML
    private ComboBox<String> resultado;
    @FXML
    private TextField busqueda;

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField telefono;
    @FXML
    private TextField direccion;

    private Contacto modeloContactos = new Contacto();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BuscarNombre("");
    }

    @FXML
    public void buscar(ActionEvent event) {
        BuscarNombre(busqueda.getText());
        busqueda.setText("");
        if (resultado.getItems().size() == 0) {
            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "¿Deseas agregar un nuevo contacto?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Sí", "No"},
                    "Sí");

            if (opcion == JOptionPane.YES_OPTION) {
                System.out.println("Seleccionaste Sí");
                agregarPersona();
            } else if (opcion == JOptionPane.NO_OPTION) {
                System.out.println("cancelado");
            } else {
                System.out.println("No seleccionaste ninguna opción");
            }
        }
    }

    @FXML
    private void agregarPersona() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/vistaAgregarContacto.fxml"));
            Parent root = loader.load();
            vistaAgregarControlador controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            reiniciar();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void BuscarNombre(String busqueda) {
        resultado.getItems().clear();
        modeloContactos.BuscarContactos(resultado, busqueda);
    }

    private String seleccionado = "";

    public void seleccionado() {
        if (seleccionado.isEmpty()) {
            seleccionado = resultado.getSelectionModel().getSelectedItem().split(":")[0];
            ArrayList<String> res = modeloContactos.GetContacto(seleccionado);
            this.nombre.setText(res.get(0));
            this.apellido.setText(res.get(1));
            this.telefono.setText(res.get(2));
            this.direccion.setText(res.get(3));
        }

    }

    public void editar() {
        modeloContactos.ActualizarContacto(seleccionado, nombre.getText(), apellido.getText(), telefono.getText(), direccion.getText());
        reiniciar();
    }

    public void reiniciar() {
        BuscarNombre("");
        busqueda.setText("");
        this.nombre.setText("");
        this.apellido.setText("");
        this.telefono.setText("");
        this.direccion.setText("");
        this.seleccionado = "";
    }

    public void eliminar() {
        modeloContactos.EliminarContacto(seleccionado);
        reiniciar();
    }
}
