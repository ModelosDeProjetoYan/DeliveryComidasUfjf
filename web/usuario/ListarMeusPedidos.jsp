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


<h3>${sessionScope.carrinho.getPedido().getDataPedido()}</h3>
    <table border="1">
        <thead>
            <tr>
                <th>Nome do item</th>
                <th>Quantidade do item</th>
                <th>Preço do item</th>
            </tr>
        </thead>
<c:forEach var="itensDoPedido" items="${sessionScope.carrinho.getPedido().getCarrinho()}">
        <tbody>
            <tr>
                <td>${itensDoPedido.getNome()}</td>
                <td>${itensDoPedido.getQuantidade()}</td>
                <td>R$ ${itensDoPedido.getPreco()}</td>
            </tr>
        </tbody>
    </table>

    
</c:forEach>
   
<c:forEach var="pedido" items="${estadosDoPedido}">
    <li>${pedido}</li>
</c:forEach>
<%@include file="/jspf/rodape.jspf" %>

