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
            <label for="id_restaurante">Selecionar um restaurante</label>
            <select id="id_restaurante" name="id_restaurante" class="custom-select">
                <c:forEach var="restaurante" items="${restaurantes}">
                    <option value="${restaurante.getId()}">${restaurante.getNome()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-4">
            <label for="id_usuario_chefe">Escolher um Chefe de Cozinha</label>
            <select id="id_usuario_chefe" name="id_usuario_chefe" class="custom-select">
                <c:forEach var="usuario" items="${usuarios}">
                    <option value="${usuario.getId()}">${usuario.getNome()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group col-4">
            <label for="id_usuario_entregador">Escolher um </label>
            <select id="id_usuario_entregador" name="id_usuario_entregador" class="custom-select">
                <c:forEach var="usuario" items="${usuarios}">
                    <option value="${usuario.getId()}">${usuario.getNome()}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    
    <button type="submit" class="btn btn-primary">Cadastrar</button>
</form>

<%@include file="/jspf/rodape.jspf" %>

