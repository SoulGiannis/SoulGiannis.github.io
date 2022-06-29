/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package view_form;

import ServicesDao.ServicesDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class viewresume extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
               try{                 
                HttpSession session = request.getSession();
                Connection con = ServicesDao.createConnection();
                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                String t_name = (String) session.getAttribute("t_name");        
                
                String q = "select * from "+t_name;
                ResultSet rs = st.executeQuery(q);
                
                ResultSetMetaData rsmd = rs.getMetaData();                
                int column_count = rsmd.getColumnCount();                
                
                rs.last();
                String name = rs.getString(1);
                String education = rs.getString(2);
                String languages = rs.getString(3);                 
                String skills = rs.getString(4);                 
                String hobbies = rs.getString(5);                 
                String achievements = rs.getString(6);                 
                String experience = rs.getString(7);                 
                 
                session.setAttribute("name",name);
                session.setAttribute("education",education);
                session.setAttribute("languages",languages);                
                session.setAttribute("skills",skills);                
                session.setAttribute("hobbies",hobbies);                
                session.setAttribute("achievements",achievements);                
                session.setAttribute("experience",experience); 
                
                session.setAttribute("submit_value","Update");
                
                RequestDispatcher rd = request.getRequestDispatcher(t_name+".jsp");
                rd.forward(request,response);
            }
            catch(Exception e){
                e.printStackTrace();
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