package Model;

public abstract class Item {

    protected Restaurante restaurante;
    protected double valor;
    protected String descricao;

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Item setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
        return this;
    }


    public double getValor() {
        return valor;
    }

    public Item setValor(double valor) {
        this.valor = valor;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Item setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    abstract String getTipo();

}
