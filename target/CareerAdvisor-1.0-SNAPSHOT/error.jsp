<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>

<html>
<head>
    <title>Error</title>
</head>
<body>
<%= exception %>
<%
    String msg = (String) request.getAttribute("error-msg");
    if(msg != null){
%>
<h2><%=msg%></h2>
<%}%>
</body>
</html>
