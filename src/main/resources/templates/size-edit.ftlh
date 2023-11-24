<!DOCTYPE html>
<html>
<head>
    <title>Size-edit</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>
</header>
<h3>Редактирование информации о размерах</h3>
<hr>
<form action="/size/${size.id_size}/editInformation" method="post" enctype="multipart/form-data">
    <div>
        Длина мебели: <input type="number" value="${size.length}" required name="length"/><br><br>
        Ширина мебели: <input type="number" value="${size.width}" required name="width"/><br><br>
        Высота мебели: <input type="number" value="${size.height}" required name="height"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Подтвердить</button>
    </div>
</form>
<form action="/size" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>
</html>