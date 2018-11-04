package Action;

import Controller.Action;
import Persistence.ItemDao;
import Persistence.RestauranteDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarCardapioAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/restaurantes/ListarItens.jsp");
        int idRestaurante= Integer.parseInt(request.getParameter("Id"));
        request.setAttribute("titulo", "Restaurantes Disponiveis");
        request.setAttribute("idRestaurante", idRestaurante);
        request.setAttribute("Cardapio", ItemDao.getInstance().getItensDoRestauranteBanco(idRestaurante));
        dispacher.forward(request, response);
    }
}
