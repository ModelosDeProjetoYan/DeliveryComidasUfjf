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
        ArrayList<String> estados = new ArrayList<>();
        String cargo = RestauranteDao.getInstance().getCargoRestaurante(idRestaurante, idUsuario);
        if("Gerente".equals(cargo)){
            estados.add("Aberto");
            estados.add("Feito");
            estados.add("Preparando");
            estados.add("Pronto");
            estados.add("Entregando");
            estados.add("Entregue");
      
        }else if("ChefeDeCozinha".equals(cargo)){
            estados.add("Pronto");
        }else if("Entregador".equals(cargo)){
            estados.add("Entregando");
            estados.add("Entregue");
        }
        request.setAttribute("pedidos", pedidos);
        request.setAttribute("estados", estados);
        dispacher.forward(request, response);
    }
}
