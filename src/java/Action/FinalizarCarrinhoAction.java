package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import ChainOfResponsability_TemplateMethod.UsuarioCliente;
import Controller.Action;
import Model.Carrinho;
import Model.Endereco;
import Persistence.EnderecoEntregaDao;
import Persistence.ItemDao;
import Persistence.PedidoDao;
import Persistence.RestauranteDao;
import Persistence.UsuarioDao;
import State.Pedido;
import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FinalizarCarrinhoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionScope = request.getSession();
        Carrinho c = (Carrinho) sessionScope.getAttribute("carrinho");
        int idPedido= c.getPedido().getId();
        int idRestaurante = c.getPedido().getCarrinho().get(0).getRestaurante();
        int idUsuario = (int) sessionScope.getAttribute("id");
        int idGerenteRestaurante = RestauranteDao.getInstance().getIdGerente(idRestaurante);
       
        Endereco e = EnderecoEntregaDao.getInstance().getEnderecoUsuario(idUsuario).get(0);
        c.getPedido().setEnderecoEntrega(e).setDataPedido(new Date());
        UsuarioCliente u = (UsuarioCliente) sessionScope.getAttribute("usuario");
        u.setAcaoFeita(true);
        u.setObservable(c.getPedido());
        PedidoDao.getInstance().setPedido(c.getPedido(), idUsuario, e.getId());
        
        if(idGerenteRestaurante >= 0){
            u.setProxUsuario(UsuarioDao.getInstance().getUsuarioByID(idGerenteRestaurante));
            UsuarioDao.getInstance().updateTipoUsuario(idUsuario, "Cliente", idGerenteRestaurante);
        }
        //instanciar pedido no banco
        c.getPedido().setFeito();
        
        if (u != null) {
            sessionScope.setAttribute("usuario", u);            
            sessionScope.setAttribute("sucesso", u.mensagemUsuario());
            response.sendRedirect("MainServlet?parametro=Index");
        } else {
            sessionScope.setAttribute("erro", "Usuário ou senha inválidos.");
            response.sendRedirect("MainServlet?parametro=Login");
        }
    }
}
