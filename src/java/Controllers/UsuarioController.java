
package Controllers;

import CAD.UsuarioCAD;
import DTO.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="usuarioController")
@ViewScoped
public class UsuarioController  implements Serializable{
    
    
  
    private List<Usuario> listaUsuarios;
    UsuarioCAD usuarioCAD =  new UsuarioCAD();
    
    @PostConstruct
    public void init(){
        try {
            this.listaUsuarios = usuarioCAD.getTodos();
        } catch (Exception ex) {
            System.out.println("Error al obtener usuarios");
        }
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    public void listar(){
        try {
            this.listaUsuarios = usuarioCAD.getTodos();
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
    
    public Usuario leerIDUsuario(int id) {        
        try {
            this.usuario = usuarioCAD.getUsuarioID(id);
        } catch (Exception ex) {
            System.out.println("Error al obtener usuario por ID");
        }
     
        return this.usuario;
    }
    
    public void modificar() throws Exception{
        usuarioCAD.modificar(usuario);
        listar();
        
    }
    
    
    public void desactivar(int id) {
        usuarioCAD.desactivar(id);
        listar();
    }
    
    
    public void activar(int id) {
        usuarioCAD.desactivar(id);
        listar();
    }
     
     
}
