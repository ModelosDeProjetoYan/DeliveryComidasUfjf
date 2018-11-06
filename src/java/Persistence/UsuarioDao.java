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
    
    public Usuario insertUsuarioCliente(String nome, String email, String senha){
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
            if(rs.next()){
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
        Connection conn = null;
        Statement st = null;
        ResultSet resultado;
        Usuario u = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            resultado = st.executeQuery("select * from USUARIO where ID= "+id+"");
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
    public void save(UsuarioCliente c) {
        Connection conn = null;
        Statement st = null;

    }

/*    public ArrayList<Usuario> getAllUsuarioBanco() throws ClassNotFoundException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet resultado;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            resultado = st.executeQuery("select * from USUARIO where TIPO_USER ='"+getTipo()+"' ");
            while (resultado.next()) {
                Usuario u = Action.ActionFactoryCadastroFuncionario.create(getTipo());
                u.setId(resultado.getInt("ID")).
                        setEmail(resultado.getString("EMAIL")).
                        setSenha(resultado.getString("SENHA")).
                        setNome(resultado.getString("NOME"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
        } finally {
            closeResoucers(conn, st);
        }
        return usuarios;
    }
  */  
    public Usuario getUsuario(String email, String senha){
        Connection conn = null;
        Statement st = null;
        ResultSet resultado;
        Usuario u = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            resultado = st.executeQuery("select * from USUARIO where EMAIL='"+email+"' and SENHA='"+senha+"'");
            if (resultado.next()) {
                u = Action.ActionFactoryCadastroFuncionario.create(resultado.getString("TIPO_USER"));
                u.setId(resultado.getInt("ID")).
                        setEmail(resultado.getString("EMAIL")).
                        setSenha(resultado.getString("SENHA")).
                        setNome(resultado.getString("NOME"));
            }
        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResoucers(conn, st);
        }
        return u;
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
