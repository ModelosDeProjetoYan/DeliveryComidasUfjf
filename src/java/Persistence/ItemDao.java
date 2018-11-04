package Persistence;

import Model.Item;
import java.util.ArrayList;

public class ItemDao {

    private static ItemDao instance = new ItemDao();

    private ItemDao() {
    }

    public static ItemDao getInstance() {
        return instance;
    }

    public ArrayList<Item> getItensDoRestauranteBanco(int idRestaurante) {

        return null;
    }
}
