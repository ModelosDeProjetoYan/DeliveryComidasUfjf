/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

/**
 *
 * @author yan
 */
public class StatePedidoEntregando implements StatePedido{

    @Override
    public void setPreparando(Pedido p) {
    }
    @Override
    public void setAberto(Pedido p) {
    }

    @Override
    public void setFeito(Pedido p) {
    }
    @Override
    public void setPronto(Pedido p) {
    }

    @Override
    public void setEntregando(Pedido p) {
    }

    @Override
    public void setEntregue(Pedido p) {
        p.setPedido(new StatePedidoEntregue());
    }

    @Override
    public String getEstado() {
        return "Entregando";
    }

}
