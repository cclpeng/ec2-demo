package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.StaffDao;
import com.revature.dao.StaffDaoImpl;
import com.revature.models.Staff;
import com.revature.util.RequestHelper;

/**
 * Servlet implementation class ShowResolvedRequestsServlet
 */
public class ShowResolvedRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowResolvedRequestsServlet() {
        super();
    }
//Avaialable at /showResolved url
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) 
			response.sendRedirect("login");
		else
			request.getRequestDispatcher("Views/showResolvedRequests.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) 
			response.sendRedirect("login");
		else
		{
			String button = request.getParameter("homeButton");
			if( button != null && button.equals("Back to Home") )
			{
				StaffDao sd = new StaffDaoImpl();
				//use session username to get Staff object. Pass into RequestHelper to get String. Redirect
				String userN = (String) session.getAttribute("username");
				Staff staff = sd.getStaffByUserName(userN);
				response.sendRedirect( RequestHelper.getLoginHomepageRedirect(staff) ); //redirect to right home pg
			}
		}
	}

}
