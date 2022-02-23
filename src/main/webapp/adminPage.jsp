<%@page errorPage="error.jsp" isErrorPage="false" %>

<%--Session Handling--%>
<%
    String name=(String)session.getAttribute("adminName");
    if(name == null){
        response.sendRedirect("loginPage.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8" />
        <title>Admin Dashboard | Career Advisor</title>
        <link rel="stylesheet" href="./css/admin.css" />
        <link rel="stylesheet" href="./css/admin2.css"/>
        <link
            href="https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css"
            rel="stylesheet"
        />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    </head>
    <body>
        <div class="sidebar close">
            <div class="logo-details">
                <i class="bx bxl-c-plus-plus"></i>
                <span class="logo_name">Career Advisor</span>
            </div>
            <ul class="nav-links">
                <li>
                    <a href="#">
                        <i class="bx bx-grid-alt"></i>
                        <span class="link_name">Dashboard</span>
                    </a>
                    <ul class="sub-menu blank">
                        <li><a class="link_name" href="#">Dashboard</a></li>
                    </ul>
                </li>
                <li>
                    <div class="iocn-link">
                        <a href="#">
                            <i class="bx bxs-graduation"></i>
                            <span class="link_name">University</span>
                        </a>
                        <i class="bx bxs-chevron-down arrow"></i>
                    </div>
                    <ul class="sub-menu">
                        <li>
                            <a class="link_name" href="#">University</a>
                        </li>
                        <li>
                            <a href="addUni.jsp">Add University</a>
                        </li>

                        <li>
                            <a href="uniController?action=allUni">View All</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="iocn-link">
                        <a href="#">
                            <i class="bx bx-book-alt"></i>
                            <span class="link_name">Degree</span>
                        </a>
                        <i class="bx bxs-chevron-down arrow"></i>
                    </div>
                    <ul class="sub-menu">
                        <li><a class="link_name" href="#">Degree</a></li>
                        <li>
                            <a
                                href="addDegree.jsp"
                                >Add degree</a
                            >
                        </li>
                        <li>
                            <a
                                href="degreeController?action=degrees"
                                >View All</a
                            >
                        </li>

                    </ul>
                </li>

                <li>
                    <div class="iocn-link">
                        <a href="#">
                            <i class="bx bx-user"></i>
                            <span class="link_name">Users</span>
                        </a>

                        <i class="bx bxs-chevron-down arrow"></i>
                    </div>
                    <ul class="sub-menu">
                        <li><a class="link_name" href="#">Users</a></li>
                        <li>
                            <a
                                href="addUser.jsp"
                                >Add User</a
                            >
                        </li>
                        <li>
                            <a
                                href="userController?action=userList"
                                >View All</a
                            >
                        </li>

                    </ul>
                </li>

                <li>
                    <div class="iocn-link">
                        <a href="#">
                            <i class="bx bx-heart"></i>
                            <span class="link_name">Interest</span>
                        </a>

                        <i class="bx bxs-chevron-down arrow"></i>
                    </div>
                    <ul class="sub-menu">
                        <li><a class="link_name" href="#">Interest</a></li>
                        <li>
                            <a
                                href="degreeController?action=allDegree"
                                >Add Interest</a
                            >
                        </li>
                        <li>
                            <a
                                href="interestController?action=allInterest"
                                >View All</a
                            >
                        </li>
                    </ul>
                </li>

                <li>
                    <div class="profile-details">
                        <div class="profile-content">
                            <img src="./images/profile.jpg" alt="profileImg" />
                        </div>
                        <div class="name-job">
                            <div class="profile_name">Admin</div>
                            <div class="job">Web Desginer</div>
                        </div>

                        <a href="userController?action=logout"> <i class="bx bx-log-out"></i></a>
                    </div>
                </li>
            </ul>
        </div>
        <% String msg = (String)request.getAttribute("msg");%>
        <section class="home-section">
            <div class="home-content" id="home-content">
                <i class="bx bx-menu"></i>
            </div>
            <div class="home" id="home">
                <% if(msg != null){
                %>
                    <h3 class="success">
                        <i class="bx bxs-check-circle bx-flashing-hover"></i>&nbsp
                        <%=msg%>
                    </h3>

                <%} else{%>
                <h2>Welcome Admin</h2>
                <h3>
                    <i class="bx bxs-chevrons-left"></i>&nbsp Your tools are
                    there
                </h3>
                <%}%>

            </div>
        </section>
        <script src="js/addForms.js"></script>

        <script src="js/script.js"></script>
    </body>
</html>
