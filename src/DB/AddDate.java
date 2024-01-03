// AddDate.java

// 필요한 패키지를 가져옵니다.
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

// AddDate 클래스를 정의합니다.
public class AddDate {

    // 데이터베이스에 일정을 추가하는 메서드
    public static void addScheduleToDB(int year, int month, String day, String input, String startTime, String endTime, String username) {
        // SQLite 데이터베이스 경로
        String dbURL = "jdbc:sqlite:database.db";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 데이터베이스 연결
            conn = DriverManager.getConnection(dbURL);

            // SQL 쿼리를 작성하고 준비합니다.
            String sql = "INSERT INTO schedule (year, month, day, description, start_time, end_time, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            pstmt.setString(3, day);
            pstmt.setString(4, input);
            pstmt.setString(5, startTime);
            pstmt.setString(6, endTime);
            pstmt.setString(7, username);

            // 쿼리 실행
            pstmt.executeUpdate();
            System.out.println("일정 추가 완료");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // 리소스 정리
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // 특정 날짜의 사용자 스케줄을 가져오는 메서드
    public static ArrayList<Schedule> fetchUserSchedules(int year, int month, int day, String username) {
        ArrayList<Schedule> userSchedules = new ArrayList<>();
        String dbURL = "jdbc:sqlite:database.db";

        try (Connection conn = DriverManager.getConnection(dbURL)) {
            // SQL 쿼리 작성 및 준비
            String sql = "SELECT * FROM schedule WHERE year = ? AND month = ? AND day = ? AND username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            pstmt.setInt(3, day);
            pstmt.setString(4, username);


            // 쿼리 실행 및 결과 처리
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int retrievedYear = rs.getInt("year");
                int retrievedMonth = rs.getInt("month");
                int retrievedDay = rs.getInt("day");

                String description = rs.getString("description");
                String startTime = rs.getString("start_time");
                String endTime = rs.getString("end_time");

                System.out.printf("%d %d",retrievedMonth,retrievedYear);
                System.out.println(description);
                Schedule schedule = new Schedule(retrievedYear, retrievedMonth, retrievedDay, description, startTime, endTime, username);
                userSchedules.add(schedule);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userSchedules;
    }

    // 특정 날짜의 사용자 스케줄을 삭제하는 메서드
    public static void deleteUserSchedule(int year, int month, int day, String username) {
        String dbURL = "jdbc:sqlite:database.db";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 데이터베이스 연결
            conn = DriverManager.getConnection(dbURL);

            // SQL 쿼리 작성 및 준비
            String sql = "DELETE FROM schedule WHERE year = ? AND month = ? AND day = ? AND username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            pstmt.setInt(3, day);
            pstmt.setString(4, username);

            // 쿼리 실행
            pstmt.executeUpdate();

            System.out.println("일정 삭제 완료");

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // 리소스 정리
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
