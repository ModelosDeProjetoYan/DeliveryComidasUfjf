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
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListarPedidosRestauranteAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/restaurante/ListarPedidosRestaurante.jsp");
        request.setAttribute("titulo", "Pedidos do Restaurante");
        HttpSession sessionScope = request.getSession();
        int idUsuario = (int) sessionScope.getAttribute("id");
        
        ArrayList<Restaurante> meusRestaurantes = RestauranteDao
                .getInstance()
                .selectAllRestaurantesFromUsuarioByIdUsuario((Usuario) sessionScope.getAttribute("usuario"));
       
        
        int idRestaurante = meusRestaurantes.get(0).getId();
        
        ArrayList<Pedido> pedidos = PedidoDao.getInstance().getAllPedidosRestaurante(idRestaurante);
        request.setAttribute("pedidos", pedidos);
        dispacher.forward(request, response);
    }
}
