package Persistence;

import Memento.HistoricoDeMementos;
import Model.ActionFactoryItem;
import Model.Endereco;
import Model.Item;
import State.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnderecoEntregaDao {

    private static EnderecoEntregaDao instance = new EnderecoEntregaDao();

    private EnderecoEntregaDao() {
    }

    public static EnderecoEntregaDao getInstance() {
        return instance;
    }

    public ArrayList<Endereco> getEnderecoUsuario(int idUsuario) {
        ArrayList<Endereco> enderecos = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet resultado;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            resultado = st.executeQuery("select * from ENDERECO_DE_ENTREGA where id_usuario=" + idUsuario + "");
            while (resultado.next()) {
                Endereco e = new Endereco();
                e.setId(resultado.getInt("ID")).
                        setBairro(resultado.getString("BAIRRO")).
                        setCidade(resultado.getString("CIDADE")).
                        setComplemento(resultado.getString("COMPLEMENTO")).
                        setId_usuario(idUsuario).
                        setNumero(resultado.getInt("NUMERO")).
                        setLogradouro(resultado.getString("LOGRADOURO"));
                enderecos.add(e);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
        } finally {
            closeResoucers(conn, st);
        }
        return enderecos;
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

    public Endereco insertEndereco(Endereco endereco) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultado;
        Endereco end = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            ps = conn.prepareStatement("INSERT INTO ENDERECO_DE_ENTREGA (id_usuario,bairro, cidade, "
                    + "complemento, numero, logradouro)"
                    + " VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, endereco.getId_usuario());
            ps.setString(2, endereco.getBairro());
            ps.setString(3, endereco.getCidade());
            ps.setString(4, endereco.getComplemento());
            ps.setInt(5, endereco.getNumero());
            ps.setString(6, endereco.getLogradouro());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                end.setId(rs.getInt(1));
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResoucers(conn, ps);
        }
        return endereco;
    }

}
