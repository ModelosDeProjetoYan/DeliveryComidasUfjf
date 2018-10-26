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
public class ItemCombo extends Item{
    private ArrayList<Item> combo = new ArrayList<>();
    public ItemCombo() {
        
    }
    public void addItem(Item i){
        combo.add(i);
    }
    @Override
    public String getTipo() {
        return "Combo";
    }
    
}
