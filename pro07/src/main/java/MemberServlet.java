

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session=request.getSession();
//		String userid=(String)session.getAttribute("userid");
//		if(userid==null || userid.equals("")) {
//			response.sendRedirect("login3.html");
//			return;
//		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		MemberDAO dao=new MemberDAO();
		ArrayList<MemberVO> list=dao.listMember();
		
		out.print("<html><head><title>Result from t_member</title></heat><body>");
		out.print("<table border=1><tr><th>ID</th><th>��й�ȣ</th><th>�̸�</th><th>�����</th><th>�����</th><th>�۾�����</th></tr>"); // head line
		for(int i=0; i<list.size(); i++) {
			MemberVO mvo=list.get(i);
			String id = mvo.getId();
			String pwd = mvo.getPwd();
			String name = mvo.getName();
			String mobile = mvo.getMobile();
			String joindate = mvo.getJoinDate();
			out.print("<tr><td>"+mvo.getId()+"</td><td>"+mvo.getPwd()+"</td><td>"+mvo.getName()+"</td><td>"+mvo.getMobile()+"</td><td>"+mvo.getJoinDate()+"</td>"+
					"<td><a href='update?id="+id+"'>����</a>&nbsp;<a href='delete?id="+id+"'>����</a></tr>");
		}
		out.print("</table><a href='addnew.html'>�߰�</a></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
