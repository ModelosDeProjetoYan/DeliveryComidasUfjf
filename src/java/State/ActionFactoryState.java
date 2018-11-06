package State;

import Action.*;
import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.*;

public class ActionFactoryState {

    public static StatePedido create(String action) {
        StatePedido actionObject = null;
        String nomeClasse = "StatePedido" + action;
        Class classe = null;
        Object objeto = null;
        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        } catch (Exception e) {
            return null;
        }
        if (!(objeto instanceof StatePedido)) {
            return null;
        }
        actionObject = (StatePedido) objeto;
        return actionObject;
    }

}
