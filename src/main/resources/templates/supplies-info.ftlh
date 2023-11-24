<!DOCTYPE html>
<html>
<head>
    <title>Supplies-info</title>
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
<h1>Supply</h1>
<h4>Подробная информация о поставке</h4>

<#if supplies.suppliedFurnitureToSupplies?has_content>
    <table>
        <tr><th>Название мебели</th><th>Кол-во поставленной<br>мебели</th></tr>
        <#list supplies.suppliedFurnitureToSupplies as suppliedFurniture>
            <tr>
                <td>${suppliedFurniture.id.furniture.nameOfFurniture}</td>
                <td>${suppliedFurniture.amount}</td>
            </tr>
        </#list>
    </table>
<#else><h3>Поставленной мебели нет</h3>
</#if>
</form>
<form action="/supplies" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>
</body>
</html>