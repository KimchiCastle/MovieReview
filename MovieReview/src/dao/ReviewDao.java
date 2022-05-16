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
	//single-ton : 객체 1개만 생성해서 사용하자
	static ReviewDao single = null;

	public static ReviewDao getInstance() {

		//객체가 생성되어 있지 않으면 만들어라.
		if (single == null) {
			single = new ReviewDao();
		}
		
		//이전에 만들어 놨던 객체를 그대로 반환한다.
		return single;
	}

	//외부에서 생성하지 못하도록 접근제한. 객체는 getInstance메소드를 통해서만 생성가능.
	private ReviewDao() {
		// TODO Auto-generated constructor stub
	}
	
	//전체 리뷰테이블 조회
	public List<ReviewVo> selectList_All() {

		List<ReviewVo> list = new ArrayList<ReviewVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from review_view";

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
				ReviewVo vo = new ReviewVo();
				vo.setGeulno(rs.getInt("geulno"));
				vo.setTitle(rs.getString("title"));
				vo.setGeultext(rs.getString("geultext"));
				vo.setNickname(rs.getString("nickname"));
				vo.setGeulDate(rs.getString("geuldate"));
				
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
	
	
	//영화제목을 인자로 받아서 리뷰테이블 조회하기 
	public List<ReviewVo> selectList_UserOnly(String nickname) {
		
		List<ReviewVo> list = new ArrayList<ReviewVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from review_view where nickname = ?";
		
		try {
			//1. connection 얻어오기
			conn = movieService.getInstance().getConnection();
			
			//2. PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			
			//3. ResultSet 얻어오기 
			rs = pstmt.executeQuery();
			
			//4. 포장
			while (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어 온다.
				
				//Vo로 포장(반복을 1회 돌아서 새로운 데이터를 읽을 때마다 이 레코드를 저장할 vo를 만들어서 포장해햐 한다.)
				ReviewVo vo = new ReviewVo();
				vo.setGeulno(rs.getInt("geulno"));
				vo.setTitle(rs.getString("title"));
				vo.setGeultext(rs.getString("geultext"));
				vo.setNickname(rs.getString("nickname"));
				vo.setGeulDate(rs.getString("geuldate"));
				
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
	//영화제목을 인자로 받아서 리뷰테이블 조회하기 
	public List<ReviewVo> selectList(String title) {

		List<ReviewVo> list = new ArrayList<ReviewVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from review_view where title = ?";

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
				ReviewVo vo = new ReviewVo();
				vo.setGeulno(rs.getInt("geulno"));
				vo.setTitle(rs.getString("title"));
				vo.setGeultext(rs.getString("geultext"));
				vo.setNickname(rs.getString("nickname"));
				vo.setGeulDate(rs.getString("geuldate"));

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
	
	//작성한 영화 입력하기
	public int insert_review(ReviewVo vo) {//버튼을 누름으로써 자바에게 전달받은 vo
		// TODO Auto-generated method stub
		int res = 0;
		Connection         conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into review values((select nvl(max(geulno)+1,1) from review), ?, ?, ?, ?)";
		try {
			//1. Connection얻어오기
			conn = movieService.getInstance().getConnection();
			//2. 명령 처리 객체 얻어오기
			pstmt = conn.prepareStatement(sql);
			//3. pstmt의 파라미터 설정//데이터의 자료형을 필히 파악하라!//문자던 숫자던 자료형에 상관없이 변수는 무조건 "?"이다.
			pstmt.setInt(1, vo.getMovieidx());
			pstmt.setString(2, vo.getGeultext());
			pstmt.setString(3, vo.getUserId());
			pstmt.setString(4, vo.getGeulDate());
			//4. DML(insert/update/delete) : res는 처리된 행의 수를 반환합니다. 한번의 삽입은 무조건 1줄만 수행되므로 삽입이 성공했다면 res는 반드시 1, 만약에 res가 0이라면 삽입연산이 제대로 수행되지 않은 것이다.
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//닫기(열린역순)
				if(pstmt != null) pstmt.close(); //2
				if(conn  != null) conn.close();  //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	public int update_review(ReviewVo vo) {
	
		int res = 0;
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		//                                 
		String  sql = "update review set geultext=?, geuldate=? where geulno=?";
		
		try {
			//1.Connection얻어오기
			conn = movieService.getInstance().getConnection();
			
			//2.PreparedStatement얻어오기
			pstmt = conn.prepareStatement(sql);
			
			//3.pstmt parameter 설정
			pstmt.setString(1, vo.getGeultext());
			pstmt.setString(2, vo.getGeulDate());
			pstmt.setInt(3, vo.getGeulno());
		
			//3.DML(insert/update/delete) : res<-처리된 행수반환
			res = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
			try {
				//닫기(열린역순)
				if(pstmt!=null) pstmt.close();//2
				if(conn!=null)  conn.close(); //1
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}

	public int delete_review(int geulno) {
		
		int res = 0; //성공하면 1, 실패하면 0
		
		Connection conn = null;
		PreparedStatement pstmt = null;					
		String sql = "delete from review where geulno=?";
		
		try {
			//1.connection얻어오기
			conn = movieService.getInstance().getConnection();
			
			//2.prepared statement 얻어오기
			pstmt = conn.prepareStatement(sql);
			
			//3.pstmt parameter 설정
			pstmt.setInt(1, geulno); //첫 인덱스 설정
			
			//4.DML(insert/update/delete)
			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace(); //에러내용확인
		}finally {
			
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
		
	}
	
	public List<ReviewVo> selectList_UserOnly(int geulno) {
		
		List<ReviewVo> list = new ArrayList<ReviewVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select nickname from review_view where geulno = ?";
		
		try {
			//1. connection 얻어오기
			conn = movieService.getInstance().getConnection();
			
			//2. PreparedStatement 얻어오기
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, geulno);
			
			//3. ResultSet 얻어오기 
			rs = pstmt.executeQuery();
			
			//4. 포장
			while (rs.next()) {
				//rs가 가리키는 행(레코드)의 값을 읽어 온다.
				
				//geulno로 메인에서 받아와서 sql에 부합하는 nickname을 vo로 포장해서 data에 전달
				ReviewVo vo = new ReviewVo();
				//vo.setGeulno(rs.getInt("geulno"));
//				vo.setTitle(rs.getString("title"));
//				vo.setGeultext(rs.getString("geultext"));
				vo.setNickname(rs.getString("nickname"));
//				vo.setGeulDate(rs.getString("geuldate"));
				
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
