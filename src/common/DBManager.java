package common;


import java.sql.*;

/**
 * JDBC를 위한 로드, 연결, 닫기
 */
public class DBManager {

	/**
	 * 로드
	 */
	static {
		try {
		  Class.forName(DBProperties.DRIVER_NAME);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 연결
	 * @return Connection for JDBC
	 * @throws SQLException SQLException in Connection Process
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(DBProperties.URL,
				DBProperties.USER_ID, DBProperties.USER_PASS);
	}

	/**
	 * 닫기(DML전용)
	 * @param con Connection for JDBC
	 * @param st Statement for JDBC Query
	 */
	public static void releaseConnection(Connection con, Statement st) {
		try {
			if(st!=null) st.close();
			if(con!=null)con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 닫기(select전용)
	 * @param con Connection for JDBC
	 * @param st Statement for JDBC Query
	 * @param rs ResultSet for JDBC Query
	 */
    public static void releaseConnection(Connection con, Statement st, ResultSet rs) {
    	try {
    		if(rs!=null)rs.close();
    	}catch (SQLException e) {
			e.printStackTrace();
		}
    	releaseConnection(con, st);
	}
}





