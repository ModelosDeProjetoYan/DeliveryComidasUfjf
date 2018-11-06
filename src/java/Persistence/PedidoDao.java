package Persistence;

import Memento.HistoricoDeMementos;
import Memento.PedidoMemento;
import Model.ActionFactoryItem;
import Model.Endereco;
import Model.Item;
import State.ActionFactoryState;
import State.Pedido;
import State.StatePedido;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDao {

    private ArrayList<HistoricoDeMementos> mementos = new ArrayList<>();
    private StatePedido action = null;
    private static PedidoDao instance = new PedidoDao();

    private PedidoDao() {
    }
    public static PedidoDao getInstance(){
        return instance;
    }
    
    public void saveEstado(int id, String estado) {

        /* mementos.get(testaSePossuiHistorico(id))
                    .setEstadosSalvos(
                            new PedidoMemento(action = ActionFactoryState.create(estado)));
         */
        mementos.get(testaSePossuiHistorico(id)).setPosicaoEstadosSalvos(
                mementos.get(testaSePossuiHistorico(id)).getPosicaoEstadosSalvos() + 1);
        for (int i = mementos.get(testaSePossuiHistorico(id)).getEstadosSalvos().size() - 1;
                i > mementos.get(testaSePossuiHistorico(id)).getPosicaoEstadosSalvos();
                i--) {
            mementos.get(testaSePossuiHistorico(id)).removeHistoricoAntigo(i);
        }

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
              "SELECT *FROM PEDIDO P\n" +
" INNER JOIN ITEM_PEDIDO IT ON IT.ID_PEDIDO=P.ID\n" +
" INNER JOIN ITEM I ON I.ID=IT.ID_ITEM\n" +
" INNER JOIN ENDERECO_DE_ENTREGA EE ON EE.ID_USUARIO=P.ID_USUARIO\n" +
" WHERE P.ID_USUARIO="+id_Usuario+" AND EE.ID = P.ID_END");
            while (resultado.next()) {
                Pedido p = new Pedido(ActionFactoryState.create(resultado.getString("ESTADO")));
                Item i = ActionFactoryItem.create(resultado.getString("TIPO"));
                Endereco e = new Endereco();
                e.setBairro(resultado.getString("BAIRRO")).
                        setCidade(resultado.getString("CIDADE")).
                        setComplemento(resultado.getString("COMPLEMENTO")).
                        setId(resultado.getInt("id")).
                        setId_usuario(id_Usuario).setNumero(resultado.getInt("NUMERO"));
                        
                p.setId(resultado.getInt("ID"));
                p.addItemCarrinho(i);
                p.setDataPedido(resultado.getDate("DATA_PEDIDO")).
                        setEnderecoEntrega(e);
                if (testaSePossuiHistorico(resultado.getInt("ID")) == mementos.size()) {
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
        public Pedido getPedidoById(int id_Pedido) {
        Connection conn = null;
        Statement st = null;
        ResultSet resultado;
        try {
            conn = DataBaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            resultado = st.executeQuery("");
            while (resultado.next()) {
                Pedido p = new Pedido();
                if (testaSePossuiHistorico(resultado.getInt("ID")) == mementos.size()) {
                    HistoricoDeMementos h = new HistoricoDeMementos(resultado.getInt("ID"));
                    h.setEstadosSalvos(p.saveToMemento());
                    mementos.add(h);
                }
                return null;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
        } finally {
            closeResoucers(conn, st);
        }
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

        }

    }

    public HistoricoDeMementos getMementos(int id_pedido) {
        return mementos.get(testaSePossuiHistorico(id_pedido));
    }

    public int testaSePossuiHistorico(int id) {
        int i = 0;
        while (i < mementos.size() && mementos.get(i).getId() != id) {
            i++;
        }
        return i;
    }

    public void atualizaEstatus(Integer id_pedido, int i) throws ClassNotFoundException, SQLException {
        PedidoMemento p;
        Integer elemento = mementos.get(testaSePossuiHistorico(id_pedido)).getPosicaoEstadosSalvos();
        Connection conn = null;
        Statement st = null;
        if (i == -1) {
            if (elemento != null && elemento > 0) {
                p = mementos.get(testaSePossuiHistorico(id_pedido)).getEstadosSalvos().get(elemento - 1);
                mementos.get(testaSePossuiHistorico(id_pedido)).setPosicaoEstadosSalvos(elemento - 1);
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
                    && elemento < mementos.get(testaSePossuiHistorico(id_pedido)).getEstadosSalvos().size() - 1) {
                p = mementos.get(testaSePossuiHistorico(id_pedido)).getEstadosSalvos().get(elemento + 1);
                mementos.get(testaSePossuiHistorico(id_pedido)).setPosicaoEstadosSalvos(elemento + 1);
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
}
