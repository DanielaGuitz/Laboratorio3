package umg.ejercicio.Service;

import umg.ejercicio.DAO.ProductoDao;
import umg.ejercicio.Model.ProductoModel;

import java.util.List;

public class ProductoService {
    private ProductoDao productoDao;

    public ProductoService() {
        productoDao = new ProductoDao();
    }

    public void agregarProducto(String descripcion, String origen, int precio, int existencia) throws Exception {
        productoDao.agregarProducto(descripcion, origen, precio, existencia);
    }

    public ProductoModel obtenerProductoPorId(int idProducto) throws Exception {
        return productoDao.obtenerProductoPorId(idProducto);
    }

    public void actualizarProducto(int idProducto, String descripcion, String origen, int precio, int existencia) throws Exception {
        productoDao.actualizarProducto(idProducto, descripcion, origen, precio, existencia);
    }

    public void eliminarProducto(int idProducto) throws Exception {
        productoDao.eliminarProducto(idProducto);
    }

    public List<ProductoModel> obtenerTodosLosProductos() throws Exception {
        return productoDao.obtenerTodosLosProductos();
    }

    public List<ProductoModel> obtenerGenericos(String condicion) throws Exception {
        return productoDao.obtenerGenericos(condicion);
    }

    public List<ProductoModel> obtenerTodosLosProductosID() throws Exception {
        return productoDao.obtenerTodosLosProductosID();
    }
}

