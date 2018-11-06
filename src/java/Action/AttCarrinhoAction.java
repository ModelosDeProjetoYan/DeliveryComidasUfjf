package Action;

import Controller.Action;
import Persistence.ItemDao;
import Persistence.PedidoDao;
import Persistence.RestauranteDao;
import State.Pedido;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AttCarrinhoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPedido= Integer.parseInt(request.getParameter("idPedido"));
        int indexItem= Integer.parseInt(request.getParameter("indexItem"));
        PedidoDao.getInstance().
                getPedidoById(idPedido).
                getCarrinho().get(indexItem).
                setQuantidade(Integer.parseInt(request.getParameter("Quantidade")));
        request.setAttribute("pedido", PedidoDao.getInstance().
                getPedidoById(idPedido));
        
        request.setAttribute("idPedido", idPedido);
        
        response.sendRedirect("MainServlet?parametro=usuario/Carrinho");
    }
}
