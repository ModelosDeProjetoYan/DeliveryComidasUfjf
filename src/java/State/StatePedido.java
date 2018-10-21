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
public interface StatePedido {
    public void setAberto(Pedido p);
    public void setFeito(Pedido p);
    public void setPreparando(Pedido p);
    public void setPronto(Pedido p);
    public void setEntregando(Pedido p);
    public void setEntregue(Pedido p);
    
    public String getEstado();
}
