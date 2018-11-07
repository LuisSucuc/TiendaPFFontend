
package Controllers;

import CAD.ClienteCAD;
import CAD.OrdenCAD;
import DTO.Orden;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="ordenController")
@ViewScoped
public class OrdenController  implements Serializable{    
   
    private List<Orden> listaOrdenes;
    OrdenCAD ordenCAD =  new OrdenCAD();
    ClienteCAD clienteCAD = new ClienteCAD();
    private Date nuevaFecha; 
    private int nuevoCliente;

    public void setNuevaFecha(Date nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }

    public Date getNuevaFecha() {
        return nuevaFecha;
    }
    
    
    private Map<String,String> opcionesClientes = new HashMap<String, String>();
    
    @PostConstruct
    public void init(){
        try {
            opcionesClientes = clienteCAD.getClientesSeleccionables();
            this.listaOrdenes = ordenCAD.getTodos();
        } catch (Exception ex) {
            System.out.println("Error al obtener ordenes");
        }
        
    }

    public List<Orden> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setNuevoCliente(int nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }

    public void setOpcionesClientes(Map<String, String> opcionesClientes) {
        this.opcionesClientes = opcionesClientes;
    }

    public int getNuevoCliente() {
        return nuevoCliente;
    }

    public Map<String, String> getOpcionesClientes() {
        return opcionesClientes;
    }
    
    public void listar(){
        try {
            this.listaOrdenes = ordenCAD.getTodos();
        } catch (Exception ex) {
            System.out.println("Error al obtener ordenes (LISTAR)");
        }
    }
    
    private Orden orden = new Orden();
    
    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
    
  
    public void nuevoOrden() throws Exception{
        orden.clienteId = nuevoCliente;      
        orden.fecha = nuevaFecha;
        ordenCAD.insertar(orden);
        
        listar();
        
    }
    
    public Orden leerIDOrden(int id) {
        try {
            this.orden = ordenCAD.getOrdenID(id);
            this.nuevoCliente = Integer.parseInt(this.orden.cliente);
            this.nuevaFecha = this.orden.fecha;
            
        } catch (Exception ex) {
            System.out.println("Error al obtener orden por ID");
        }
     
        return this.orden;
    }
    
    public void modificar() throws Exception{
        
        orden.cliente = Integer.toString(this.nuevoCliente);
        
        orden.fecha = this.nuevaFecha;
        ordenCAD.modificar(orden);
        listar();
        
    }
    
    
    public void desactivar(int id) {
        ordenCAD.desactivar(id);
        listar();
    }
    
    
    public void activar(int id) {
        ordenCAD.desactivar(id);
        listar();
    }
   
     public void redirect(Orden orden, boolean autorizar) throws IOException {
        ControlID.ordenId = orden.id;
        ControlID.edit = autorizar;
        
            
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ordenController",orden);
        FacesContext.getCurrentInstance().getExternalContext().redirect("ver_orden.xhtml");
        //return "View/clientes";
    }
}
