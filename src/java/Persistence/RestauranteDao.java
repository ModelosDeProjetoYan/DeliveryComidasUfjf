/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Model.Restaurante;
import java.util.ArrayList;

/**
 *
 * @author yan
 */
public class RestauranteDao {
    private static RestauranteDao instance = new RestauranteDao();

    private RestauranteDao() {
    }
    public static RestauranteDao getInstance(){
        return instance;
    }
    public ArrayList<Restaurante> getRestaurantesBanco(){
    
        return null;
    }
}
