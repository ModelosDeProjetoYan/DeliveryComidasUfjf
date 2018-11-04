package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.Action;
import Persistence.UsuarioDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginPostAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher;
        request.setAttribute("titulo", "Página inicial");
        String email = request.getParameter("login");
        String senha = request.getParameter("senha");
        Usuario u = UsuarioDao.getInstance().getUsuario(email, senha);
        if (u != null) {
            HttpSession sessionScope = request.getSession();
            sessionScope.setAttribute("id", u.getId());
            sessionScope.setAttribute("nome", u.getNome());
            sessionScope.setAttribute("login", u.getEmail());
            sessionScope.setAttribute("user", u);
            dispacher = request.getRequestDispatcher("/usuario/Interface" + u.getTipo() + ".jsp");

        } else {
            dispacher = request.getRequestDispatcher("/usuario/UsuarioLogin.jsp");
        }

        dispacher.forward(request, response);
    }
}
