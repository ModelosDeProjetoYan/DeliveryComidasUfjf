package ChainOfResponsability_TemplateMethod;

public class UsuarioChefeDeCozinha extends Usuario {

    public UsuarioChefeDeCozinha() {
    }

    @Override
    String getTipo() {
        return "Chefe de Cozinha";
    }

    @Override
    String acompanhaPedido() {
        return pedido.getStatusPedido();
    }

}
