<%@page errorPage="error.jsp" isErrorPage="false" %>
<%@ page import="com.careeradvisor.interest.InterestBean" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@ page import="com.careeradvisor.degree.DegreeBean" %>

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

        <title>Update Interest</title>
    </head>
    <body>

    <% InterestBean interest = (InterestBean) request.getAttribute("interest");
    %>
    <% ArrayList<DegreeBean> degreeList = (ArrayList) request.getAttribute("allDegree");
    %>
    <div class="navbar">
        <div class="home">
            <a href="adminPage.jsp">Career Advisor</a>
        </div>

        <div class="logout">
            <span class="name">${adminName}</span>
            <span class="line">|</span>
            <a class="exit" href="userController>action=logout"
            >Logout</a>
        </div>
    </div>
        <div class="container">
            <div class="header">
                <h2>Update Interest</h2>
            </div>
            <form
                class="form"
                id="form"
                action="interestController?action=updateInterest"
                method="post"
                autocomplete="off"
            >
                <div class="form-control">
                    <label for="interest-name">Interest Name</label>
                    <input
                        placeholder="e.g. Computer"
                        type="text"
                        id="interest-name"
                        name="interest-name"
                        required
                        value="<%=interest.getInterest_name()%>"
                    />
                    <small>Error Message</small>
                </div>
                <div class="form-control">
                    <label for="">Select Degree</label>
                    <select name="degree-name" id="">
                        <option value=""></option>
                        <%
                            // Iterating through subjectList

                            if(request.getAttribute("allDegree") != null)  // Null check for the object
                            {
                                Iterator<DegreeBean> iterator = degreeList.iterator();  // Iterator interface

                                while(iterator.hasNext())  // iterate through all the data until the last record
                                {
                                    DegreeBean degree = iterator.next(); //assign individual employee record to the employee class object
                                    String degree_name = degree.getDegree_name();
                        %>
                        <option value="<%=degree_name%>"><%=degree_name%></option>

                        <%
                                }
                            }
                        %>

                    </select>
                    <small>Error Message</small>
                </div>
                <input type="hidden" value="<%=interest.getInterest_name()%>" name="getName"/>
                <button type="submit">Update</button>
            </form>
            <script src="js/addForms.js"></script>
        </div>
    </body>
</html>
