<!DOCTYPE html>
<html lang="">
<head>
    <title>Rooms</title>
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
<h1>Rooms</h1>
<h4>Комнаты</h4>
<form action="/rooms" method="get">
    Поиск по названию комнаты:
    <input type="text" name="nameOfRoom"/>
    <button type="submit">Поиск</button>
</form>
<#if rooms?has_content>
    <table>
        <tr>
            <th>Номер<br>комнаты</th>
            <th>Название комнаты</th>
            <#if staff.isManager()>
                <th>Редактирование инф.</th>
            </#if>
        </tr>
        <#list rooms as room>
            <tr>
                <td>${room.id_rooms}</td>
                <td>${room.nameOfRoom}</td>
                <#if staff.isManager()><td><a href="/rooms/${room.id_rooms}/edit">Редактирование</a></td></#if>
            </tr>

        </#list>
    </table>
<#else>
    <h3>Комнат нет</h3>
</#if>


<#if staff.isManager()>
<form action="/rooms/delete" method="post">
    Введите номер комнаты для удаления: <input type="number" name="id_rooms" style="width: 55px"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Удалить комнату</button>
</form>
</#if>

<#if staff.isSeller() || staff.isManager()>
<h3>Создать новую комнату</h3>
<form action="/rooms/create" method="post" enctype="multipart/form-data">
    Название комнаты: <input type="text" name="nameOfRoom"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Добавить комнату</button>
</form>
</#if>
</body>
</html>