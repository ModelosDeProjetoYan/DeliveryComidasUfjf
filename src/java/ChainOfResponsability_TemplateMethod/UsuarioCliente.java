package ChainOfResponsability_TemplateMethod;

import Action.ActionFactoryCadastroFuncionario;
import Persistence.PedidoDao;
import State.Pedido;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioCliente extends Usuario implements Observer {

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

    public Usuario setObservable(PedidoDao p) {
        p.addObserver(this);
        return this;
    }

    @Override
    public void update(Observable pedido, Object arg) {
        if ((int) arg == this.pedido.getId()) {
            this.pedido = PedidoDao.getInstance().getPedidoById(this.pedido.getId());
            System.out.println(acompanhaPedido());
        }

    }

}
