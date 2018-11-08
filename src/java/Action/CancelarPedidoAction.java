package Action;

import Controller.Action;
import Persistence.UsuarioDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelarPedidoAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession sessionScope = request.getSession();
        sessionScope.setAttribute("carrinho", null);
        response.sendRedirect("MainServlet?parametro=ListarRestaurantes");
    }
}
