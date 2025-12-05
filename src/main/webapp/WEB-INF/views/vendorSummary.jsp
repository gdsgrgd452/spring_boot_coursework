<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Vendor Summary</title>
</head>
<body>
<div style="border:1px solid #ccc; margin:10px; padding:10px;">
    <p><a href ="editVendor?id=${vendor.id}">Edit vendor details</a></p>
    <p><a href ="editVendorProfile?id=${vendor.id}">Add/Edit vendor profile details</a></p>
    <p><a href ="newDish?id=${vendor.id}">Add a dish with tags</a></p>
    <p><a href ="newPhoto?vendorid=${vendor.id}">Add a photo</a></p>
    <p><a href ="newAward?vendorid=${vendor.id}">Add an award</a></p>
    <p>More edit and delete options are below</p>
    <p><a href="/admin">Back to vendor overview</a></p>
</div>
<h1>${vendor.name} (${vendor.cuisineType})</h1>
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

                <a href ="newReview?vendorid=${vendor.id}&dishid=${dish.id}">Add a review for dish</a>
                <a href ="editDish?vendorid=${vendor.id}&dishid=${dish.id}">Edit dish</a>
                <a href ="deleteDish?vendorid=${vendor.id}&dishid=${dish.id}">Delete dish</a>
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
                <a href ="editReview?vendorId=${vendor.id}&reviewId=${review.id}">Edit review</a>
                <a href ="deleteReview?vendorId=${vendor.id}&reviewId=${review.id}">Delete review</a>
            </c:forEach>
        </c:forEach>
    </ul>
    <h3>Photo gallery</h3>
    <ul>
        <c:forEach var="photo" items="${vendor.photos}">
            <img src="${photo.url}" height="150" alt="Alt text: ${photo.description} (picture not displaying)"/>
            <a href ="editPhoto?photoId=${photo.id}">Edit photo</a>
            <a href ="deletePhoto?photoId=${photo.id}">Delete photo</a>
        </c:forEach>
    </ul>
    <h3>Awards</h3>
    <ul>
        <c:forEach var="award" items="${vendor.awards}">
            <li>${award.title} (${award.year})</li>
            <a href ="editAward?awardId=${award.id}">Edit award</a>
            <a href ="deleteAward?awardId=${award.id}">Delete award</a>
        </c:forEach>
    </ul>
</body>
</html>
