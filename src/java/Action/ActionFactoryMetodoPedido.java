package Action;

import ChainOfResponsability_TemplateMethod.Usuario;
import Controller.*;
import State.Pedido;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActionFactoryMetodoPedido {

    public static Pedido create(String action, Pedido p) {
        try {
            Class classe = Class.forName("State.Pedido");
            Object objeto = p;
            Method metodo = classe.getMethod("set"+action);
            metodo.invoke(objeto);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AtualizarEstadoPedidoPostAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AtualizarEstadoPedidoPostAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(AtualizarEstadoPedidoPostAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AtualizarEstadoPedidoPostAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AtualizarEstadoPedidoPostAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AtualizarEstadoPedidoPostAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
