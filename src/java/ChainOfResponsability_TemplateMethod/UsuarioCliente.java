package ChainOfResponsability_TemplateMethod;

public class UsuarioCliente extends Usuario {

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
}
