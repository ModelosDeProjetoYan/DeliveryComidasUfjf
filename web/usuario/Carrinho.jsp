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

<c:forEach var="item" items="${sessionScope.carrinho.getPedido().getCarrinho()}">
    <%@include file="/jspf/item.jspf" %>
</c:forEach>

<a href="MainServlet?parametro=FinalizarPedido" class="btn btn-success" title="Finalizar Pedido"><i class="fas fa-star"></i> Finalizar Pedido</a>
<a href="MainServlet?parametro=CancelarPedido" class="btn btn-danger" title="Cancelar Pedido"><i class="fas fa-star"></i> Cancelar Pedido</a>

<%@include file="/jspf/rodape.jspf" %>
