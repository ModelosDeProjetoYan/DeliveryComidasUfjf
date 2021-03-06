package Persistence;

import ChainOfResponsability_TemplateMethod.Usuario;
import ChainOfResponsability_TemplateMethod.UsuarioCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDao {

    private static UsuarioDao instance = new UsuarioDao();

    private UsuarioDao() {

    }

    public static UsuarioDao getInstance() {
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

    public Usuario insertUsuarioCliente(String nome, String email, String senha) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultado;
        Usuario u = null;

        try {
            conn = DataBaseLocator.getInstance().getConnection();

            ps = conn.prepareStatement("INSERT INTO USUARIO (nome, email, senha, tipo_user) VALUES(?, ?, ?, 'Cliente')", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, senha);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            u = new UsuarioCliente();
            u.setNome(nome).setEmail(email).setSenha(senha);
            if (rs.next()) {
                u.setId(rs.getInt(1));
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResoucers(conn, ps);
        }
        return u;
    }

    public Usuario getUsuarioByID(int id) {
        return getUsuarioBanco("select * from USUARIO where ID= " + id + "");
    }
    public Usuario getUsuario(String email, String senha) {
        return getUsuarioBanco("select * from USUARIO where EMAIL='"
                + email + "' and SENHA='" + senha + "'");
    }
    private Usuario getUsuarioBanco(String query) {
        Connection conn = null;
        Statement st = null;
        ResultSet resultado;
        Usuario u = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            resultado = st.executeQuery(query);
            while (resultado.next()) {
                u = Action.ActionFactoryCadastroFuncionario.create(resultado.getString("TIPO_USER"));
                u.setId(resultado.getInt("ID")).
                        setEmail(resultado.getString("EMAIL")).
                        setSenha(resultado.getString("SENHA")).
                        setNome(resultado.getString("NOME"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResoucers(conn, st);
        }
        return u;
    }

    public ArrayList<Usuario> selectAllFuncionariosByIdRestaurante(Integer idRestaurante) {
        return getUsuarios("SELECT * FROM usuario AS u "
                    + " INNER JOIN funcionario AS f ON"
                + " u.id = f.id_usuario AND f.id_restaurante = ?");
    }    
    public ArrayList<Usuario> selectAllUsuarios() {
        return getUsuarios("SELECT * FROM usuario");
    }
    private ArrayList<Usuario> getUsuarios(String query) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();

            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        //    ps.setInt(1, idRestaurante);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                String tipoUsuarioRestaurante = resultado.getString("TIPO_USER_RESTAURANTE");
                Usuario usuario = Action.ActionFactoryCadastroFuncionario.create(tipoUsuarioRestaurante);
                usuario.setId(resultado.getInt("id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setTipoUsuario(resultado.getString("TIPO_USER_RESTAURANTE"));
                usuario.setSenha(resultado.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResoucers(conn, ps);
        }

        return usuarios;
    }

    public Boolean updateTipoUsuario(int idUsuario, String tipoUsuario, Integer idNextFuncionario) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql;
        if (idNextFuncionario != null) {
            sql = "UPDATE usuario SET ID_PROX= ?"
                    + "WHERE id = ?";
        } else {
            return false;
        }
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idNextFuncionario);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResoucers(conn, ps);
        }
        return true;
    }

    public String getTipoFuncionario(int idRestaurante, int idUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultado;
        String tipoFuncionario = "";
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            ps = conn.prepareStatement("select tipo_user_restaurante from funcionario"
                    + " where (id_restaurante = ? and id_usuario = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idRestaurante);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
            resultado = ps.getGeneratedKeys();
            if(resultado.next()){
                tipoFuncionario = resultado.getString("tipo_user_restaurante");
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResoucers(conn, ps);
        }

        return tipoFuncionario;
    }

    public Boolean insertFuncionario(int idRestaurante, int idUsuario, String tipoUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultado;

        try {
            conn = DataBaseLocator.getInstance().getConnection();

            ps = conn.prepareStatement("INSERT INTO funcionario (id_restaurante,"
                    + " id_usuario, tipo_user_restaurante) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idRestaurante);
            ps.setInt(2, idUsuario);
            ps.setString(3, tipoUsuario);

            ps.executeUpdate();
            resultado = ps.getGeneratedKeys();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResoucers(conn, ps);
        }

        return true;
    }


}
