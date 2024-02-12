<%@ page import="java.util.Map" %>
<%@ page import="com.test.oleksiy.demo1.WelcomeServlet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Статистика опитування</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        h1 {
            text-align: center;
        }
        table {
            width: 50%;
            margin: 0 auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .center {
            text-align: center;
        }
    </style>
</head>
<body>
<h1>Статистика опитування</h1>
<table>
    <tr>
        <th>Питання</th>
        <th>Кількість вибору</th>
    </tr>
    <%
        Map<String, Integer> answerCounts = WelcomeServlet.getAnswerCounts();
        for (Map.Entry<String, Integer> entry : answerCounts.entrySet()) {
    %>
    <tr>
        <td><%= entry.getKey() %></td>
        <td><%= entry.getValue() %></td>
    </tr>
    <%
        }
    %>
</table>

<div class="center">
    <form action="index.html">
        <button type="submit">Повернутися на головну сторінку</button>
    </form>
</div>
</body>
</html>
