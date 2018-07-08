<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags" %>

<html xml:lang="en" lang="en">
<head>
    <title>Login</title>
    <s:head/>
</head>
<body>
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><s:property value="name"/></td>
        </tr>
        <tr>
            <th>Birthday</th>
            <td><s:property value="birthday"/></td>
        </tr>
        <tr>
            <th>Password</th>
            <td><s:property value="password"/></td>
        </tr>
    </table>
</body>
</html>
