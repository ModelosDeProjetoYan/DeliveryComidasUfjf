package Persistence;

import Model.Restaurante;
import java.util.ArrayList;

public class RestauranteDao {

    private static RestauranteDao instance = new RestauranteDao();

    private RestauranteDao() {
    }

    public static RestauranteDao getInstance() {
        return instance;
    }

    public ArrayList<Restaurante> getRestaurantesBanco() {

        return null;
    }
    
    public Restaurante insertRestaurante() {
        return new Restaurante();
    }
}
