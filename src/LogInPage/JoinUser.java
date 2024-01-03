// JoinUser.java

// 필요한 패키지를 가져옵니다.
package LogInPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// JoinUser 클래스를 정의합니다.
public class JoinUser extends JFrame {

    // JoinUser 클래스의 생성자
    public JoinUser() {
        // 필요한 UI 요소들을 생성합니다.
        JPanel panel = new JPanel();
        JLabel label = new JLabel("ID : ");
        JLabel password = new JLabel("PassWord : ");

        JTextField txtID = new JTextField(10);
        JPasswordField txtPass = new JPasswordField(10);
        JButton joinuserbtn = new JButton("회원가입");

        // 패널에 UI 요소들을 추가합니다.
        panel.add(label);
        panel.add(txtID);
        panel.add(password);
        panel.add(txtPass);
        panel.add(joinuserbtn);

        // 회원가입 버튼에 대한 ActionListener를 추가합니다.
        joinuserbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 데이터베이스에 사용자를 추가하고 로그인 화면을 엽니다.
                addUserToDB(txtID.getText(), new String(txtPass.getPassword()));
                new LogInMenu();
                setVisible(false);
            }
        });

        // 패널을 프레임에 추가하고 화면을 표시합니다.
        add(panel);

        setVisible(true);
        setBounds(100, 100, 780, 780);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // 데이터베이스에 사용자를 추가하는 메서드
    private void addUserToDB(String enteredID, String enteredPass) {
        // SQLite 데이터베이스 경로
        String dbURL = "jdbc:sqlite:database.db";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 데이터베이스 연결
            conn = DriverManager.getConnection(dbURL);

            // SQL 쿼리 작성 및 준비
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, enteredID);
            pstmt.setString(2, enteredPass);

            // 쿼리 실행
            pstmt.executeUpdate();
            System.out.println("사용자 추가 완료");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 리소스 정리
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
