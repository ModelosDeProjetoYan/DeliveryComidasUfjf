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

    public Restaurante insertRestaurante(int idGerente, String nome, String descricao, String logradouro, String numero, String complemento, String bairro, String cidade, String tipoComida) {
        Restaurante restaurante = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultado;

        if (nome != null && descricao != null 
                && logradouro != null && numero != null 
                && complemento != null && bairro != null 
                && cidade != null && tipoComida != null) {
            try {
                conn = DataBaseLocator.getInstance().getConnection();

                ps = conn.prepareStatement("INSERT INTO RESTAURANTE "
                        + "(nome, descricao, logradouro, numero, complemento, bairro, cidade, tipo_comida, id_usuario) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, nome);
                ps.setString(2, descricao);
                ps.setString(3, logradouro);
                ps.setString(4, numero);
                ps.setString(5, complemento);
                ps.setString(6, bairro);
                ps.setString(7, cidade);
                ps.setString(8, tipoComida);
                ps.setInt(9, idGerente);

                ps.executeUpdate();
                resultado = ps.getGeneratedKeys();

                restaurante = new Restaurante();
                restaurante.setNome(nome)
                        .setDescricao(descricao)
                        .setLogradouro(logradouro)
                        .setNumero(Integer.parseInt(numero))
                        .setComplemento(complemento)
                        .setBairro(bairro)
                        .setCidade(cidade)
                        .setTipoDeComida(tipoComida);
                if (resultado.next()) {
                    restaurante.setId(resultado.getInt(1));
                }
                
                UsuarioDao.getInstance().updateTipoUsuario(idGerente, "Gerente", null);
                UsuarioDao.getInstance().insertFuncionario(restaurante.getId(), idGerente, "gerente");
            } catch (SQLException | ClassNotFoundException e) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                closeResoucers(conn, ps);
            }
        }
        return restaurante;
    }
    
    public ArrayList<Restaurante> selectAllRestaurantesFromUsuarioByIdUsuario(Usuario gerente) {
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DataBaseLocator.getInstance().getConnection();
            
            ps = conn.prepareStatement("SELECT * FROM restaurante "
                        + "WHERE id_usuario = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, gerente.getId());
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
                        .setTipoDeComida(resultado.getString("tipo_comida"))
                        .setGerente(gerente);
                
                restaurante.setItens(ItemDao.getInstance().selectAllItensByIdRestaurante(restaurante.getId()));
                restaurante.setFuncionarios(new ArrayList<>());
                restaurantes.add(restaurante);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResoucers(conn, ps);
        }
        
        return restaurantes;
    }
    
    public ArrayList<Restaurante> selectAllRestaurantes() {
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DataBaseLocator.getInstance().getConnection();
            
            ps = conn.prepareStatement("SELECT * FROM restaurante ", Statement.RETURN_GENERATED_KEYS);
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
            
            ps = conn.prepareStatement("SELECT * FROM restaurante "
                        + "WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
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
    
    
    
}
