package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.Action;
import Persistence.UsuarioDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/usuario/UsuarioLogin.jsp");
        request.setAttribute("titulo", "Página inicial");
        dispacher.forward(request, response);
    }
}
