/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CAD.UsuarioCAD;
import DTO.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author luis
 */

@ManagedBean(name="usuariosInactivosController")
@ViewScoped
public class UsuariosInactivosController  implements Serializable{
    
    
    
    private int nuevaCantidad = 0;

    public void setNuevaCantidad(int nuevaCantidad) {
        this.nuevaCantidad = nuevaCantidad;
    }

    public int getNuevaCantidad() {
        return nuevaCantidad;
    }
    private List<Usuario> listaUsuarios;
    UsuarioCAD usuarioCAD =  new UsuarioCAD();
    
    @PostConstruct
    public void init(){
        try {
            this.listaUsuarios = usuarioCAD.getInactivos();
        } catch (Exception ex) {
            System.out.println("Error al obtener usuarios");
        }
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    public void listar(){
        try {
            this.listaUsuarios = usuarioCAD.getInactivos();
        } catch (Exception ex) {
            System.out.println("Error al obtener usuarios (LISTAR)");
        }
    }
    
    private Usuario usuario = new Usuario();
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
  
    public void nuevoUsuario() throws Exception{
        usuarioCAD.insertar(usuario);
        listar();
        
    }
    
    
    
    public void activar(int id) {
        usuarioCAD.activar(id);
        listar();
    }
     
     
}
