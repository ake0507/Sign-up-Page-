package com.uniquedeveloper.regestration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Servlet implementation class RegstrationServlet
 */
@WebServlet("/register")
public class RegstrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uname=request.getParameter("name");
    String uemail=request.getParameter("email");
    String upwd=request.getParameter("pass");
    String umobile=request.getParameter("contact");
    RequestDispatcher dispatcher=null;
    Connection con =null;
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	 con =DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube","root","ake0507,.") ;
    	PreparedStatement pst=con.prepareStatement("insert into users(uname,upwd,uemail,umobile) values(?,?,?,?)");
    	pst.setString(1,uname);
    	pst.setString(1,upwd);
    	pst.setString(1,uemail);
    	pst.setString(1,umobile);
    	
    	int rowCount=pst.executeUpdate();
    	dispatcher=request.getRequestDispatcher("registration.jsp");
    	if(rowCount>0) {
    	   request.setAttribute("status","success")	;
    	}else {
    		request.setAttribute("status","failed")	;
    	}
    	
       dispatcher.forward(request, response);	
    }catch (Exception e) {
    	e.printStackTrace();
    }finally {
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	}

}
