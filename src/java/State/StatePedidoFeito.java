package State;

public class StatePedidoFeito implements StatePedido {

    @Override
    public void setAberto(Pedido p) {

    }

    @Override
    public void setFeito(Pedido p) {
    }

    @Override
    public void setPreparando(Pedido p) {
        p.setPedido(new StatePedidoPreparando());
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
    }

    @Override
    public String getEstado() {
        return "Feito";
    }

}
