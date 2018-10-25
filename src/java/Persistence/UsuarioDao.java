package Persistence;

import ChainOfResponsability_TemplateMethod.UsuarioCliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDao {

    private static UsuarioDao instance = new UsuarioDao();

    private UsuarioDao() {
    }

    public static UsuarioDao getInstance() {
        return instance;
    }

    public UsuarioCliente getClienteByID(int id) {
        Connection conn = null;
        Statement st = null;

        return null;
    }

    public void save(UsuarioCliente c) {
        Connection conn = null;
        Statement st = null;

    }

    public ArrayList<UsuarioCliente> getClientesBanco() throws ClassNotFoundException {
        ArrayList clientes = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet resultado;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            resultado = st.executeQuery("select * from USUARIO where id_tipo_usuario = 1");
            while (resultado.next()) {
                UsuarioCliente c = new UsuarioCliente();
                c.setId(resultado.getInt("ID")).
                        setEmail(resultado.getString("EMAIL")).
                        setSenha(resultado.getString("SENHA"));
                clientes.add(c);
            }
        } catch (SQLException e) {
        } finally {
            closeResoucers(conn, st);
        }
        return clientes;
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

        }

    }
}
