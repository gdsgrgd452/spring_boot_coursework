<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Form</title>
</head>
<body>
<form:form action="/addVendor" modelAttribute="vendor">
    Name:
    <form:input path="name"/>
    <form:errors path="name"/><br/>

    Location:
    <form:input path="location"/>
    <form:errors path="location"/><br/>

    Cuisine Type:
    <form:input path="cuisineType"/>
    <form:errors path="cuisineType"/><br/>

    <input type="submit"/><form:errors/>
</form:form>
</body>
</html>