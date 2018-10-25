/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainOfResponsability_TemplateMethod;

import java.util.ArrayList;

/**
 *
 * @author yan
 */
public class UsuarioEntregador extends Usuario{

    public UsuarioEntregador() {
    }
    @Override
    String getTipo() {
        return "Entregador";
    }

    @Override
    String acompanhaPedido() {
        return pedido.getStatusPedido();
    }
    
    
}
