package State;

import Memento.PedidoMemento;
import Model.Endereco;
import Model.Item;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class Pedido {

    private int id;
    private ArrayList<Item> carrinho;
    private String statusPedido;
    private Endereco enderecoEntrega;
    private Date dataPedido;
    private StatePedido pedido;
    private StatePedido estadoAntigo;

    public Pedido() {
    }

    public Pedido(StatePedido p) {
        carrinho = new ArrayList<>();
        pedido = p;
        estadoAntigo = p;
        statusPedido = p.getEstado();
    }

    public int getId() {
        return id;
    }

    public Pedido setId(int id) {
        this.id = id;
        return this;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public Pedido setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
        return this;
    }

    public void setAberto() {
        pedido.setAberto(this);
    }

    public void setFeito() {
        pedido.setFeito(this);
    }

    public void setPreparando() {
        pedido.setPreparando(this);
    }

    public void setPronto() {
        pedido.setPronto(this);
    }

    public void setEntregando() {
        pedido.setEntregando(this);
    }

    public void setEntregue() {
        pedido.setEntregue(this);
    }
    public void removeItemCarrinho(Item i) {
        carrinho.remove(i);
    }

    public void removeAllItensCarrinho() {
        carrinho.removeAll(carrinho);
    }

    public void addItemCarrinho(Item i) {
        carrinho.add(i);
    }

    public ArrayList<Item> getCarrinho() {
        return carrinho;
    }

    public Pedido setCarrinho(ArrayList<Item> carrinho) {
        this.carrinho = carrinho;
        return this;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public Pedido setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
        return this;
    }

    public StatePedido getPedido() {
        return pedido;
    }

    public String getStatusPedido() {
        statusPedido = pedido.getEstado();
        return statusPedido;
    }

    public void setPedido(StatePedido pedido) {
        if (this.pedido == null) {
            this.estadoAntigo = pedido;
        }
        this.pedido = pedido;
    }

    public PedidoMemento saveToMemento() {
        return new PedidoMemento(pedido);
    }

    public void restoreFromMemento(PedidoMemento memento) {
        pedido = memento.getEstadoSalvo();
    }

}
