<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
    <link rel="stylesheet" href="/css/Profile.css">
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
</head>
<body>


<header class="header">
    <div class="nav-container">
        <h1><a href="/">Epicure</a></h1>
        <nav class="navigation">
            <ul>
                <li><a href="/profile">Профиль</a></li>
                <li><a href="/">Главная</a></li>
                <li><a href="/external/posts">Публикации</a></li>
                <li>
                    <form action="/logout" method="POST" style="display: inline;">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" style="background: none; border: none; color: #333; cursor: pointer; font-size: inherit;">Выйти</button>
                    </form>
                </li>

            </ul>
        </nav>
    </div>
</header>


<main class="main-content">

    <div class="userInfo">
        <h2>Добро пожаловать, ${userName!"User"}!</h2>

        <#if posts?has_content>
            <#list posts as post>
                <div class="post">
                    <div class="post-container">
                        <h3>${post.title!"Untitled Post"}</h3>
                        <p>Заведение: ${post.restaurantName!"Unknown"}</p>
                        <p>Адресс: ${post.restaurantAddress!"N/A"}</p>
                        <p>${post.content!"No content"}</p>
                        <p>${post.date}</p>
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