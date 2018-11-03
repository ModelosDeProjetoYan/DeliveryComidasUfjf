package Action;

import Controller.Action;
import ChainOfResponsability_TemplateMethod.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarUsuarioAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/usuario/CadastrarUsuario.jsp");
        request.setAttribute("titulo", "Cadastrar Usuário");
        request.setAttribute("erro", "TESTEEEEEEEEEE !!!");

        dispacher.forward(request, response);
    }
}
