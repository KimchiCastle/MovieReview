package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.movieService;
import vo.ReviewVo;

public class ReviewDao {
	//single-ton : ��ü 1���� �����ؼ� �������
	static ReviewDao single = null;

	public static ReviewDao getInstance() {

		//��ü�� �����Ǿ� ���� ������ ������.
		if (single == null) {
			single = new ReviewDao();
		}
		//������ ����� ���� ��ü�� �״�� ��ȯ�Ѵ�.
		return single;
	}

	//�ܺο��� �������� ���ϵ��� ��������. ��ü�� getInstance�޼ҵ带 ���ؼ��� ��������.
	private ReviewDao() {
		// TODO Auto-generated constructor stub
	}
	
	//��ȭ������ ���ڷ� �޾Ƽ� �������̺� ��ȸ�ϱ� 
	public List<ReviewVo> selectList_UserOnly(String nickname) {
		
		List<ReviewVo> list = new ArrayList<ReviewVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from review_view where nickname = ?";
		
		try {
			//1. connection ������
			conn = movieService.getInstance().getConnection();
			
			//2. PreparedStatement ������
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			
			//3. ResultSet ������ 
			rs = pstmt.executeQuery();
			
			//4. ����
			while (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о� �´�.
				
				//Vo�� ����(�ݺ��� 1ȸ ���Ƽ� ���ο� �����͸� ���� ������ �� ���ڵ带 ������ vo�� ���� �������� �Ѵ�.)
				ReviewVo vo = new ReviewVo();
				vo.setGeulno(rs.getInt("geulno"));
				vo.setTitle(rs.getString("title"));
				vo.setGeultext(rs.getString("geultext"));
				vo.setNickname(rs.getString("nickname"));
				vo.setGeulDate(rs.getString("geuldate"));
				
				//list�� �߰� 
				list.add(vo);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();//
		} finally {//�ݵ�� �����ϴ� ����
			
			try {
				
				//����Ǿ� �ִ� ���¸� �����.(���� ��������)
				
				if (rs != null)
					rs.close(); //3
				if (pstmt != null)
					pstmt.close();//2
				if (conn != null)
					conn.close();//1
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	//��ȭ������ ���ڷ� �޾Ƽ� �������̺� ��ȸ�ϱ� 
	public List<ReviewVo> selectList(String title) {

		List<ReviewVo> list = new ArrayList<ReviewVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from review_view where title = ?";

		try {
			//1. connection ������
			conn = movieService.getInstance().getConnection();

			//2. PreparedStatement ������
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);

			//3. ResultSet ������ 
			rs = pstmt.executeQuery();

			//4. ����
			while (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о� �´�.

				//Vo�� ����(�ݺ��� 1ȸ ���Ƽ� ���ο� �����͸� ���� ������ �� ���ڵ带 ������ vo�� ���� �������� �Ѵ�.)
				ReviewVo vo = new ReviewVo();
				vo.setGeulno(rs.getInt("geulno"));
				vo.setTitle(rs.getString("title"));
				vo.setGeultext(rs.getString("geultext"));
				vo.setNickname(rs.getString("nickname"));
				vo.setGeulDate(rs.getString("geuldate"));

				//list�� �߰� 
				list.add(vo);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();//
		} finally {//�ݵ�� �����ϴ� ����

			try {

				//����Ǿ� �ִ� ���¸� �����.(���� ��������)

				if (rs != null)
					rs.close(); //3
				if (pstmt != null)
					pstmt.close();//2
				if (conn != null)
					conn.close();//1

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
	
	//�ۼ��� ��ȭ �Է��ϱ�
	public int insert_review(ReviewVo vo) {//��ư�� �������ν� �ڹٿ��� ���޹��� vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into review values((select nvl(max(geulno)+1,1) from review), ?, ?, ?, ?)";
		try {
			//1. Connection������
			conn = movieService.getInstance().getConnection();
			//2. ��� ó�� ��ü ������
			pstmt = conn.prepareStatement(sql);
			//3. pstmt�� �Ķ���� ����//�������� �ڷ����� ���� �ľ��϶�!//���ڴ� ���ڴ� �ڷ����� ������� ������ ������ "?"�̴�.
			pstmt.setInt(1, vo.getMovieidx());
			pstmt.setString(2, vo.getGeultext());
			pstmt.setString(3, vo.getUserId());
			pstmt.setString(4, vo.getGeulDate());
			//4. DML(insert/update/delete) : res�� ó���� ���� ���� ��ȯ�մϴ�. �ѹ��� ������ ������ 1�ٸ� ����ǹǷ� ������ �����ߴٸ� res�� �ݵ�� 1, ���࿡ res�� 0�̶�� ���Կ����� ����� ������� ���� ���̴�.
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//�ݱ�(��������)
				if(pstmt != null) pstmt.close(); //2
				if(conn  != null) conn.close();  //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}
}
