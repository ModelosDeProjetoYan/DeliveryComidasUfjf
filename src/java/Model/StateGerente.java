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
public class StateGerente implements StateUsuario{
    //ver o que o gerente vai gerenciar
    private ArrayList<Usuario> usuarios;
    private Restaurante restaurante;
    public StateGerente() {
        usuarios = new ArrayList<>();
    } 
    @Override
    public void setGerente(Usuario user) {
    }

    @Override
    public void setChefeDeCozinha(Usuario user) {
    }

    @Override
    public void setEntregador(Usuario user) {
    }

    @Override
    public void setCliente(Usuario user) {
    }

    @Override
    public String getEstado() {
        return "Gerente";
    }
    
    public void removeUsuario(int i){
        usuarios.remove(i);
    }
}
