package Model;

public abstract class Item {
    
    protected int id;
    protected Restaurante restaurante;
    protected double preco;
    protected String descricao;
    protected String nome;
    protected Integer quantidade;
    
    public int getId() {
        return id;
    }

    public Item setId(int id) {
        this.id = id;
        return this;
    }
    
    public Integer getQuantidade() {
        return quantidade;
    }

    public Item setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

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

    public double getPreco() {
        return preco;
    }

    public Item setPreco(double preco) {
        this.preco = preco;
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
