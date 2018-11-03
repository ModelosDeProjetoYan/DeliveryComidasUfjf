package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.Action;
import Model.Restaurante;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarFuncionarioAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/Index.jsp");
        request.setAttribute("titulo", "Cadastrasr Funcionario");
        Restaurante r = null;
        Usuario u = null;
        switch(Integer.parseInt(request.getParameter("comboBox"))){
            case 0: u = ActionFactoryCadastroFuncionario.create("UsuarioGerente");
                //falta setar as informações od usuario e criar ele no banco
            break;
            case 1: u = ActionFactoryCadastroFuncionario.create("UsuarioEntregador");
                //falta setar as informações od usuario e criar ele no banco
            break;
            case 2: u = ActionFactoryCadastroFuncionario.create("UsuarioChefeDeCozinha");
                //falta setar as informações od usuario e criar ele no banco
            break;
            case 3: r = new Restaurante();
                //falta setar as informações od usuario e criar ele no banco
            break;
            default: dispacher = request.getRequestDispatcher("/pagina-de-erro.jsp");
        }
        if(u != null)
            u.setRestaurante(r);

        dispacher.forward(request, response);
    }
    
}
