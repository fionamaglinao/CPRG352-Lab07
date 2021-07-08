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

        <h3>Add User</h3>
        <form method="POST" action="">
            <label>Email: </label><br>
            <input type="text" name="email" value="">
            <label>First Name: </label><br>
            <input type="text" name="firstName" value="">
            <label>Last Name: </label><br>
            <input type="text" name="lastName" value="">
            <label>Password: </label><br>
            <input type="text" name="password" value="">

            <label>Choose a role: </label>
            <select name="role">
                <option value="systemAdmin">System Admin</option>
                <option value="regularUser">Regular User</option>
                <option value="companyAdmin">Company Admin</option>
            </select>
            
            <label>Status: </label><br>
            <input type="checkbox" name="status" value="">
            
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
        </form>

        <h3>Manage Users</h3>

        <h3>Edit User</h3>
        <form method="POST" action="">
            <input type="text" name="editEmail" value="">
            <input type="text" name="editFirstName" value="">
            <input type="text" name="editLastName" value="">
            <input type="text" name="editRole" value="">

            <input type="submit" value="Save">
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>
