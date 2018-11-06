package Persistence;

import ChainOfResponsability_TemplateMethod.Usuario;
import ChainOfResponsability_TemplateMethod.UsuarioCliente;
import ChainOfResponsability_TemplateMethod.UsuarioGerente;
import Model.Restaurante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

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

    public Restaurante insertRestaurante(Usuario gerente, String nome, String logradouro, String numero, String complemento, String bairro, String cidade, String tipoComida) {
        Restaurante restaurante = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultado;

        if (nome != null && logradouro != null
                && numero != null && complemento != null
                && bairro != null && cidade != null
                && tipoComida != null) {
            try {
                conn = DataBaseLocator.getInstance().getConnection();

                ps = conn.prepareStatement("INSERT INTO RESTAURANTE "
                        + "(nome, logradouro, numero, complemento, bairro, cidade, tipo_comida, id_usuario) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, nome);
                ps.setString(2, logradouro);
                ps.setString(3, numero);
                ps.setString(4, complemento);
                ps.setString(5, bairro);
                ps.setString(6, cidade);
                ps.setString(7, tipoComida);
                ps.setInt(8, gerente.getId());

                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                restaurante = new Restaurante();
                restaurante.setNome(nome)
                        .setLogradouro(logradouro)
                        .setNumero(Integer.parseInt(numero))
                        .setComplemento(complemento)
                        .setBairro(bairro)
                        .setCidade(cidade)
                        .setTipoDeComida(tipoComida);
                if (rs.next()) {
                    restaurante.setId(rs.getInt(1));
                }
            } catch (SQLException | ClassNotFoundException e) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                closeResoucers(conn, ps);
            }
        }
        return restaurante;
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
}
