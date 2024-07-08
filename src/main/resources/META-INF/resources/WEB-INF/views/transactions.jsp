<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transactions</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        a {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 20px;
            color: white;
            background-color: #4CAF50;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <a href="/bank/main">Main Menu</a>
    <h1>Transactions</h1>
    <table>
        <tr>
            <th>Transaction ID</th>
            <th>Transaction Type</th>
            <th>Status</th>
            <th>Transaction Amount</th>
            <th>Remaining Balance</th>
            <th>Date</th>
            <th>Account Number</th>
        </tr>
        <c:forEach var="transaction" items="${transactionsList}">
            <tr>
                <td>${transaction.trnxId}</td>
                <td>${transaction.type}</td>
                <td>${transaction.status}</td>
                <td>${transaction.amount}</td>
                <td>${transaction.remainingBalance}</td>
                <td>${transaction.timestamp}</td>
                <td>${transaction.account.accountNumber}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
