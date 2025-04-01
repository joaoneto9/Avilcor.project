package program;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import db.DB;
import db.DbException;


public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		try (Scanner sc = new Scanner(System.in)) {
			conn = DB.getConnection();
			while (true) {
				conn.setAutoCommit(false);
				Menu menu = new Menu(conn, sc);
				System.out.println(menu.menuInterface());
				String input = sc.nextLine();
				String output = menu.functionInput(input);
				System.out.println(output);
				if (output.equals("FIM DO PROGRAMA"))
					break;
				conn.commit();
			}
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
				throw new DbException(e2.getMessage());
			}
			throw new DbException(e.getMessage());
		} finally {
			DB.closeConnection();
		}
	}

}





