
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

        <title>Add University</title>
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
                <h2>Add University</h2>
            </div>
            <form
                class="form"
                id="form"
                action="uniController"
                method="post"
                autocomplete="off"
            >
                <div class="form-control">
                    <label for="uni-name">University Name</label>
                    <input
                        placeholder="e.g. Punjab University"
                        type="text"
                        id="uni-name"
                        name="uni-name"
                        required
                    />
                    <small>Error Message</small>
                </div>
                <div class="form-control">
                    <label for="worldRanking">World Ranking</label>
                    <input
                        placeholder="e.g. 1st"
                        type="number"
                        id="worldRanking"
                        name="worldRanking"
                        required
                    />
                    <small>Error Message</small>
                </div>
                <div class="form-control white">
                    <label for="city">City</label>
                    <input
                        type="text"
                        placeholder="e.g. Lahore"
                        id="city"
                        name="city"
                        autocomplete="on"
                        required
                    />
                    <small>Error Message</small>
                </div>
                <div class="form-control white">
                    <label for="website">Website</label>
                    <input
                        type="url"
                        id="website"
                        name="website"
                        autocomplete="on"
                        placeholder="e.g. www.pucit.edu.pk"
                        required
                    />

                    <small>Error Message</small>
                </div>
                <div class="form-control white">
                    <label for="type">Type</label>
                    <select name="type">
                        <option value=""></option>
                        <option value="Public">Public</option>
                        <option value="Private">Private</option>
                    </select>
                    <small>Error Message</small>
                </div>
                <% String error = (String )request.getAttribute("error-msg");
                    if(error != null){
                %>
                <div style="color: #ed4245; margin-bottom: 10px;"><%=error%></div>
                <%}%>
                <button type="submit" name="action" value="addUni">Create</button>
            </form>
            <script src="/js/addForms.js"></script>
        </div>
    </body>
</html>
