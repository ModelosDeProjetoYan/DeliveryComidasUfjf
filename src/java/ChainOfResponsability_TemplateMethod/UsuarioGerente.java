package ChainOfResponsability_TemplateMethod;

import State.Pedido;
import java.util.Observer;

public class UsuarioGerente extends Usuario {

    public UsuarioGerente() {
    }

    @Override
    public String getTipo() {
        return "Gerente";
    }

    @Override
    String acompanhaPedido() {
        return pedido.getStatusPedido();
    }

}
