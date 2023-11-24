<!DOCTYPE html>
<html>
<head>
    <title>Room-edit</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>

</header>
<h3>Редактирование информации о комнате</h3>

<form action="/rooms/${room.id_rooms}/editInformation" method="post" enctype="multipart/form-data">
    <div>
        Название комнаты: <input type="text" value="${room.nameOfRoom}" required name="nameOfRoom"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Подтвердить</button>
    </div>
</form>
<form action="/rooms" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>
</html>