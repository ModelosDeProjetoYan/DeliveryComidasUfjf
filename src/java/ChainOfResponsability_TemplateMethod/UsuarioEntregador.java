package ChainOfResponsability_TemplateMethod;

public class UsuarioEntregador extends Usuario {

    public UsuarioEntregador() {
    }

    @Override
    String getTipo() {
        return "Entregador";
    }

    @Override
    String acompanhaPedido() {
        return pedido.getStatusPedido();
    }

}
