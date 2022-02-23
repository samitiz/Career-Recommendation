<%--Importing all the dependent classes--%>
<%@page import="com.careeradvisor.university.UniversityBean"%>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<%--Session Handling--%>
<%
    String name=(String)session.getAttribute("username");
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
        <link rel="stylesheet" href="css/universityDetails.css" />
        <link rel="stylesheet" href="./css/navbar.css"/>
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
            integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        />
        <title>University Detail</title>
    </head>
    <body>

        <div class="navbar">
            <div class="home">
                <a href="interestController?action=listInterest">Career Advisor</a>
            </div>

            <% UniversityBean university = (UniversityBean) request.getAttribute("university");
            %>
            <div class="logout">
                <span class="username">${username}</span>
                <span class="line">|</span>
                <a class="exit" href="userController?action=logout"
                >Logout</a>
            </div>
        </div>
        <div class="uniname">
            <p><%= university.getUniversity_name()%></p>
        </div>
        <div class="container">
            <div class="left">
                <img cass="img" src="./images/pucit.jpg" alt="" />
            </div>

            <div class="right">
                <table>
                    <tr>
                        <th>World Ranking</th>
                        <td><%= university.getRanking()%></td>
                    </tr>
                    <tr>
                        <th>Location</th>
                        <td><%= university.getLocation()%></td>
                    </tr>
                </table>
                <div class="item">
                    <a href="<%=university.getWebsite()%>">
                    <i class="fas fa-globe-asia"></i>
                    Website
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>
