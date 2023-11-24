<!DOCTYPE html>
<html lang="">
<head>
    <title>Sizes of furniture</title>
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
<h1>Sizes of furniture</h1>
<h4>Размеры мебели</h4>

<#if sizes?has_content>
    <table>
        <tr>
            <th>Номер<br>размеров</th>
            <th>Длина</th>
            <th>Ширина</th>
            <th>Высота</th>
            <#if staff.isManager()>
                <th>Редактирование инф.</th>
            </#if>
        </tr>
        <#list sizes as size>
            <tr>
                <td>${size.id_size}</td>
                <td>${size.length}</td>
                <td>${size.width}</td>
                <td>${size.height}</td>
                <#if staff.isManager()><td><a href="/size/${size.id_size}/edit">Редактирование</a></td></#if>
            </tr>
        </#list>
    </table>
<#else>
    <h3>Размеров нет</h3>
</#if>

<#if staff.isManager()>
<form action="/size/delete" method="post">
    Введите номер размера для удаления: <input type="number" name="id_size" min="1" step="1" style="width: 55px"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Удалить размеры</button>
</form>
</#if>

<#if staff.isSeller() || staff.isManager()>
<h3>Записать новые размеры мебели</h3>
<form action="/size/create" method="post" enctype="multipart/form-data">
    Длина мебели: <input type="number" name="length"/><br><br>
    Ширина мебели: <input type="number" name="width"/><br><br>
    Высота мебели: <input type="number" name="height"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Записать размеры</button>
</form>
</#if>
</body>
</html>