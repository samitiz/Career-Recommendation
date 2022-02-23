<%--Importing all the dependent classes--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@ page import="com.careeradvisor.degree.DegreeBean" %>
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
        <link rel="stylesheet" href="./css/degreePage.css" />
        <link rel="stylesheet" href="./css/navbar.css" />
        <title>Degree Page</title>
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
    <% ArrayList<DegreeBean> degreeList = (ArrayList) request.getAttribute("degreeList");
    %>
    <%--Assigning ArrayList object containing Degree data to the local object --%>
        <p class="top">TOP DEGREE'S FOR YOUR INTEREST</p>
        <p class="bottom">Select to explore it more...</p>
        <div class="container">
            <form action="uniController?action=uniList" method="POST" name="universities">
            <div class="select">
                <%
                    // Iterating through degreeList

                    if(request.getAttribute("degreeList") != null)  // Null check for the object
                    {
                        Iterator<DegreeBean> iterator = degreeList.iterator();  // Iterator interface

                        while(iterator.hasNext())  // iterate through all the data until the last record
                        {
                            DegreeBean degree = iterator.next();
                            String degree_name = (degree.getDegree_name()).trim();
                %>
                <input type ="submit" class="select-item" value=" <%=degree_name %>" name="uni" />

                <%
                        }
                    }
                %>
            </div>
            </form>
        </div>
    </body>
</html>
