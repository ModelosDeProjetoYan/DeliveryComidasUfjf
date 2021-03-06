package Memento;

import java.util.ArrayList;

public class HistoricoDeMementos {

    private ArrayList<PedidoMemento> estadosSalvos = new ArrayList<>();
    private Integer posicaoEstadosSalvos = null;
    private Integer id = null;

    public HistoricoDeMementos(int id) {
        this.id = id;
        posicaoEstadosSalvos = 0;
    }

    public void removeHistoricoAntigo(int i) {
        estadosSalvos.remove(i);
    }

    public ArrayList<PedidoMemento> getEstadosSalvos() {
        return estadosSalvos;
    }

    public void setEstadosSalvos(PedidoMemento estadosSalvos) {
        this.estadosSalvos.add(estadosSalvos);
    }

    public int getId() {
        return id;
    }

    public Integer getPosicaoEstadosSalvos() {
        return posicaoEstadosSalvos;
    }

    public void setPosicaoEstadosSalvos(int posicaoEstadosSalvos) {
        this.posicaoEstadosSalvos = posicaoEstadosSalvos;
    }

}
