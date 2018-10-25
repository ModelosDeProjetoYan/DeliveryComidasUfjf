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
public class Bebida extends Item {

    public Bebida() {
        setTipo("Bebida");
    }    
    @Override
    String getTipo() {
        return "Bebida";
    }
}
