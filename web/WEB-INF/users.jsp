<%-- 
    Document   : users
    Created on : Jul 1, 2021, 12:45:38 PM
    Author     : Fiona Maglinao and Sana Zi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MVC Users</title>
    </head>
    <body>
        
        
        <h1>MVC Users</h1>

        <ul>

            <c:forEach items="${users}" var="account">
                <li><a href="users?action=view&amp;email=${account.email}">${account.email}</a><br></li>
                </c:forEach>
        </ul>
        
        <c:if test="${selectedAcc eq null}">
        <h3>Add User</h3>
        <form method="POST" action="users">
            <label>Email: </label>
            <input type="text" name="email" value=""><br>
            <label>First Name: </label>
            <input type="text" name="firstName" value=""><br>
            <label>Last Name: </label>
            <input type="text" name="lastName" value=""><br>
            <label>Password: </label>
            <input type="text" name="password" value=""><br>

            <label>Choose a role: </label>
            <select name="role">
                <option value="systemAdmin">System Admin</option>
                <option value="regularUser">Regular User</option>
                <option value="companyAdmin">Company Admin</option>
            </select><br>
            
            <label>Status: </label>
            <input type="checkbox" name="status" value=""><br>
            
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
        </form>
</c:if>
        
        <!--<h3>Manage Users</h3>-->

        <c:if test="${selectedAcc ne null}">
        <h3>Edit User</h3>
        <form method="POST" action="users">
            <label>Email: </label>
            <input type="text" name="editEmail" value="${selectedAcc.email}" disabled="true"><br>
            <label>First Name: </label>
            <input type="text" name="editFirstName" value="${selectedAcc.firstName}"><br>
            <label>Last Name: </label><br>
            <input type="text" name="editLastName" value="${selectedAcc.lastName}"><br>
             <label>The role: </label><br>
             <input type="text" name="editRole" value="${selectedAcc.role}"><br>
            
           
            <input type="hidden" name="action" value="edit">
            <input type="submit" value="Edit">
            <!-- maybe for cancel we can jus in the servlet redirect it back to the jsp but i donno 
            <!--<input type="submit" value="Cancel">-->
            
        </form>
            
            <form action="users" method="POST">
                <input type="hidden" name="action" value="delete">
             <input type="hidden" name="" value="${selectedAcc.email}">
                <input type="submit" value="Delete">
            </form>
        </c:if>
    </body>
</html>
