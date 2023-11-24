<!DOCTYPE html>
<html>
<head>
    <title>Sales-info</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/tableStyle.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<body>
<h1>Sale</h1>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>

</header>
<h4>Подробная информация о продаже</h4>

<#if sales.soldFurnitureToSales?has_content>
    <table>
        <tr><th>Название мебели</th><th>Кол-во проданной<br>мебели</th></tr>
        <#list sales.soldFurnitureToSales as soldFurniture>
            <tr>
                <td>${soldFurniture.id.furniture.nameOfFurniture}</td>
                <td>${soldFurniture.amount}</td>
            </tr>
        </#list>
    </table>
<#else><h3>Проданной мебели нет</h3>
</#if>

</form>
<form action="/sales" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>
</body>
</html>