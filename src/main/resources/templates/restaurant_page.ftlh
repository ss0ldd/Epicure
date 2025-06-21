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

        <h3>Создать приглашение</h3>
        <form action="/restaurant/${restaurant.restaurantId}/event" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <label for="eventName">Название мероприятия:</label>
            <input type="text" id="eventName" name="eventName" required>

            <label for="inviteeEmail">Почти приглашаемого:</label>
            <input type="email" id="inviteeEmail" name="inviteeEmail" required>

            <label for="inviteeName">Имя приглашаемого:</label>
            <input type="text" id="inviteeName" name="inviteeName" required>

            <label for="eventDate">Дата:</label>
            <input type="date" id="eventDate" name="eventDate" required>

            <label for="eventTime">Время:</label>
            <input type="time" id="eventTime" name="eventTime" required>

            <button type="submit">Отправить приглашение</button>
        </form>

        <h3>Оставить отзыв</h3>
        <form action="/restaurant/${restaurant.restaurantId}" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <input name="title" type="text" placeholder="Заголовок" required>
            <input name="content" type="text" placeholder="Ваш отзыв..." required>

            <select id="rating" name="rating">
                <option value="1">★</option>
                <option value="2">★★</option>
                <option value="3">★★★</option>
                <option value="4">★★★★</option>
                <option value="5">★★★★★</option>
            </select>

            <input type="file" name="files" multiple accept="image/*">
            <input type="submit" value="Опубликовать">
        </form>

        <#if posts?has_content>
            <h3>Публикации о заведении</h3>
            <#list posts as post>
                <div class="post">
                    <div class="post-container">
                        <h4>${post.title!"Untitled Post"}</h4>
                        <p>Пользователь: ${post.userName!"Anonymous"}</p>
                        <p>Заведение: ${post.restaurantName!"Unknown"}</p>
                        <p>Адресс: ${post.restaurantAddress!"N/A"}</p>
                        <p>${post.content!"No content provided."}</p>
                        <p>Рейтинг: ${post.rating}</p>
                        <p>Дата: ${post.date}</p>

                        <a href="${springMacroRequestContext.contextPath}/post/${post.postId}">Узнать больше</a>
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