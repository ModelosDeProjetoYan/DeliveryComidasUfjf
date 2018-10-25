package Strategy;

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
