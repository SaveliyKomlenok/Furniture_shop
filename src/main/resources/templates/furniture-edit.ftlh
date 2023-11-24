<!DOCTYPE html>
<html>
<head>
    <title>Furniture-edit</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>
</header>
<br>
<h3>Редактирование информации о мебели</h3>
<form action="/furniture/${furniture.id_furniture}/editInformation" method="post" enctype="multipart/form-data">
    <div>
        Название мебели: <input type="text" value="${furniture.nameOfFurniture}" required name="nameOfFurniture"/><br><br>
        Количество мебели: <input type="number" value="${furniture.amountOfFurniture}" required name="amountOfFurniture"/><br><br>
        Цена: <input type="number" step="any" value="${furniture.priceOfFurniture}" required name="priceOfFurniture"/><br><br>

        Размеры мебели:
        <select required name="size">
            <option disabled> Выберите размеры </option>
            <option selected value="${furniture.size.id_size}">${furniture.size.length}X${furniture.size.width}X${furniture.size.height}</option>
            <#list sizes as size>
                <option value="${size.id_size}"> ${size.length}Х${size.width}Х${size.height}</option>
            </#list>
        </select><br><br>

        Комната в которой должна находится мебель:
        <select required name="rooms">
            <option disabled> Выберите комнату </option>
            <option selected value="${furniture.rooms.id_rooms}"> ${furniture.rooms.nameOfRoom} </option>
            <#list rooms as room>
                <option value="${room.id_rooms}"> ${room.nameOfRoom} </option>
            </#list>
        </select><br><br>

        Изготовитель:
        <select required name="manufacturer">
            <option disabled> Выберите изготовителя </option>
            <option selected value="${furniture.manufacturer.id_manufacturer}">${furniture.manufacturer.nameOfManufacturer}</option>
            <#list manufacturers as manufacturer>
                <option value="${manufacturer.id_manufacturer}"> ${manufacturer.nameOfManufacturer} </option>
            </#list>
        </select><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Подтвердить</button>
    </div>
</form>

<form action="/furniture" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>
</html>