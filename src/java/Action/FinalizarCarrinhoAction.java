package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import ChainOfResponsability_TemplateMethod.UsuarioCliente;
import Controller.Action;
import Model.Carrinho;
import Model.Endereco;
import Model.Item;
import Persistence.EnderecoEntregaDao;
import Persistence.ItemDao;
import Persistence.PedidoDao;
import Persistence.RestauranteDao;
import Persistence.UsuarioDao;
import State.Pedido;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FinalizarCarrinhoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionScope = request.getSession();
        Carrinho carrinho = (Carrinho) sessionScope.getAttribute("carrinho");
        int idPedido = carrinho.getPedido().getId();
        int idRestaurante = carrinho.getPedido().getCarrinho().get(0).getRestaurante();
        int idUsuario = (int) sessionScope.getAttribute("id");
        int idGerenteRestaurante = RestauranteDao.getInstance().getIdGerente(idRestaurante);

        Endereco enderecoAux = EnderecoEntregaDao.getInstance().getEnderecoUsuario(idUsuario).get(0);
        carrinho.getPedido().setEnderecoEntrega(enderecoAux).setDataPedido(new Date());
        UsuarioCliente cliente = (UsuarioCliente) sessionScope.getAttribute("usuario");
        
        cliente.setAcaoFeita(true);
        cliente.setObservable(PedidoDao.getInstance());
        
        cliente.setPedido(carrinho.getPedido());
        Pedido pedido = PedidoDao.getInstance().setPedido(carrinho.getPedido(), idUsuario, enderecoAux.getId());
        
        for (Iterator i = carrinho.getPedido().getCarrinho().iterator(); i.hasNext();) {
            Item item = (Item) i.next();
            ItemDao.getInstance().insertItemPedido(item.getId(), pedido.getId(), item.getQuantidade());
        }
        if (idGerenteRestaurante >= 0) {
            cliente.setProxUsuario(UsuarioDao.getInstance().getUsuarioByID(idGerenteRestaurante));
            UsuarioDao.getInstance().updateTipoUsuario(idUsuario, "Cliente", idGerenteRestaurante);
        }
        //instancia no banco por meio do observer
        pedido.setFeito();
        try {
            PedidoDao.getInstance().saveEstado(pedido.getId(), pedido.getStatusPedido());
            PedidoDao.getInstance().setRestaurantePedido(idRestaurante, pedido.getId());
        } catch (SQLException ex) {
            Logger.getLogger(FinalizarCarrinhoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cliente != null) {
            sessionScope.setAttribute("usuario", cliente);
            sessionScope.setAttribute("sucesso", cliente.mensagemUsuario());
            response.sendRedirect("MainServlet?parametro=Index");
        } else {
            sessionScope.setAttribute("erro", "Usuário ou senha inválidos.");
            response.sendRedirect("MainServlet?parametro=Login");
        }
    }
}
