package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.*;

public class ActionFactoryCadastroFuncionario {

    public static Usuario create(String action) {
        Usuario actionObject = null;
        String nomeClasse = "ChainOfResponsability_TemplateMethod.Usuario" + action;
        Class classe = null;
        Object objeto = null;
        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        } catch (Exception e) {
            return null;
        }
        if (!(objeto instanceof Usuario)) {
            return null;
        }
        actionObject = (Usuario) objeto;
        return actionObject;
    }

}
