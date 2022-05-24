

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class empServlet
 */
@WebServlet("/empList")
public class empServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public empServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		empDAO dao=new empDAO();
		ArrayList<empVO> list=dao.listemp();
		
		out.print("<html><head><title>Result from employees</title></heat><body>");
		out.print("<table border=1><tr><th>사번</th><th>사원명</th><th>매니저ID</th><th>부서ID</th></tr>"); // head line
		for(int i=0; i<list.size(); i++) {
			empVO evo=list.get(i);
			int employee_id = evo.getEmployee_id();
			String emp_name = evo.getEmp_name();
			String manager_id = evo.getManager_id();
			String department_id = evo.getDepartment_id();
			out.print("<tr><td>"+employee_id+"</td><td>"+emp_name+"</td><td>"+manager_id+"</td><td>"+department_id+"</td></tr>");
		}
		out.print("</table></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
