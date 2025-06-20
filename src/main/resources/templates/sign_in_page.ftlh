<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign In</title>
    <link rel="stylesheet" href="/css/SignIn.css">
</head>
<body>
<div class="page-container">

    <main class="main-content">

        <div class="sign-in-box" id="main-box">
            <div class="sign-in-form" id="form">

                <form action="/login" method="POST">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <input name="username" type="text" placeholder="USERNAME">
                    <input name="password" type="password" placeholder="PASSWORD">
                    <input type="submit" value="SIGN IN">
                </form>

                <p>Don't have an account? <a href="${springMacroRequestContext.contextPath}/signUp">Sign up for free</a></p>
            </div>
        </div>

    </main>

    <footer class="footer">
        <p>Epicure</p>
    </footer>

</div>


</body>
</html>