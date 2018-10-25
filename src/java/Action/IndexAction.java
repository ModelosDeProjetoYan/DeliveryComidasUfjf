package Action;

import Controller.Action;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/index.jsp");
        request.setAttribute("titulo", "Página inicial");

//        ArrayList<Restaurante>restaurantes = RestauranteDao.getInstance().getRestaurantesBanco();
//        request.setAttribute("Restaurantes", restaurantes);

        dispacher.forward(request, response);
    }
}
