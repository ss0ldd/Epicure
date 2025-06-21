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

    <div class="restaurantInfoList">

        <form action="/" method="get">
            <input type="search" name="s" placeholder="Введите название заведения..." />
            <button type="submit">Поиск</button>
        </form>

        <#if searchQuery??>
            <h2>Search results for: ${searchQuery}</h2>
        </#if>

        <#if restaurants?has_content>
            <#list restaurants as restaurant>
                <div class="restaurant-info">
                    <div class="restaurant-container">
                        <p>Заведение: ${restaurant.restaurantName!"Unknown"}</p>
                        <p>Адресс: ${restaurant.address!"N/A"}</p>
                        <p>Рейтинг: ${restaurant.rating!"Not rated"}</p>
                    </div>

                    <a href="${springMacroRequestContext.contextPath}/restaurant/${restaurant.restaurantId}">Узнать больше</a>
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