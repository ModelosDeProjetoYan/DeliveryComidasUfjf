package State;

public class StatePedidoPronto implements StatePedido {

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
         p.setPedido(new StatePedidoEntregando());
    }

    @Override
    public void setEntregue(Pedido p) {
         p.setPedido(new StatePedidoEntregue());
    }

    @Override
    public String getEstado() {
        return "Pronto";
    }

}
