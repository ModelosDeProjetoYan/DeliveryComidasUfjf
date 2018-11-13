package ChainOfResponsability_TemplateMethod;

import Action.ActionFactoryCadastroFuncionario;
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
    public Usuario setNewTipoUsuario(String tipoUser){
        Usuario u = ActionFactoryCadastroFuncionario.create(tipoUser);
        u.setId(this.id).
                setEmail(this.email).
                setNome(this.nome).
                setSenha(this.senha).
                setAcaoFeita(false).setTipoUsuario(tipoUser);
        return u;
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
            int idRestaurante = p.getCarrinho().get(0).getRestaurante();
            try {
                PedidoDao.getInstance().saveEstado(p.getId(), p.getStatusPedido());
                PedidoDao.getInstance().setRestaurantePedido(idRestaurante, p.getId());

            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
