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
public class Cardapio {
    private Restaurante restaurante;
    private ArrayList<Item> cardapio;

    public Cardapio() {
        cardapio = new ArrayList<>();
    }

    public Cardapio(Restaurante restaurante) {
        this.restaurante = restaurante;
        cardapio = new ArrayList<>();
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Cardapio setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
        return this;
    }
    
    
    public void addItemCardapio(Item i){
        cardapio.add(i);
    }
    public void removeItemCardapio(Item i){
        cardapio.remove(i);
    }

    public ArrayList<Item> getCardapio() {
        return cardapio;
    }
    
}
