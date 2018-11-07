package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.Action;
import Persistence.ItemDao;
import Persistence.PedidoDao;
import Persistence.RestauranteDao;
import Persistence.UsuarioDao;
import State.Pedido;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FinalizarCarrinhoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPedido= Integer.parseInt(request.getParameter("idPedido"));
        HttpSession sessionScope = request.getSession();
        int idUsuario = (int) sessionScope.getAttribute("id");
        int idGerenteRestaurante = Integer.parseInt(request.getParameter("idRestaurante"));
        Usuario u = UsuarioDao.getInstance().getUsuarioByID(idUsuario);
        u.setAcaoFeita(true);
        Pedido p = PedidoDao.getInstance().getPedidoById(idPedido);
        u.setObservable(p);
        if(RestauranteDao.getInstance().getIdGerente(idUsuario) >= 0){
            u.setProxUsuario(UsuarioDao.getInstance().getUsuarioByID(idGerenteRestaurante));
            UsuarioDao.getInstance().updateTipoUsuario(idUsuario, "Cliente", idGerenteRestaurante);
        }
        p.setFeito();
        if (u != null) {
            sessionScope.setAttribute("id", u.getId());
            sessionScope.setAttribute("nome", u.getNome());
            sessionScope.setAttribute("login", u.getEmail());
            sessionScope.setAttribute("usuario", u);            
            sessionScope.setAttribute("sucesso", u.mensagemUsuario());
            response.sendRedirect("MainServlet?parametro=Index");
        } else {
            sessionScope.setAttribute("erro", "Usuário ou senha inválidos.");
            response.sendRedirect("MainServlet?parametro=Login");
        }
    }
}
