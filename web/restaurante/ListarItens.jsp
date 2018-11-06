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

<table border="1">
    <thead>
        <tr>
            <th>Cardapio</th>
        </tr>
    </thead>
    <tbody>
        <tr>
        <c:forEach var="item" items="${Cardapio}">
            <td>
                <ul type="circle">
                    <li>${item.getNome()}</li>
                    <li>${item.getDescricao()}</li>
                    <li>${item.getPreco()}</li>
                </ul>
            </td>
            <td>
                <form method="POST" action="MainServlet?parametro=AddCarrinho & id=${restaurante.getId()} & idR=${param.idRestaurante}">
                    <input type="number" name="Quantidade" value="0" />
                    <input type="submit" value="Adicionar ao Carrinho" name="btnAddCarrinho" />
                </form>
            </td>
        </c:forEach>    
        </tr>
    </tbody>
</table>

<%@include file="/jspf/rodape.jspf" %>
