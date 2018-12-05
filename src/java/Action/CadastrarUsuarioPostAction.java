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

public class CadastrarUsuarioPostAction implements Action  {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Usuario usuarioAux = UsuarioDao.getInstance().insertUsuarioCliente(nome, email, senha);
        
        if (usuarioAux != null) {
            response.sendRedirect("MainServlet?parametro=Login");
        } else {
            HttpSession sessionScope = request.getSession();
            sessionScope.setAttribute("erro", "Erro ao cadastrar usuário.");
            response.sendRedirect("MainServlet?parametro=CadastrarUsuario");
        }
    }
}
