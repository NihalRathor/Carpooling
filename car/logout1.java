package in.sp.car;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/logout")
public class logout1 extends HttpServlet
{
   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   PrintWriter out = resp.getWriter();
	   HttpSession session = req.getSession();
	   
	   session.invalidate();
	   RequestDispatcher rd= req.getRequestDispatcher("/index.jsp");
	   rd.include(req, resp);
	   out.close();
}
}
