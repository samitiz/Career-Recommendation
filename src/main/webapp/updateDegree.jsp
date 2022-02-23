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

        <title>Update Degree</title>
    </head>
    <body>
    <div class="navbar">
        <div class="home">
            <a href="adminPage.jsp">Career Advisor</a>
        </div>

        <div class="logout">
            <span class="name">${adminName}</span>
            <span class="line">|</span>
            <a class="exit" href="logout"
            >Logout</a>
        </div>
    </div>
        <div class="container">
            <div class="header">
                <h2>Update Degree</h2>
            </div>
            <form
                class="form"
                id="form"
                action="Insert"
                method="post"
                autocomplete="off"
            >
                <div class="form-control">
                    <label for="degree-name">Degree Name</label>
                    <input
                        placeholder="e.g. Punjab University"
                        type="text"
                        id="degree-name"
                        name="degree-name"
                        required
                    />
                    <small>Error Message</small>
                </div>
                <div class="form-control">
                    <label for="degree-name">Select University</label>
                    <select name="" id="">
                        <option value=""></option>
                        <option value="PUCIT">PUCIT</option>
                        <option value="PUCIT">PUCIT</option>
                        <option value="PUCIT">PUCIT</option>
                        <option value="PUCIT">PUCIT</option>
                        <option value="PUCIT">PUCIT</option>
                        <option value="PUCIT">PUCIT</option>
                    </select>
                    <small>Error Message</small>
                </div>

                <button type="submit">Update</button>
            </form>
            <script src="js/addForms.js"></script>
        </div>
    </body>
</html>
