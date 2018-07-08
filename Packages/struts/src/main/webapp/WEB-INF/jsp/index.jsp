<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags" %>

<html xml:lang="en" lang="en">
<head>
    <title>Index</title>
    <s:head/>
</head>
<body>
    <s:form action="login">
        <s:textfield label="Name" name="name"/>
        <s:textfield label="Birthday" name="birthday"/>
        <s:password label="Password" name="password"/>
        <s:submit/>
    </s:form>
</body>
</html>
