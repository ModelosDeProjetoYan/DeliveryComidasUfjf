package Model;

import ChainOfResponsability_TemplateMethod.UsuarioCliente;
import State.*;
import Strategy.Conta;
import Strategy.StrategyPagamento;
import java.util.ArrayList;

public class Carrinho extends Conta {

    private UsuarioCliente cliente;
    private Pedido pedido;

    public Carrinho() {
        pedido = new Pedido(new StatePedidoAberto());
    }
    
    public Pedido getPedido() {
        return this.pedido;
    }

    public void addCarrinho(Item i) {
        pedido.addItemCarrinho(i);
    }

    public void removeItemCarrinho(Item i) {
        pedido.removeItemCarrinho(i);
    }

    public void limparCarrinho() {
        pedido.removeAllItensCarrinho();
    }

    public void fecharPedido() {
        pedido.setFeito();
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
