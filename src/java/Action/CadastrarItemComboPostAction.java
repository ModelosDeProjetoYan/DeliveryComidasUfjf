package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.Action;
import Persistence.ItemDao;
import Persistence.RestauranteDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CadastrarItemComboPostAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession sessionScope = request.getSession();
        
        Usuario usuario = (Usuario) sessionScope.getAttribute("usuario");
        Integer numDeRestaurantes = RestauranteDao.getInstance().selectCountNumberOfRestaurantes(usuario.getId());
        
        ArrayList<Integer> itens = new ArrayList<>();
        
        int contador = 0;
        int contadorRestaurante = 0;
        for (int i = 0; i < numDeRestaurantes; i++) {
            if (request.getParameter("id_item_" + contadorRestaurante + "_" + contador) != null 
                && !"".equals(request.getParameter("id_item_" + contadorRestaurante + "_" + contador))) {
                break;
            }
            contadorRestaurante++;
        }
        
        for (int i=0; i<6; i++) {
            itens.add(Integer.parseInt(request.getParameter("id_item_" + contadorRestaurante + "_" + i)));
        }
//        
//        Integer idRestaurante = Integer.parseInt((String) request.getParameter("id_restaurante"));
//        String nome = (String) request.getParameter("nome");
//        String tipo = (String) request.getParameter("tipo");
//        String descricao = (String) request.getParameter("descricao");
//        Double preco = Double.parseDouble(request.getParameter("preco"));
//        Integer disponivel = "Sim".equals((String) request.getParameter("disponivel")) ? 1 : 0;
//        Integer promocao = "Sim".equals((String) request.getParameter("promocao")) ? 1 : 0;
//        
//        if (ItemDao.getInstance().insertItem(nome, tipo, descricao, preco, disponivel, promocao, idRestaurante)) {
//            sessionScope.setAttribute("sucesso", "Item cadastrado com sucesso.");
//        } else {
//            sessionScope.setAttribute("erro", "Item não foi cadastrado.");
//        }
//        response.sendRedirect("MainServlet?parametro=CadastrarItem");
    }
}
