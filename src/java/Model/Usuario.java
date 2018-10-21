/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author yan
 */
public class Usuario {
    private StateUsuario user;
    private String nome;
    private int id;
    private String tipo;
    
    
    public Usuario(StateUsuario user) {        
        setTipo();
    }

    public void setChefeDeCozinha(){
        user.setChefeDeCozinha(this);
        setTipo();
    }
    public void setCliente(){
        user.setCliente(this);
        setTipo();
    }
    public void setEntregador(){
        user.setEntregador(this);
        setTipo();
    }
    public void setGerente(){
        user.setGerente(this);
        setTipo();
    }
    
    
    public StateUsuario getUser() {
        return user;
    }
    public Usuario setUser(StateUsuario user) {
        this.user = user;
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
    public String getTipo() {
        return tipo;
    }
    private void setTipo() {
        this.tipo = user.getEstado();
    }  
    
}
