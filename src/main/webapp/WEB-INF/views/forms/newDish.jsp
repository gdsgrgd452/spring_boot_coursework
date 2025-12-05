<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Form</title>
</head>
<body>
<form:form action="/addDish?vendorid=${vendor.id}" modelAttribute="dish">
    Name:
    <form:input path="name"/>
    <form:errors path="name"/><br/>

    Description:
    <form:input path="description"/>
    <form:errors path="description"/><br/>

    Price:
    <form:input path="price"/>
    <form:errors path="price"/><br/>

    Spice level:
    <form:input path="spiceLevel"/>
    <form:errors path="spiceLevel"/><br/>


    <p>Select Tags:</p>
    <c:forEach var="tag" items="${tags}">
        <label>
            <input type="checkbox" name="tagIds" value="${tag.id}" />
                ${tag.name}
        </label><br/>
    </c:forEach>

    <input type="submit"/><form:errors/>
</form:form>
</body>
</html>