<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Form</title>
</head>
<body>
<form:form action="/addReview?vendorid=${vendor.id}&dishid=${dishid}" modelAttribute="review">
    Reviewer name:
    <form:input path="reviewerName"/>
    <form:errors path="reviewerName"/><br/>

    Rating:
    <form:input path="rating"/>
    <form:errors path="rating"/><br/>

    Comment:
    <form:input path="comment"/>
    <form:errors path="comment"/><br/>

    <input type="submit"/><form:errors/>
</form:form>
</body>
</html>