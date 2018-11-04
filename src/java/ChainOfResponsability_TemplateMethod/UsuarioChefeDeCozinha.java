package ChainOfResponsability_TemplateMethod;

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
