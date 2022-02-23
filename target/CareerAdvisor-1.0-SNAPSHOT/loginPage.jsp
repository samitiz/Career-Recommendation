<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
            integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        />
        <link rel="stylesheet" href="./css/login.css" />
        <title>Login Form</title>
    </head>
    <body>
        <div class="image"><img src="./images/signin2.svg" alt="" /></div>
        <div class="container">
            <div class="header">
                <h2>Welcome Back!</h2>
                <p>We're so excited to see you again</p>
            </div>
            <form
                class="form"
                id="form"
                action="userController?action=login"
                onsubmit="return checkInputs()"
                method="post"
                autocomplete="off"
            >
                <div class="form-control">
                    <label for="username">Username</label>
                    <input
                        placeholder="e.g. samitiz"
                        type="text"
                        id="username"
                        name="username"
                        oninput="checkUser()"
                    />
                    <small>Error Message</small>
                </div>
                <div class="form-control white">
                    <label for="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        oninput="checkPass()"
                    />
                    <i class="fa fa-eye" id="eye" onclick="toogle()"></i>
                    <small>Error Message</small>
                    <%
                        if(request.getAttribute("error") != null)
                        {%>
                    <span style="color: red" ><%=request.getAttribute("error")%></span>
                    <%}else{%>
                    <span  ></span>
                    <%}%>
                </div>
                <button type="submit" name="action" value="login">SIGN IN</button>
            </form>
            <div class="create-acc">
                <p>
                    Don't have an account?
                    <a href="signupPage.jsp">Sign up</a>
                </p>
            </div>
        </div>
        <script src="js/login.js"></script>
    </body>
</html>
