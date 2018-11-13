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

public class CadastrarItemComboAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/restaurante/CadastrarItemCombo.jsp");
        request.setAttribute("titulo", "Cadastrar Combo");
        HttpSession sessionScope = request.getSession();
        
        ArrayList<Restaurante> restaurantes = RestauranteDao
                .getInstance()
                .selectAllRestaurantesFromUsuarioByIdUsuario((Usuario) sessionScope.getAttribute("usuario"));
        request.setAttribute("restaurantes", restaurantes);
        request.setAttribute("contador", 0);
        request.setAttribute("contador_restaurante", 0);
        
        dispacher.forward(request, response);
    }
    
}
