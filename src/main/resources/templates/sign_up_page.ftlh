<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

<form action="/signUp" method="POST">
    <input name="email" type="email" placeholder="EMAIL">
    <input name="password" type="password" placeholder="PASSWORD">
    <input name="username" type="text" placeholder="USERNAME">
    <input type="submit" value="SIGN UP">
</form>

<div th:if="${error}" style="color:red;">
    <p th:text="${error}"></p>
</div>

</body>
</html>