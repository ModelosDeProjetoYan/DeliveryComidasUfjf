package Action;

import Controller.Action;
import Persistence.RestauranteDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarMeusFuncionariosAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/restaurantes/ListarMeusFuncionarios.jsp");
        request.setAttribute("titulo", "Meus Funcionários");
        dispacher.forward(request, response);
    }
}
