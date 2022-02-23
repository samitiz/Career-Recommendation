package com.careeradvisor.degree;

import com.careeradvisor.interest.InterestBean;
import com.careeradvisor.interest.InterestDaoImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class DegreeController extends HttpServlet {
    DegreeDaoImp degreeDao;

    public void init() {
        degreeDao = new DegreeDaoImp();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String action = request.getParameter("action");

        switch (action) {
            case "allDegree":
                allDegree(request, response);
                break;
            case "degreeList":
                degreeList(request, response);
                break;
            case "degrees":
                degrees(request, response);
                break;
            case "removeDegree":
                removeDegree(request, response);
                break;


            default:
                break;
        }
    }

    private void removeDegree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "adminName")) {
            String id = (String) request.getParameter("degreeId");

            degreeDao.deleteDegree(id);
            request.setAttribute("degreeList", degreeDao.getAllDegrees());
            RequestDispatcher rd = request.getRequestDispatcher("allDegree.jsp");
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");
    }

    private void degrees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "adminName")) {
            request.setAttribute("degreeList", degreeDao.getAllDegrees());
            RequestDispatcher rd = request.getRequestDispatcher("allDegree.jsp");
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");
    }

    private void degreeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "username")) {
            InterestDaoImp interestDao = new InterestDaoImp();
            String name = request.getParameter("inte");

            InterestBean interest = interestDao.getByName(name);

            request.setAttribute("degreeList", degreeDao.getAllDegrees(interest.getId()));
            RequestDispatcher rd = request.getRequestDispatcher("degreePage.jsp");
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");
    }

    private void allDegree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "adminName")) {
            String page = (String) request.getAttribute(("page"));

            request.setAttribute("allDegree", degreeDao.getAllDegrees());
            RequestDispatcher rd;
            if (page == null) {
                rd = request.getRequestDispatcher("addInterest.jsp");
            } else {
                rd = request.getRequestDispatcher("updateInterest.jsp");
            }
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");
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