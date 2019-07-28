<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
</head>

<body>
    <div>
        <span><strong>${loggedinuser}</strong>, welcome </span>
        <span class="floatRight"><a href="/logout">Logout</a></span>

        <div class="well lead">User Registration Form</div>
        <form method="POST" modelAttribute="user" class="form-horizontal">
            <input type="hidden" path="id" id="id" />

            <div>
                <label for="firstName">Name</label>
                <div>
                    <input type="text" path="name" id="name" />
                </div>
            </div>

            <div>
                <label for="login">Login</label>
                <div>
                    <input type="text" path="login" id="login" />
                </div>
            </div>

            <div>
                <label for="password">Password</label>
                <div>
                    <input type="password" path="password" id="password" />
                </div>
            </div>

            <div>
                <label for="email">Email</label>
                <div>
                    <input type="text" path="email" id="email" />
                </div>
            </div>

            <div>
                <label for="userProfiles">Roles</label>
                <div>
                        <input type="text" path="role" id="role" />
                </div>
            </div>

            <div>
                <br>
                <input type="submit" value="Register" /> or <a href="/list">Cancel</a>
            </div>
        </form>
    </div>
</body>

</html>