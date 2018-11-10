package ChainOfResponsability_TemplateMethod;

import Persistence.PedidoDao;
import State.Pedido;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioCliente extends Usuario  implements Observer {

    public UsuarioCliente() {
        this.restaurante = null;
    }
    
    @Override
    public String getTipo() {
        return "Cliente";
    }

    @Override
    String acompanhaPedido() {
        return pedido.getStatusPedido();
    }

    public Usuario setObservable(Pedido p) { 
        p.addObserver(this);
        return this;
    }
    @Override
    public void update(Observable pedido, Object arg) {
        if (pedido instanceof Pedido) {
            this.pedido = (Pedido) pedido;
            System.out.println(acompanhaPedido());
            Pedido p = (Pedido) pedido;
            try {
                PedidoDao.getInstance().saveEstado(p.getId(), p.getStatusPedido());
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
