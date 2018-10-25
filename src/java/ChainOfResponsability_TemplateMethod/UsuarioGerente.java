package ChainOfResponsability_TemplateMethod;

public class UsuarioGerente extends Usuario {

    public UsuarioGerente() {
    }

    @Override
    String getTipo() {
        return "Gerente";
    }

    @Override
    String acompanhaPedido() {
        return pedido.getStatusPedido();
    }

}
