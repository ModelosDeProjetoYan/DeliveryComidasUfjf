package Model;
import ChainOfResponsability_TemplateMethod.UsuarioCliente;
import State.*;
import Strategy.Conta;
import Strategy.StrategyPagamento;
import java.util.ArrayList;

public class Carrinho extends Conta {
    private UsuarioCliente cliente;
    private Pedido carrinho;
    public Carrinho() {
    }
    public void addCarrinho(Item i){
        if(carrinho == null)
            carrinho = new Pedido(new StatePedidoAberto());
        carrinho.addItemCarrinho(i);
    }
    public void removeItemCarrinho(Item i){
        carrinho.removeItemCarrinho(i);
    }
    public void limparCarrinho(){
        carrinho.removeAllItensCarrinho();
    }
    public void fecharPedido(){
        carrinho.setFeito();
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
