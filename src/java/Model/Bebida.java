package Model;

public class Bebida extends Item {

    public Bebida() {
        setTipo("Bebida");
    }

    @Override
    String getTipo() {
        return "Bebida";
    }
}
