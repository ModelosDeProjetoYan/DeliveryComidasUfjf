package Action;

import Controller.Action;
import Memento.PedidoMemento;
import Persistence.PedidoDao;
import Persistence.RestauranteDao;
import State.Pedido;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import static java.util.Spliterators.iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListarMeusPedidosAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/usuario/ListarMeusPedidos.jsp");
        request.setAttribute("titulo", "Meus Pedidos");
        HttpSession sessionScope = request.getSession();
        int idUsuario = (int) sessionScope.getAttribute("id");
        ArrayList<String> estadosDoPedido = new ArrayList<>();
        ArrayList<Pedido> p = PedidoDao.getInstance().getAllPedidosUsuario(idUsuario);
        PedidoDao.getInstance().getMementos(0).getEstadosSalvos();
        for (Iterator i =  PedidoDao.getInstance().getMementos(0).getEstadosSalvos().iterator(); i.hasNext();) {
            PedidoMemento pedidoMemento = (PedidoMemento) i.next();
            estadosDoPedido.add(pedidoMemento.toString());   
        }
        request.setAttribute("estadosDoPedido", estadosDoPedido);
        dispacher.forward(request, response);
    }
}
