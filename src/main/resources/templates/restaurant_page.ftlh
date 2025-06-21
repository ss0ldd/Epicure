<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${restaurant.restaurantName!"Restaurant"} | Epicure</title>
    <link rel="stylesheet" href="/css/Restaurant.css">
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
</head>
<body>

<header class="header">
    <div class="nav-container">
        <h1><a href="/">Epicure</a></h1>
        <nav class="navigation">
            <ul>
                <li><a href="/profile">Profile</a></li>
                <li><a href="/">Home</a></li>
                <li><a href="/external/posts">External Posts</a></li>
                <li>
                    <form action="/logout" method="POST" style="display: inline;">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" style="background: none; border: none; color: #333; cursor: pointer; font-size: inherit;">Logout</button>
                    </form>
                </li>
            </ul>
        </nav>
    </div>
</header>

<main class="main-content">

    <div class="restaurantInfo">

        <h2>${restaurant.restaurantName!"Unknown Restaurant"}</h2>
        <p>Rating: ${restaurant.rating?string["0.0"]}/5.0</p>

        <h3>Location</h3>
        <iframe
                width="60%"
                height="300"
                frameborder="0"
                scrolling="no"
                marginheight="0"
                marginwidth="0"
                src="https://yandex.ru/map-widget/v1/?text=~ ${restaurant.address?url}">
        </iframe>

        <h3>Create Event</h3>
        <form action="/restaurant/${restaurant.restaurantId}/event" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <label for="eventName">Event Name:</label>
            <input type="text" id="eventName" name="eventName" required>

            <label for="inviteeEmail">Invitee Email:</label>
            <input type="email" id="inviteeEmail" name="inviteeEmail" required>

            <label for="inviteeName">Invitee Name:</label>
            <input type="text" id="inviteeName" name="inviteeName" required>

            <label for="eventDate">Date:</label>
            <input type="date" id="eventDate" name="eventDate" required>

            <label for="eventTime">Time:</label>
            <input type="time" id="eventTime" name="eventTime" required>

            <button type="submit">Send Invitation</button>
        </form>

        <h3>Add Review</h3>
        <form action="/restaurant/${restaurant.restaurantId}" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <input name="title" type="text" placeholder="Title" required>
            <input name="content" type="text" placeholder="Your review..." required>

            <select id="rating" name="rating">
                <option value="1">★</option>
                <option value="2">★★</option>
                <option value="3">★★★</option>
                <option value="4">★★★★</option>
                <option value="5">★★★★★</option>
            </select>

            <input type="file" name="files" multiple accept="image/*">
            <input type="submit" value="Create Post">
        </form>

        <#if posts?has_content>
            <h3>Posts about this restaurant</h3>
            <#list posts as post>
                <div class="post">
                    <div class="post-container">
                        <h4>${post.title!"Untitled Post"}</h4>
                        <p>User: ${post.userName!"Anonymous"}</p>
                        <p>Restaurant: ${post.restaurantName!"Unknown"}</p>
                        <p>Address: ${post.restaurantAddress!"N/A"}</p>
                        <p>${post.content!"No content provided."}</p>
                        <p>Rating: ${post.rating}</p>
                        <p>Date: ${post.date}</p>

                        <a href="${springMacroRequestContext.contextPath}/post/${post.postId}">See more</a>
                    </div>
                </div>
            </#list>
        </#if>

    </div>

</main>

<footer class="footer">
    <p>Epicure &copy; 2025</p>
</footer>

</body>
</html>