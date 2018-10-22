/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainOfResponsability_TemplateMethod;

import State.Pedido;
import java.util.ArrayList;

/**
 *
 * @author yan
 */
public abstract class Usuario {
    protected Pedido pedido;
    protected String nome;
    protected String email;
    protected String senha;
    protected Integer id;
    protected boolean acaoFeita;
    private Usuario proxUsuario;
    
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Usuario getProxUsuario() {
        return proxUsuario;
    }
    public void setProxUsuario(Usuario proxUsuario) {
        this.proxUsuario = proxUsuario;
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
    
    //abstract do template
    abstract String getTipo();
    
    //abstract do chain
    abstract String acompanhaPedido();
    
    //implementar observer para chamar delegar pedido assim que trocar valor de ação feita
    
    public String delegarPedido(Pedido p){
        if(acaoFeita && proxUsuario != null){
            return proxUsuario.delegarPedido(p);
        }else{
            return acompanhaPedido();
        }
    }
    
}
