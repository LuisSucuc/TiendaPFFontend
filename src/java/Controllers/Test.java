
package Controllers;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import CAD.UserCAD;

@ManagedBean
@Named(value = "usuario")
@SessionScoped
public class Test implements Serializable {
    
    public String validarUsuario() throws Exception{
        UserCAD test  = new UserCAD();
        test.validarUsuer("LUIS");
        return "</h:form>";
    }
}
