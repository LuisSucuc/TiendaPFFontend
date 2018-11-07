
package Controllers;

import CAD.ProductoAsignadoCAD;
import CAD.ProductoCAD;
import DTO.ProductoAsignado;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="productoAsignadoController")
@ViewScoped
public class ProductoAsignadoController  implements Serializable{
    
    private List<ProductoAsignado> listaProductoAsignados;
    ProductoAsignadoCAD productoAsignadoCAD =  new ProductoAsignadoCAD();
    private Map<String,String> opcionesProductos = new HashMap<String, String>();
    ProductoCAD productoCAD = new ProductoCAD();
    private int limiteModificar = 0;
    private int nuevaCantidad = 0;
    private float total = 0;

    
    @PostConstruct
    public void init(){
        try {
            this.listaProductoAsignados = productoAsignadoCAD.getTodos(ControlID.ordenId);
            this.total = 0;
            for(ProductoAsignado asignado: this.listaProductoAsignados){
                
                sumarTotal(asignado.cantidad * asignado.precio);
            }
            opcionesProductos = productoCAD.getProductosSeleccionables();
        } catch (Exception ex) {
            System.out.println("Error al obtener productoAsignados");
        }
    }

    public List<ProductoAsignado> getListaProductoAsignados() {
        return listaProductoAsignados;
    }
    
    public void listar(){
        try {
            this.listaProductoAsignados = productoAsignadoCAD.getTodos(ControlID.ordenId);
            this.total = 0;
            for(ProductoAsignado asignado: this.listaProductoAsignados){
                sumarTotal(asignado.cantidad * asignado.precio);
            }
        } catch (Exception ex) {
            System.out.println("Error al obtener productoAsignados (LISTAR)");
        }
    }
    
    private ProductoAsignado productoAsignado = new ProductoAsignado();
    
    public ProductoAsignado getProductoAsignado() {
        return productoAsignado;
    }

    public void setProductoAsignado(ProductoAsignado productoAsignado) {
        this.productoAsignado = productoAsignado;
    }

    public int getLimiteModificar() {
        return limiteModificar;
    }

    public void setLimiteModificar(int limiteModificar) {
        this.limiteModificar = limiteModificar;
    }

    public int getNuevaCantidad() {
        return nuevaCantidad;
    }

    public void setNuevaCantidad(int nuevaCantidad) {
        this.nuevaCantidad = nuevaCantidad;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    public void sumarTotal(float subtotal){
        this.total = this.total + subtotal;
    }
  
    public void nuevoProductoAsignado() throws Exception{
        
        
        productoAsignado.ordenId = ControlID.ordenId;
        
        //Si se recibe 0 se ha insertado correctamente
        int disponibles = productoAsignadoCAD.insertar(productoAsignado);
        if(disponibles== 0){
            productoAsignado.cantidad = 0;
            productoAsignado.productoId = 0;
            listar();
        }
        else{
            String mensaje = "Sólo existen " + disponibles + "unidades";
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Existencias insuficientes", mensaje));
        }
        
    }

    public Map<String, String> getOpcionesProductos() {
        return opcionesProductos;
    }

    public void setOpcionesProductos(Map<String, String> opcionesProductos) {
        this.opcionesProductos = opcionesProductos;
    }
    
    
    public ProductoAsignado leerIDProductoAsignado(int id) {        
        try {
            this.productoAsignado = productoAsignadoCAD.getProductoAsignadoID(id);
        } catch (Exception ex) {
            System.out.println("Error al obtener productoAsignado por ID");
        }
        limiteModificar = productoAsignado.limite+ productoAsignado.cantidad;
        nuevaCantidad = productoAsignado.cantidad;
        return this.productoAsignado;
    }
    
    public void modificar() throws Exception{
        productoAsignado.cantidad = nuevaCantidad - productoAsignado.cantidad;
        productoAsignadoCAD.modificar(productoAsignado);
        listar();
        
    }
    
    
    public void nuevasUnidades() throws Exception{
        productoAsignadoCAD.modificar(productoAsignado);
        listar();
        
    }
    
    public void desactivar(ProductoAsignado productoEliminar) {
        productoAsignadoCAD.eliminar(productoEliminar);
        listar();
    }
    
    public boolean autorizarEdit(){
        return ControlID.edit;
    }
     
     
}
