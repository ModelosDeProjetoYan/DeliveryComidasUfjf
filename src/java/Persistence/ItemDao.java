package Persistence;

import Model.Item;
import Model.Restaurante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private void closeResoucers(Connection conn, Statement st) {
        try {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public Boolean insertItem(String nome, String tipo, String descricao, Double preco, Integer disponivel, Integer promocao, Integer idRestaurante) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultado;

        if (idRestaurante != null
                && nome != null && tipo != null
                && descricao != null && preco != null
                && disponivel != null && promocao != null) {
            try {
                conn = DataBaseLocator.getInstance().getConnection();

                ps = conn.prepareStatement("INSERT INTO item "
                        + "(nome, tipo, descricao, preco, disponivel, promocao, id_restaurante) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, nome);
                ps.setString(2, tipo);
                ps.setString(3, descricao);
                ps.setDouble(4, preco);
                ps.setInt(5, disponivel);
                ps.setInt(6, promocao);
                ps.setInt(7, idRestaurante);

                ps.executeUpdate();
                resultado = ps.getGeneratedKeys();
            } catch (SQLException | ClassNotFoundException e) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                closeResoucers(conn, ps);
            }
        }
        return true;
    }
}
