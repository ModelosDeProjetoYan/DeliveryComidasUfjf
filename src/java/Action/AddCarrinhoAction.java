package Action;

import Controller.Action;
import Persistence.ItemDao;
import Persistence.RestauranteDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCarrinhoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRestaurante= Integer.parseInt(request.getParameter("idR"));
        int idItem= Integer.parseInt(request.getParameter("id"));
        //adicionar itens no carrinho
        response.sendRedirect("/restaurantes/ListarItens.jsp");
    }
}
