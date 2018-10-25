package Model;

public class Prato extends Item {

    public Prato() {
        setTipo("Prato");
    }

    @Override
    String getTipo() {
        return "Prato";
    }

}
