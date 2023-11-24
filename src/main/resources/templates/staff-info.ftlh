<!DOCTYPE html>
<html>
<head>
    <title>Staff</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<body>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>

</header>
<h1>Staff</h1>
<h4>Подробная информация о пользователе</h4>
<b>ФИО: </b>${staff.fullName}<br>
<b>Логин: </b>${staff.login}<br>
<b>Номер телефона работника: </b>${staff.phoneNumber}<br>
<#if staff.isUser()>
    <form action="/admin/staff/hire" method="post">
        <input type="hidden" value="${staff.idStaff}" name="idStaff">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Нанять</button>
    </form>
</#if>
</body>
<form action="/staff" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>
</html>