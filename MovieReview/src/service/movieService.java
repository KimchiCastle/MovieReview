package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class movieService {

	static {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	static movieService single = null;

	public static movieService getInstance() {
		// ��ü ������ �ڽŰ�ü�� ������
		if (single == null)
			single = new movieService();

		return single;
	}

//�ܺο��� �������� ����� 
	private movieService() {

	}

	public Connection getConnection() throws SQLException {

		Connection conn = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "test";
		String pwd = "test";

		conn = DriverManager.getConnection(url, id, pwd);

		return conn;

	}

}
