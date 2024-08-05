package in.sp.car;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/loginForm")
public class Login1 extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		  PrintWriter out = resp.getWriter();
		   resp.setContentType("text/html");
		   
		   String myemail= req.getParameter("email");
		   String mypass=req.getParameter("pass");
		   
		   try
		   {
			   Class.forName("com.mysql.cj.jdbc.Driver");
			   Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/car2share_db1","root","nihal123");
			   PreparedStatement ps= con.prepareStatement("select * from userdetail_tb1 where email=? and pass=?");
			   
			   ps.setString(1, myemail);
			   ps.setString(2, mypass);
			   
			   ResultSet rs= ps.executeQuery();
			   
			   if(rs.next())
			   {
				   HttpSession session =req.getSession();
				   session.setAttribute("name", rs.getString("name"));
				   
				   RequestDispatcher rd= req.getRequestDispatcher("/profile.jsp");
				   rd.include(req, resp);
			   }else
			   {
				   out.print("<h6 style='color:red'>invalid user and password</h6>");
				   RequestDispatcher rd= req.getRequestDispatcher("index.jsp");
				   rd.include(req, resp);
			   }
			   
			   
		   }catch(Exception e)
		   {
			   e.printStackTrace();
			   out.print("<h6 style='color:red'>"+e.getMessage()+"</h6>");
			   RequestDispatcher rd= req.getRequestDispatcher("index.jsp");
			   rd.include(req, resp);
		   }

	}

}
