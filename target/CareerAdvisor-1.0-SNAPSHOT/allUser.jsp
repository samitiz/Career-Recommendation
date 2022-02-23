<%--Importing all the dependent classes--%>
<%@page errorPage="error.jsp" isErrorPage="false" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@ page import="com.careeradvisor.user.UserBean" %>

<%--Session Handling--%>
<%
    String name=(String)session.getAttribute("adminName");
    if(name == null){
        response.sendRedirect("loginPage.jsp");
    }
%>

<% ArrayList<UserBean> users = (ArrayList) request.getAttribute("users");
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
        <link
            href="https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css"
            rel="stylesheet"
        />
        <link rel="stylesheet" href="css/viewAll.css" />
        <link rel="stylesheet" href="./css/navbar.css"/>

        <title>All Users</title>
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
                    <th>Name</th>
                    <th class="username">Email</th>
                    <th>Edit</th>
                    <th>Remove</th>
                </thead>

                <tbody>
                <%
                    // Iterating through subjectList

                    if(request.getAttribute("users") != null)  // Null check for the object
                    {
                        Iterator<UserBean> iterator = users.iterator();  // Iterator interface

                        while(iterator.hasNext())  // iterate through all the data until the last record
                        {
                            UserBean user = iterator.next();
                %>
                <tr>
                    <td><%= user.getUsername()%></td>
                    <td><%= user.getEmail()%></td>
                    <td> <form action="userController" method="post">
                        <button type="submit" name="action" value="edit"><i class="far fa-edit"></i></button>

                        <input type="hidden" name="userId" value="<%= user.getId()%>"/>
                    </form></td>
                    <td>
                        <form action="userController" method="post">
                        <button type="submit" name="action" value="delete"><i class="far fa-trash-alt"></i></button>

                        <input type="hidden" name="userId" value="<%= user.getId()%>"/>
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
