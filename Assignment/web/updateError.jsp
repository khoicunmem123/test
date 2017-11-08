<%-- 
    Document   : updateError
    Created on : Oct 24, 2017, 11:46:54 PM
    Author     : khoi_
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Error Page</title>
    </head>
    <body>
        <h1>Update Error Page</h1>
        <font color="red" >
        <c:set var="err" value="${requestScope.ERROR}"/>
        <c:if test="${not empty err}">
            <c:if test="${err.classError}">
                Class can not empty!<br/>
            </c:if>
            <c:if test="${err.address1Error}">
                Address1 can not empty!<br/>
            </c:if>
            <c:if test="${err.statusError}">
                Wrong Status!<br/>
            </c:if>
            <c:if test="${err.phoneError}">
                Wrong phone number!<br/>
            </c:if>
        </c:if>   
        </font>
    </body>
</html>
