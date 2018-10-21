/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author yan
 */
public interface StateUsuario {
    public void setGerente(Usuario user);
    public void setChefeDeCozinha(Usuario user);
    public void setEntregador(Usuario user);
    public void setCliente(Usuario user);
    public String getEstado();
}
