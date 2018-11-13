package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.Action;
import Model.Restaurante;
import Persistence.RestauranteDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListarMeusRestaurantesAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/restaurante/ListarRestaurantes.jsp");
        request.setAttribute("titulo", "Meus Restaurantes");
        HttpSession sessionScope = request.getSession();
        
        ArrayList<Restaurante> restaurantes = RestauranteDao
                .getInstance()
                .selectAllRestaurantesFromUsuarioByIdUsuario((Usuario) sessionScope.getAttribute("usuario"));
        sessionScope.setAttribute("isProprietario", true);
        request.setAttribute("contador", 0);
        request.setAttribute("restaurantes", restaurantes);
        
        dispacher.forward(request, response);
    }
}
