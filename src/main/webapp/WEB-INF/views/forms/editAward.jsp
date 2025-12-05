<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Form</title>
</head>
<body>
<form:form action="/editedAward?awardId=${award.id}" modelAttribute="award">
    Title:
    <form:input path="title" value="${title}"/>
    <form:errors path="title"/><br/>

    Year:
    <form:input path="year" value="${year}"/>
    <form:errors path="year"/><br/>

    <input type="submit"/><form:errors/>
</form:form>
</body>
</html>