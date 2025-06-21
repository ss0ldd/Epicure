<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>External Posts - Epicure</title>
    <link rel="stylesheet" href="/css/MainPage.css">
    <style>
        .external-posts {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .post-card {
            background: white;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .post-title {
            color: #e63946;
            font-size: 1.5em;
            margin-bottom: 10px;
        }
        .post-body {
            color: #333;
            line-height: 1.6;
            margin-bottom: 15px;
        }
        .post-meta {
            color: #666;
            font-size: 0.9em;
        }
        .create-btn {
            background: #e63946;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }
        .create-btn:hover {
            background: #c1121f;
        }
        .error {
            background: #f8d7da;
            color: #721c24;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
    </style>
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

    <main class="external-posts">
        <h2>Публикации из JSONPlaceholder API</h2>
        
        <a href="/external/posts/create" class="create-btn">Создать новую публикацию</a>
        
        <#if error??>
            <div class="error">
                ${error}
            </div>
        </#if>
        
        <#if posts?? && posts?has_content>
            <#list posts as post>
                <div class="post-card">
                    <h3 class="post-title">${post.title!"Untitled"}</h3>
                    <p class="post-body">${post.body!"No content"}</p>
                    <div class="post-meta">
                        <strong>Id публикации:</strong> ${post.id!0} |
                        <strong>Id пользователя:</strong> ${post.userId!0}
                    </div>
                    <a href="/external/posts/${post.id}">Узнать больше</a>
                </div>
            </#list>
        <#else>
            <p>No posts available.</p>
        </#if>
    </main>

    <footer class="footer">
        <p>Epicure &copy; 2025</p>
    </footer>
</body>
</html> 