

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
 * Servlet implementation class OperationForm
 */
@WebServlet("/OperationForm")
public class OperationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationForm() {
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
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		HttpSession session=request.getSession(false);
		if(session==null) {
			response.sendRedirect("Login");
		}
		Connection con=ConnectDB.connect();
		try {
			String fetch_students="Select * from Student";
			String count_student="Select (*) from Student";
			String active_student="Select (*) from Student";
			
			
			PreparedStatement pst=con.prepareStatement(fetch_students);
			ResultSet rs=pst.executeQuery();
			

			PreparedStatement pst2=con.prepareStatement(count_student);
			ResultSet rs2=pst2.executeQuery();
			rs2.next();
			

			PreparedStatement pst3=con.prepareStatement(active_student);
			ResultSet rs3=pst3.executeQuery();
			rs3.next();
			pw.print("<html>"
					+"<head><title>Operation Form</title>"
					+"<style>"
					    +"td{paddin:10px 10px}"
					    +"body{font-family:arial;}"
					    +"table{border:1px solid black;padding:20px;}"
					    +"a{text-decoration:none;border:1px solid black;padding:10px 10px;}"
					    +"a:hover{color:red;}"
					    +".btn{padding:10px 20px;}"
					    
					    
					+"</style>"
					+"</head>"
					+"<body>");
			
			
			pw.print("<center>"
					
                      +"<h2>Hii admin</h2>"
                      +"<br>"

                      +"<div style='float:left;color:orange;border:1px solid black;padding:5px 5px;'>Total Student<h3>"+rs2.getInt(1)+"</h3></div>"


                      +"<div style='float:left;margin-left:10px;color:blue;border:1px solid black;padding:5px 5px;'>Total Active Student<h3><a href='SearchResult?Fetch=Active' style='border:none;'>"+rs3.getInt(1)+"</a></h3></div>"

                      +"<div style='float:left;margin-left:10px;color:green;border:1px solid black;padding:5px 5px;'>Total Deactive Student<h3><a href='SearchResult?Fetch=Deactive' style='border:none;'>"+(rs2.getInt(1)-rs3.getInt(1))+"</a></h3></div>"

                      +"<div style='Clear:both;'></div><div style='float:right;'><a href='OperationForm?Id=Add'>Add Student</a>"

                      +"<a href='Logout' style='margin-left:10px;'>Log Out</a></div>"
                      +"<br><br><br>"

                      +"<div style='float:left';><form action='SearchResult'><input type='text' name='Fetch' placeholder='Search Student' required><input type='submit' value='Search' style='margin-left:10px;'></form><br></div>"


                      +"<div style='float:right';><form action='GeneratePDF'><select name='status'><option>Active</option><option>Deactive</option></select><input type='submit' value='Generate Report' style='margin-left:10px;'>"
                      

					);
			
			String Id=request.getParameter("Id");
			String sname,enrollment,gender,phone;
			ResultSet rs4=null;
			
			if(Id.equals("Add")) {
				pw.print("<h1>Add New Student</h1>");
				
				pw.print("<form action='OperationWithDatabse' method='post'> "
						+"<table>"
						+"        <tr>"
						+"        <td>Student Name</td>"
						+"        <td><input type='text' name='sname' placeholder='Student Name'></td>"
						+"        </tr>"
						
						
                        +"        <tr>"
                        +"        <td>Enrollment</td>"
                        +"        <td><input type='text' name='enroll' placeholder='Enrollment'></td>"
                        +"        </tr>"
                        
                        +"        <tr>"
                        +"        <td>Date of Birth</td>"
                        +"        <td><input type='date' name='dob'></td>"
                        +"        </tr>"


                       +"        <tr>"
                       +"        <td>Gender</td>"
                       +"        <td><input type='radio' name='gender' value='Male'>Male"
                       +"            <input type='radio'name='gender' value='Female'>Female</td>"
                       +"        </tr>"


						
                       +"        <tr>"
                       +"        <td>Phone</td>"
                       +"        <td><input type='text' name='phone' placeholder='Phone No'></td>"
                       +"        </tr>"
                       
                       +"        <tr>"
                       +"        <td>Status</td>"
                       +"        <td><select name='status'><option>Active</option><option>Deactive</option></select></td>"
                       +"        </tr>"
                       
                       
                       +"        <tr>"
                       +"        <input type='hidden' value='Add' name='OperationType'>"
                       +"        <td><input type='submit' value='Add' class='btn'</td>"
                       +"        </tr>"
                       
                       +"        <tr></table></form>"
						
						);
			}else {
				pw.print("<h1>Update Student Id="+Id+"</h1>");
				String q="select * from Student where id="+Integer.parseInt(Id)+"";
				PreparedStatement pst1=con.prepareStatement(q);
				rs4=pst1.executeQuery();
				rs4.next();
				
				pw.print("<form action='OperationWithDatabase'method='post'>"
						   +"<table>"
						
						   +"        <tr>"
	                       +"        <td>Student Name</td>"
	                       +"        <td><input type='text' name='sname' placeholder='Student Name' value='"+rs4.getString(2)+"'></td>"
	                       +"        </tr>"
	                       
	                       +"        <tr>"
	                       +"        <td>Enrollment</td>"
	                       +"        <td><input type='text' name='enroll' placeholder='Enrollment' value='"+rs4.getString(3)+"'></td>"
	                       +"        </tr>"
	                       
	                       +"        <tr>"
	                       +"        <td>Date of Birth</td>"
	                       +"        <td><input type='date' name='dob' value='"+rs4.getString(4)+"'></td>"
	                       +"        </tr>"
	                       
	                       +"        <tr>"
	                       +"        <td>Gender</td>");
	                       
	                       if(rs4.getString(5).equals("Male")) {
	                    	   pw.print("<td><input type='radio' name='gender' value='Male' checked>Male"
	                    			   +"<input type='radio' name='gender'value='Female' checked>Female");   
	                       }else {
	                    	   pw.print("<td><input type='radio' name='gender' value='Male' checked>Male"
	                    			   +"<input type='radio' name='gender'value='Female' checked>Female");
	                    	   
	                       }
	                       
	                       pw.print("</tr>"
	                       		    
	                    		   +"<tr>"
	                    		   +"<td>Phone</td>"
	                    		   +"<td><input type='text' name='phone' placeholder='Phone No' value='"+rs4.getString(6) + "'></td>"
	                    		   +"</tr>"
	                    		   
	                    		   );
	                       
	                       if(rs4.getString(7).equals("Active")) {
	                    	   pw.print("</tr>"
		                       		    
	                    		   
	                    		   +"<td>Status</td>"
	                    		   +"<td><select name='status'><option selected>Active</option><option>Deactive</option></select> </td>"
	                    		   +"</tr>");  
	                    	   
	                       }else {
	                    	   pw.print("</tr>"
		                       		    
		                    		   
	                    		   +"<td>Status</td>"
	                    		   +"<td><select name='status'><option>Active</option><option selected>Deactive</option></select> </td>"
	                    		   +"</tr>"); 
	                       }
	                       
	                       pw.print("</tr>"
	                       		    
	                    		   +"<input type='hidden' value='Update' name='OperationType'>"
	                    		   +"<input type='hidden' value='"+rs4.getString("Id")+"'name='Id'>"
	                    		   +"<td><input type='submit'value='Update'class='btn'></td>"
	                    		   +"</tr>"
	                    		   +"</table></form>"
	                    		   
	                    		   );
	                     						
			}pw.print("</center></body></html>");
			
			
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
