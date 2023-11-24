<!DOCTYPE html>
<html>
<head>
    <title>Admin panel on Furniture shop</title>
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
<h1>Staff</h1>
<h4>Работник(ца) ${staff.fullName}</h4>

<b>ФИО: </b>${staff.fullName}<br>
<b>Логин: </b>${staff.login}<br>
<b>Номер телефона: </b>${staff.phoneNumber}<br>
<b>Дата создания акк.: </b> ${staff.dateOfCreated}<br>
<b>Активность: </b><#if staff.active>true
    <#else>false</#if><br>
<b>Роль:</b><#list staff.jobTitles as role>${role}<#sep>, </#list><br>

    <#if !staff.isAdmin()>
        <form action="/admin/staff/ban/${staff.idStaff}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
                <#if staff.active>
                    <button type="submit">Заблокировать</button>
                <#else>
                    <button type="submit">Разблокировать</button>
                </#if>
        </form>
    </#if>

        <#if !staff.isAdmin()>
            <form action="/admin/staff/edit/${staff.idStaff}" method="get">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit">Редактировать</button>
            </form>
        </#if>

        <#if !staff.isAdmin()>
            <form action="/staff/delete/${staff.idStaff}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit">Удалить</button>
            </form>
        </#if>

<form action="/staff" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>
</body>
</html>