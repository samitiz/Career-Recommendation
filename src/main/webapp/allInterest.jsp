<%--Importing all the dependent classes--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.careeradvisor.interest.InterestBean"%>
<%@page import="java.util.Iterator"%>
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
        <link rel="stylesheet" href="css/viewAll.css" />
        <link rel="stylesheet" href="./css/navbar.css"/>

        <title>All Interests</title>
    </head>
    <body>
    <% ArrayList<InterestBean> interestList = (ArrayList) request.getAttribute("interests");

    %>
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

                    if(request.getAttribute("interests") != null)  // Null check for the object
                    {
                        Iterator<InterestBean> iterator = interestList.iterator();  // Iterator interface

                        while(iterator.hasNext())  // iterate through all the data until the last record
                        {
                            InterestBean interest = iterator.next();
                            String interest_name = interest.getInterest_name();
                            int interest_id = interest.getId();

                %>
                <tr>
                    <td><%=interest_id%></td>
                    <td><%=interest_name%></td>
                    <td> <form action="interestController?action=updateInterest" method="post">
                        <button type="submit"><i class="far fa-edit"></i></button>

                        <input type="hidden" name="interestId" value="<%= interest_name%>"/>
                    </form></td>
                    <td>
                        <form action="interestController?action=removeInterest" method="post">
                            <button type="submit"><i class="far fa-trash-alt"></i></button>

                            <input type="hidden" name="interestId" value="<%= interest_id%>"/>
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
