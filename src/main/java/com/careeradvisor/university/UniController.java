package com.careeradvisor.university;

import com.careeradvisor.degree.DegreeBean;
import com.careeradvisor.degree.DegreeDaoImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class UniController extends HttpServlet {
    UniversityDaoImp uniDao;

    public void init() {
        uniDao = new UniversityDaoImp();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String action = request.getParameter("action");

        switch (action) {
            case "addUni":
                addUniversity(request, response);
                break;
            case "removeUni":
                removeUniversity(request, response);
                break;
            case "uniList":
                universityList(request, response);
                break;
            case "updateUni":
                updateUniversity(request, response);
                break;
            case "editUni":
                editUniversity(request, response);
                break;
            case "allUni":
                allUniversity(request, response);
                break;
            case "uniDetail":
                universityDetail(request, response);
                break;
            default:
                break;
        }
    }

    private void universityDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "username")) {
            String name = request.getParameter("uni");
            UniversityBean uni = uniDao.getByName(name);

            request.setAttribute("university", uni);

            RequestDispatcher rd = request.getRequestDispatcher("universityDetail.jsp");
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");
    }

    private void allUniversity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "adminName")) {
            request.setAttribute("uniList", uniDao.listAllUniversity());
            RequestDispatcher rd = request.getRequestDispatcher("allUni.jsp");
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");
    }

    private void editUniversity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "adminName")) {
            String name = request.getParameter("uniId");

            request.setAttribute("uni", uniDao.getByName(name));
            RequestDispatcher rd = request.getRequestDispatcher("updateUni.jsp");
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");

    }

    private void updateUniversity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "adminName")) {
            UniversityBean uni = new UniversityBean();
            uni.setUniversity_name(request.getParameter("uni-name"));
            uni.setRanking((Integer.parseInt(request.getParameter("worldRanking"))));
            uni.setLocation(request.getParameter("city"));
            uni.setWebsite(request.getParameter("website"));
            uni.setUniversity_type(request.getParameter("type"));
            int uid = Integer.parseInt(request.getParameter("id"));
            uni.setId(uid);

            request.setAttribute("uni", uniDao.updateUniversity(uni));
            request.setAttribute("uniList", uniDao.listAllUniversity());
            RequestDispatcher rd = request.getRequestDispatcher("allUni.jsp");
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");

    }

    private void universityList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "username")) {
            DegreeDaoImp degreeDao = new DegreeDaoImp();
            String name = request.getParameter("uni");

            DegreeBean degree = degreeDao.getByName(name.trim());

            request.setAttribute("uniListPublic", uniDao.getAllUniversity(degree.getId(), "public"));
            request.setAttribute("uniListPrivate", uniDao.getAllUniversity(degree.getId(), "private"));

            RequestDispatcher rd = request.getRequestDispatcher("universityList.jsp");
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");

    }

    private void removeUniversity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateSession(request, response, "adminName")) {
            String id = request.getParameter("uniId");

            request.setAttribute("uni", uniDao.deleteUniversity(id));
            request.setAttribute("uniList", uniDao.listAllUniversity());
            RequestDispatcher rd = request.getRequestDispatcher("allUni.jsp");
            rd.forward(request, response);
        } else errorPage(request, response, "Error! You are not Authorized...");
    }

    private void addUniversity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UniversityBean uni = new UniversityBean();

        uni.setUniversity_name(request.getParameter("uni-name"));
        uni.setRanking(Integer.parseInt(request.getParameter("worldRanking")));
        uni.setLocation(request.getParameter("city") + ", Pakistan");
        uni.setUniversity_type(request.getParameter("type"));
        uni.setWebsite(request.getParameter("website"));


        if (uniDao.getByName(uni.getUniversity_name()) == null) {

            int flag = uniDao.createUniversity(uni);

            if (flag > 0) {
                request.setAttribute("msg", "University added successfully");
                request.getRequestDispatcher("adminPage.jsp").forward(request, response);
            } else {
                request.setAttribute("error-msg", "Some error occurred! Please try again later...");
                request.getRequestDispatcher("addUni.jsp").forward(request, response);
            }
        } else {

            request.setAttribute("error-msg", "University already exists");
            request.getRequestDispatcher("addUni.jsp").forward(request, response);
        }
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