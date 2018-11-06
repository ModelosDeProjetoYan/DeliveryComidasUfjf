package Persistence;

import Model.Item;
import java.util.ArrayList;

public class EnderecoEntregaDao {

    private static EnderecoEntregaDao instance = new EnderecoEntregaDao();

    private EnderecoEntregaDao() {
    }

    public static EnderecoEntregaDao getInstance() {
        return instance;
    }

    public ArrayList<Item> getItensDoRestauranteBanco(int idRestaurante) {

        return null;
    }
}
