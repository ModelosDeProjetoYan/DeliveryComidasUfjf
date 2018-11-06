package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.Action;
import Model.Restaurante;
import Persistence.RestauranteDao;
import java.io.IOException;
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
        
        HttpSession sessionScope = request.getSession();
        Restaurante restaurante = RestauranteDao.getInstance().insertRestaurante((int) sessionScope.getAttribute("id"), nome, logradouro, numero, complemento, bairro, cidade, tipoComida);
        
        if (restaurante != null) {
            sessionScope.setAttribute("sucesso", "Restaurante cadastrado com sucesso.");
            response.sendRedirect("MainServlet?parametro=ListarMeusRestaurantes");
        } else {
            sessionScope.setAttribute("erro", "Erro ao cadastrar o restaurante.");
            response.sendRedirect("MainServlet?parametro=CadastrarRestaurante");
        }
    }
}
