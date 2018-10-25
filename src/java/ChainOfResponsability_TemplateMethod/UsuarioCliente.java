package ChainOfResponsability_TemplateMethod;

public class UsuarioCliente extends Usuario {

    @Override
    String getTipo() {
        return "Cliente";
    }

    @Override
    String acompanhaPedido() {
        return pedido.getStatusPedido();
    }
}
