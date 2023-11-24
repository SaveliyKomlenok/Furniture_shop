<!DOCTYPE html>
<html>
<head>
    <title>Staff edit</title>
    <link rel="stylesheet"  type="text/css" href="/static/styles/backgroundForPages.css">
    <link rel="stylesheet"  type="text/css" href="/static/styles/buttonStyle.css">
</head>
<body>
<header>
    <div style="float: left">
        <a href="/"><img width="45" height="40" src="/static/styles/emblemNumber2.png"></a>
    </div>

</header>
<h1>Редактирование работника</h1>
<h3>${staff.fullName}</h3>
<form action="/admin/staff/edit" method="post">
    <#list jobTitles as role>
        <div>
            <input type="checkbox" name="${role}" ${staff.jobTitles?seq_contains(role)?string("checked",  "")}>
            <label>${role}</label>
        </div>
    </#list>
    <input type="hidden" value="${staff.idStaff}" name="idStaff">
    <input type="hidden" value="${_csrf.token}" name="_csrf"><br>
    <button type="submit">Подтвердить</button>
</form>

<form action="/staff" method="get">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Назад</button>
</form>
<br>

</body>
</html>