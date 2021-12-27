<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main page</title>
    <%@ include file="headers.jsp" %>

</head>
<body>

<%@ include file="navigation.jsp" %>

<%-- This is JSP comment --%>

<span>Hello <c:out value="${userName}"/></span>
<div style="text-align: right">
    <a href="<%=request.getContextPath()%>/logout">Logout</a>
</div>

</body>
</html>
