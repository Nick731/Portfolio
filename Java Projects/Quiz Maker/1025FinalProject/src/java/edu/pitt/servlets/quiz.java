/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.servlets;

import edu.pitt.utilities.DbUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mattk
 */
@WebServlet(name = "quiz", urlPatterns = {"/quiz"})
public class quiz extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * This file lets the user select a quiz to take.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect("loginScreen");
            } else {
            out.println("<!DOCTYPE html>");
                    
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Quiz Page</title>");
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
            
            out.println("<form action='flashcard' id='quizForm' method='post'>");
            out.println("<h2>Select a Quiz</h2>");
            out.println("<select name='quizDropDown' form='quizForm'>");
            
            String sql = "SELECT * FROM quizes;";
            DbUtilities db = new DbUtilities();
            
                try {
                    ResultSet rs = db.getResultSet(sql);
                    while (rs.next()) {
                        out.println("<option value = '"+ rs.getString("quizID") +"'>"+ rs.getString("quizName") + "</option>");
                }
                db.closeDbConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            out.println("</select>");
            out.println("<input type='submit' id='btnSubmit' name ='btnSubmit' value='Submit'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }}
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
