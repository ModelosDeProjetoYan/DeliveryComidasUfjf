package ChainOfResponsability_TemplateMethod;

import State.Pedido;

public class UsuarioChefeDeCozinha extends Usuario {

    public UsuarioChefeDeCozinha() {
    }

    @Override
    public String getTipo() {
        return "ChefeDeCozinha";
    }

    @Override
    String acompanhaPedido() {
        return pedido.getStatusPedido();
    }

}
