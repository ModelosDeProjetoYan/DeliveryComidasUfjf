/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainOfResponsability;

import java.util.ArrayList;

/**
 *
 * @author yan
 */
public abstract class Funcionario {

    protected ArrayList listaDocumentos = new ArrayList();
    private Funcionario funcionarioSuperior;

    public ArrayList getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(ArrayList listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public Funcionario getFuncionarioSuperior() {
        return funcionarioSuperior;
    }

    abstract String getDescricaoCargo();

    public void setFuncionarioSuperior(Funcionario funcionarioSuperior) {
        this.funcionarioSuperior = funcionarioSuperior;
    }

    /*public String assinarDocumento(Documento documento) {
        if (listaDocumentos.contains(documento.getTipoDocumento())) {
            return getDescricaoCargo();
        }else{
            if(funcionarioSuperior != null){
                return funcionarioSuperior.assinarDocumento(documento);
            }else{
                return "sem assinar";
            }
        
        }

    }*/

}
