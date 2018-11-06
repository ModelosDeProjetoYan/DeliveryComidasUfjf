<%@page 
    import="java.util.Date" 
    errorPage="pagina-de-erro.jsp" 
    isErrorPage="false" 
    contentType="text/html" 
    pageEncoding="UTF-8" %>

<!--The core group of tags are the most commonly used JSTL tags.-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--The JSTL formatting tags are used to format and display text, the date, the time, and numbers for internationalized Websites.-->
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="/jspf/cabecalho.jspf" %>

<div class="alert alert-warning" role="alert">
    <h4>Carrinho: </h4><table border="0">
        <thead>
            <tr>
                <th>Produto: </th>
                <th>Quantidade: </th>
                <th>Valor: </th>
                <th>Atualizar quantidade item:</th>
            </tr>
        </thead>
        <tbody>
            <tr>
        <c:forEach var="item" items="${pedido.getCarrinho()}">
            <form  method="POST" action="MainServlet?parametro=AttCarrinho & indexItem=${pedido.getCarrinho().indexof(item)} & idPedido=${param.idPedido}">
                <td>${item.getNome()}</td>
                <td><input type="number" name="Quantidade" value="${item.getQuantidade()}"/></td>
                <td>${item.getValor() * item.getQuantidade()}</td>
                <td><input type="submit" value="Atualizar Item" name="btnAtualizarCarrinho" /></td>
            </form>
        </c:forEach>
            </tr>
        </tbody>
    </table>

</div>

<%@include file="/jspf/rodape.jspf" %>
