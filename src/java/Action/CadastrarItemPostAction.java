package Action;

import Controller.Action;
import Model.ActionFactoryItem;
import Model.Item;
import Persistence.ItemDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CadastrarItemPostAction implements Action{
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession sessionScope = request.getSession();
        
        Integer idRestaurante = Integer.parseInt((String) request.getParameter("id_restaurante"));
        String nome = (String) request.getParameter("nome");
        String tipo = (String) request.getParameter("tipo");
        String descricao = (String) request.getParameter("descricao");
        Double preco = Double.parseDouble(request.getParameter("preco"));
        boolean disponivel = "Sim".equals((String) request.getParameter("disponivel")) ? true : false;
        boolean promocao = "Sim".equals((String) request.getParameter("promocao")) ? true : false;
        Item item = ActionFactoryItem.create(tipo);
        item.setDescricao(descricao).
                setDisponivel(disponivel).
                setIdRestaurante(idRestaurante).
                setNome(nome).
                setPreco(preco).
                setPromocao(promocao);
        if (ItemDao.getInstance().insertItem(item)) {
            sessionScope.setAttribute("sucesso", "Item cadastrado com sucesso.");
        } else {
            sessionScope.setAttribute("erro", "Item não foi cadastrado.");
        }
        response.sendRedirect("MainServlet?parametro=CadastrarItem");
    }
}
