
package Controllers;

import CAD.ProductoCAD;
import DTO.Producto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="productoInactivoController")
@ViewScoped
public class ProductosInactivosController  implements Serializable{
    
    
    
    
    private List<Producto> listaProductos;
    ProductoCAD productoCAD =  new ProductoCAD();
    
    @PostConstruct
    public void init(){
        try {
            this.listaProductos = productoCAD.getInactivos();
        } catch (Exception ex) {
            System.out.println("Error al obtener productos");
        }
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
    
    public void listar(){
        try {
            this.listaProductos = productoCAD.getInactivos();
        } catch (Exception ex) {
            System.out.println("Error al obtener productos (LISTAR)");
        }
    }
    
    private Producto producto = new Producto();
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
  
    
    public void activar(int id) {
        productoCAD.activar(id);
        listar();
    }
     
     
}
