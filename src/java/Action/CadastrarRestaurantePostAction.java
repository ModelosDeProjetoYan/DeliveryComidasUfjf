package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.Action;
import Model.Restaurante;
import Persistence.RestauranteDao;
import Persistence.UsuarioDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CadastrarRestaurantePostAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nome = request.getParameter("nome");
        String logradouro = request.getParameter("logradouro");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String tipoComida = request.getParameter("tipo_comida");
//        Restaurante r = RestauranteDao.getInstance().insertRestaurante();
        
//        if (r != null) {
//            request.setAttribute("titulo", "Usuário Login");
//            response.sendRedirect("MainServlet?parametro=UsuarioLogin");
//        } else {
//            request.setAttribute("titulo", "Cadastrar Usuário");
//            HttpSession sessionScope = request.getSession();
//            sessionScope.setAttribute("id", r.getId());
//            response.sendRedirect("MainServlet?parametro=CadastrarUsuario");
//        }
    }
}
