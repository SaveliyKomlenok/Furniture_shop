<!DOCTYPE html>
<html>
<head>
    <title>Manufacturer-edit</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>

</header>
<h3>Редактирование информации об изготовителе</h3>
<hr>
<form action="/manufacturer/${manufacturer.id_manufacturer}/editInformation" method="post" enctype="multipart/form-data">
    <div>
        Наименование изготовителя: <input type="text" value="${manufacturer.nameOfManufacturer}" required name="nameOfManufacturer"/><br><br>
        Адрес изготовителя: <input type="text" value="${manufacturer.addressOfManufacturer}" required name="addressOfManufacturer"/><br><br>
        Номер телефона изготовителя: <input type="text" value="${manufacturer.phoneNumberOfManufacturer}" required name="phoneNumberOfManufacturer"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Подтвердить</button>
    </div>
</form>
<form action="/manufacturer" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>
</html>