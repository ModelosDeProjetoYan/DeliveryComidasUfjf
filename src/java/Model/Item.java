package Model;

public abstract class Item {

    protected Restaurante restaurante;
    protected double valor;
    protected String descricao;
    protected String nome;

    public String getNome() {
        return nome;
    }

    public Item setNome(String nome) {
        this.nome = nome;
        return this;
    }
    
    
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
