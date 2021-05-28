package model.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class DBUtil {
	static {
		try {
			Class.forName(DBProperty.DRIVER_NAME);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DBProperty.URL, DBProperty.ID, DBProperty.PW);
	}
	
	public static void dbClose(Connection con, Statement st) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void dbClose(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			dbClose(con, st);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
