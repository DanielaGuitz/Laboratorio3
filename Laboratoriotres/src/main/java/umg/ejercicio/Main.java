package umg.ejercicio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import umg.ejercicio.database.ConexionSQLite;
import umg.ejercicio.database.Interfaz;

public class Main {
    public static void main(String[] args) {
        // Conectar a la base de datos
        ConexionSQLite.conectar();

        // Crear la tabla de productos
        ConexionSQLite.crearTablaProductos();

        // Iniciar la interfaz
        new Interfaz();

        // Cerrar la conexión si es necesario
        // ConexionSQLite.desconectar(); // Esto lo haremos al cerrar la aplicación
    }

}