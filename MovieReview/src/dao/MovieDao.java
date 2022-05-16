package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Vo.CategoryVo;
import Vo.MovieVo;
import dbService.movieService;

public class MovieDao {
	//single-ton : ��ü 1���� �����ؼ� �������
	static MovieDao single = null;

	public static MovieDao getInstance() {

		//��ü�� �����Ǿ� ���� ������ ������.
		if (single == null) {
			single = new MovieDao();
		}
		//������ ����� ���� ��ü�� �״�� ��ȯ�Ѵ�.
		return single;
	}

	//�ܺο��� �������� ���ϵ��� ��������. ��ü�� getInstance�޼ҵ带 ���ؼ��� ��������.
	private MovieDao() {
		// TODO Auto-generated constructor stub
	}
	
	//��ȭ���̺��� ��ȸ�ؼ� ��ȭ��ȣ�� ���ڷ� �޾Ƽ� ��ȭ������ �˷���
	public String selectMovieTitle(int movieNo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select title from movie where movieidx = ?";
		String title="";
		
		try {
			//1. connection ������
			conn = movieService.getInstance().getConnection();

			//2. PreparedStatement ������
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			
			//3. ResultSet ������ 
			rs = pstmt.executeQuery();

			//4. ����
			while (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о� �´�.

				//Vo�� ����(�ݺ��� 1ȸ ���Ƽ� ���ο� �����͸� ���� ������ �� ���ڵ带 ������ vo�� ���� �������� �Ѵ�.)
				
				title = rs.getString("title");
				//list�� �߰� 

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

		return title;
	}
	
	//��ȭ���̺� ��ȸ�ؼ� ��ȭ������ ���ڷ� �޾Ƽ� ��ȭ��ȣ�� �˷���
	public int selectMovieNo(String title) {

		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select movieidx from movie where title=?";
		
		int movieidx = 0;
		
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
				//MovieVo vo = new MovieVo(rs.getInt("movieidx"), rs.getString("title"));

				movieidx = rs.getInt("movieidx");

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

		return movieidx;
	}
	
	//�������� ��ȭ���� �о���� 
	public List<MovieVo> selectList() {

		List<MovieVo> list = new ArrayList<MovieVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from movie where movieidx between 1 and 5";

		try {
			//1. connection ������
			conn = movieService.getInstance().getConnection();

			//2. PreparedStatement ������
			pstmt = conn.prepareStatement(sql);

			//3. ResultSet ������ 
			rs = pstmt.executeQuery();

			//4. ����
			while (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о� �´�.

				//Vo�� ����(�ݺ��� 1ȸ ���Ƽ� ���ο� �����͸� ���� ������ �� ���ڵ带 ������ vo�� ���� �������� �Ѵ�.)
				MovieVo vo = new MovieVo();
				vo.setMovieidx(rs.getInt("movieidx"));
				vo.setTitle(rs.getString("title"));

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
	
	//�������� ��ȭ ī�װ� �о����
	public List<CategoryVo> select_Category_List() {

		List<CategoryVo> list = new ArrayList<CategoryVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from category";

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
				CategoryVo vo = new CategoryVo();
				vo.setCateno(rs.getInt("cateno"));
				vo.setCatename(rs.getString("catename"));
				
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
	
	//�����ε�, ī�װ� ��ȭ ���
	public List<MovieVo> selectList(int cateno) {

		List<MovieVo> list = new ArrayList<MovieVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select movieidx, title from category_view where cateno = ?";

		try {
			//1.connection ������
			//				 Ŀ���� ��ü����, DB���� Ŀ���Ǿ��
			conn = movieService.getInstance().getConnection();

			//2.PreparedStatement ������
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cateno);
			
			//3.ResultSet ������

			rs = pstmt.executeQuery();

			//4.����(record -> Vo -> list)

			while (rs.next()) {
				//rs�� ����Ű�� ��(���ڵ�)�� ���� �о�´�.

				//Vo�� ����
				MovieVo vo = new MovieVo();
				vo.setMovieidx(rs.getInt("movieidx"));
				vo.setTitle(rs.getString("title"));
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
	

	
	
	
	
}
