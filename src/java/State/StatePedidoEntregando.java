package State;

public class StatePedidoEntregando implements StatePedido {

    @Override
    public void setPreparando(Pedido p) {
    }

    @Override
    public void setAberto(Pedido p) {
    }

    @Override
    public void setFeito(Pedido p) {
    }

    @Override
    public void setPronto(Pedido p) {
         p.setPedido(new StatePedidoPronto());
    }

    @Override
    public void setEntregando(Pedido p) {
    }

    @Override
    public void setEntregue(Pedido p) {
        p.setPedido(new StatePedidoEntregue());
    }

    @Override
    public String getEstado() {
        return "Entregando";
    }

}
