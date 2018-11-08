package Action;

import Controller.Action;
import Model.Carrinho;
import Model.Item;
import Persistence.ItemDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdicionarItemNoCarrinhoPostAction implements Action {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession sessionScope = request.getSession();
        
        Integer quantidade_item = Integer.parseInt((String) request.getParameter("quantidade_item"));
        Integer id_item = Integer.parseInt((String) request.getParameter("id_item"));
        Item item = ItemDao.getInstance().selectItemById(id_item);
        item.setQuantidade(quantidade_item);
        
        if (sessionScope.getAttribute("carrinho") == null) {
            sessionScope.setAttribute("carrinho", new Carrinho());
        }
        
        ArrayList<Item> itens = ((Carrinho) sessionScope.getAttribute("carrinho"))
                .getPedido()
                .getCarrinho();
        
        Boolean existe = false;
        Item aux = null;
        
        for (Iterator i = itens.iterator(); i.hasNext();) {
            Item itemIterator =(Item) i.next();
            if (itemIterator.getId() == id_item) {
                if (quantidade_item <= 0) {
                    aux = itemIterator;
                } else {
                    itemIterator.setQuantidade(quantidade_item);
                }
                existe = true;
            }
        }
        if (!existe) {
            ((Carrinho) sessionScope.getAttribute("carrinho")).addCarrinho(item);
        }
        if (aux != null) {
            itens.remove(aux);
        }
        
        response.sendRedirect("MainServlet?parametro=ListarRestaurantes");
    }
}
