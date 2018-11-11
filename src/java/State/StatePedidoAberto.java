package State;

public class StatePedidoAberto implements StatePedido {

    @Override
    public void setAberto(Pedido p) {

    }

    @Override
    public void setFeito(Pedido p) {
        p.setPedido(new StatePedidoFeito());
    }

    @Override
    public void setPreparando(Pedido p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPronto(Pedido p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEntregando(Pedido p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEntregue(Pedido p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEstado() {
        return "Aberto";
    }

}
