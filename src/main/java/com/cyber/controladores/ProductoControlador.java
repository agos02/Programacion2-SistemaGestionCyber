
package com.cyber.controladores;

import java.util.List;
import com.cyber.dao.ProductoDAO;
import com.cyber.modelos.Producto;

public class ProductoControlador {
//utiliza una instancia dao para realizar operaciones sobre la bd
    private final ProductoDAO dao = new ProductoDAO();

    public void agregarProducto(Producto p) {//recibe el producto y llama al metodo de dao para guardar en bd
        dao.insertar(p);
    }

    public List<Producto> listarProductos() {
        return dao.listarProducto();
    }

   public Producto buscarPorId(int id_producto) {
    try {
        return dao.buscarPorId(id_producto);
    } catch (Exception e) {
        System.out.println("Error en controlador: " + e.getMessage());
        return null;
    }
}
    public void eliminarProducto(int id_producto) {
        dao.eliminar(id_producto);
    }

    public void modificarProducto(Producto p) {
        dao.modificar(p);
    }
 
}