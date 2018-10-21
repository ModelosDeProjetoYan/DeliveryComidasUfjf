/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author yan
 */
public class StateCliente implements StateUsuario{
    private ArrayList<Item> pedido;
    private Endereco enderecoEntrega;
    public StateCliente() {
        pedido= new ArrayList<>();
    }  
    @Override
    public void setGerente(Usuario user) {
    }
    @Override
    public void setChefeDeCozinha(Usuario user) {
    }
    @Override
    public void setEntregador(Usuario user) {
    }
    @Override
    public void setCliente(Usuario user) {
    }
    @Override
    public String getEstado() {
        return "Cliente";
    }
    
    public void removeItemCarrinho(int i){
        pedido.remove(i);
    }
    public void addItemCarrinho(Item i){
        pedido.add(i);
    }
    public ArrayList<Item> getCarrinho() {
        return pedido;
    }
    public StateCliente setCarrinho(ArrayList<Item> carrinho) {
        this.pedido = carrinho;
        return this;
    }
    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }
    public StateCliente setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
        return this;
    }
}
