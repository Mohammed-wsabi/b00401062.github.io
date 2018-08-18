<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html xml:lang="en" lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><decorator:title default="Struts Starter"/></title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <link href="<s:url value='/css/main.css'/>" rel="stylesheet"/>
    <script src="<s:url value='/js/main.js'/>"></script>
    <decorator:head/>
</head>
<body id="page-home" class="container-fluid">
    <div id="page" class="card">
        <div id="header" class="card-header">
            <h1>Struts</h1>
        </div>
        <div id="content" class="card-body">
            <h3>Login Information</h3><br/>
            <decorator:body/>
        </div>
        <div id="footer" class="card-footer">
            Please contact <a href="mailto:b00401062@ntu.edu.tw">b00401062@ntu.edu.tw</a> if you have any advice or wish any additional features regarding this app.
        </div>
    </div>
</body>
</html>
