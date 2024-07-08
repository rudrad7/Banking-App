<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Bank Account</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }
        h1, h2, h5 {
            color: #333;
            margin-top: 0;
        }
        h2 {
            margin-bottom: 20px;
        }
        .customer-details {
            margin-bottom: 20px;
        }
        .container form {
            margin-top: 20px;
        }
        .container button {
            background-color: #2196F3; /* Changed color to a shade of blue */
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 150px; /* Adjusted button width */
            margin: 0 10px; /* Added margin between buttons */
        }
        .container button:hover {
            background-color: #0b7dda; /* Darker shade on hover */
        }
        .button-container {
            display: flex;
            justify-content: center; /* Center align buttons horizontally */
            align-items: center;
            flex-wrap: wrap; /* Allow wrapping buttons */
        }
        .button-container form {
            margin-bottom: 10px; /* Added space between rows of buttons */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to DIGITAL BANK</h1>
        <h2>Customer Details</h2>
        <div class="customer-details">
            <h5>Account Number: ${accountNumber}</h5>
            <h5>Full Name: ${customer.fullName}</h5>
            <h5>User Name: ${customer.userName}</h5>
            <h5>Email: ${customer.email}</h5>
            <h5>Phone Number: ${customer.phoneNumber}</h5>
        </div>
        <div class="button-container">
            <form action="balance" method="post">
                <button type="submit">Display Balance</button>
            </form>

            <form action="withdraw" method="get">
                <button type="submit">Withdraw</button>
            </form>

            <form action="deposit" method="get">
                <button type="submit">Deposit</button>
            </form>

            <form action="transfer" method="get">
                <button type="submit">Transfer</button>
            </form>

            <form action="transactions" method="get">
                <button type="submit">Display Transactions</button>
            </form>

            <form action="logout" method="get">
                <button type="submit">Logout</button>
            </form>
        </div>
    </div>
</body>
</html>
