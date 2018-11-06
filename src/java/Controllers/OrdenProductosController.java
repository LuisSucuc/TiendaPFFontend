
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
    private List<Orden> listaProductos;
    OrdenCAD ordenCAD =  new OrdenCAD();
    
    @PostConstruct
    public void init(){
        System.out.println("ORDEN");
        System.out.println(ControlID.ordenId);
        try {
            this.orden =  ordenCAD.getOrdenID(ControlID.ordenId);
            
        } catch (Exception ex) {
            System.out.println("Error al obtener ordenes");
        }
        
    }
    
    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Orden getOrden() {
        return orden;
    }
}
