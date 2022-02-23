package com.careeradvisor.interest;

import com.careeradvisor.degree.DegreeBean;
import com.careeradvisor.degree.DegreeDaoImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class InterestController extends HttpServlet {
    InterestDaoImp interestDao;

    public void init(){
        interestDao = new InterestDaoImp();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String action = request.getParameter("action");

        switch (action) {
            case "addInterest":
                addInterest(request, response);
                break;
            case "allInterest":
                allInterest(request, response);
                break;
            case "listInterest":
                listInterest(request, response);
                break;
            case "removeInterest":
                removeInterest(request, response);
                break;
            case "updateInterest":
                updateInterest(request, response);
                break;
            default:
                break;
        }
    }

    private void updateInterest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(validateSession(request,response,"adminName")){
            DegreeDaoImp degreeDao = new DegreeDaoImp();
            String name = (String) request.getParameter("interestId");

            if(name != null){
                InterestBean interest = interestDao.getByName(name.trim());
                request.setAttribute("interest", interest);
                request.setAttribute("page", "update");
                RequestDispatcher rd = request.getRequestDispatcher("degreeController?action=allDegree");
                rd.forward(request, response);
            }else{
                String i_name = (String) request.getParameter("getName");
                InterestBean interest = interestDao.getByName(i_name);

                interest.setInterest_name(request.getParameter("interest-name"));
                DegreeBean degree = degreeDao.getByName(request.getParameter("degree-name"));
                System.out.println(request.getParameter("degree-name"));

                request.setAttribute("int", interestDao.updateInterest(interest));
                int flag = interestDao.updateInsert(interest.getId(), degree.getId());
                if(flag == 1){
                    request.setAttribute("interests", interestDao.getAllInterests());
                    request.setAttribute("msg", "Interest Updated successfully");
                    RequestDispatcher rd = request.getRequestDispatcher("adminPage.jsp");
                    rd.forward(request, response);
                }

            }
        }else errorPage(request, response, "Error! You are not Authorized...");

    }

    private void removeInterest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(validateSession(request,response,"adminName")){
        String id = (String) request.getParameter("interestId");

        request.setAttribute("interest", interestDao.deleteInterest(id));
        request.setAttribute("interests", interestDao.getAllInterests());
        RequestDispatcher rd = request.getRequestDispatcher("allInterest.jsp");
        rd.forward(request, response);
        }else errorPage(request, response, "Error! You are not Authorized...");
    }

    private void listInterest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(validateSession(request,response,"username")){
        request.setAttribute("interestList", interestDao.getAllInterests());
        RequestDispatcher rd = request.getRequestDispatcher("interestPage.jsp");
        rd.forward(request, response);
        }else errorPage(request, response, "Error! You are not Authorized...");
    }

    private void allInterest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(validateSession(request,response,"adminName")){
        request.setAttribute("interests", interestDao.getAllInterests());
        RequestDispatcher rd = request.getRequestDispatcher("allInterest.jsp");
        rd.forward(request, response);
        }else errorPage(request, response, "Error! You are not Authorized...");
    }

    private void addInterest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(validateSession(request,response,"adminName")){
            DegreeDaoImp degreeDao = new DegreeDaoImp();

            String interestName = request.getParameter("interest-name");
            String degreeName = request.getParameter("degree-name");
            InterestBean interest = new InterestBean();
            interest.setInterest_name(interestName);

            //duplicate check
            if(interestDao.getByName(interestName)== null) {

                InterestBean interest1 = interestDao.createInterest(interest);
                DegreeBean degree = degreeDao.getByName(degreeName);

                int flag = interestDao.insert(interest1.getId(),degree.getId() );

                if(flag == 1){
                    request.setAttribute("msg", "Interest added successfully");
                    RequestDispatcher rd = request.getRequestDispatcher("adminPage.jsp");
                    rd.forward(request, response);
                }else{
                        response.sendRedirect("error.jsp");
                }
            }else{
                request.setAttribute("error-msg", "Interest with this name already exist");
                RequestDispatcher rd = request.getRequestDispatcher("addInterest.jsp");
                rd.forward(request, response);
            }
        }else errorPage(request, response, "Error! You are not Authorized...");

    }

    private static Boolean validateSession(HttpServletRequest request,HttpServletResponse response,String name){
        HttpSession session = request.getSession(false); //getting the session
        return session.getAttribute(name) != null;
    }

    private static void errorPage(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        request.setAttribute("error-msg", msg);
        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
        rd.forward(request,response);
    }

}