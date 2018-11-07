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

<c:forEach var="restaurante" items="${restaurantes}">
    <h3>${restaurante.getNome()}</h3>
    <div class="row">
        <div class="col-2">
            <br />
            <br />
            <!--<img class="rounded-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="170" height="170">-->
            <img class="rounded-circle" src="./imagens/logo-restaurante.jpg" alt="Logo do restaurante ${restaurante.getNome()}" width="170" height="170">
        </div>
        <div class="col-9">
            <nav>
                <div class="nav nav-tabs" id="nav-tab${contador}" role="tablist">
                    <a class="nav-item nav-link active" id="nav-descricao-tab${contador}" data-toggle="tab" href="#nav-descricao${contador}" role="tab" aria-controls="nav-descricao${contador}" aria-selected="true">Descrição</a>
                    <a class="nav-item nav-link" id="nav-itens-tab${contador}" data-toggle="tab" href="#nav-itens${contador}" role="tab" aria-controls="nav-itens${contador}" aria-selected="false">Cardápio</a>
                    <a class="nav-item nav-link" id="nav-funcionarios-tab${contador}" data-toggle="tab" href="#nav-funcionarios${contador}" role="tab" aria-controls="nav-funcionarios${contador}" aria-selected="false">Funcionários</a>
                </div>
            </nav>
            <div class="tab-content p-1" id="nav-tabContent${contador}">
                <div class="tab-pane fade show active p-1" id="nav-descricao${contador}" role="tabpanel" aria-labelledby="nav-descricao-tab${contador}">
                    <%@include file="/jspf/restaurante.jspf" %>
                </div>
                <div class="tab-pane fade p-1" id="nav-itens${contador}" role="tabpanel" aria-labelledby="nav-itens-tab${contador}">
                    <c:forEach var="item" items="${restaurante.getItens()}">
                        <%@include file="/jspf/item.jspf" %>
                    </c:forEach>
                </div>
                <div class="tab-pane fade p-1" id="nav-funcionarios${contador}" role="tabpanel" aria-labelledby="nav-funcionarios-tab${contador}">
                    <c:forEach var="funcionario" items="${restaurante.getFuncionarios()}">
                        <%@include file="/jspf/funcionario.jspf" %>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="col-1">
            <br />
            <br />
            <div class="row mt-2">
                <a href="#" class="btn btn-warning btn-block" title="Favoritar"><i class="fas fa-star"></i></a>
            </div>
            <div class="row mt-2">
                <div class="btn-group-vertical mt-1 btn-block">
                    <a href="#" class="btn btn-success btn-block" title="Avaliações Positivas"><i class="fas fa-angle-up"></i> &nbsp; 9</a>
                    <a href="#" class="btn btn-danger btn-block" title="Avaliações Negativas"><i class="fas fa-angle-down"></i> &nbsp; 3</a>
                </div>
            </div>
            <c:if test="${isProprietario}">
                <div class="row mt-2">
                    <div class="btn-group-vertical btn-block text-center mt-2">
                        <a href="aaaa" class="btn btn-dark btn-block" title="Editar Restaurante"><i class="fas fa-edit"></i></a>
                        <button class="btn btn-danger btn-block" onclick="confirm('Clique em OK para EXCLUIR o restaurante ${restaurante.getNome()}.') ? (location.href = '#') : false" title="Excluir Restaurante"><i class="fas fa-trash-alt"></i></button>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <hr />
    <% if (request.getAttribute("contador") != null) {request.setAttribute("contador", (Integer) request.getAttribute("contador") + 1);} %>
</c:forEach>

<%@include file="/jspf/rodape.jspf" %>
