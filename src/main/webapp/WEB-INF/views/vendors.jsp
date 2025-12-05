<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Street Food Vendors</title>
</head>
<body>
<h1>Street Food Vendors</h1>
<a href="/">Return to Homepage</a><br/>

<div style="border:1px solid #ccc; margin:10px; padding:10px;">
    <form action="/search1">
        <label>Filter by vendor:</label>
        <input type="text" name="vendor"/>
        <input type="submit"/>
    </form>
    <form action="/search2">
        <label>Filter by dish:</label>
        <input type="text" name="dish"/>
        <input type="submit"/>
    </form>
</div>

<c:forEach var="vendor" items="${vendors}">
<div style="border:1px solid #ccc; margin:10px; padding:10px;">
    <h2>${vendor.name} (${vendor.cuisineType})</h2>
    <p><b>Location:</b> ${vendor.location}</p>
    <p><b>Bio:</b> ${vendor.profile.bio}</p>
    <p><b>Social:</b> ${vendor.profile.socialMediaHandle}</p>
    <p><b>Website:</b> ${vendor.profile.website}</p>

    <h3>Dishes</h3>
    <ul>
        <c:forEach var="dish" items="${vendor.dishes}">
            <li>
                <b>${dish.name}</b> - ${dish.description} (£${dish.price})
                <p>Spice level: ${dish.spiceLevel}/5</p>
                <c:forEach var="tag" items="${dish.tags}">
                    <p>#${tag.name}</p>
                </c:forEach>
            </li>
        </c:forEach>
    </ul>
    <h3>Reviews</h3>
    <ul>
        <c:forEach var="dish" items="${vendor.dishes}">
            <c:forEach var="review" items="${dish.reviews}">
                <li>
                    <b>${review.reviewerName}</b> rated <b>${dish.name}</b>: ${review.rating}/5<br/>
                    "${review.comment}"
                </li>
            </c:forEach>
        </c:forEach>
    </ul>
    <h3>Photo gallery</h3>
    <ul>
        <c:forEach var="photo" items="${vendor.photos}">
            <img src="${photo.url}" height="150" alt="Alt text: ${photo.description} (picture not displaying)"/>
        </c:forEach>
    </ul>
    <h3>Awards</h3>
    <ul>
        <c:forEach var="award" items="${vendor.awards}">
            <li>${award.title} (${award.year})</li>
        </c:forEach>
    </ul>
</div>
</c:forEach>
</body>
</html>
