<!DOCTYPE html>
<html>
<head>
    <title>Log in furniture shop</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<body>
<h1>LOG IN</h1>
<h4>Авторизация</h4>
<form action="/login" method="post">
    <h3 style="color: #ce1521" id="error-message">Неверный логин или пароль</h3>
    Логин: <input type="text" name="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Вход</button><br><br>
</form>
<form action="/registration" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Регистрация</button>
</form>
</body>
</html>