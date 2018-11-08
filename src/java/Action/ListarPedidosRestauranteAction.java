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

public class ListarPedidosRestauranteAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/restaurante/ListarPedidosRestaurante.jsp");
        request.setAttribute("titulo", "Pedidos do Restaurante");
        int idRestaurante = 1;
        ArrayList<Pedido> pedidos = PedidoDao.getInstance().getAllPedidosRestaurante(idRestaurante);
        request.setAttribute("pedidos", pedidos);
        dispacher.forward(request, response);
    }
}
