
package com.cyber.controladores;
import java.util.List;
import com.cyber.dao.ProductoDAO;
import com.cyber.modelos.Producto;


public class ProductoControlador {

    ProductoDAO dao = new ProductoDAO();

    public void agregarProducto(Producto p) {
        dao.insertar(p);
    }
    
    public Producto buscarPorId(int id) {
    return dao.buscarPorId(id);
}
    public List<Producto> listarProductos() {
        return dao.listar();
    }

    public void eliminarProducto(int id) {
        dao.eliminar(id);
    }

    public void modificarProducto(Producto p) {
        dao.modificar(p);
    }
}