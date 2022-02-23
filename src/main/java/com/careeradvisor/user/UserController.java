package com.careeradvisor.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class UserController extends HttpServlet {
    UserDaoImp userDao;

    public void init() {
        userDao = new UserDaoImp();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        switch (action) {
            case "signup":
                registerUser(request, response);
                break;
            case "login":
                validateUser(request, response);
                break;
            case "logout":
                logoutUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            case "edit":
                editUser(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            case "userList":
                userList(request, response);
                break;
            default:
                break;
        }
    }

    private void userList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "adminName")) {
            request.setAttribute("users", userDao.getAllUsers());

            RequestDispatcher rd = request.getRequestDispatcher("allUser.jsp");
            rd.forward(request, response);
        } else {
            errorPage(request, response, "Error! You are not Authorized...");
        }
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (validateSession(request, response, "adminName") || validateSession(request, response, "username")) {
            HttpSession session = request.getSession(false); //Creating a session
            session.removeAttribute("username");
            session.removeAttribute("adminName");
            session.invalidate();
            response.sendRedirect("index.jsp");
        } else {
            errorPage(request, response, "Error! You are not Authorized...");
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (validateSession(request, response, "adminName")) {
            UserBean user = new UserBean();

            user.setUsername(request.getParameter("username"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            int uid = Integer.parseInt(request.getParameter("id"));
            user.setId(uid);

            request.setAttribute("user", userDao.updateUser(user));
            request.setAttribute("users", userDao.getAllUsers());
            RequestDispatcher rd = request.getRequestDispatcher("allUser.jsp");
            rd.forward(request, response);
        } else {
            errorPage(request, response, "Error! You are not Authorized...");
        }
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (validateSession(request, response, "adminName")) {
            String id = (String) request.getParameter("userId");

            request.setAttribute("user", userDao.getUser(id));
            RequestDispatcher rd = request.getRequestDispatcher("updateUser.jsp");
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "adminName")) {
            String id = (String) request.getParameter("userId");
            request.setAttribute("user", userDao.deleteUser(id));
            request.setAttribute("users", userDao.getAllUsers());
            RequestDispatcher rd = request.getRequestDispatcher("allUser.jsp");
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");
    }

    private void validateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean user = new UserBean();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));


        if (userDao.authenticate(user)) {
            createSession(request, response, "username", user.getUsername());
            request.getRequestDispatcher("interestController?action=listInterest").forward(request, response);
        } else if (userDao.check_admin(user)) {

            createSession(request, response, "adminName", user.getUsername());
            response.sendRedirect("adminPage.jsp");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("loginPage.jsp").forward(request, response);
        }

    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserBean user = new UserBean();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        String type = request.getParameter("user");


        boolean duplicate_user = userDao.duplicate(user);
        boolean duplicate_admin = userDao.duplicate_admin(user);

        //For admin panel registration
        if (type != null) {
            if (!duplicate_user && !duplicate_admin) {

                int flag = userDao.createUser(user);

                if (flag > 0) {
                    request.setAttribute("msg", "User added successfully");
                    request.getRequestDispatcher("adminPage.jsp").forward(request, response);
                } else {
                    response.sendRedirect("signupPage.jsp");
                }
            } else {

                request.setAttribute("error", "Email already exists");
                request.getRequestDispatcher("addUser.jsp").forward(request, response);
            }
        }
        //For User panel registration
        else {

            if (!duplicate_user && !duplicate_admin) {
                int flag = userDao.createUser(user);

                if (flag > 0) {
                    response.sendRedirect("loginPage.jsp");
                } else {
                    response.sendRedirect("signupPage.jsp");
                }
            } else {

                request.setAttribute("error", "Email already exists");
                request.getRequestDispatcher("signupPage.jsp").forward(request, response);
            }
        }


    }

    private static void createSession(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        HttpSession session = request.getSession(); //Creating a session
        session.setAttribute(name, value); //setting session attribute
    }

    private static Boolean validateSession(HttpServletRequest request, HttpServletResponse response, String name) {
        HttpSession session = request.getSession(false); //getting the session
        return session.getAttribute(name) != null;
    }

    private static void errorPage(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        request.setAttribute("error-msg", msg);
        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
        rd.forward(request, response);
    }
}