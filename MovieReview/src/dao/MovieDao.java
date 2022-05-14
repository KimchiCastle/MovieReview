package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.movieService;
import vo.MovieVo;

public class MovieDao {
	//single-ton : 객체 1개만 생성해서 사용하자
	static MovieDao single = null;

	public static MovieDao getInstance() {

		//객체가 생성되어 있지 않으면 만들어라.
		if (single == null) {
			single = new MovieDao();
		}
		//이전에 만들어 놨던 객체를 그대로 반환한다.
		return single;
	}

	//외부에서 생성하지 못하도록 접근제한. 객체는 getInstance메소드를 통해서만 생성가능.
	private MovieDao() {
		// TODO Auto-generated constructor stub
	}
	
	//영화테이블을 조회해서 영화번호를 인자로 받아서 영화제목을 알려줌
	public String selectMovieTitle(int movieNo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select title from movie where movieidx = ?";
		String title="";
		
		try {
			//1. connection 얻어오기
			conn = movieService.getInstance().getConnection();

			//2. PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			
			//3. ResultSet 얻어오기 
			rs = pstmt.executeQuery();

			//4. 포장
			while (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어 온다.

				//Vo로 포장(반복을 1회 돌아서 새로운 데이터를 읽을 때마다 이 레코드를 저장할 vo를 만들어서 포장해햐 한다.)
				
				title = rs.getString("title");
				//list에 추가 

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();//
		} finally {//반드시 실행하는 구문

			try {

				//연결되어 있는 상태면 끊어라.(생성 역순으로)

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
	
	//영화테이블 조회해서 영화제목을 인자로 받아서 영화번호를 알려줌
	public int selectMovieNo(String title) {

		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select movieidx from movie where title=?";
		
		int movieidx = 0;
		
		try {
			//1. connection 얻어오기
			conn = movieService.getInstance().getConnection();

			//2. PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			//3. ResultSet 얻어오기 
			rs = pstmt.executeQuery();

			//4. 포장
			while (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어 온다.

				//Vo로 포장(반복을 1회 돌아서 새로운 데이터를 읽을 때마다 이 레코드를 저장할 vo를 만들어서 포장해햐 한다.)
				//MovieVo vo = new MovieVo(rs.getInt("movieidx"), rs.getString("title"));

				movieidx = rs.getInt("movieidx");

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();//
		} finally {//반드시 실행하는 구문

			try {

				//연결되어 있는 상태면 끊어라.(생성 역순으로)

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
	
	//동적으로 영화제목 읽어오기 
	public List<MovieVo> selectList() {

		List<MovieVo> list = new ArrayList<MovieVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from movie where movieidx between 1 and 5";

		try {
			//1. connection 얻어오기
			conn = movieService.getInstance().getConnection();

			//2. PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);

			//3. ResultSet 얻어오기 
			rs = pstmt.executeQuery();

			//4. 포장
			while (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어 온다.

				//Vo로 포장(반복을 1회 돌아서 새로운 데이터를 읽을 때마다 이 레코드를 저장할 vo를 만들어서 포장해햐 한다.)
				MovieVo vo = new MovieVo();
				vo.setMovieidx(rs.getInt("movieidx"));
				vo.setTitle(rs.getString("title"));

				//list에 추가 
				list.add(vo);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();//
		} finally {//반드시 실행하는 구문

			try {

				//연결되어 있는 상태면 끊어라.(생성 역순으로)

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
}
