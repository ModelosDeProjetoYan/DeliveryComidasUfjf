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
public class StateEntregador implements StateUsuario{
    private ArrayList<Endereco> entrega;
    private ArrayList<Item> pedidos;
    private Restaurante restaurante;

    public StateEntregador() {
        entrega= new ArrayList<>();
        pedidos= new ArrayList<>();
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
        return "Entregador";
    }
    
}
