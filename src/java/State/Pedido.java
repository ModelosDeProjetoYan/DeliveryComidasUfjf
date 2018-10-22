/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

import Memento.PedidoMemento;
import Model.Endereco;
import Model.Item;
import State.StatePedido;
import java.util.ArrayList;

/**
 *
 * @author yan
 */
public class Pedido {

    private StatePedido pedido;
    private ArrayList<Item> carrinho;
    private String statusPedido;
    private Endereco enderecoEntrega;

    public Pedido() {
    }

    
    public Pedido(StatePedido p) {
        carrinho = new ArrayList<>();
        pedido = p;
    }

    public void setAberto() {
        pedido.setAberto(this);
        setStatusPedido();
    }

    public void setFeito() {
        pedido.setFeito(this);
        setStatusPedido();
    }

    public void setPreparando() {
        pedido.setPreparando(this);
        setStatusPedido();
    }

    public void setPronto() {
        pedido.setPronto(this);
        setStatusPedido();
    }

    public void setEntregando() {
        pedido.setEntregando(this);
        setStatusPedido();
    }

    public void setEntregue() {
        pedido.setEntregue(this);
        setStatusPedido();
    }

    private void setStatusPedido() {
        this.statusPedido = pedido.getEstado();
    }

    public void removeItemCarrinho(int i) {
        carrinho.remove(i);
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
        return statusPedido;
    }

    public void setPedido(StatePedido pedido) {
        this.pedido = pedido;
    }

    public PedidoMemento saveToMemento() {
        return new PedidoMemento(pedido);
    }

    public void restoreFormMemento(PedidoMemento memento) {
        pedido = memento.getEstadoSalvo();
    }

}