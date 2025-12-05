<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Form</title>
</head>
<body>
<form:form action="/editedPhoto?photoId=${photo.id}" modelAttribute="photo">
    URL:
    <form:input path="url" value="${photo.url}"/>
    <form:errors path="url"/><br/>

    Description:
    <form:input path="description" value="${photo.description}"/>
    <form:errors path="description"/><br/>

    <input type="submit"/><form:errors/>
</form:form>
</body>
</html>