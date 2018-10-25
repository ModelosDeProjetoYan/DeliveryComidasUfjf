/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Model.Restaurante;
import Persistence.RestauranteDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yan
 */
public class IndexAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispachante = request.getRequestDispatcher("WEB-INF/index.jsp");
                ArrayList<Restaurante> restaurantes = new ArrayList<>();
                restaurantes =RestauranteDao.getInstance().getRestaurantesBanco(); 
                request.setAttribute("Restaurantes",
                         restaurantes);      
                dispachante.forward(request, response);         
    
    }
    
}
