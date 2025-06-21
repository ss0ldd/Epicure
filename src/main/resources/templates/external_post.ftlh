<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>External Post - Epicure</title>
    <link rel="stylesheet" href="/css/MainPage.css">
    <style>
        .external-post {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .post-detail {
            background: white;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .post-title {
            color: #e63946;
            font-size: 2em;
            margin-bottom: 20px;
        }
        .post-body {
            color: #333;
            line-height: 1.8;
            font-size: 1.1em;
            margin-bottom: 30px;
        }
        .post-meta {
            color: #666;
            font-size: 1em;
            border-top: 1px solid #eee;
            padding-top: 20px;
        }
        .back-btn {
            background: #e63946;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }
        .back-btn:hover {
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
                    <li><a href="/">Home</a></li>
                    <li><a href="/external/posts">External Posts</a></li>
                    <li><a href="/profile">Profile</a></li>
                    <li><a href="/sign-out">Sign Out</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="external-post">
        <a href="/external/posts" class="back-btn">← Back to Posts</a>
        
        <#if error??>
            <div class="error">
                ${error}
            </div>
        </#if>
        
        <#if post??>
            <div class="post-detail">
                <h2 class="post-title">${post.title!"Untitled"}</h2>
                <p class="post-body">${post.body!"No content"}</p>
                <div class="post-meta">
                    <strong>Post ID:</strong> ${post.id!0}<br>
                    <strong>User ID:</strong> ${post.userId!0}
                </div>
            </div>
        <#else>
            <p>Post not found.</p>
        </#if>
    </main>

    <footer class="footer">
        <p>Epicure &copy; 2025</p>
    </footer>
</body>
</html> 