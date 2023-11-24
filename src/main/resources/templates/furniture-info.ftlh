<!DOCTYPE html>
<html>
<head>
    <title>Furniture store</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<body>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>
</header>
<h1>Furniture</h1>
<h4>Подробная информация о мебели</h4>
<b>Название мебели: </b>${furniture.nameOfFurniture}<br>
<b>Количество мебели: </b>${furniture.amountOfFurniture}<br>
<b>Цена: </b>${furniture.priceOfFurniture}<br>
<b>Размеры: </b>${furniture.size.length}Х${furniture.size.width}Х${furniture.size.height}<br>
<b>Номер комнаты: </b>${furniture.rooms.nameOfRoom}<br>
<b>Наименование изготовителя: </b>${furniture.manufacturer.nameOfManufacturer}<br>
<#if staff.isManager()>
    <form action="/furniture/delete/${furniture.id_furniture}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Удалить</button>
    </form>
</#if>

<form action="/furniture" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>
</body>
</html>