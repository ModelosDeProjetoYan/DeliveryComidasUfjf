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
        
        int idRestaurante = Integer.parseInt((String) request.getParameter("id_restaurante"));
        int idUsuarioFuncionario = Integer.parseInt((String) request.getParameter("id_usuario"));
        String tipoUsuario = (String) request.getAttribute("tipo_usuario");
        
        if (UsuarioDao.getInstance().insertFuncionario(idRestaurante, idUsuarioFuncionario, tipoUsuario)) {
            sessionScope.setAttribute("sucesso", "Funcionário cadastrado com sucesso.");
        } else {
            sessionScope.setAttribute("erro", "Funcionário não foi cadastrado.");
        }
        response.sendRedirect("MainServlet?parametro=CadastrarFuncionario");
    }
}
