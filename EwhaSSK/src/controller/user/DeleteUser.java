package controller.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;

/**
 * @author Lee Ji Won
 * 사용자 삭제
 * - 전문가가 아동을 삭제하는 경우
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
	    ServletContext sc = getServletContext();
	 	Connection conn= (Connection) sc.getAttribute("DBconnection");
	 	
	 	int deleteUserId = Integer.parseInt(request.getParameter("deleteUserId"));
	 	
	 	UserDAO.deleteUser(conn, deleteUserId);
	 	
	 	RequestDispatcher rd = request.getRequestDispatcher("/GetExpertHome");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
