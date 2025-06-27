

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Check_User
 */
@WebServlet("/Check_User")
public class Check_User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check_User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		try {
			
			String uname=request.getParameter("uname");
			String pass=request.getParameter("pass");
			
			Connection con=ConnectDB.connect();
			
			String q="select * from users where username=(?) and password=(?)";
			PreparedStatement pst=con.prepareStatement(q);
			pst.setString(1, uname);
			pst.setString(2, pass);
			
			ResultSet rs=pst.executeQuery();
			String U,P;
			if(rs.next()) {
				U =rs.getString(1);
				P=rs.getString(2);
				if(uname.equalsIgnoreCase(U)&&pass.equalsIgnoreCase(P)) {
					HttpSession session=request.getSession();
					session.setAttribute("Username", U);
					RequestDispatcher rd=request.getRequestDispatcher("Profile");
					rd.forward(request, response);
				}else {
					pw.print("<center><h3 style='color:red;'>Sorry,User name and pass doesnot match</h3></center>");
					RequestDispatcher rd=request.getRequestDispatcher("Login");
					rd.include(request,response);
				}
				con.close();
			}
			
			
		}catch(Exception ex) {
			pw.print(ex);
		}
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
