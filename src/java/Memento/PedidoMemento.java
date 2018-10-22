/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

import State.StatePedido;

/**
 *
 * @author yan
 */
public class PedidoMemento {
    private StatePedido estado;

    public PedidoMemento(StatePedido estado) {
        this.estado = estado;
    }

    public StatePedido getEstadoSalvo() {
        return estado;
    }
    public String toString(){
        return estado.getEstado();
    }
    
}
