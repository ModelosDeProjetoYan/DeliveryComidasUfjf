package Action;

import Controller.Action;
import Persistence.RestauranteDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarRestaurantesAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/restaurante/ListarRestaurantes.jsp");
        request.setAttribute("titulo", "Restaurantes Disponiveis");
        dispacher.forward(request, response);
    }
}
