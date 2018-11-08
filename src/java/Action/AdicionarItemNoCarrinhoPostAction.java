package Action;

import Controller.Action;
import Persistence.ItemDao;
import Persistence.PedidoDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdicionarItemNoCarrinhoPostAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession sessionScope = request.getSession();
        
        Integer quantidadeItem = Integer.parseInt((String) request.getParameter("quantidade_item"));
        Integer idItem = Integer.parseInt((String) request.getParameter("id_item"));
        
        response.sendRedirect("MainServlet?parametro=ListarRestaurantes");
    }
}
