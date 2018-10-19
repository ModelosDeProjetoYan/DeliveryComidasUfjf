/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

/**
 *  com BUILDER
 * @author yan
 */
public class Carrinho extends Conta{

    public Carrinho() {
    }

    public Carrinho setValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public Carrinho setPagamento(StrategyPagamento pagamento) {
        this.pagamento = pagamento;
        return this;
    }
    
}
