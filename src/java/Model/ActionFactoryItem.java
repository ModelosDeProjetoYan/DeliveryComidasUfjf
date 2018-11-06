package Model;

import Action.*;
import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.*;

public class ActionFactoryItem {

    public static Item create(String action) {
        Item actionObject = null;
        String nomeClasse = action;
        Class classe = null;
        Object objeto = null;
        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        } catch (Exception e) {
            return null;
        }
        if (!(objeto instanceof Item)) {
            return null;
        }
        actionObject = (Item) objeto;
        return actionObject;
    }

}
