<!DOCTYPE html>
<html lang="">
<head>
    <title>Manufacturers</title>
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
<h1>Manufacturers</h1>
<h4>Изготовители</h4>
<form action="/manufacturer" method="get">
    Поиск по наименованию изготовителя:
    <input type="text" name="nameOfManufacturer"/>
    <button type="submit">Поиск</button>
</form>

<#if manufacturers?has_content>
    <table>
        <tr><th>Наименование<br>изготовителя</th><th>Номер телефона</th><th>Подробная инф.</th><#if staff.isManager()><th>Редактирование инф.</th></#if></tr>
        <#list manufacturers as manufacturer>
            <tr><td>${manufacturer.nameOfManufacturer}</td>
                <td>${manufacturer.phoneNumberOfManufacturer}</td>
                <td><a href="/manufacturer/${manufacturer.id_manufacturer}">Подробнее...</a></td>
               <#if staff.isManager()><td><a href="/manufacturer/${manufacturer.id_manufacturer}/edit">Редактировать</a></td></#if>
            </tr>
        </#list>
    </table>
<#else>
    <h3>Изготовителей мебели нет</h3>
</#if>

<#if staff.isSeller() || staff.isManager()>
<h3>Создать нового изготовителя</h3>
<form action="/manufacturer/create" method="post" enctype="multipart/form-data">
    Наименование изготовителя: <input type="text" name="nameOfManufacturer"/><br><br>
    Адрес изготовителя: <input type="text" name="addressOfManufacturer"/><br><br>
    Номер телефона изготовителя: <input type="text" name="phoneNumberOfManufacturer"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Добавить изготовителя</button>
</form>
</#if>
</body>
</html>