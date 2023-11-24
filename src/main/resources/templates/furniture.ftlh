<!DOCTYPE html>
<html lang="">
<head>
    <title>Furniture store</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/tableStyle.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<body>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>
</header>
<h1>Furniture</h1>
<h4>Мебельный магазин</h4>
<form action="/furniture" method="get">
    Поиск по названию мебели:
    <input type="text" name="nameOfFurniture"/>
    <button type="submit">Поиск</button>
</form>

<#if furnitures?has_content>
    <table>
        <tr><th>Название мебели</th><th>Цена мебели(руб.)</th><th>Подробная информация о мебели</th><#if staff.isManager()><th>Ред. информации</th></#if></tr>
        <#list furnitures as furniture>
            <tr>
                <td>${furniture.nameOfFurniture}</td>
                <td>${furniture.priceOfFurniture}</td>
                <td><a href="/furniture/${furniture.id_furniture}">Подробнее...</a></td>
                <#if staff.isManager()><td><a href="/furniture/${furniture.id_furniture}/edit">Редактировать</a></td></#if>
            </tr>
        </#list>
    </table>
<#else><h3>Мебели нет</h3>
</#if>

<#if staff.isManager()>
    <h3>Создать новую мебель</h3>
    <form action="/furniture/create" method="post" enctype="multipart/form-data">

        Название мебели: <input type="text" name="nameOfFurniture"/><br><br>
        Количество мебели: <input type="number" name="amountOfFurniture"/><br><br>
        Цена: <input type="number" name="priceOfFurniture"/><br><br>

        Размеры мебели:
        <select name="size">
            <option disabled selected> Выберите размеры </option>
            <#list sizes as size>
                <option value="${size.id_size}"> ${size.length}Х${size.width}Х${size.height}</option>
            </#list>
        </select><br><br>

        Комната в которой должна находится мебель:
        <select name="rooms">
            <option disabled selected> Выберите комнату </option>
            <#list rooms as room>
                <option value="${room.id_rooms}"> ${room.nameOfRoom} </option>
            </#list>
        </select><br><br>

        Изготовитель:
        <select name="manufacturer">
            <option disabled selected> Выберите изготовителя </option>
            <#list manufacturers as manufacturer>
                <option value="${manufacturer.id_manufacturer}"> ${manufacturer.nameOfManufacturer} </option>
            </#list>
        </select><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Добавить мебель</button>
    </form>
</#if>
</body>
</html>