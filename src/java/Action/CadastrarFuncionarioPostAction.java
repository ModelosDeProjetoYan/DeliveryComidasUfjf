package Action;

import Controller.Action;
import Persistence.UsuarioDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CadastrarFuncionarioPostAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession sessionScope = request.getSession();
        int idGerente = (int) sessionScope.getAttribute("id");
        int idRestaurante = Integer.parseInt((String) request.getParameter("id_restaurante"));
        int idChefe = Integer.parseInt((String) request.getParameter("id_usuario_chefe"));
        int idEntregador = Integer.parseInt((String) request.getParameter("id_usuario_entregador"));
        
        if (UsuarioDao.getInstance().insertFuncionario(idRestaurante, idChefe, "ChefeDeCozinha") &&
            UsuarioDao.getInstance().insertFuncionario(idRestaurante, idEntregador, "Entregador") &&
            UsuarioDao.getInstance().insertFuncionario(idRestaurante, idGerente, "Gerente")) {
            
            sessionScope.setAttribute("sucesso", "Funcionário cadastrado com sucesso.");
            UsuarioDao.getInstance().updateTipoUsuario(idGerente, "Gerente", idChefe);
            UsuarioDao.getInstance().updateTipoUsuario(idChefe, "ChefeDeCozinha", idEntregador);
            UsuarioDao.getInstance().updateTipoUsuario(idEntregador, "Entregador",null);
            
        } else {
            sessionScope.setAttribute("erro", "Funcionário não foi cadastrado.");
        }
        response.sendRedirect("MainServlet?parametro=CadastrarFuncionario");
    }
}
