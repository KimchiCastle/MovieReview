package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Vo.LoginVo;
import dbService.movieService;

public class LoginDao {


	static LoginDao single = null;

	public static LoginDao getInstance() {

		
		if (single == null)
			single = new LoginDao();

		return single;
	}

	//�ܺο��� ��ü�� �������� ���ϰ� ����
	private LoginDao() {
		// TODO Auto-generated constructor stub
	}

	
	
	//��� ���̺� ��ȸ
	public List<LoginVo> selectList() {

		List<LoginVo> list = new ArrayList<LoginVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member";

		try {
			//1.connection ������
			//				 Ŀ���� ��ü����, DB���� Ŀ���Ǿ��
			conn = movieService.getInstance().getConnection();

			//2.PreparedStatement ������
			pstmt = conn.prepareStatement(sql);

			//3.ResultSet ������

			rs = pstmt.executeQuery();

			//4.����(record -> Vo -> list)

			while (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о�´�.

				//Vo�� ����
				LoginVo vo = new LoginVo();
				vo.setUserid(rs.getString("userid"));
				vo.setNickname(rs.getString("nickname"));
				vo.setPassword(rs.getString("password"));
				//list�� �߰�

				list.add(vo);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//����(����) �Ǿ����� �ݾƶ�.(���� �������� �ݱ�)
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
	
	
	//id �ߺ�üũ�� �޼ҵ�
	public LoginVo selectOneFromID(String userid) {

		LoginVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where userid=?";

		try {
			//1.connection ������
			//				 Ŀ���� ��ü����, DB���� Ŀ���Ǿ��
			conn = movieService.getInstance().getConnection();

			//2.PreparedStatement ������
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userid);
			//3.ResultSet ������

			rs = pstmt.executeQuery();

			//4.����(record -> Vo -> list)

			while (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о�´�.

				//Vo�� ����
				vo = new LoginVo();
				vo.setUserid(rs.getString("userid"));
				vo.setNickname(rs.getString("nickname"));
				vo.setPassword(rs.getString("password"));
				

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//����(����) �Ǿ����� �ݾƶ�.(���� �������� �ݱ�)
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

		return vo;
	}
	
	
	//password �ߺ�üũ �޼ҵ�
	public LoginVo passwordCheck(String userid) {
		
		LoginVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select password from member where userid=?";
		
		try {
			//1.connection ������
			//				 Ŀ���� ��ü����, DB���� Ŀ���Ǿ��
			conn = movieService.getInstance().getConnection();
			
			//2.PreparedStatement ������
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			//3.ResultSet ������
			
			rs = pstmt.executeQuery();
			
			//4.����(record -> Vo -> list)
			
			while (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о�´�.
				
				//Vo�� ����
				vo = new LoginVo();
				vo.setUserid(rs.getString("userid"));
				vo.setNickname(rs.getString("nickname"));
				vo.setPassword(rs.getString("password"));
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
			try {
				//����(����) �Ǿ����� �ݾƶ�.(���� �������� �ݱ�)
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
		
		return vo;
	}
	
	//�г��� �ߺ�üũ�� �޼ҵ�
	public LoginVo selectOneFromNickname(String nickname) {

		LoginVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where nickname=?";

		try {
			//1.connection ������
			//				 Ŀ���� ��ü����, DB���� Ŀ���Ǿ��
			conn = movieService.getInstance().getConnection();

			//2.PreparedStatement ������
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, nickname);
			//3.ResultSet ������

			rs = pstmt.executeQuery();

			//4.����(record -> Vo -> list)

			while (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о�´�.

				//Vo�� ����
				vo = new LoginVo();
				vo.setUserid(rs.getString("userid"));
				vo.setNickname(rs.getString("nickname"));
				vo.setPassword(rs.getString("password"));
				

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//����(����) �Ǿ����� �ݾƶ�.(���� �������� �ݱ�)
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

		return vo;
	}
	
	
	
	
	
	//ȸ�������Ҷ� ���Ǵ�
	public int signUp_insert(LoginVo vo) { // ȣ���� ����ڰ� ������ ��
		// TODO Auto-generated method stub
		
		int res = 0;
		
		Connection		   conn  = null;
		PreparedStatement  pstmt = null;
		
		//										1  2  3
		String sql = "insert into member values(?, ?, ?)";
		
		
		try {
			//1.Connection ������
			conn = movieService.getInstance().getConnection();
			
			//2.PreparedStatement ������
			pstmt = conn.prepareStatement(sql); // ĳ��
			
			//�Ķ���͸����
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getNickname());
			pstmt.setString(3, vo.getPassword());
			
			
			//4.DML(insert/update/delete)��� ����, res�� ó���� �������ȯ
			res = pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally {
			
			try {
				//�ݱ� (���� ����)
				if(pstmt != null) pstmt.close(); // 2
				if(conn != null) conn.close();   // 1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		//������ 0���� ������ ��� ����!
		return res;
	}
	
	
	
	
}
