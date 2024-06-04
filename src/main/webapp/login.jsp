<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Interview Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        #container-box {
            height: 100vh;
        }

        h5 {
            margin-bottom: 20px;
            text-align: center;
        }

        input[type="text"],
        input[type="password"] {
            border: 2px solid #dcdcdc;
            border-radius: 10px;
            box-sizing: border-box;
            padding: 10px;
            width: 400px;
        }

        input[type="text"] {
            margin-left: 50px;
        }

        input[type="password"] {
            margin-left: 55px;
        }

        .box {
            margin-bottom: 15px;
        }

        #login-box {
            background-color: rgba(255, 255, 255, 0.5);
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            margin: 0 auto;
            backdrop-filter: blur(0px);
        }

        p {
            font-weight: bold;
        }

        p#a-error, p#p-error {
            margin: 10px 0 0 130px;
            color: red;
        }
    </style>
</head>
<body style="margin: 0; padding: 0; height: 100vh; background: url('https://upload.wikimedia.org/wikipedia/commons/e/ed/Nokia_office_building_in_Hervanta_Tampere_1.jpg') center/cover no-repeat;">
<div class="container-fluid d-flex align-items-center justify-content-center" id="container-box">
    <div class="row">
        <div class="col-12">
            <div id="login-box">
                <h5>IMS Login</h5>
                <form action="login" method="post" id="loginForm">
                    <div class="box">
                        <span>Username</span>
                        <input type="text" name="account" placeholder="Username" id="account">
                        <p id="a-error"></p>
                    </div>
                    <div class="box">
                        <span>Password</span>
                        <input type="password" name="password" placeholder="Password" id="password">
                        <p id="p-error"></p>
                    </div>
                    <div class="text-center">
                        <p id="wrong-credentials" style="color: red;">${sessionScope.error}</p>
                    </div>
                    <div class="text-end box">
                        <button type="submit" class="btn btn-success">Login</button>
                    </div>
                    <div class="text-center box">
                        <p>Only members in our company have accounts to log in.</p>
                        <p>If you encounter any issues, please contact people with authorities.</p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>
<script>
    const loginForm = document.getElementById('loginForm');
    const wrongCredentials = document.getElementById('wrong-credentials');

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();
        wrongCredentials.textContent = '';

        const account = document.getElementById('account').value;
        const password = document.getElementById('password').value;
        const accountError = document.getElementById('a-error');
        const passwordError = document.getElementById('p-error');
        let errorCount = 0;

        if (account.trim() === '') {
            accountError.textContent = 'Please fill in the username field.';
            errorCount ++;
        } else {
            accountError.textContent = '';
        }

        if (password.trim() === '') {
            passwordError.textContent = 'Please fill in the password field.';
            errorCount ++;
        } else {
            passwordError.textContent = '';
        }

        if (errorCount === 0) {
            loginForm.submit();
        }
    });
</script>
</body>
</html>