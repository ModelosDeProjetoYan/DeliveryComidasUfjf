/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

/**
 *
 * @author yan
 */
public abstract class Conta {
    protected Double valor;
    protected StrategyPagamento pagamento;

    public Double getValor() {
        return valor;
    }

    public StrategyPagamento getPagamento() {
        return pagamento;
    }    
}
