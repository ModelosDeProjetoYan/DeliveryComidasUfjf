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
public class StateChefeDeCozinha implements StateUsuario{
    private ArrayList<Item> pedido;
    private Restaurante restaurante;

    public StateChefeDeCozinha() {
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
        return "ChefeDeCozinha";
    }

    public ArrayList<Item> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<Item> pedido) {
        this.pedido = pedido;
    }
    
}
