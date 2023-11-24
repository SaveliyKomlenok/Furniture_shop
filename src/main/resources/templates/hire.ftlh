<!DOCTYPE html>
<html lang="">
<head>
    <title>Staff</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/tableStyle.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<body>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>
</header>
<h1>Hire</h1>
<h4>Нанять пользователя на работу</h4>
<form action="/staff" method="get">
    Поиск по ФИО
    <input type="text" name="fullName"/>
    <button type="submit">Поиск</button>
</form>

<#if staffs?has_content>
    <table>
        <tr><th>Номер<br>пользователя</th><th>Ф.И.О.</th><th>Номер телефона</th><th>Нанять <br>на работу</th></tr>
        <#list staffs as staff>
            <#if staff.isUser()>
                <tr>
                    <td>${staff.idStaff}</td>
                    <td>${staff.fullName}</td>
                    <td>${staff.phoneNumber}</td>
                    <td>
                        <#if staff.isUser()>
                            <form action="/admin/staff/hire" method="post">
                                <input type="hidden" value="${staff.idStaff}" name="idStaff">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <button type="submit">Нанять</button>
                            </form>
                        </#if>
                    </td>
                </tr>
            </#if>
        </#list>
    </table>
<#else>
    <h3>Пользователей нет</h3>
</#if>
<form action="/staff" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>
</body>
</html>