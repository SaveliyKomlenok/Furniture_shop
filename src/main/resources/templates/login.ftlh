<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Log in furniture shop</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<body>
<h1>LOG IN</h1>
<h4>Авторизация</h4>
<form action="/login" method="post">
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