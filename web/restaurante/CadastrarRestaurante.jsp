<%@page 
    import="java.util.Date" 
    errorPage="../pagina-de-erro.jsp" 
    isErrorPage="false" 
    contentType="text/html" 
    pageEncoding="UTF-8" %>

<!--The core group of tags are the most commonly used JSTL tags.-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--The JSTL formatting tags are used to format and display text, the date, the time, and numbers for internationalized Websites.-->
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="/jspf/cabecalho.jspf" %>

<form method="POST" action="MainServlet?parametro=CadastrarRestaurantePost">
    <div class="row">
        <div class="form-group col-12">
            <label for="nome">Nome do Restaurante</label>
            <input type="text" class="form-control" maxlength="100" id="nome" name="nome" placeholder="Digite o nome de seu restaurante">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-6">
            <label for="logradouro">Logradouro</label>
            <input type="text" class="form-control" maxlength="100" id="logradouro" name="logradouro" placeholder="Digite o logradouro">
        </div>
        <div class="form-group col-2">
            <label for="numero">Número</label>
            <input type="text" class="form-control" maxlength="100" id="numero" name="numero" placeholder="Ex.: 99">
        </div>
        <div class="form-group col-4">
            <label for="complemento">Complemento</label>
            <input type="text" class="form-control" maxlength="100" id="complemento" name="complemento" placeholder="Digite o complemento">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-6">
            <label for="bairro">Bairro</label>
            <input type="text" class="form-control" maxlength="100" id="bairro" name="bairro" placeholder="Digite o bairro">
        </div>
        <div class="form-group col-6">
            <label for="cidade">Cidade</label>
            <input type="text" class="form-control" maxlength="100" id="cidade" name="cidade" placeholder="Digite a cidade">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-6">
            <label for="tipo_comida">Tipo de Comida</label>
            <input type="text" class="form-control" maxlength="100" id="tipo_comida" name="tipo_comida" placeholder="Digite o tipo da comida">
        </div>
    </div>

    <button type="submit" class="btn btn-primary">Cadastrar</button>
</form>

<%@include file="/jspf/rodape.jspf" %>

