/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tushar;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java .lang.*;

/**
 *
 * @author Work
 */
@WebServlet(name = "radiobuttondemo", urlPatterns = {"/radiobuttondemo"})
public class radiobuttondemo extends HttpServlet {
    

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
        PrintWriter out = response.getWriter();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.jdbc.Driver").newInstance();
	    Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/uvadb", "root", "admin");
            String opt=request.getParameter("info");
            String formval=request.getParameter("txtdeptinfo");
            Statement stmt = con.createStatement();
            String qry="";
            if(opt.equals("deptno"))
            qry="select * from emp where deptno="+request.getParameter("txtdeptinfo");
            else if(opt.equals("sal"))
            qry = "select * from emp where sal>="+request.getParameter("txtdeptinfo");
            else if(opt.equals("job"))
            qry="select * from emp where job='"+request.getParameter("txtdeptinfo")+"'";
            else
            qry="select * from emp ";    
            ResultSet rs = stmt.executeQuery(qry);
            out.println("<table border=1 width=75%>");
            out.println("<tr><th>Employe ID <th>Name of Employee<th>Desingnation<th>Salary</th></tr>");
            while (rs.next()) 
            {
            String eno = rs.getString("EMPNO");
	    String enm=rs.getString("ENAME");
            String ejob = rs.getString("JOB");
            String esal = rs.getString("SAL");
            out.println("<tr><td>"+eno+"<td>"+enm+"<td>"+ejob+"<td>"+esal+"</td></tr>");
            }
            out.println("</table>");
        }
        catch(Exception e )
        {
            out.println("<h3>Error : "+e.getMessage()+"<h3>");
        } finally {
            out.close();
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
