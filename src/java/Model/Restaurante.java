package Model;

import ChainOfResponsability_TemplateMethod.Usuario;
import java.util.ArrayList;

public class Restaurante {
    private int id;
    private String nome;
    private String descricao;
    private String endereco;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String tipoDeComida;
    private Usuario gerente;
    private ArrayList<Item> itens;
    private ArrayList<Usuario> funcionarios;

    public Restaurante() {
    }

    public int getId() {
        return id;
    }

    public Restaurante setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Restaurante setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Restaurante setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public Restaurante setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Restaurante setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public int getNumero() {
        return numero;
    }

    public Restaurante setNumero(int numero) {
        this.numero = numero;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public Restaurante setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Restaurante setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Restaurante setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getTipoDeComida() {
        return tipoDeComida;
    }

    public Restaurante setTipoDeComida(String tipoDeComida) {
        this.tipoDeComida = tipoDeComida;
        return this;
    }

    public Usuario getGerente() {
        return gerente;
    }

    public Restaurante setGerente(Usuario gerente) {
        this.gerente = gerente;
        return this;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public Restaurante setItens(ArrayList<Item> itens) {
        this.itens = itens;
        return this;
    }

    public ArrayList<Usuario> getFuncionarios() {
        return funcionarios;
    }

    public Restaurante setFuncionarios(ArrayList<Usuario> funcionarios) {
        this.funcionarios = funcionarios;
        return this;
    }
}
