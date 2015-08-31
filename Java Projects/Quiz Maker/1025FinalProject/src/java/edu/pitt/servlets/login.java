/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.servlets;

import edu.pitt.core.User;
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
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * This file is used as the validate that the credentials inputed are valid.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
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
            
            String userName = request.getParameter("txtLogin");
            String userPassword = request.getParameter("txtPassword");
            
            if (userName.equalsIgnoreCase("") || userName == null || userPassword.equalsIgnoreCase("") || userPassword == null ){
                out.println("Please fill out all required fields.");
            }
            else{
                User loggedInUser = new User(userName, userPassword);
                HttpSession session = request.getSession();
                if(loggedInUser.getIsValidUser()){
                    session.setAttribute("user", loggedInUser);
                    response.sendRedirect("index.html"); 
                }
                else{
                    out.println("Invalid credentials. ");
                    session.setAttribute("user", null);
                }
            }
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
