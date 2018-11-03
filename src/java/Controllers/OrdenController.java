
package Controllers;

import CAD.OrdenCAD;
import DTO.Orden;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="ordenController")
@ViewScoped
public class OrdenController  implements Serializable{    
   
    private List<Orden> listaOrdenes;
    OrdenCAD ordenCAD =  new OrdenCAD();
    
    @PostConstruct
    public void init(){
        try {
            this.listaOrdenes = ordenCAD.getTodos();
        } catch (Exception ex) {
            System.out.println("Error al obtener ordenes");
        }
    }

    public List<Orden> getListaOrdenes() {
        return listaOrdenes;
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
        ordenCAD.insertar(orden);
        listar();
        
    }
    
    public Orden leerIDOrden(int id) {        
        try {
            this.orden = ordenCAD.getOrdenID(id);
        } catch (Exception ex) {
            System.out.println("Error al obtener orden por ID");
        }
     
        return this.orden;
    }
    
    public void modificar() throws Exception{
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
     
    public void getCliente(){
        
    }
}
