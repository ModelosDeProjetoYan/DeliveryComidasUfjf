<%@page 
    import="Model.Restaurante, Model.Item, java.util.ArrayList" 
    errorPage="../pagina-de-erro.jsp" 
    isErrorPage="false" 
    contentType="text/html" 
    pageEncoding="UTF-8" %>

<!--The core group of tags are the most commonly used JSTL tags.-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--The JSTL formatting tags are used to format and display text, the date, the time, and numbers for internationalized Websites.-->
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="/jspf/cabecalho.jspf" %>

<c:forEach var="restaurante" items="${restaurantes}">
    <form method="POST" action="MainServlet?parametro=CadastrarItemComboPost">
        <h3>${restaurante.getNome()}</h3>
        <div class="row">
            <div class="form-group col-4">
                <label for="id_item_${contador_restaurante}_${contador}">Selecionar um item</label>
                <select id="id_item_${contador_restaurante}_${contador}" name="id_item_${contador_restaurante}_${contador}" class="custom-select">
                    <option value="-">-</option>
                    <c:forEach var="item" items="${restaurante.getItens()}">
                        <option value="${item.getId()}">${item.getNome()} / ${item.getPreco()}</option>
                    </c:forEach>
                </select>
            </div>
            <% if (request.getAttribute("contador") != null) {request.setAttribute("contador", (Integer) request.getAttribute("contador") + 1);} %>
            <div class="form-group col-4">
                <label for="id_item_${contador_restaurante}_${contador}">Selecionar um item</label>
                <select id="id_item_${contador_restaurante}_${contador}" name="id_item_${contador_restaurante}_${contador}" class="custom-select">
                    <option value="-">-</option>
                    <c:forEach var="item" items="${restaurante.getItens()}">
                        <option value="${item.getId()}">${item.getNome()} / ${item.getPreco()}</option>
                    </c:forEach>
                </select>
            </div>
            <% if (request.getAttribute("contador") != null) {request.setAttribute("contador", (Integer) request.getAttribute("contador") + 1);} %>
            <div class="form-group col-4">
                <label for="id_item_${contador_restaurante}_${contador}">Selecionar um item</label>
                <select id="id_item_${contador_restaurante}_${contador}" name="id_item_${contador_restaurante}_${contador}" class="custom-select">
                    <option value="-">-</option>
                    <c:forEach var="item" items="${restaurante.getItens()}">
                        <option value="${item.getId()}">${item.getNome()} / ${item.getPreco()}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row">
            <% if (request.getAttribute("contador") != null) {request.setAttribute("contador", (Integer) request.getAttribute("contador") + 1);} %>
            <div class="form-group col-4">
                <label for="id_item_${contador_restaurante}_${contador}">Selecionar um item</label>
                <select id="id_item_${contador_restaurante}_${contador}" name="id_item_${contador_restaurante}_${contador}" class="custom-select">
                    <option value="-">-</option>
                    <c:forEach var="item" items="${restaurante.getItens()}">
                        <option value="${item.getId()}">${item.getNome()} / ${item.getPreco()}</option>
                    </c:forEach>
                </select>
            </div>
            <% if (request.getAttribute("contador") != null) {request.setAttribute("contador", (Integer) request.getAttribute("contador") + 1);} %>
            <div class="form-group col-4">
                <label for="id_item_${contador_restaurante}_${contador}">Selecionar um item</label>
                <select id="id_item_${contador_restaurante}_${contador}" name="id_item_${contador_restaurante}_${contador}" class="custom-select">
                    <option value="-">-</option>
                    <c:forEach var="item" items="${restaurante.getItens()}">
                        <option value="${item.getId()}">${item.getNome()} / ${item.getPreco()}</option>
                    </c:forEach>
                </select>
            </div>
            <% if (request.getAttribute("contador") != null) {request.setAttribute("contador", (Integer) request.getAttribute("contador") + 1);} %>
            <div class="form-group col-4">
                <label for="id_item_${contador_restaurante}_${contador}">Selecionar um item</label>
                <select id="id_item_${contador_restaurante}_${contador}" name="id_item_${contador_restaurante}_${contador}" class="custom-select">
                    <option value="-">-</option>
                    <c:forEach var="item" items="${restaurante.getItens()}">
                        <option value="${item.getId()}">${item.getNome()} / ${item.getPreco()}</option>
                    </c:forEach>
                </select>
            </div>
            <% if (request.getAttribute("contador") != null) {request.setAttribute("contador", (Integer) request.getAttribute("contador") + 1);} %>
        </div>
        <div class="row">
            <div class="form-group col-4">
                <label for="valor_do_combo">Valor do Combo</label>
                <input type="number" class="form-control" maxlength="100" id="valor_do_combo" name="valor_do_combo" placeholder="Digite o valor do combo">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Cadastrar</button>
    </form>
    <hr />
    <% if (request.getAttribute("contador_restaurante") != null) {request.setAttribute("contador", (Integer) request.getAttribute("contador") + 1);} %>
</c:forEach>

<%@include file="/jspf/rodape.jspf" %>

