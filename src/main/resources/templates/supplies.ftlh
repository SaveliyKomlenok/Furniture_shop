<!DOCTYPE html>
<html lang="">
<head>
    <title>Supplied furniture</title>
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
<h1>Supplied furniture</h1>
<form action="/supplies" method="get">
    Поиск по дате поставки мебели:
    <input type="date" name="dateOfSupplies"/>
    <button type="submit">Поиск</button>
</form>

<#if supplies?has_content>
    <h4>Поставленная мебель</h4>
    <table>
        <tr><th>Дата поставки</th><th>Ф.И.О.</th><th>Подробная инф.</th></tr>
        <#list supplies as supply>
            <#if supply.staff??>
                <tr>
                    <td>${supply.dateOfSupplies}</td>
                    <td>${supply.staff.fullName}</td>
                    <td><a href="/supplies/${supply.id_supplies}">Подробнее...</a></td>
                </tr>
            <#else>
                <tr>
                    <td>${supply.dateOfSupplies}</td>
                    <td>Работник уволен</td>
                    <td><a href="/supplies/${supply.id_supplies}">Подробнее...</a></td>
                </tr>
            </#if>
        </#list>
    </table>
<#else>
    <h3>В указанную дату поставки не производились</h3>
</#if>

<#if staff.isManager()>
    <br><h3>Создать поставку мебели</h3>
    <form action="/supplies/startCreate" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Поставить мебель</button>
    </form>
</#if>
</body>
</html>