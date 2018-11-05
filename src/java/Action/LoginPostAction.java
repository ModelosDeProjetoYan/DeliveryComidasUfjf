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
        request.setAttribute("titulo", "Página inicial");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Usuario u = UsuarioDao.getInstance().getUsuario(email, senha);
        HttpSession sessionScope = request.getSession();
        
        if (u != null) {
            sessionScope.setAttribute("id", u.getId());
            sessionScope.setAttribute("nome", u.getNome());
            sessionScope.setAttribute("login", u.getEmail());
            sessionScope.setAttribute("user", u);
            
            sessionScope.setAttribute("erro", u.mensagemUsuario());
            response.sendRedirect("MainServlet?parametro=Interface" + u.getTipo());
        } else {
            sessionScope.setAttribute("erro", "Usuário ou senha inválidos.");
            response.sendRedirect("MainServlet?parametro=UsuarioLogin");
        }
    }
}
