/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CAD.ClienteCAD;
import DTO.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luis
 */

@ManagedBean(name="clienteController")
@ViewScoped
public class ClienteController  implements Serializable{
    
    
    
    private int nuevaCantidad = 0;

    public void setNuevaCantidad(int nuevaCantidad) {
        this.nuevaCantidad = nuevaCantidad;
    }

    public int getNuevaCantidad() {
        return nuevaCantidad;
    }
    private List<Cliente> listaClientes;
    ClienteCAD clienteCAD =  new ClienteCAD();
    
    @PostConstruct
    public void init(){
        try {
            this.listaClientes = clienteCAD.getTodos();
        } catch (Exception ex) {
            System.out.println("Error al obtener clientes");
        }
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    
    public void listar(){
        try {
            this.listaClientes = clienteCAD.getTodos();
        } catch (Exception ex) {
            System.out.println("Error al obtener clientes (LISTAR)");
        }
    }
    
    private Cliente cliente = new Cliente();
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
  
    public void nuevoCliente() throws Exception{
        clienteCAD.insertar(cliente);
        listar();
        
    }
    
    public Cliente leerIDCliente(int id) {        
        try {
            this.cliente = clienteCAD.getClienteID(id);
        } catch (Exception ex) {
            System.out.println("Error al obtener cliente por ID");
        }
     
        return this.cliente;
    }
    
    public void modificar() throws Exception{
        clienteCAD.modificar(cliente);
        listar();
        
    }
    
    
    public void desactivar(int id) {
        clienteCAD.desactivar(id);
        listar();
    }
    
    
    public void activar(int id) {
        clienteCAD.desactivar(id);
        listar();
    }
     
     
}
