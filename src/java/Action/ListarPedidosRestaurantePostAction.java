package Action;

import Controller.Action;
import Persistence.PedidoDao;
import Persistence.RestauranteDao;
import State.Pedido;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarPedidosRestaurantePostAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("MainServlet?parametro=ListarPedidosRestaurante");
    }
}
