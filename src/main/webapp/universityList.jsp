<%@page import="java.util.ArrayList"%>
<%@page import="com.careeradvisor.university.UniversityBean"%>
<%@page import="java.util.Iterator"%>
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
        <link rel="stylesheet" href="./css/universityList.css" />
        <link rel="stylesheet" href="./css/navbar.css" />
        <title>Univeristy</title>
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
    <% ArrayList<UniversityBean> uniListPublic = (ArrayList) request.getAttribute("uniListPublic");
        ArrayList<UniversityBean> uniListPrivate = (ArrayList) request.getAttribute("uniListPrivate");
    %>
        <p class="top">TOP UNIVERSITIES FOR YOUR INTEREST</p>
        <!-- <p class="bottom">Select to explore it more...</p> -->
        <div class="container">

            <div class="left-col">
                <form action="uniController?action=uniDetail" method="POST" name="university">
                <div class="public">Public Sector</div>
                <div class="select">
                    <%
                        // Iterating through subjectList

                        if(request.getAttribute("uniListPublic") != null)  // Null check for the object
                        {
                            Iterator<UniversityBean> iterator1 = uniListPublic.iterator();  // Iterator interface

                            while(iterator1.hasNext())  // iterate through all the data until the last record
                            {
                                UniversityBean university = iterator1.next(); //assign individual employee record to the employee class object
                                String university_name = (university.getUniversity_name()).trim();

                    %>
                    <input class = "select-item" value="<%=university_name %>" name="uni" type="submit"/>

                    <%
                            }
                        }
                    %>

                </div>
                </form>
            </div>
            <div class="right-col">
                <form action="uniController?action=uniDetail" method="POST" name="university">
                <div class="public">Private Sector</div>


                    <div class="select">
                        <%
                            // Iterating through subjectList

                            if(request.getAttribute("uniListPrivate") != null)  // Null check for the object
                            {
                                Iterator<UniversityBean> iterator1 = uniListPrivate.iterator();  // Iterator interface

                                while(iterator1.hasNext())  // iterate through all the data until the last record
                                {
                                    UniversityBean university = iterator1.next(); //assign individual employee record to the employee class object
                                    String university_name = (university.getUniversity_name()).trim();
                        %>
                        <input class = "select-item" value="<%=university_name %>" name="uni" type="submit"/>

                        <%
                                }
                            }
                        %>


                </div>
            </form>
            </div>
        </div>
    </body>
</html>
