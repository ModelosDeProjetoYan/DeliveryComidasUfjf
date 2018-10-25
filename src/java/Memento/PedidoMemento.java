package Memento;

import State.StatePedido;

public class PedidoMemento {

    private StatePedido estado;

    public PedidoMemento(StatePedido estado) {
        this.estado = estado;
    }

    public StatePedido getEstadoSalvo() {
        return estado;
    }

    public String toString() {
        return estado.getEstado();
    }

}
