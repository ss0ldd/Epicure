<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create External Post - Epicure</title>
    <link rel="stylesheet" href="/css/MainPage.css">
    <style>
        .create-form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }
        .form-container {
            background: white;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        .form-group input,
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        .form-group textarea {
            height: 150px;
            resize: vertical;
        }
        .submit-btn {
            background: #e63946;
            color: white;
            padding: 12px 24px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        .submit-btn:hover {
            background: #c1121f;
        }
        .back-btn {
            background: #6c757d;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }
        .back-btn:hover {
            background: #5a6268;
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

    <main class="create-form">
        <a href="/external/posts" class="back-btn">← Вернуться к публикациям</a>
        
        <div class="form-container">
            <h2>Создать новую публикацию</h2>
            <p>Будет создана публикация с помощью JSONPlaceholder API</p>
            
            <form action="/external/posts/create" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                
                <div class="form-group">
                    <label for="title">Заголовок:</label>
                    <input type="text" id="title" name="title" required>
                </div>
                
                <div class="form-group">
                    <label for="body">Содержание:</label>
                    <textarea id="body" name="body" required></textarea>
                </div>
                
                <div class="form-group">
                    <label for="userId">Id пользователя:</label>
                    <input type="number" id="userId" name="userId" value="1" required>
                </div>
                
                <button type="submit" class="submit-btn">Опубликовать</button>
            </form>
        </div>
    </main>

    <footer class="footer">
        <p>Epicure &copy; 2025</p>
    </footer>
</body>
</html> 