package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import ChainOfResponsability_TemplateMethod.UsuarioCliente;
import ChainOfResponsability_TemplateMethod.UsuarioGerente;
import Controller.Action;
import Model.Carrinho;
import Model.Restaurante;
import Persistence.PedidoDao;
import Persistence.RestauranteDao;
import Persistence.UsuarioDao;
import State.Pedido;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AtualizarEstadoPedidoPostAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String estado = request.getParameter("state");
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        Pedido p = PedidoDao.getInstance().getPedidoById(idPedido);
        switch (estado) {
            case "Aberto":
                p.setAberto();
                break;
            case "Feito":
                p.setFeito();
                break;
            case "Preparando":
                p.setPreparando();
                break;

            case "Pronto":
                p.setPronto();
                break;
            case "Entregando":
                p.setEntregando();
                break;
            case "Entregue":
                p.setEntregando();
                break;
        }
        if (!p.getStatusPedido().equals(PedidoDao.getInstance().getPedidoById(idPedido).getStatusPedido())) {
            try {
                PedidoDao.getInstance().saveEstado(idPedido, estado);
            } catch (SQLException ex) {
                Logger.getLogger(AtualizarEstadoPedidoPostAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.sendRedirect("MainServlet?parametro=ListarPedidosRestaurante");
    }
}
