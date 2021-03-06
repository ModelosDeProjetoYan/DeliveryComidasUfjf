package Persistence;

import ChainOfResponsability_TemplateMethod.Usuario;
import ChainOfResponsability_TemplateMethod.UsuarioCliente;
import ChainOfResponsability_TemplateMethod.UsuarioGerente;
import Memento.PedidoMemento;
import Model.Restaurante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
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

    public boolean insertRestaurante(Restaurante restaurante) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultado;

        if (restaurante != null) {
            try {
                conn = DataBaseLocator.getInstance().getConnection();

                ps = conn.prepareStatement("INSERT INTO RESTAURANTE "
                        + "(nome, descricao, logradouro, numero, complemento, bairro, cidade, tipo_comida, id_usuario) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, restaurante.getNome());
                ps.setString(2, restaurante.getDescricao());
                ps.setString(3, restaurante.getLogradouro());
                ps.setInt(4, restaurante.getNumero());
                ps.setString(5, restaurante.getComplemento());
                ps.setString(6, restaurante.getBairro());
                ps.setString(7, restaurante.getCidade());
                ps.setString(8, restaurante.getTipoDeComida());
                ps.setInt(9, restaurante.getGerente().getId());

                ps.executeUpdate();
                resultado = ps.getGeneratedKeys();

                if (resultado.next()) {
                    restaurante.setId(resultado.getInt(1));
                }
                
                UsuarioDao.getInstance().updateTipoUsuario(restaurante.getGerente().getId(), "Gerente", null);
                UsuarioDao.getInstance().insertFuncionario(restaurante.getId(), 
                        restaurante.getGerente().getId(), "Gerente");
            } catch (SQLException | ClassNotFoundException e) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
                return false;
            } finally {
                closeResoucers(conn, ps);
            }
        }
        return true;
    }
    
    public ArrayList<Restaurante> selectAllRestaurantesFromUsuarioByIdUsuario(Usuario gerente) {
        ArrayList<Restaurante> restaurantes = getRestauranteBanco("SELECT * FROM restaurante "
                    + "WHERE id_usuario = "+gerente.getId());
        for (int i = restaurantes.size()-1;i>=0;i--) {
            restaurantes.get(i).setGerente(gerente);
        }
        
        return restaurantes;            
    }
    
    public ArrayList<Restaurante> selectAllRestaurantes() {
        return getRestauranteBanco("SELECT * FROM restaurante");
    }
    
    private ArrayList<Restaurante> getRestauranteBanco(String query) {
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();          
            ps = conn.prepareStatement(query);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Restaurante restaurante = new Restaurante();
                restaurante.setId(resultado.getInt("id"))
                        .setNome(resultado.getString("nome"))
                        .setDescricao(resultado.getString("descricao"))
                        .setLogradouro(resultado.getString("logradouro"))
                        .setNumero(resultado.getInt("numero"))
                        .setComplemento(resultado.getString("complemento"))
                        .setBairro(resultado.getString("bairro"))
                        .setCidade(resultado.getString("cidade"))
                        .setTipoDeComida(resultado.getString("tipo_comida"));
                restaurante.setItens(ItemDao.getInstance().selectAllItensByIdRestaurante(restaurante.getId()));
                restaurante.setFuncionarios(UsuarioDao.getInstance().selectAllFuncionariosByIdRestaurante(restaurante.getId()));
                restaurantes.add(restaurante);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResoucers(conn, ps);
        }
        return restaurantes;
    }
    
    
    public int getIdGerente(int idRestaurante){
        int idGerente = -1;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DataBaseLocator.getInstance().getConnection();
            
            ps = conn.prepareStatement("SELECT * FROM restaurante WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idRestaurante);
            ResultSet resultado = ps.executeQuery();

            if (resultado.next()) {
               idGerente = resultado.getInt("id_usuario");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResoucers(conn, ps);
        }
        
        return idGerente;
    }
    
    public String getCargoRestaurante(int idRestaurante, int idUsuario){
        String cargo = "";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            ps = conn.prepareStatement("SELECT * FROM funcionario where id_restaurante = ? and id_usuario = ? ", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idRestaurante);
            ps.setInt(2, idUsuario);
            ResultSet resultado = ps.executeQuery();
            if (resultado.next()) {
                cargo = resultado.getString("tipo_user_restaurante");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResoucers(conn, ps);
        }        
        return cargo;
    }
    
    public Integer selectCountNumberOfRestaurantes(Integer idUsuario){
        Connection conn = null;
        PreparedStatement ps = null;
        Integer numero = null;
        
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            ps = conn.prepareStatement("SELECT COUNT(*) AS contador FROM restaurante WHERE id_usuario = ? ", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, (int) idUsuario);
            ResultSet resultado = ps.executeQuery();
            if (resultado.next()) {
                numero = resultado.getInt("contador");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResoucers(conn, ps);
        }        
        return numero;
    }
    
}
