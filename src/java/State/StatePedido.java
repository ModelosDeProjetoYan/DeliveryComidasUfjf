package State;

public interface StatePedido {

    public void setAberto(Pedido p);

    public void setFeito(Pedido p);

    public void setPreparando(Pedido p);

    public void setPronto(Pedido p);

    public void setEntregando(Pedido p);

    public void setEntregue(Pedido p);

    public String getEstado();
}
