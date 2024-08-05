package in.sp.car;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/signupForm")
public class Signup1 extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 PrintWriter out= resp.getWriter();
	  resp.setContentType("text/html");
	  String myname=req.getParameter("name");
	  String myemail=req.getParameter("email");
	  String mypass=req.getParameter("pass");
	 
	  
	  try
	  {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  
		  Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/car2share_db1","root","nihal123");
		  
		  PreparedStatement ps= con.prepareStatement("insert into userdetail_tb1 (name,email,pass) values(?,?,?)");
		  
		  ps.setString(1, myname);
		  ps.setString(2, myemail);
		  ps.setString(3,mypass);
		  
		  
		  int count=ps.executeUpdate();
		  
		  if(count==1)
		  {
			  
			  out.print("<h6 style='color:green'>User registered successfully</h6>");
			 RequestDispatcher rd= req.getRequestDispatcher("/index.jsp");
			  rd.include(req, resp);
		  }
		  else
		  {
			 
			  out.print("<h6 style='color:red'>User registered  not successfully</h6>");
			  RequestDispatcher rd= req.getRequestDispatcher("/signup.jsp");
			  rd.include(req, resp);
		  }
	  }catch(Exception e)
	  {
		  e.printStackTrace();
		  out.print("<h6 style='color:red'> Excepyion Occured : "+e.getMessage()+" </h6>");
			 
		  RequestDispatcher rd= req.getRequestDispatcher("/index.jsp");
	      rd.include(req, resp);
	  }

}
}
