package umg.ejercicio.database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz {private JFrame frame;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    JTextField txtPais = new JTextField(20);



    public Interfaz() {
        frame = new JFrame("Gestión de Productos");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(new JLabel("País:"));
        frame.add(txtPais);

        // Campos de entrada
        txtDescripcion = new JTextField(20);
        txtPrecio = new JTextField(10);
        txtCantidad = new JTextField(10);

        // Botones
        JButton btnInsertar = new JButton("Insertar Producto");
        JButton btnReporte = new JButton("Generar Reporte");
        JButton btnReporteMenor20 = new JButton("Reporte < 20 unidades");
        JButton btnReporteMayor2000 = new JButton("Reporte > 2000");



        // Añadir componentes
        frame.add(new JLabel("Descripción:"));
        frame.add(txtDescripcion);
        frame.add(new JLabel("Precio:"));
        frame.add(txtPrecio);
        frame.add(new JLabel("Cantidad:"));
        frame.add(txtCantidad);
        frame.add(btnInsertar);
        frame.add(btnReporte);
        frame.add(btnReporteMenor20);
        frame.add(btnReporteMayor2000);


        // Acción para insertar producto
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descripcion = txtDescripcion.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                int cantidad = Integer.parseInt(txtCantidad.getText());
                String pais = txtPais.getText();
                ConexionSQLite.insertarProducto(descripcion, precio, cantidad, pais);
                JOptionPane.showMessageDialog(frame, "Producto insertado.");
                txtDescripcion.setText("");
                txtPrecio.setText("");
                txtCantidad.setText("");
            }
        });

        // Acción para generar reportes
        btnReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes llamar a los métodos de reporte
                // Para simplificar, solo llamamos al primer reporte
                System.out.println("Reporte: Productos con existencia menor a 20");
                ConexionSQLite.reporteExistenciaMenorA20();
            }
        });
        // Acción para generar reporte de existencia menor a 20
        btnReporteMenor20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reporte: Productos con existencia menor a 20");
                ConexionSQLite.reporteExistenciaMenorA20();
            }
        });

// Acción para generar reporte de precios mayores a 2000
        btnReporteMayor2000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reporte: Productos con precios mayores a 2000");
                ConexionSQLite.reportePreciosMayoresA2000();
            }
        });

        frame.setVisible(true);
    }
}

