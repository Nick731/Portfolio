/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.servlets;

import edu.pitt.core.User;
import edu.pitt.utilities.DbUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "processQuiz", urlPatterns = {"/processQuiz"})
public class processQuiz extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * This file is used to process the results for the flashcards and input the results into the database.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Results</title>"); 
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
            
            String quizID = request.getParameter("quizID");
            String questionCountString = request.getParameter("questionCount");
            int questionCount = Integer.parseInt(questionCountString);
            out.println("Number of questions: " + questionCount + "<br><br>");
            
            List<String> results = new ArrayList<String>();
            List<String> answers = new ArrayList<String>();
            
            for (int i = 0; i < questionCount; i++){
                int questionNum = i*6;
                String questionNumString = Integer.toString(questionNum);
                String questionNumAnswer = Integer.toString(i*6+1000);
                results.add(request.getParameter(questionNumString));   
                answers.add(request.getParameter(questionNumAnswer));
            }
            
            String selectedAnswer;
            String correctAnswer;
            double score = 0;
            double total = questionCount*10;
            
            for (int i = 0; i < results.size(); i++){
                selectedAnswer = results.get(i);
                correctAnswer = answers.get(i);
                out.println("Selected choice number: " + selectedAnswer + "<br>");
                out.println("Correct choice number: " + correctAnswer + "<br>");
                if (selectedAnswer.equals(correctAnswer)){
                    out.println("You got the question correct! +10 points!<br><br>");
                    score += 10;
                } else {
                    out.println("You did not get the question correct. Please you the back button to try again.<br><br>");
                }
            }
            
            double percent = (score/total)*100;
            out.println("Score: " + score + "/" + total);
            out.println("<br>Percent: " + percent);
            
            HttpSession session = request.getSession(true);
            if (session == null) {
                out.println("Please log in if you want to record your results.");
            } else {
                out.println("<br><br>Your results are being recorded. <a href='index.html'>Click here to return to the main page</a>.");
                
                User user = (User) session.getAttribute("user");

                int userID = user.getUserID();

                String sql = "INSERT INTO `is1025_team6`.`users_quiz` (`users_userID`,`quizes_quizID`,`score`) VALUES (" + userID + ", " + quizID + ", " + percent + ");";

                DbUtilities db = new DbUtilities();
                db.executeQuery(sql);
                db.closeDbConnection();

                out.println("</body>");
                out.println("</html>");
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
