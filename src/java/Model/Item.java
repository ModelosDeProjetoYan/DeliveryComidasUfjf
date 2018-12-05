package Model;

public abstract class Item {
    
    protected int id;
    protected int idRestaurante;
    protected double preco;
    protected String descricao;
    protected String nome;
    protected Integer quantidade;
    protected boolean disponivel, promocao;

    public int getIdRestaurante() {
        return idRestaurante;
    }
    public Item setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
        return this;
    }
    public boolean isDisponivel() {
        return disponivel;
    }
    public Item setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
        return this;
    }
    public boolean isPromocao() {
        return promocao;
    }
    public Item setPromocao(boolean promocao) {
        this.promocao = promocao;
        return this;
    }
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
    
    public int getRestaurante() {
        return idRestaurante;
    }

    public Item setRestaurante(int restaurante) {
        this.idRestaurante = restaurante;
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

    public abstract String getTipo();
}
