package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.Action;
import Model.Restaurante;
import Persistence.RestauranteDao;
import Persistence.UsuarioDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CadastrarFuncionarioAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/restaurante/CadastrarFuncionario.jsp");
        request.setAttribute("titulo", "Cadastrar Funcionário");
        HttpSession sessionScope = request.getSession();
        
        ArrayList<Restaurante> restaurantes = RestauranteDao
                .getInstance()
                .selectAllRestaurantesFromUsuarioByIdUsuario((Usuario) sessionScope.getAttribute("usuario"));
        request.setAttribute("restaurantes", restaurantes);
        
        ArrayList<Usuario> usuarios = UsuarioDao.getInstance().selectAllUsuarios();
        request.setAttribute("usuarios", usuarios);
        
        
//        Restaurante r = null;
//        Usuario u = null;      
//        switch(Integer.parseInt(request.getParameter("comboBox"))){
//            case 0: u = ActionFactoryCadastroFuncionario.create("UsuarioGerente");
//                //falta setar as informações od usuario e criar ele no banco
//            break;
//            case 1: u = ActionFactoryCadastroFuncionario.create("UsuarioEntregador");
//                //falta setar as informações od usuario e criar ele no banco
//            break;
//            case 2: u = ActionFactoryCadastroFuncionario.create("UsuarioChefeDeCozinha");
//                //falta setar as informações od usuario e criar ele no banco
//            break;
//            case 3: r = new Restaurante();
//                //falta setar as informações od usuario e criar ele no banco
//            break;
////            default: dispacher = request.getRequestDispatcher("/pagina-de-erro.jsp");
//        }
//        if(u != null) {
//            u.setRestaurante(r);
//        }

        dispacher.forward(request, response);
    }
    
}
