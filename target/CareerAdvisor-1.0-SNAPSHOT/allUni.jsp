<%--Importing all the dependent classes--%>
<%@page errorPage="error.jsp" isErrorPage="false" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@ page import="com.careeradvisor.university.UniversityBean" %>

<%--Session Handling--%>
<%
    String name=(String)session.getAttribute("adminName");
    if(name == null){
        response.sendRedirect("loginPage.jsp");
    }
%>

<% ArrayList<UniversityBean> uniList = (ArrayList) request.getAttribute("uniList");
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
        <link rel="stylesheet" href="css/viewAll.css" />
        <link rel="stylesheet" href="./css/navbar.css"/>

        <title>All University</title>
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
            <table class="universities">
                <thead>
                    <th class="username">Name</th>
                    <th>Ranking</th>
                    <th class="location">Location</th>
                    <th>Type</th>
                    <th>Edit</th>
                    <th>Remove</th>
                </thead>
                <tbody>

                <%
                    // Iterating through subjectList

                    if(request.getAttribute("uniList") != null)  // Null check for the object
                    {
                        Iterator<UniversityBean> iterator = uniList.iterator();  // Iterator interface

                        while(iterator.hasNext())  // iterate through all the data until the last record
                        {
                            UniversityBean uni = iterator.next();
                %>
                <tr>
                    <td><%= uni.getUniversity_name()%></td>
                    <td><%= uni.getRanking()%></td>
                    <td><%= uni.getLocation()%></td>
                    <td><%= uni.getUniversity_type()%></td>

                    <td> <form action="uniController?action=editUni" method="post">
                        <button type="submit"><i class="far fa-edit"></i></button>

                        <input type="hidden" name="uniId" value="<%= uni.getUniversity_name()%>"/>
                    </form></td>
                    <td>
                        <form action="uniController?action=removeUni" method="post">
                            <button type="submit"><i class="far fa-trash-alt"></i></button>

                            <input type="hidden" name="uniId" value="<%= uni.getId()%>"/>
                        </form>
                    </td>
                </tr>

                <%
                        }
                    }
                %>


                </tbody>
            </table>
        </div>
    </body>
</html>
