<!DOCTYPE html>
<html lang="">
<head>
    <title>Sales furniture</title>
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
<h1>Sales furniture</h1>
<form action="/sales" method="get">
    Поиск по дате продажи мебели:
    <input type="date" name="dateOfSales"/>
    <button type="submit">Поиск</button>
</form>

<#if sales?has_content>
    <h4>Продажи мебели</h4>
    <table>
        <tr><th>Дата продажи</th><th>Ф.И.О.</th><th>Подробная инф.</th></tr>
        <#list sales as sale>
            <#if sale.staff??>
                <tr><td>${sale.dateOfSales}</td>
                    <td>${sale.staff.fullName}</td>
                    <td><a href="/sales/${sale.id_sales}">Подробнее...</a></td>
                </tr>
            <#else>
                <tr><td>${sale.dateOfSales}</td>
                    <td>Работник уволен</td>
                    <td><a href="/sales/${sale.id_sales}">Подробнее...</a></td>
                </tr>
            </#if>
        </#list>
    </table>
<#else>
    <h3>В указанную дату продажи не производились</h3>
</#if>

<#if staff.isSeller()>
    <br><h3>Создать продажу мебели</h3>
    <form action="/sales/startCreate" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Продать мебель</button>
    </form>
</#if>

</body>
</html>