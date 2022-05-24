import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {
	private Statement stmt;
	private Connection conn;
	
	public boolean exist(String userid, String passwd) {
		boolean result=false;
		try {
			connDB();
			String query="select * from t_member where id='"+userid+"' and pwd='"+passwd+"'";
			this.stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			int n=0;
			while(rs.next()) {
				n++;
			}
			if(n==1) result=true;
			else result=false;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void updateMember(MemberVO mvo) {
		try {
			connDB();
			String query="update t_member set name=?, pwd=?, mobile=?, joindate=? where id=?";
			PreparedStatement psmt=conn.prepareStatement(query);
			psmt.setString(1, mvo.getName());
			psmt.setString(2, mvo.getPwd());
			psmt.setString(3, mvo.getMobile());
			psmt.setString(4, mvo.getJoinDate());
			psmt.setString(5, mvo.getId());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteMember(String id) {
		try {
			connDB();
			String query="delete from t_member where id=?";
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
			psmt.executeUpdate();
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void addnewMember(MemberVO mvo) {
		// db connection
		// SQl 구성
		// 실행요청 -> DB에 추가(insert)
		try {
			connDB();
//			String query="insert into t_member values('"+mvo.getId()+"','"+mvo.getPwd()+"','"+mvo.getName()+
//					"','"+mvo.getMobile()+"','"+mvo.getJoinDate()+"')";
			String query="insert into t_member values (?,?,?,?,?)";
			PreparedStatement psmt= conn.prepareStatement(query);
			psmt.setString(1, mvo.getId());
			psmt.setString(2, mvo.getPwd());
			psmt.setString(3, mvo.getName());
			psmt.setString(4, mvo.getMobile());
			psmt.setString(5, mvo.getJoinDate());
			psmt.executeUpdate();			
			psmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public MemberVO getMember(String id) {
		MemberVO mvo=new MemberVO();
		try {
			connDB();
			String query="select * from t_member where id='"+id+"'";
			this.stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			mvo.setId(rs.getString("id"));
			mvo.setName(rs.getString("name"));
			mvo.setPwd(rs.getString("pwd"));
			mvo.setMobile(rs.getString("mobile"));
			mvo.setJoinDate(rs.getString("joindate"));
			this.stmt.close();
			rs.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mvo;
	}
	public ArrayList<MemberVO> listMember() {	// Read - select
		ArrayList<MemberVO> list=new ArrayList<MemberVO>();
		try {
			connDB();
			String query="select * from t_member order by name";
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String mobile=rs.getString("mobile");
				String joindate=rs.getString("joindate");
				MemberVO mvo=new MemberVO();
				mvo.setId(id);
				mvo.setPwd(pwd);
				mvo.setName(name);
				mvo.setMobile(mobile);
				mvo.setJoinDate(joindate);
				list.add(mvo);
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