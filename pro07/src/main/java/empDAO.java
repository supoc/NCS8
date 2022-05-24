import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class empDAO {
	private Statement stmt;
	private Connection conn;
	
	public ArrayList<empVO> listemp() {
		ArrayList<empVO> list=new ArrayList<empVO>();
		try {
			connDB();
//			String query="select employee_id, emp_name, manager_id, department_id from employees order by employee_id";
			String query="select a.employee_id, a.emp_name, b.emp_name manager_name, c.department_name "+
							"from employees a, employees b, departments c "+
							"where a.manager_id=b.employee_id "+
							"and a.department_id=c.department_id";
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				int employee_id=rs.getInt("employee_id");
				String emp_name=rs.getString("emp_name");
				String manager_id=rs.getString("manager_name");
				String department_id=rs.getString("department_name");
				empVO evo=new empVO();
				evo.setEmployee_id(employee_id);
				evo.setEmp_name(emp_name);
				evo.setManager_id(manager_id);
				evo.setDepartment_id(department_id);
				list.add(evo);
			}
			rs.close();		// memory 반납
			stmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// DB와 연결하는 코드
	private void connDB() {
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String userid="ora_user";
		String passcode="human123";
		try {
			Class.forName(driver);
			this.conn=DriverManager.getConnection(url, userid, passcode);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}