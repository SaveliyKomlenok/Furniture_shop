<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<body>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>
</header>
<h1>Registration</h1>
<h4>Регистрация</h4>
<form action="/registration" method="post">
    ФИО пользователя: <input required type="text" name="fullName"><br><br>
    Логин: <input required type="text" name="login"><br><br>
    Пароль: <input required type="password" name="password"><br><br>
    Номер телефона: <input required type="text" name="phoneNumber"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Зарегистрироваться</button>
</form>
<#if errorMessage??>
    <h2 style="color: #ce1521"> ${errorMessage} </h2>
</#if>
</body>
</html>