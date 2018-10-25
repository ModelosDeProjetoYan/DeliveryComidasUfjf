package State;

public class StatePedidoEntregue implements StatePedido {

    @Override
    public void setAberto(Pedido p) {
    }

    @Override
    public void setFeito(Pedido p) {
    }

    @Override
    public void setPreparando(Pedido p) {
    }

    @Override
    public void setPronto(Pedido p) {
    }

    @Override
    public void setEntregando(Pedido p) {
    }

    @Override
    public void setEntregue(Pedido p) {
    }

    @Override
    public String getEstado() {
        return "Entregue";
    }

}
