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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mattk and nickpeters
 */
@WebServlet(name = "flashcard", urlPatterns = {"/flashcard"})
public class flashcard extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * This file displays the form to take the flashcard quiz.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Flashcard</title>"); 
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
            String quizID = request.getParameter("quizDropDown");
           
            out.println("<body>");
            
            out.println("<form action='processQuiz' id='flashcardForm' method='post'>");
            
            String sql = "SELECT * FROM flashcards f JOIN quizes q ON q.quizID = f.fk_quizID WHERE fk_quizID = " + quizID + ";";
            
            DbUtilities db = new DbUtilities();
            
            try {

                List<String> results = new ArrayList<String>();    
                    
                ResultSet rs = db.getResultSet(sql);
                
                while (rs.next()) {
                    results.add(rs.getString("cardQuestion"));
                    results.add(rs.getString("answer1"));
                    results.add(rs.getString("answer2"));
                    results.add(rs.getString("answer3"));
                    results.add(rs.getString("answer4"));
                    results.add(rs.getString("correctAnswer"));
                }
                
                int questionCount = 0;
                
                for (int i = 0; i < results.size(); i++){
                    int questionNum = i;
                    int questionNumAnswer = i+1000;
                    questionCount++;
                    out.println(results.get(i) + "<br>");
                    i++;
                    out.println("<input type= 'radio' value = '1' name= '" + questionNum + "'>"+ " 1: " + results.get(i) + "<br />");
                    i++;
                    out.println("<input type= 'radio' value = '2' name= '" + questionNum + "'>"+ " 2: " + results.get(i) + "<br />");
                    i++;
                    out.println("<input type= 'radio' value = '3' name= '" + questionNum + "'>"+ " 3: " + results.get(i) + "<br />");
                    i++;
                    out.println("<input type= 'radio' value = '4' name= '" + questionNum + "'>"+ " 4: " + results.get(i) + "<br /><br />");
                    i++;
                    out.println("<input type='hidden' name='" + questionNumAnswer + "' value='" + results.get(i) + "'>");
                }
                
                out.println("<input type='hidden' name='questionCount' value='" + questionCount + "'>");
                out.println("<input type='hidden' name='quizID' value='" + quizID + "'>");

                
                db.closeDbConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
                
            out.println("<br />");
            out.println("<input type='submit' id='btnSubmit' name ='btnSubmit' value='Submit' >");
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
