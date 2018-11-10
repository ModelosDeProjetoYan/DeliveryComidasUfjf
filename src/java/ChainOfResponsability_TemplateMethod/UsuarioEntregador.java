package ChainOfResponsability_TemplateMethod;

import State.Pedido;

public class UsuarioEntregador extends Usuario {

    public UsuarioEntregador() {
    }

    @Override
    public String getTipo() {
        return "Entregador";
    }

    @Override
    String acompanhaPedido() {
        return pedido.getStatusPedido();
    }

}
