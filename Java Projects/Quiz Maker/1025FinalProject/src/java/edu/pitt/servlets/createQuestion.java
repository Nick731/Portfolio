package edu.pitt.servlets;

import edu.pitt.core.User;
import edu.pitt.utilities.DbUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nickpeters
 */
@WebServlet(name = "createQuestion", urlPatterns = {"/createQuestion"})
public class createQuestion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * This file inserts the new question into the database.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add Question</title>");   
            out.println("<style type='text/css'>");
            out.println("body {");
            out.println("background-color:  #404040;");
            out.println("color: #FFFF00; ");
            out.println("font-family: arial;");
            out.println("width:950px;");
            out.println("margin-left:auto;");
            out.println("margin-right:auto;");
            out.println("text-align:left; ");
            out.println("font-weight: bold;");
            out.println("line-height: 30px;");
            out.println("}");
            out.println("a {");
            out.println("font-size: 150%;");
            out.println("color: #3399FF;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect("loginScreen");
            } else {
                User user = (User) session.getAttribute("user");
                boolean validUser = user.getIsValidUser();
                if(validUser){
                    int quizId = Integer.parseInt(request.getParameter("quizId"));
                    String cardName = request.getParameter("cardName");
                    String cardQuestion = request.getParameter("cardQuestion");
                    String answer1 = request.getParameter("answer1");
                    String answer2 = request.getParameter("answer2");
                    String answer3 = request.getParameter("answer3");
                    String answer4 = request.getParameter("answer4");
                    int correctAnswer = Integer.parseInt(request.getParameter("correctAnswer"));

                    String sql = "INSERT INTO `is1025_team6`.`flashcards`(`cardName`,`cardQuestion`,`answer1`,`answer2`,`answer3`,`answer4`,`correctAnswer`,`difficulty`,`fk_quizID`)VALUES('" + cardName + "', '" + cardQuestion + "', '" + answer1 + "', '" + answer2 + "', '" + answer3 + "', '" + answer4 + "',  " + correctAnswer + ", 0 , " + quizId + ");"; 
    
                    DbUtilities db = new DbUtilities();
                    db.executeQuery(sql);
                    out.println("Your question has been added. <a href='index.html'>Click here to return to the main page</a>.");
                    db.closeDbConnection();
                }
                else {
                    response.sendRedirect("login.html");
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
