<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Admin - Manage Vendors</title>
</head>
<body>
    <h1>Vendor Administration</h1>
    <a href="/">Return to Homepage</a><br/><br/>
    <a href="/newVendor">Add New Vendor</a>
    <ul>
        <c:forEach var="vendor" items="${vendors}">
            <li>
                ${vendor.name}
                <a href ="vendor?id=${vendor.id}">View vendor details</a>
                <a href ="deleteVendor?id=${vendor.id}">Delete this vendor</a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
