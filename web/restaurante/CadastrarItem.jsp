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

<form method="POST" action="MainServlet?parametro=CadastrarItemPost">
    <div class="row">
        <div class="form-group col-4">
            <label for="nome">Selecionar um restaurante</label>
            <select id="id_restaurante" name="id_restaurante" class="custom-select">
                <c:forEach var="restaurante" items="${restaurantes}">
                    <option value="${restaurante.getId()}">${restaurante.getNome()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-4">
            <label for="nome">Nome do Item</label>
            <input type="text" class="form-control" maxlength="100" id="nome" name="nome" placeholder="Digite o nome do item">
        </div>
        <div class="form-group col-4">
            <label for="tipo">Tipo</label>
            <select id="tipo" name="tipo" class="custom-select">
                <option value="Prato">Prato</option>
                <option value="Bebida">Bebida</option>
            </select>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-4">
            <label for="descricao">Descrição</label>
            <input type="text" class="form-control" maxlength="100" id="descricao" name="descricao" placeholder="Digite o tipo do item">
        </div>
        <div class="form-group col-4">
            <label for="preco">Preco</label>
            <input type="text" class="form-control" maxlength="100" id="preco" name="preco" placeholder="Digite o tipo do item">
        </div>
        <div class="form-group col-2">
            <label for="disponivel">Disponível?</label>
            <select id="disponivel" name="disponivel" class="custom-select">
                <option value="Sim">Sim</option>
                <option value="Não">Não</option>
            </select>
        </div>
        <div class="form-group col-2">
            <label for="promocao">Em promoção?</label>
            <select id="promocao" name="promocao" class="custom-select">
                <option value="Não">Não</option>
                <option value="Sim">Sim</option>
            </select>
        </div>
    </div>
    
    <button type="submit" class="btn btn-primary">Cadastrar</button>
</form>

<%@include file="/jspf/rodape.jspf" %>

