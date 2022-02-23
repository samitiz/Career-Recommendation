<%--Importing all the dependent classes--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.careeradvisor.interest.InterestBean"%>
<%@page import="java.util.Iterator"%>
<%@page errorPage="error.jsp" isErrorPage="false" %>


<%--    Session Handling --%>

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
        <link rel="stylesheet" href="css/interestPage.css" />
        <link rel="stylesheet" href="./css/navbar.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
            integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        />
        <title>Interest Page</title>
    </head>
    <body>

    <div class="navbar">
            <div class="home">
                <a href="interestController?action=listInterest">Career Advisor</a>
            </div>

            <div class="logout">
                <span class="name">${username}</span>
                <span class="line">|</span>
                <a class="exit" href="userController?action=logout"
                >Logout</a>
            </div>
    </div>
        <% ArrayList<InterestBean> interestList = (ArrayList) request.getAttribute("interestList");
        %>
        <%--Assigning ArrayList object containing interests data to the local object --%>


        <div class="container">
            <div class="header-wrapper">LISTEN TO YOUR <span>HEART</span></div>
            <div class="search-wrapper">
                <input
                    type="text"
                    name=""
                    id=""
                    placeholder="Search your interest"
                />
                <i class="fas fa-search"></i>
            </div>

            <div class="interest-wrapper">
                <p>SELECT YOUR INTEREST</p>
                <form action="degreeController?action=degreeList" method="POST" name="interests">
                    <div class="select">
                        <%
                            // Iterating through interestList

                            if(request.getAttribute("interestList") != null)  // Null check for the object
                            {
                                Iterator<InterestBean> iterator = interestList.iterator();  // Iterator interface

                                while(iterator.hasNext())  // iterate through all the data until the last record
                                {
                                    InterestBean interest = iterator.next();
                                    String interest_name = interest.getInterest_name();
                        %>
                        <input class = "select-item" value=<%=interest_name %> name="inte" type="submit"/>

                        <%
                                }
                            }
                        %>
                    </div>
                </form>
            </div>
        </div>
        <script src="js/interest.js"></script>
    </body>
</html>
