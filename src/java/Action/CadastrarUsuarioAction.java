package Action;

import Controller.Action;
import ChainOfResponsability_TemplateMethod.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarUsuarioAction implements Action {

    public CadastrarUsuarioAction() {
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //aqui vem a logica para cadastrar qlqr tipo de usuario.

    }
}
