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


<table border="1">
    <thead>
        <tr>
            <th>Restaurantes</th>
        </tr>
    </thead>
    <tbody>
        <tr>
        <c:forEach var="restaurante" items="${restaurantes}">
            <td>
                <ul type="circle">
                    <li>${restaurante.getNome()}</li>
                    <li>${restaurante.getTipoDeComida()}</li>
                    <li>${restaurante.getLogradouro()}  ${restaurante.getNumero()}
                        <c:if test="${restaurante.getComplemento()}!=null">
                            ${restaurante.getComplemento()}
                        </c:if>
                    </li>
                    <li>${restaurante.getBairro()}   ${restaurante.getCidade()}</li>
                </ul>
            </td>
        </c:forEach>    
        </tr>
    </tbody>
</table>


<%@include file="/jspf/rodape.jspf" %>
