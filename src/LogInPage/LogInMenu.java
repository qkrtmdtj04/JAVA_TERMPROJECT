// LogInManu.java

// 필요한 패키지를 가져옵니다.
package LogInPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import calender.main_ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// LogInManu 클래스를 정의합니다.
public class LogInMenu extends JFrame {
    // 추가: username을 저장하기 위한 전역 변수
    public static String loggedInUsername;

    // LogInManu 클래스의 생성자
    public LogInMenu() {
        // 필요한 UI 요소들을 생성합니다.
        JPanel panel = new JPanel();
        JLabel label = new JLabel("ID : ");
        JLabel password = new JLabel("PassWord : ");

        JTextField txtID = new JTextField(10);
        JPasswordField txtPass = new JPasswordField(10);
        JButton logbtn = new JButton("로그인");
        JButton newuserbtn = new JButton("회원가입");

        // 패널에 UI 요소들을 추가합니다.
        panel.add(label);
        panel.add(txtID);
        panel.add(password);
        panel.add(txtPass);
        panel.add(logbtn);
        panel.add(newuserbtn);

        // 로그인 버튼에 대한 ActionListener를 추가합니다.
        logbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 입력한 ID와 비밀번호를 확인하여 로그인 여부를 판단합니다.
                if (IdPassCheck(txtID.getText(), new String(txtPass.getPassword()))) {
                    loggedInUsername = txtID.getText(); // 로그인 성공 시, loggedInUsername에 값 설정
                    main_ui mainUI = new main_ui();
                    mainUI.setLoggedInUserID(loggedInUsername);
                    mainUI.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "로그인에 실패했습니다");
                }
            }
        });

        // 회원가입 버튼에 대한 ActionListener를 추가합니다.
        newuserbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 회원가입 페이지를 엽니다.
                new JoinUser();
                // 현재 로그인 페이지를 숨깁니다.
                setVisible(false);
            }
        });

        // 패널을 프레임에 추가하고 화면을 표시합니다.
        add(panel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 780, 780);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // ID와 비밀번호를 확인하여 로그인 여부를 반환하는 메서드
    private boolean IdPassCheck(String enteredID, String enteredPass) {
        // SQLite 데이터베이스 경로
        String dbURL = "jdbc:sqlite:database.db";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 데이터베이스 연결
            conn = DriverManager.getConnection(dbURL);

            // SQL 쿼리 작성 및 준비
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, enteredID);
            pstmt.setString(2, enteredPass);
            rs = pstmt.executeQuery();

            // 입력한 ID와 비밀번호가 데이터베이스에 있는지 확인하고 결과 반환
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                // 리소스 정리
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
