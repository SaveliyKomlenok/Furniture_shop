<!DOCTYPE html>
<html>
<head>
    <title>Furniture shop</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<body>

<h1>Мебельный магазин</h1>

<p><a href="/personalArea/{idStaff}/()"> Мебель </a></p>
<p><a href="/furniture"> Мебель </a></p>

<#if staff.isAdmin()>
    <p><a href="/staff"> Работники </a></p>
    <p><a href="/manufacturer"> Изготовители </a></p>
    <p><a href="/rooms"> Комнаты </a></p>
    <p><a href="/size"> Размеры </a></p>
    <p><a href="/supplies"> Поставки </a></p>
</#if>

<#if staff.isManager()>
    <p><a href="/supplies"> Поставки </a></p>
    <p><a href="/manufacturer"> Изготовители </a></p>
    <p><a href="/rooms"> Комнаты </a></p>
    <p><a href="/size"> Размеры </a></p>
</#if>

<#if staff.isSeller()>
    <p><a href="/sales"> Продажи </a></p>
    <p><a href="/manufacturer"> Изготовители </a></p>
    <p><a href="/rooms"> Комнаты </a></p>
    <p><a href="/size"> Размеры </a></p>
</#if>

<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Выйти из аккаунта</button>
</form>

</body>
</html>