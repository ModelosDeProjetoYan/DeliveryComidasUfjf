package ChainOfResponsability_TemplateMethod;

import Model.Restaurante;
import State.Pedido;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class Usuario implements Observer {

    protected Pedido pedido;
    protected String nome;
    protected String email;
    protected String senha;
    protected Integer id;
    protected Restaurante restaurante;
    protected Boolean acaoFeita;
    private Usuario proxUsuario;

    public String mensagemUsuario(){
        return "Seja bem vindo " + getTipo() + " " + getNome();
    }
    
    public Pedido getPedido() {
        return pedido;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Usuario setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
        return this;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario getProxUsuario() {
        return proxUsuario;
    }

    public Usuario setProxUsuario(Usuario proxUsuario) {
        this.proxUsuario = proxUsuario;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public int getId() {
        return id;
    }

    public Usuario setId(int id) {
        this.id = id;
        return this;
    }

    public Usuario setAcaoFeita(boolean acaoFeita) {
        this.acaoFeita = acaoFeita;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Usuario setObservable(Pedido p) {
        p.addObserver(this);
        return this;
    }

    //abstract do template
    public abstract String getTipo();
    
    
    //abstract do chain
    abstract String acompanhaPedido();

    //implementar observer para chamar delegar pedido assim que trocar valor de ação feita
    public String delegarPedido(Pedido p) {
        if (acaoFeita && proxUsuario != null) {
            proxUsuario.setObservable(p);
            return proxUsuario.delegarPedido(p);
        } else if (this instanceof UsuarioCliente && !acaoFeita) {
            p.deleteObservers();
            return acompanhaPedido();
        } else {
            return acompanhaPedido();
        }
    }

    @Override
    public void update(Observable pedido, Object arg) {
        if (pedido instanceof Pedido) {
            Pedido p = (Pedido) pedido;
            delegarPedido(p);
        }
    }

}
