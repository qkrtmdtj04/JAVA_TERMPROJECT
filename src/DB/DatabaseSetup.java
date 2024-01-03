
/*

package DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // SQLite JDBC 드라이버 로드
            Class.forName("org.sqlite.JDBC");

            // SQLite 데이터베이스 파일 경로
            String dbURL = "jdbc:sqlite:database.db";

            // 데이터베이스에 연결
            connection = DriverManager.getConnection(dbURL);

            // SQL 문 실행을 위한 Statement
            Statement statement = connection.createStatement();

            // 사용자 테이블 생성
            String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "user_id INTEGER PRIMARY KEY," +
                    "username TEXT," +
                    "password TEXT)";
            statement.execute(createUserTable);

            // 일정 테이블 생성
            String createScheduleTable = "CREATE TABLE IF NOT EXISTS schedules (" +
                    "schedule_id INTEGER PRIMARY KEY," +
                    "user_id INTEGER," +
                    "schedule_date TEXT," +
                    "schedule_description TEXT," +
                    "start_time TEXT," +
                    "end_time TEXT," +
                    "FOREIGN KEY (user_id) REFERENCES users(user_id))";
            statement.execute(createScheduleTable);

            System.out.println("테이블 생성 완료");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



*/