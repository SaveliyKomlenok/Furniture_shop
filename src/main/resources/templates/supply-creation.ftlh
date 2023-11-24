<!DOCTYPE html>
<html lang="">
<head>
    <title>Supply creation</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/linkStyle.css">
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
<h1>Supply creation</h1>
<h3>Поставить мебель</h3>

<div>
    <form action="/supplies/addFurniture" method="post" enctype="multipart/form-data">
        Мебель:
        <select name="id_furniture">
            <option disabled selected> Выберите мебель </option>
            <#list furnitures as furniture>
                <option value="${furniture.id_furniture}"> ${furniture.nameOfFurniture} </option>
            </#list>
        </select><br><br>

        Количество поставляемой мебели:
        <input type="number" min="1" step="1" name="amountOfFurniture"/><br><br>
        <div style="float: left">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить мебель в поставку</button>
        </div>
    </form>

    <#if supply.suppliedFurnitureToSupplies?has_content>
    <div style="float: left">
        <form action="/supplies/save" method="post" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Поставить выбранный товар(ы)</button>
        </form>
    </div>
    </#if>
    <div style="float: left">
        <form action="/supplies" method="get">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Назад</button>
        </form>
    </div>
</div>
<br><br>
<#if supply.suppliedFurnitureToSupplies?has_content>
    <table>
        <tr><th>Название мебели</th><th>Удаление</th></tr>
        <#list supply.suppliedFurnitureToSupplies as newSuppliedFurniture>
            <tr>
                <td><a href="/furniture/${newSuppliedFurniture.furniture.id_furniture}" style="color: #c668ff">${newSuppliedFurniture.furniture.nameOfFurniture}</a></td>
                <td><a href="/supplies/delete/${newSuppliedFurniture_index}" style="color: #ff1524" > Удалить </a></td>
            </tr>
        </#list>
    </table>
<#else>
    <h3>Добавьте мебель в поставки</h3>
</#if>

</body>
</html>