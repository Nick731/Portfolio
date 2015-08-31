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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nickpeters
 */
@WebServlet(name = "addQuestion", urlPatterns = {"/addQuestion"})
public class addQuestion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * This file displays the form to add a question.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
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
            out.println("<h1>Add Qestion</h1>");
            out.println("<form name=\"frmAddQuestion\" id=\"frm\" method=\"post\" action=\"createQuestion\">");
            out.println("Select the quiz that this question will belong to: <select name='quizId'>");

            String sql = "SELECT * FROM quizes";
            DbUtilities db = new DbUtilities();
            try {
                ResultSet rs = db.getResultSet(sql);
                while(rs.next()){
                    out.println("<option value='" + rs.getString("quizId") + "'>" + rs.getString("quizName") + "</option>");
                }
                db.closeDbConnection();
                out.println("</select><br>");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            out.println("Question Name: <input type=\"text\" size=\"35\" name=\"cardName\"><br>");
            out.println("Question: <br><textarea name=\"cardQuestion\" rows=\"4\"  cols=\"50\"></textarea><br>");
            out.println("Possible Answer 1: <input type=\"text\" size=\"35\" name=\"answer1\"><br>");
            out.println("Possible Answer 2: <input type=\"text\" size=\"35\" name=\"answer2\"><br>");
            out.println("Possible Answer 3: <input type=\"text\" size=\"35\" name=\"answer3\"><br>");
            out.println("Possible Answer 4: <input type=\"text\" size=\"35\" name=\"answer4\"><br>");
            out.println("Correct Answer: <select name='correctAnswer'><option value=\"1\">1</option><option value=\"2\">2</option><option value=\"3\">3</option><option value=\"4\">4</option></select><br>");
            out.println("<input type='submit' name='btnSubmit' id='btnSubmit' value='Add Question'/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(addQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(addQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
