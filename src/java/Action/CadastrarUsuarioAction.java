/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yan
 */
public class CadastrarUsuarioAction implements Action{

    public CadastrarUsuarioAction() {
    }
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //aqui vem a logica para cadastrar qlqr tipo de usuario.
        String action = request.getParameter("Action");
        Usuario u = ActionFactoryUser.create(action);
        
    }
    
}
