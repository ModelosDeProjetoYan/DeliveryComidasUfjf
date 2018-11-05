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
        RequestDispatcher dispacher;
        
        
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        Usuario u = UsuarioDao.getInstance().insertUsuarioCliente(nome, email, senha);
        
        if (u != null) {
            request.setAttribute("titulo", "Usuário Login");
            dispacher = request.getRequestDispatcher("/usuario/UsuarioLogin.jsp");
        } else {
            request.setAttribute("titulo", "Cadastrar Usuário");
            request.setAttribute("erro", "Erro ao cadastrar usuário.");
            dispacher = request.getRequestDispatcher("/usuario/CadastrarUsuario.jsp");
        }

        dispacher.forward(request, response);
    }
}
