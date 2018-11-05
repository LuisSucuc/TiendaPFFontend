
package Controllers;

import CAD.ClienteCAD;
import CAD.OrdenCAD;
import DTO.Orden;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named(value = "ordenController")
@SessionScoped
public class OrdenController  implements Serializable{    
   
    private List<Orden> listaOrdenes;
    OrdenCAD ordenCAD =  new OrdenCAD();
    ClienteCAD clienteCAD = new ClienteCAD();
    private Date date; 

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
    
    
    private String new_client;
    private Map<String,String> news_clients = new HashMap<String, String>();
    
    @PostConstruct
    public void init(){
        try {
            news_clients = clienteCAD.getClientesSeleccionables();
            this.listaOrdenes = ordenCAD.getTodos();
        } catch (Exception ex) {
            System.out.println("Error al obtener ordenes");
        }
        
    }

    public List<Orden> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setNew_client(String new_client) {
        this.new_client = new_client;
    }

    public void setNews_clients(Map<String, String> news_clients) {
        this.news_clients = news_clients;
    }

    public String getNew_client() {
        return new_client;
    }

    public Map<String, String> getNews_clients() {
        return news_clients;
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
        System.out.println(new_client);        
        System.out.println(orden.cliente);
        System.out.println(orden.cliente_id);
        System.out.println(orden.fecha);
        System.out.println(orden.descripcion);
        System.out.println(orden.id);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String reportDate = df.format(date);
        System.out.println("Report Date: " + reportDate);

        //ordenCAD.insertar(orden);
        
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
   
     public void redirect(Orden orden) throws IOException {
        this.orden = orden;
         System.out.println(this.orden.getCliente());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ordenController",orden);
        FacesContext.getCurrentInstance().getExternalContext().redirect("ver_orden.xhtml");
        //return "View/clientes";
    }
}
