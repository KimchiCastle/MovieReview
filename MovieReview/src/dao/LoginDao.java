package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.movieService;
import vo.LoginVo;

public class LoginDao {

	//아주 좋습니다. Great!
	//스태틱객체는 무조건 하나만 만들어진다.
	static LoginDao single = null;

	//스태틱은 무조건 스태틱으로만
	public static LoginDao getInstance() {

		//객체가 없으면 생성해라 호출된 한번만 객체를 생성
		if (single == null)
			single = new LoginDao();

		return single;
	}

	//외부에서 객체를 생성하지 못하게 막음
	private LoginDao() {
		// TODO Auto-generated constructor stub
	}

	
	public List<LoginVo> selectList() {

		List<LoginVo> list = new ArrayList<LoginVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member";

		try {
			//1.connection 얻어오기
			//				 커낵션 객체생성, DB에게 커낵션얻기
			conn = movieService.getInstance().getConnection();

			//2.PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3.ResultSet 얻어오기

			rs = pstmt.executeQuery();

			//4.포장(record -> Vo -> list)

			while (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어온다.

				//Vo로 포장
				LoginVo vo = new LoginVo();
				vo.setUserid(rs.getString("userid"));
				vo.setNickname(rs.getString("nickname"));
				vo.setPassword(rs.getString("password"));
				//list에 추가

				list.add(vo);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//연결(생성) 되었으면 닫아라.(생성 역순으로 닫기)
				if (rs != null)
					rs.close(); // 3
				if (pstmt != null)
					pstmt.close(); // 2
				if (conn != null)
					conn.close(); // 1

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
	
	//회원가입할때 사용되는
	public int signUp_insert(LoginVo vo) { // 호출한 사용자가 전달한 값
		// TODO Auto-generated method stub
		
		int res = 0;
		
		Connection		   conn  = null;
		PreparedStatement  pstmt = null;
		
		//										1  2  3
		String sql = "insert into member values(?, ?, ?)";
		
		
		try {
			//1.Connection 얻어오기
			conn = movieService.getInstance().getConnection();
			
			//2.PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql); // 캐싱
			
			//파라미터만들기
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getNickname());
			pstmt.setString(3, vo.getPassword());
			
			
			//4.DML(insert/update/delete)명령 실행, res는 처리된 행수를반환
			res = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally {
			
			try {
				//닫기 (열린 역순)
				if(pstmt != null) pstmt.close(); // 2
				if(conn != null) conn.close();   // 1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		//리턴을 0으로 받으면 명령 실패!
		return res;
	}
	
	
	
	
}