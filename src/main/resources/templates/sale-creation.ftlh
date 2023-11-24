<!DOCTYPE html>
<html lang="">
<head>
    <title>Sale creation</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/tableStyle.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/linkStyle.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<body>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>
</header>
<h1>Sale creation</h1>
<h3>Продать мебель</h3>

<div>
    <form action="/sales/addFurniture" method="post" enctype="multipart/form-data">
        Мебель:
        <select name="id_furniture">
            <option disabled selected> Выберите мебель </option>
            <#list furnitures as furniture>
                <option value="${furniture.id_furniture}"> ${furniture.nameOfFurniture} </option>
            </#list>
        </select><br><br>

        Количество мебели на продажу:
        <input type="number" min="1" step="1" name="amountOfFurniture"/><br><br>
        <div style="float: left">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить мебель в продажу</button>
        </div>
    </form>
    <#if sale.soldFurnitureToSales?has_content>
        <div style="float: left">
            <form action="/sales/save" method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit">Продать выбранный товар(ы)</button>
            </form>
        </div>
    </#if>
    <div style="float: left">
        <form action="/sales" method="get">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Назад</button>
        </form>
    </div>
</div>
<br><br>
<#if sale.soldFurnitureToSales?has_content>
    <table>
        <tr><th>Название мебели</th><th>Удаление</th></tr>
        <#list sale.soldFurnitureToSales as newSoldFurniture>
            <tr>
                <td><a href="/furniture/${newSoldFurniture.furniture.id_furniture}" style="color: #c668ff">${newSoldFurniture.furniture.nameOfFurniture}</a></td>
                <td><a href="/sales/delete/${newSoldFurniture_index}" style="color: #ff1524" > Удалить </a></td>
            </tr>
        </#list>
    </table>
<#else>
    <h3>Добавьте мебель в продажи</h3>
</#if>

</body>
</html>