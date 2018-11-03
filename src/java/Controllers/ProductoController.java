/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CAD.ProductoCAD;
import DTO.Producto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luis
 */

@ManagedBean(name="productoController")
@ViewScoped
public class ProductoController  implements Serializable{
    
    
    
    private int nuevaCantidad = 0;

    public void setNuevaCantidad(int nuevaCantidad) {
        this.nuevaCantidad = nuevaCantidad;
    }

    public int getNuevaCantidad() {
        return nuevaCantidad;
    }
    private List<Producto> listaProductos;
    ProductoCAD productoCAD =  new ProductoCAD();
    
    @PostConstruct
    public void init(){
        try {
            this.listaProductos = productoCAD.getTodos();
        } catch (Exception ex) {
            System.out.println("Error al obtener productos");
        }
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
    
    public void listar(){
        try {
            this.listaProductos = productoCAD.getTodos();
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
    
  
    public void nuevoProducto() throws Exception{
        productoCAD.insertar(producto);
        listar();
        
    }
    
    public Producto leerIDProducto(int id) {        
        try {
            this.producto = productoCAD.getProductoID(id);
        } catch (Exception ex) {
            System.out.println("Error al obtener producto por ID");
        }
     
        return this.producto;
    }
    
    public void modificar() throws Exception{
        productoCAD.modificar(producto);
        listar();
        
    }
    
    
    public void nuevasUnidades() throws Exception{
        System.out.println("NUEVA CANTIDAD");
        System.out.println(nuevaCantidad);
        productoCAD.actualizarUnidades(producto.getId(), nuevaCantidad);
        nuevaCantidad = 0;
        System.out.println(nuevaCantidad);
        listar();
        
    }
    
    public void desactivar(int id) {
        productoCAD.desactivar(id);
        listar();
    }
    
    
    public void activar(int id) {
        productoCAD.desactivar(id);
        listar();
    }
     
     
}
