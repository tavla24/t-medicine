<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
</head>

<body>
    <div>
        <div>
            <span class="lead">List of Users </span>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Login</th>
                    <th>Password</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>${user.name}</th>
                    <th>${user.email}</th>
                    <th>${user.login}</th>
                    <th>${user.password}</th>
                    <th>${user.role}</th>
                </tr>
            </tbody>
        </table>
    </div>
</body>

</html>