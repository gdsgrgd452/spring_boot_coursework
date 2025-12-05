<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Form</title>
</head>
<body>
<form:form action="/editedVendorProfile?id=${vendor}" modelAttribute="profile">
    Bio:
    <form:input path="bio" value="${profile.bio}"/>
    <form:errors path="bio"/><br/>

    Location:
    <form:input path="socialMediaHandle" value="${profile.socialMediaHandle}"/>
    <form:errors path="socialMediaHandle"/><br/>

    Cuisine Type:
    <form:input path="website" value="${profile.website}"/>
    <form:errors path="website"/><br/>

    <input type="submit"/><form:errors/>
</form:form>
</body>
</html>