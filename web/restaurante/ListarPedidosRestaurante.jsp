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

<c:forEach var="pedido" items="${pedidos}">
    <table border="1">
        <thead>
            <tr>
                <th>Nome do item</th>
                <th>Quantidade do item</th>
                <th>Preço do item</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="itensDoPedido" items="${pedido.getCarrinho()}">
                <tr>
                    <td>${itensDoPedido.getNome()}</td>
                    <td>${itensDoPedido.getQuantidade()}</td>
                    <td>R$ ${itensDoPedido.getPreco()}</td>
                </tr>
            </c:forEach>
            <tr>
                <td align='center'>${pedido.getStatusPedido()}</td>
        <div class="row">
            <form method="POST" action="MainServlet?parametro=AtualizarEstadoPedidoPost&idPedido=${pedido.getId()}">
                <td><div class="form-group">
                        <select id="state" name="state" class="custom-select">
                            <c:forEach var="state" items="${estados}">
                                <option value="${state}">${state}</option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="btn btn-primary">Atualizar</button>
                    </div></td>
                <td>
                    <div class="row mt-2">
                        <div class="btn-group mt-1 btn-block">
                            <button type="submit" name="btnVoltar" value="Voltar" class="btn btn-success btn-block" title="Voltar"><i class="fas fa-angle-left"></i> &nbsp; Voltar</a> </button>
                            <button type="submit" name="btnAvancar" value="Avancar" class="btn btn-success btn-block" title="Avancar"><i class="fas fa-angle-right"></i> &nbsp; Avançar</a> </button>
                        </div>
                    </div>
                </td>
            </form>
        </div>
    </tr>
</tbody>
</table>
</c:forEach>


<%@include file="/jspf/rodape.jspf" %>

