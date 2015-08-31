package edu.pitt.servlets;

import edu.pitt.core.User;
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
 * @author nickpeters
 */
@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class admin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * This file is used to determine the user's level and display either their profile or the admin profile.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.sendRedirect("loginScreen");
            } else {
                User user = (User) session.getAttribute("user");
                int userLevel = user.getUserLevel();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Profile</title>");  
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
                if(userLevel == 1){
                    
                    out.println("<h1>Welcome, Admin</h1>");

                    
                    out.println("<table border=\"1\" style=\"width:100%\">");
                    
                    out.println("<tr>");
                    out.println("<th>Username</th><th>Quiz Name</th><th>Score</th>");
                    out.println("</tr>");
                    
                    String sql = "select * from users_quiz left join users on users_userID = userID left join quizes on quizes_quizID = quizID;";
                    DbUtilities db = new DbUtilities();
                    try {
                        ResultSet rs = db.getResultSet(sql);
                        while(rs.next()){
                            out.println("<tr>");
                            out.println("<td>" + rs.getString("userName") + "</td>");
                            out.println("<td>" + rs.getString("quizName") + "</td>");
                            out.println("<td>" + rs.getString("score") + "</td>");
                            out.println("</tr>");
                        }
                        db.closeDbConnection();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    out.println("</table>");
                    
                } else if (userLevel == 2){
                    out.println("<h1>Welcome, " + user.getUserName() + "</h1>");

                    out.println("<table border=\"1\" style=\"width:100%\">");
                    
                    out.println("<tr>");
                    out.println("<th>Quiz Name</th><th>Score</th>");
                    out.println("</tr>");
                    
                    String sql = "select * from users_quiz left join users on users_userID = userID left join quizes on quizes_quizID = quizID where userID=" + user.getUserID() + ";";
                    DbUtilities db = new DbUtilities();
                    try {
                        ResultSet rs = db.getResultSet(sql);
                        while(rs.next()){
                            out.println("<tr>");
                            out.println("<td>" + rs.getString("quizName") + "</td>");
                            out.println("<td>" + rs.getString("score") + "</td>");
                            out.println("</tr>");
                        }
                        db.closeDbConnection();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    out.println("</table>");
                } else {
                    out.println("You do not have permission to view this page. <a href='index.html'>Click here to return to the homepage</a>.");
                }
                out.println("<br><br><a href='index.html'>Click here to return to the homepage</a>.");
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
