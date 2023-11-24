<!DOCTYPE html>
<html>
<head>
    <title>Manufacturers</title>
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
<h4>Подробная информация об изготовителе</h4>
<b>Наименование изготовителя: </b>${manufacturer.nameOfManufacturer}<br>
<b>Адрес изготовителя: </b>${manufacturer.addressOfManufacturer}<br>
<b>Номер телефона изготовителя: </b>${manufacturer.phoneNumberOfManufacturer}<br>

<#if staff.isManager()>
<form action="/manufacturer/delete/${manufacturer.id_manufacturer}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Удалить</button>
</form>
</#if>
<form action="/manufacturer" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>
</body>
</html>