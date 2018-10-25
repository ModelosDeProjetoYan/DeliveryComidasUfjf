package Strategy;

public class Carrinho extends Conta {

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
