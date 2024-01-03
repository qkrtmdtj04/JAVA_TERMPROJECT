// main_ui.java

// 필요한 패키지 및 클래스를 가져옵니다.
package calender;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import DB.Schedule;
import java.util.ArrayList;

// main_ui 클래스를 JFrame을 확장하여 정의합니다.
public class main_ui extends JFrame {

    // DateBox 배열을 선언합니다. 7x6 그리드를 나타냅니다.
    DateBox[] dateBoxAr = new DateBox[42]; // 7x6 grid
    JPanel p_north, p_center;
    JButton bt_prev;
    JLabel lb_title;
    JButton bt_next;
    Calendar cal;

    int yy;
    int mm;
    int startDay;
    int lastDate;
    JLabel userIDLabel;
    ArrayList<Schedule> userSchedules;

    // main_ui 클래스의 생성자
    public main_ui() {
        // 필요한 UI 요소들을 초기화합니다.
        p_north = new JPanel();
        bt_prev = new JButton("이전");
        lb_title = new JLabel("년도 디폴트", SwingConstants.CENTER);
        bt_next = new JButton("다음");
        p_center = new JPanel();
        userIDLabel = new JLabel("사용자 ID: ");

        // 레이블 폰트 및 크기 설정
        lb_title.setFont(new Font("Arial-Black", Font.BOLD, 25));
        lb_title.setPreferredSize(new Dimension(300, 30));

        // UI 요소들을 패널에 추가하고 프레임에 배치합니다.
        p_north.add(bt_prev);
        p_north.add(lb_title);
        p_north.add(bt_next);
        p_north.add(userIDLabel); // userIDLabel을 p_north에 추가
        add(p_north, BorderLayout.NORTH);
        add(p_center);

        // 사용자 스케줄을 가져옵니다.
        userSchedules = fetchUserSchedules();

        // 버튼에 대한 ActionListener를 추가합니다.
        bt_prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateMonth(-1);
            }
        });

        bt_next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateMonth(1);
            }
        });

        // 현재 날짜 정보를 가져오고, 날짜 정보를 설정합니다.
        getCurrentDate();
        getDateInfo();
        setDateTitle();

        // 캘린더를 생성하고 날짜를 출력합니다.
        createCalendar();
        printDate();

        // 프레임을 설정하고 표시합니다.
        setVisible(true);
        setBounds(100, 100, 780, 780);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // 로그인된 사용자의 ID를 설정합니다.
    public void setLoggedInUserID(String userID) {
        userIDLabel.setText("사용자 ID: " + userID);
    }

    // 현재 날짜를 가져오는 메서드
    public void getCurrentDate() {
        cal = Calendar.getInstance();
    }

    // 날짜 정보를 가져오는 메서드
    public void getDateInfo() {
        yy = cal.get(Calendar.YEAR);
        mm = cal.get(Calendar.MONTH);
        startDay = getFirstDayOfMonth(yy, mm);
        lastDate = getLastDate(yy, mm);
    }

    // 사용자의 스케줄을 가져오는 메서드
    private ArrayList<Schedule> fetchUserSchedules() {
        // 사용자 스케줄을 데이터베이스에서 가져오는 로직을 작성
        // 예를 들어,
        // return DBManager.getUserSchedules(year, month, day);
        return new ArrayList<>(); // 임시로 빈 배열을 반환
    }

    // 캘린더를 생성하는 메서드
    public void createCalendar() {
        p_center.removeAll();

        String[] dayAr = {"일", "월", "화", "수", "목", "금", "토"};

        // 요일 레이블을 생성합니다.
        for (String day : dayAr) {
            DateBox dayBox = new DateBox(day, Color.PINK, 100, 70);
            p_center.add(dayBox);
        }

        // 날짜 상자를 생성합니다.
        for (int i = 0; i < dateBoxAr.length; i++) {

            if (i >= startDay && i - startDay < lastDate) {
                int dayOfMonth = i - startDay + 1;
                dateBoxAr[i] = new DateBox(yy, mm, dayOfMonth, Color.LIGHT_GRAY, 100, 100);
            } else {
                dateBoxAr[i] = new DateBox("", Color.DARK_GRAY, 100, 100);

            }
            p_center.add(dateBoxAr[i]);
        }
    }

    // 해당 월의 첫 날의 요일을 가져오는 메서드
    public int getFirstDayOfMonth(int yy, int mm) {
        Calendar cal = Calendar.getInstance();
        cal.set(yy, mm, 1);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    // 해당 월의 마지막 날을 가져오는 메서드
    public int getLastDate(int yy, int mm) {
        Calendar cal = Calendar.getInstance();
        cal.set(yy, mm + 1, 0);
        return cal.get(Calendar.DATE);
    }

    // 날짜를 출력하는 메서드
    public void printDate() {
        for (int i = 0; i < dateBoxAr.length; i++) {

            if (i >= startDay && i - startDay < lastDate) {
                int dayOfMonth = i - startDay + 1;
                dateBoxAr[i].day = Integer.toString(dayOfMonth);
                dateBoxAr[i].repaint();
            } else {
                dateBoxAr[i].day = "";
                dateBoxAr[i].repaint();
            }
        }
    }

    // 월을 업데이트하는 메서드
    public void updateMonth(int data) {
        cal.add(Calendar.MONTH, data);
        getDateInfo(); // 날짜 정보를 가져오는 메서드
        setDateTitle(); // 레이블에 날짜 타이틀을 설정하는 메서드
        createCalendar(); // 캘린더를 생성하는 메서드
        printDate();  // 날짜를 출력하는 메서드
    }

    // 레이블에 날짜 타이틀을 설정하는 메서드
    public void setDateTitle() {
        lb_title.setText(yy + "-" + StringManager.getZeroString(mm + 1));
        lb_title.updateUI();
    }
}
