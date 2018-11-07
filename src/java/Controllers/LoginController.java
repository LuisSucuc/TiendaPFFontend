
package Controllers;

import CAD.UsuarioCAD;
import DTO.Usuario;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;



@ManagedBean(name="loginController")
@ViewScoped
public class LoginController  implements Serializable{
    
    Usuario usuario = new Usuario();
    UsuarioCAD usuarioCAD =  new UsuarioCAD();
    
    @PostConstruct
    public void init(){
        
    }
    
    public void validarUsuario() throws Exception{
        if (usuarioCAD.validarUsuario(usuario)) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/Views/productos.xhtml");
        }
        else{
            FacesContext context = FacesContext.getCurrentInstance();
            
            context.addMessage(null, new FacesMessage("Usuario no existente", "Inténtalo nuevamente"));
        }
        
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

  
    
     
}