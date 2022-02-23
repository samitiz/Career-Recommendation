<%@ page import="java.util.ArrayList" %>
<%@ page import="com.careeradvisor.degree.DegreeBean" %>
<%@ page import="java.util.Iterator" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<%--Session Handling--%>
<%
    String name=(String)session.getAttribute("adminName");
    if(name == null){
        response.sendRedirect("loginPage.jsp");
    }
%>

<% ArrayList<DegreeBean> degreeList = (ArrayList) request.getAttribute("degreeList");
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

        <title>All Degrees</title>
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
                    <th class="id">ID</th>
                    <th class="username">Name</th>
                    <th class="remove">Edit</th>
                    <th class="remove">Remove</th>
                </thead>
                <tbody>
                <%
                    // Iterating through subjectList

                    if(request.getAttribute("degreeList") != null)  // Null check for the object
                    {
                        Iterator<DegreeBean> iterator = degreeList.iterator();  // Iterator interface

                        while(iterator.hasNext())  // iterate through all the data until the last record
                        {
                            DegreeBean degree = iterator.next();
                %>
                <tr>
                    <td><%= degree.getId()%></td>
                    <td><%= degree.getDegree_name()%></td>

                    <td> <form action="degreeController?action=editDegree" method="post">
                        <button type="submit"><i class="far fa-edit"></i></button>

                        <input type="hidden" name="uniId" value="<%= degree.getDegree_name()%>"/>
                    </form></td>
                    <td>
                        <form action="degreeController?action=removeDegree" method="post">
                            <button type="submit"><i class="far fa-trash-alt"></i></button>

                            <input type="hidden" name="degreeId" value="<%= degree.getId()%>"/>
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
