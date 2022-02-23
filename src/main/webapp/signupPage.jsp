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
        <link rel="stylesheet" href="./css/signup.css" />
        <title>Signup Form</title>
    </head>
    <body>
        <div class="image"><img src="./images/back-l.svg" alt="" /></div>

        <div class="container">
            <div class="header">
                <h2>Create Account</h2>
            </div>
            <form
                class="form"
                id="form"
                action="userController?action=signup"
                method="post"
                onsubmit="return checkInputs()"
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
                <div class="form-control">
                    <label for="email">Email</label>
                    <input
                        placeholder="e.g. sam@example.com"
                        type="text"
                        id="email"
                        name="email"
                        oninput="checkEmail()"
                    />
                    <small>Error Message</small>
                    <%
                        if(request.getAttribute("error") != null)
                        {%>
                    <span style="color: red; margin-top: 5px; text-align: left" ><%=request.getAttribute("error")%></span>
                    <%}%>
                </div>
                <div class="form-control white">
                    <label for="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        autocomplete="on"
                        oninput="checkPass()"
                    />
                    <i
                        class="fa fa-eye"
                        id="eye-p"
                        onclick="toogle('password','eye-p')"
                    ></i>
                    <small>Error Message</small>
                </div>
                <div class="form-control white">
                    <label for="confirmPassword">Confirm Password</label>
                    <input
                        type="password"
                        id="confirmPassword"
                        name="confirmPassword"
                        autocomplete="on"
                        oninput="checkcPass()"
                    />
                    <i
                        class="fa fa-eye"
                        id="eye-c"
                        onclick="toogle('confirmPassword','eye-c')"
                    ></i>

                    <small>Error Message</small>


                </div>
                <button type="submit" name="action" value="signup">SIGN UP</button>
            </form>
            <div class="member">
                <p>
                    Already a member?
                    <a href="loginPage.jsp">Sign in</a>
                </p>
            </div>
        </div>
        <script src="js/signup.js"></script>
    </body>
</html>
