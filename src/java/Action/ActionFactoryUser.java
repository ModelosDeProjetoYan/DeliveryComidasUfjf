/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import ChainOfResponsability_TemplateMethod.*;

/**
 *
 * @author YanNotebook
 */
public class ActionFactoryUser {
    public static Usuario create(String action){
        Usuario actionObject = null;
        String nomeClasse = "ChainOfResponsability_TemplateMethod.Usuario"+action;
        Class classe = null;
        Object objeto = null;
        try {
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();
        } catch (Exception e) {
            return null;
        }
        if(!(objeto instanceof Usuario)) return null;
        actionObject = (Usuario) objeto;
        return actionObject;
    }
    
}
