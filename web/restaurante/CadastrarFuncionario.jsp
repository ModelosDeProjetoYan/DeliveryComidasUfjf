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

<form method="POST" action="MainServlet?parametro=CadastrarFuncionarioPost">
    <div class="row">
        <div class="form-group col-4">
            <label for="nome">Selecionar um restaurante</label>
            <select class="custom-select">
                <c:forEach var="restaurante" items="${restaurantes}">
                    <option value="${restaurante.getId()}">${restaurante.getNome()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-4">
            <label for="nome">Selecionar um usuário</label>
            <select class="custom-select">
                <c:forEach var="usuario" items="${usuarios}">
                    <option value="${usuario.getId()}">${usuario.getNome()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-4">
            <label for="nome">Selecionar um cargo</label>
            <select class="custom-select">
                <option value="Cliente">Entregador</option>
                <option value="ChefeDeCozinha">Chefe de Cozinha</option>
                <option value="Gerente">Gerente</option>
            </select>
        </div>
    </div>
</form>

<%@include file="/jspf/rodape.jspf" %>

