<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home</title>
    <link rel="stylesheet" href="/css/MainPage.css">
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
                <li><a href="/logout">Logout</a></li>
            </ul>
        </nav>
    </div>
</header>

<main class="main-content">

    <div class="restaurantInfoList">

        <form action="/" method="get">
            <input type="search" name="s" placeholder="Enter restaurant name..." />
            <button type="submit">Find</button>
        </form>

        <#if searchQuery??>
            <h2>Search results for: ${searchQuery}</h2>
        </#if>

        <#if restaurants?has_content>
            <#list restaurants as restaurant>
                <div class="restaurant-info">
                    <div class="restaurant-container">
                        <p>Name: ${restaurant.restaurantName!"Unknown"}</p>
                        <p>Address: ${restaurant.address!"N/A"}</p>
                        <p>Rating: ${restaurant.rating!"Not rated"}</p>
                    </div>

                    <a href="${springMacroRequestContext.contextPath}/restaurant/${restaurant.restaurantId}">See more</a>
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