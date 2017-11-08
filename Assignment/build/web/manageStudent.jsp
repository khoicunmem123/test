<%-- 
    Document   : manageStudent
    Created on : Oct 24, 2017, 2:04:05 PM
    Author     : khoi_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Students</title>
    </head>
    <body>
        Welcome <font color="red">${sessionScope.FULLNAME}</font>
        <a href="logout">Log out</a><br/>
        <h1>Manage Students</h1>
        <form action="search">
            Status <input type="text" name="txtSearchStatus" value="${param.txtSearchStatus}" /><br/>
            Or <br/>
            Name <input type="text" name="txtSearchName" value="${param.txtSearchName}" /><br/>
            <input type="submit" value="Search" /> <br/>
        </form>
        <form action="showAll">
            <input type="submit" value="Show All" />
        </form>
        <c:set var="result" value="${requestScope.SEARCHRESULT}" />
        <c:if test="${empty result}">
            <c:if test="${not empty param.txtSearchStatus or not empty param.txtSearchName}">
                <font color="red">Not found!!!</font><br/>
            </c:if>
        </c:if>
        <c:if test="${not empty result}">
            List of student  <b>Total of search Result: ${requestScope.TOTAL} </b> <br/>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Full name</th>
                        <th>Class</th>
                        <th>Address 1</th>
                        <th>Address 2</th>
                        <th>Status</th>
                        <th>Phone</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${result}" varStatus="counter" >
                    <form action="update">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${item.value.id}
                                <input type="hidden" name="txtId" value="${item.value.id}" />
                                <input type="hidden" name="txtSearchStatus" value="${param.txtSearchStatus}" />
                                <input type="hidden" name="txtSearchName" value="${param.txtSearchName}" />
                            </td>
                            <td>${item.value.fullname}</td>
                            <td>
                                <input type="text" name="txtClass" value="${item.value.classname}" />
                            </td>
                            <td>
                                <input type="text" name="txtAddress1" value="${item.value.address1}" />
                            </td>
                            <td>
                                <input type="text" name="txtAddress2" value="${item.value.address2}" />
                            </td>
                            <td>
                                <input type="text" name="txtStatus" value="${item.value.status}" />
                            </td>
                            <td>
                                <input type="text" name="txtPhone" value="${item.value.phone}" />
                            </td>
                            <td>
                                <input type="submit" value="Update" />
                            </td>
                            <td>
                                <c:url var="delLink" value="delete" >
                                    <c:param name="id" value="${item.value.id}" />
                                    <c:param name="searchStatus" value="${param.txtSearchStatus}" />
                                    <c:param name="searchName" value="${param.txtSearchName}"/>
                                </c:url> 
                                <a href="${delLink}" >Delete</a>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <b>Insert New Student</b><br/>
    <c:set var="err" value="${requestScope.ERROR}"/>
    <form action="insertStudent">
        Id &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="txtId" value="${param.txtId}" /><br/>
        <c:if test="${err.idLengthError}">
            <font color="red">Id can not empty!<br/></font>
        </c:if>
        <c:if test="${err.idDuplicaError}">
            <font color="red">Id is existed!<br/></font>
        </c:if>
        Last name <input type="text" name="txtLastname" value="${param.txtLastname}" />
        Middle name <input type="text" name="txtMiddlename" value="${param.txtMiddlename}" />
        First name <input type="text" name="txtFirstname" value="${param.txtFirstname}" /><br/>
        <c:if test="${err.lastnameError}">
            <font color="red">Last name can not empty!<br/></font>
        </c:if>
        <c:if test="${err.firstnameError}">
            <font color="red">First name can not empty!<br/></font>
        </c:if>
        Male &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="checkbox" name="chkSex" value="ON" /><br/>
        Address1 &nbsp;<input type="text" name="txtAddress1" value="${param.txtAddress1}" /><br/>
        <c:if test="${err.address1Error}">
            <font color="red">Address1 can not empty!<br/></font>
        </c:if>
        Address2 &nbsp;<input type="text" name="txtAddress2" value="${param.txtAddress2}" /><br/>
        Phone &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="txtPhone" value="${param.txtPhone}" /><br/>
        <c:if test="${err.phoneError}">
            <font color="red">Wrong Phone number!<br/></font>
        </c:if>
        Class &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="txtClass" value="${param.txtClass}" /><br/>
        <c:if test="${err.classError}">
            <font color="red">Class can not empty!<br/></font>
        </c:if>
        <input type="submit" value="Create New Student" />
    </form>
</body>
</html>
