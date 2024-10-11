package umg.ejercicio.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {private static final String URL = "jdbc:sqlite:productos.db";  // Archivo SQLite
    private static Connection conn = null;

    // Método para conectar a la base de datos
    public static Connection conectar() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL);
                System.out.println("Conexión establecida con SQLite.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a SQLite: " + e.getMessage());
        }
        return conn;
    }

    // Método para desconectar de la base de datos
    public static void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada con SQLite.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    public static void crearTablaProductos() {
        String sql = "CREATE TABLE IF NOT EXISTS productos ("
                + "id_producto INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "descripcion TEXT NOT NULL,"
                + "precio REAL NOT NULL,"
                + "cantidad INTEGER NOT NULL,"
                + "pais TEXT NOT NULL"  // Nuevo campo para el país
                + ");";


        try (Connection conn = conectar();
             java.sql.Statement stmt = conn.createStatement()) {
            // Ejecutar la consulta SQL
            stmt.execute(sql);
            System.out.println("Tabla 'productos' creada o ya existe.");
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }
    public static void insertarProducto(String descripcion, double precio, int cantidad, String pais) {
        String sql = "INSERT INTO productos(descripcion, precio, cantidad, pais) VALUES(?, ?, ?, ?)";

        try (Connection conn = conectar();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, descripcion);
            pstmt.setDouble(2, precio);
            pstmt.setInt(3, cantidad);
            pstmt.setString(4, pais);
            pstmt.executeUpdate();
            System.out.println("Producto insertado: " + descripcion);
        } catch (SQLException e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
        }
    }

    //Para consultar productos que tienen menos de 20 unidades
    public static void reporteExistenciaMenorA20() {
        // Consulta para incluir el cálculo del total
        String sql = "SELECT *, (precio * cantidad) AS total FROM productos WHERE cantidad < 20";

        try (Connection conn = conectar();
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {

            // Mostrar los resultados en la consola
            System.out.println("ID | Descripción | Precio | Cantidad | Total");
            System.out.println("-----------------------------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id_producto") + " | " +
                                rs.getString("descripcion") + " | " +
                                rs.getDouble("precio") + " | " +
                                rs.getInt("cantidad") + " | " +
                                rs.getDouble("total") // Mostrar el total calculado
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
    }
    //Precios mayores a 2000
    public static void reportePreciosMayoresA2000() {
        String sql = "SELECT * FROM productos WHERE precio > 2000";

        try (Connection conn = conectar();
             java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {

            // Recorrer los resultados y mostrar los productos
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id_producto") +
                                ", Descripción: " + rs.getString("descripcion") +
                                ", Precio: " + rs.getDouble("precio") +
                                ", Cantidad: " + rs.getInt("cantidad")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
    }



}

