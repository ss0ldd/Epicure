<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Публикация</title>
    <link rel="stylesheet" href="/css/Post.css">
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
</head>
<body>

<!-- Navigation -->
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

    <div class="postInfo">

        <div class="post">
            <div class="post-container">
                <h2>${post.title!"Untitled Post"}</h2>
                <p>Пользователь: ${post.userName!"Anonymous"}</p>
                <p>Заведение: ${post.restaurantName!"Unknown"}</p>
                <p>Адресс: ${post.restaurantAddress!"N/A"}</p>
                <p>${post.content!"No content provided."}</p>
                <p>Рейтинг: ${post.rating!"Not rated yet"}</p>
                <p>Дата: ${post.date}</p>

                <#if post.files?? && post.files?size gt 0>
                    <div class="post-images">
                        <#list post.files as file>
                            <img src="${file.url}" alt="Post image" class="post-image">
                        </#list>
                    </div>
                </#if>

                <button id="like-btn-${post.postId}" onclick="likePost(${post.postId})" class="like-btn">
                    ❤️ Нравится
                </button>
                <span id="likes-count-${post.postId}">${post.likes!0}</span>
            </div>
        </div>

        <form action="/post/${post.postId}" method="POST">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input name="comment" type="text" placeholder="Оставить комментарий...">
            <input type="submit" value="Send">
        </form>

        <#if comments?has_content>
            <#list comments as comment>
                <div class="comment">
                    <div class="comment-container">
                        <p><strong>${comment.userName!"Пользователь"}:</strong></p>
                        <p>${comment.comment!"..."}</p>
                        <p>${comment.date}</p>
                    </div>
                </div>
            </#list>
        </#if>

    </div>

</main>

<footer class="footer">
    <p>Epicure &copy; 2025</p>
</footer>

<script>
    function likePost(postId) {
        const token = document.querySelector('meta[name="_csrf"]').content;
        const header = document.querySelector('meta[name="_csrf_header"]').content;

        fetch(`/like/${postId}`, {
            method: 'POST',
            headers: {
                [header]: token,
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': token
            }
        })
            .then(response => {
                if (!response.ok) throw new Error('Network response was not ok');
                return response.json();
            })
            .then(data => {
                document.getElementById(`likes-count-${postId}`).textContent = data.likes;

                const likeBtn = document.getElementById(`like-btn-${postId}`);
                if (data.isLiked) {
                    likeBtn.innerHTML = '❤️ Liked';
                    likeBtn.classList.add('active-like');
                } else {
                    likeBtn.innerHTML = '🤍 Like';
                    likeBtn.classList.remove('active-like');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при обновлении лайка: ' + error.message);
            });
    }
</script>

</body>
</html>