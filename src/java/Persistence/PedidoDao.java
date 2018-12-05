package Persistence;

import ChainOfResponsability_TemplateMethod.Usuario;
import ChainOfResponsability_TemplateMethod.UsuarioCliente;
import Memento.HistoricoDeMementos;
import Memento.PedidoMemento;
import Model.ActionFactoryItem;
import Model.Endereco;
import Model.Item;
import State.Pedido;
import State.StatePedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDao extends Observable {

    private ArrayList<HistoricoDeMementos> mementos = new ArrayList<>();
    private StatePedido action = null;
    private static PedidoDao instance = new PedidoDao();

    private PedidoDao() {
    }

    public static PedidoDao getInstance() {
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

        }

    }

    //instanciar pedido no banco
    public Pedido setPedido(Pedido p, int idUsuario, int idEnd) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultado;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            conn = DataBaseLocator.getInstance().getConnection();
            ps = conn.prepareStatement("INSERT INTO PEDIDO (ESTADO, DATA_PEDIDO, ID_USUARIO,ID_END) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getStatusPedido());
            ps.setString(2, sdf.format(new Date()));
            ps.setInt(3, idUsuario);
            ps.setInt(4, idEnd);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                p.setId(rs.getInt(1));
            }
            if (getPedidoHistoricoId(p.getId()) == 0) {
                HistoricoDeMementos h = new HistoricoDeMementos(p.getId());
                h.setEstadosSalvos(p.saveToMemento());
                mementos.add(h);
            } else {
                if (getPedidoHistoricoId(p.getId()) == mementos.size()) {
                    HistoricoDeMementos h = new HistoricoDeMementos(p.getId());
                    h.setEstadosSalvos(p.saveToMemento());
                    mementos.add(h);
                } else {
                    mementos.
                            get(getPedidoHistoricoId(p.getId())).
                            setEstadosSalvos(p.saveToMemento());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResoucers(conn, ps);
        }
        setChanged();
        notifyObservers(p.getId());
        return p;
    }

    public ArrayList<Pedido> getAllPedidosUsuario(int id_Usuario) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet resultado;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            resultado = st.executeQuery(
                    "SELECT *FROM PEDIDO P\n"
                    + " INNER JOIN ITEM_PEDIDO IT ON IT.ID_PEDIDO=P.ID\n"
                    + " INNER JOIN ITEM I ON I.ID=IT.ID_ITEM\n"
                    + " INNER JOIN ENDERECO_DE_ENTREGA EE ON EE.ID_USUARIO=P.ID_USUARIO\n"
                    + " WHERE P.ID_USUARIO=" + id_Usuario + " AND EE.ID = P.ID_END ORDER BY DATA_PEDIDO DESC");
            while (resultado.next()) {
                Pedido p = new Pedido(ActionFactoryState.create(resultado.getString("ESTADO")));
                Item i = ActionFactoryItem.create(resultado.getString("TIPO"));
                Endereco e = new Endereco();
                e.setBairro(resultado.getString("BAIRRO")).
                        setCidade(resultado.getString("CIDADE")).
                        setComplemento(resultado.getString("COMPLEMENTO")).
                        setId(resultado.getInt("id")).
                        setId_usuario(id_Usuario).
                        setNumero(resultado.getInt("NUMERO"));

                p.setId(resultado.getInt("ID"));
                p.addItemCarrinho(i);
                p.setDataPedido(resultado.getDate("DATA_PEDIDO")).
                        setEnderecoEntrega(e);
                if (getPedidoHistoricoId(resultado.getInt("ID")) == mementos.size()) {
                    HistoricoDeMementos h = new HistoricoDeMementos(resultado.getInt("ID"));
                    h.setEstadosSalvos(p.saveToMemento());
                    mementos.add(h);
                }
                pedidos.add(p);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
        } finally {
            closeResoucers(conn, st);
        }
        return pedidos;
    }

    public ArrayList<Pedido> getAllPedidosRestaurante(int idRestaurante) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        Connection conn2 = null;
        Statement st2 = null;
        ResultSet resultado;
        ResultSet resultado2;

        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            conn2 = DataBaseLocator.getInstance().getConnection();
            st2 = conn.createStatement();
            resultado = st.executeQuery("SELECT *FROM PEDIDO"
                    + " WHERE ID_RESTAURANTE=" + idRestaurante + "");
            while (resultado.next()) {
                Pedido p = new Pedido(ActionFactoryState.create(resultado.getString("ESTADO")));
                p.setId(resultado.getInt("ID"));
                resultado2 = st2.executeQuery("SELECT * FROM ITEM I INNER JOIN ITEM_PEDIDO IT ON IT.ID_ITEM=I.ID WHERE IT.ID_PEDIDO = " + p.getId() + "");
                while (resultado2.next()) {
                    Item i = ActionFactoryItem.create(resultado2.getString("tipo"));
                    i.setId(resultado2.getInt("id_item")).
                            setNome(resultado2.getString("nome")).
                            setDescricao(resultado2.getString("descricao")).
                            setPreco(resultado2.getDouble("preco")).
                            setQuantidade(resultado2.getInt("QUANTIDADE")).
                            setRestaurante(idRestaurante);
                    p.addItemCarrinho(i);
                }
                p.setDataPedido(resultado.getDate("DATA_PEDIDO"));
                if (getPedidoHistoricoId(resultado.getInt("ID")) == mementos.size()) {
                    HistoricoDeMementos h = new HistoricoDeMementos(resultado.getInt("ID"));
                    h.setEstadosSalvos(p.saveToMemento());
                    mementos.add(h);
                }
                pedidos.add(p);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
        } finally {
            closeResoucers(conn, st);
            closeResoucers(conn2, st2);
        }
        return pedidos;
    }

    public Pedido getPedidoById(int id_Pedido) {
        Connection conn = null;
        Statement st = null;
        Connection conn2 = null;
        Statement st2 = null;
        ResultSet resultado;
        ResultSet resultado2;
        Pedido p = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            conn2 = DataBaseLocator.getInstance().getConnection();
            st2 = conn.createStatement();
            resultado = st.executeQuery(
                    "SELECT *FROM PEDIDO P\n"
                    + " WHERE ID=" + id_Pedido + "");
            if (resultado.next()) {
                p = new Pedido(ActionFactoryState.create(resultado.getString("ESTADO")));
                p.setId(resultado.getInt("ID"));
                resultado2 = st2.executeQuery("SELECT * FROM ITEM I"
                        + "INNER JOIN ITEM_PEDIDO IT ON IT.ID_ITEM=I.ID"
                        + "WHERE IT.ID_PEDIDO = " + resultado.getInt("id") + "");
                while (resultado2.next()) {
                    Item i = ActionFactoryItem.create(resultado.getString("TIPO"));
                    i.setId(resultado2.getInt("id")).
                            setNome(resultado2.getString("nome")).
                            setDescricao(resultado2.getString("descricao")).
                            setPreco(resultado2.getDouble("preco")).
                            setQuantidade(resultado2.getInt("QUANTIDADE")).
                            setRestaurante(resultado2.getInt("id_restaurante"));
                    p.addItemCarrinho(i);
                }
                p.setDataPedido(resultado.getDate("DATA_PEDIDO"));
                if (getPedidoHistoricoId(resultado.getInt("ID")) == mementos.size()) {
                    HistoricoDeMementos h = new HistoricoDeMementos(resultado.getInt("ID"));
                    h.setEstadosSalvos(p.saveToMemento());
                    mementos.add(h);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
        } finally {
            closeResoucers(conn, st);
            closeResoucers(conn2, st2);
        }
        return p;
    }

    public void saveEstado(int id, String estado) throws SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.executeUpdate("update PEDIDO set ESTADO = '" + estado
                    + "' where(id = " + id + ")");
            int idPedidoHistorico = getPedidoHistoricoId(id);
            int ultimoEstadoSalvo = mementos.get(idPedidoHistorico).
                    getEstadosSalvos().size() - 1;
            mementos.get(idPedidoHistorico)
                    .setEstadosSalvos(
                            new PedidoMemento(action = ActionFactoryState.create(estado)));
            
            if(ultimoEstadoSalvo > mementos.get(idPedidoHistorico)
                            .getPosicaoEstadosSalvos()){
                while (ultimoEstadoSalvo > mementos.get(idPedidoHistorico)
                            .getPosicaoEstadosSalvos()){
                    mementos.get(idPedidoHistorico).
                            removeHistoricoAntigo(ultimoEstadoSalvo);
                    ultimoEstadoSalvo--; 
                }
                mementos.get(idPedidoHistorico).setPosicaoEstadosSalvos(ultimoEstadoSalvo);            
            }
            
            mementos.get(idPedidoHistorico).setPosicaoEstadosSalvos(
                    mementos.get(idPedidoHistorico)
                            .getPosicaoEstadosSalvos() + 1);

            setChanged();
            notifyObservers(id);
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResoucers(conn, st);
        }
    }

    public HistoricoDeMementos getMementos(int idPedido) {
        int indice = getPedidoHistoricoId(idPedido);
        if (mementos.size() > 0) {
            if (indice == 0
                    && mementos.get(indice)
                            .getId() != idPedido) {
                return null;
            }
            return mementos.get(indice);
        }
        return null;
    }

    public int getPedidoHistoricoId(int id) {
        int i = 0;
        while (i < mementos.size()) {
            if (mementos.get(i).getId() != id) {
                i++;
            } else {
                return i;
            }
        }
        return i;

    }

    public void atualizaEstatus(Integer id_pedido, int i) throws ClassNotFoundException, SQLException {
        PedidoMemento p;
        Integer elemento = mementos.get(getPedidoHistoricoId(id_pedido)).getPosicaoEstadosSalvos();
        Connection conn = null;
        Statement st = null;
        if (i == -1) {
            if (elemento != null && elemento > 0) {
                p = mementos.get(getPedidoHistoricoId(id_pedido))
                        .getEstadosSalvos().get(elemento - 1);

                mementos.get(getPedidoHistoricoId(id_pedido))
                        .setPosicaoEstadosSalvos(elemento - 1);
                try {
                    conn = DataBaseLocator.getInstance().getConnection();
                    st = conn.createStatement();
                    st.executeUpdate("update PEDIDO set ESTADO = '" + p.toString()
                            + "' where(id = " + id_pedido + ")");

                } catch (SQLException e) {
                    throw e;
                } finally {
                    closeResoucers(conn, st);
                }
            }
        } else if (i == 1) {
            if (elemento != null && elemento >= 0
                    && elemento < mementos.get(getPedidoHistoricoId(id_pedido)).
                            getEstadosSalvos().size() - 1) {

                p = mementos.get(getPedidoHistoricoId(id_pedido))
                        .getEstadosSalvos().get(elemento + 1);

                mementos.get(getPedidoHistoricoId(id_pedido)).
                        setPosicaoEstadosSalvos(elemento + 1);
                try {
                    conn = DataBaseLocator.getInstance().getConnection();
                    st = conn.createStatement();
                    st.executeUpdate("update PEDIDO set ESTADO = '" + p.toString()
                            + "' where(id = " + id_pedido + ")");
                } catch (SQLException e) {
                    throw e;
                } finally {
                    closeResoucers(conn, st);
                }
            }
        }
    }

    public void setRestaurantePedido(int idRestaurante, int idPedido) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            ps = conn.prepareStatement("UPDATE PEDIDO SET ID_RESTAURANTE= ?"
                    + "WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idRestaurante);
            ps.setInt(2, idPedido);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResoucers(conn, ps);
        }
    }
}
