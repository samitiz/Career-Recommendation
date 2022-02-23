<%@page errorPage="error.jsp" isErrorPage="false" %>

<%--Session Handling--%>
<%
    String name=(String)session.getAttribute("adminName");
    if(name == null){
        response.sendRedirect("loginPage.jsp");
    }
%>
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
        <link rel="stylesheet" href="./css/addForm.css" />
        <link rel="stylesheet" href="./css/navbar.css"/>

        <title>Signup Form</title>
    </head>
    <body>

    <div class="navbar">
        <div class="home">
            <a href="adminPage.jsp">Career Advisor</a>
        </div>

        <div class="logout">
            <span class="name">${adminName}</span>
            <span class="line">|</span>
            <a class="exit" href="userController?action=logout"
            >Logout</a>
        </div>
    </div>
        <div class="container">
            <div class="header">
                <h2>Add User</h2>
            </div>
            <form
                class="form"
                id="form"
                action="userController"
                method="post"
<%--                onsubmit="return checkInputs()"--%>
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
                        required
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
                        required
                    />
                    <small>Error Message</small>
                </div>
                <div class="form-control white">
                    <label for="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        autocomplete="on"
                        oninput="checkPass()"
                        required
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
                <% String error = (String )request.getAttribute("error");
                    if(error != null){
                %>
                <div style="color: #ed4245; margin-bottom: 10px;"><%=error%></div>
                <%}%>

                <button type="submit" name="action" value="signup">Create User</button>
                <input type="hidden"  name="user" value="admin">
            </form>
            <script src="js/addForms.js"></script>
        </div>
    </body>
</html>
