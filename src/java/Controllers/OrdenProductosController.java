
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

@ManagedBean(name="ordenProductoController")
@ViewScoped
public class OrdenProductosController  implements Serializable{    
    
    Orden orden = new Orden();
    private String cliente;
    private String fecha;
    private String descripcion;
    private List<Orden> listaProductos;
    OrdenCAD ordenCAD =  new OrdenCAD();
    ClienteCAD clienteCAD =  new ClienteCAD();
    
    @PostConstruct
    public void init(){
        
        try {
            orden =  ordenCAD.getOrdenID(ControlID.ordenId);
            cliente = clienteCAD.getComercioCliente(Integer.parseInt(orden.cliente));
            fecha = Utils.Herramientas.dateAString(orden.fecha);
            descripcion = orden.descripcion;
            
        } catch (Exception ex) {
            System.out.println("Error al obtener ordenes");
        }
        
    }
    
    public Orden leerIDOrden(int id) {
        try {
            this.orden = ordenCAD.getOrdenID(id);
            
        } catch (Exception ex) {
            System.out.println("Error al obtener orden por ID");
        }
        return this.orden;
    }
  
    public Orden getOrden() {
        return orden;
    }
    
  

    public String getCliente() {
        return cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

   
    
    
}
