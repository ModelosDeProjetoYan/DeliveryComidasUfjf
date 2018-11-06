package Model;

public class Endereco {
    private int id;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private int id_usuario;

    public Endereco() {
    }

    public int getId() {
        return id;
    }

    public Endereco setId(int id) {
        this.id = id;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Endereco setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public int getNumero() {
        return numero;
    }

    public Endereco setNumero(int numero) {
        this.numero = numero;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public Endereco setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Endereco setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Endereco setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public Endereco setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
        return this;
    }
    
}
