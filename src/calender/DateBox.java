// 필요한 패키지 및 클래스를 가져옵니다.
package calender;
import LogInPage.LogInMenu;
import DB.AddDate;
import DB.Schedule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

// JPanel을 확장하는 DateBox 클래스를 정의합니다.
class DateBox extends JPanel {
    // 인스턴스 변수를 선언합니다.
    String day;
    Color color;
    int width;
    int height;
    int year;
    int month;
    JButton CheckButton;
    JButton scheduleButton;
    ArrayList<Schedule> userSchedules;
    JButton deleteButton;

    // 매개변수를 가진 DateBox 생성자
    public DateBox(int year, int month, int dayOfMonth, Color color, int width, int height) {
        // 인스턴스 변수를 주어진 값으로 초기화합니다.
        this.year = year;
        this.month = month;
        this.day = Integer.toString(dayOfMonth);
        this.color = color;
        this.width = width;
        this.height = height;

        // DateBox의 선호 크기 및 레이아웃을 설정합니다.
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout(30, 20));

        // 날짜가 비어 있는지 확인합니다.
        if (day.equals("")) {
            // 색상을 어두운 회색으로 설정하고 버튼을 null로 설정합니다.
            color = Color.DARK_GRAY;
            System.out.println(2);
            scheduleButton = null;
            CheckButton = null;
        } else {
            // 지정된 날짜에 대한 사용자 스케줄을 가져옵니다.
            ArrayList<Schedule> schedules = AddDate.fetchUserSchedules(year, month + 1, Integer.parseInt(day), LogInMenu.loggedInUsername);

            // 해당 날짜에 스케줄이 없는지 확인합니다.
            if (schedules.isEmpty()) {
                scheduleButton = null;
            } else {
                // "일정" 버튼을 만듭니다.
                scheduleButton = new JButton("일정");
                scheduleButton.setFont(new Font("Arial-Black", Font.BOLD, 10));
                scheduleButton.setBackground(new Color(255, 233, 246, 184));
                scheduleButton.setBorderPainted(false);
                JPanel emptyPanel = new JPanel();
                emptyPanel.setOpaque(false);
                add(emptyPanel, BorderLayout.NORTH);

                // DateBox에 "일정" 버튼을 추가합니다.
                add(scheduleButton, BorderLayout.WEST);

                // "일정" 버튼에 대한 ActionListener를 추가합니다.
                scheduleButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showScheduleDialog(year, month, Integer.parseInt(day));
                    }
                });

                // "삭제" 버튼을 만듭니다.
                deleteButton = new JButton("삭제");
                deleteButton.setFont(new Font("Arial-Black", Font.BOLD, 10));
                deleteButton.setBackground(Color.RED); // 원하는 색상으로 설정 가능
                deleteButton.setBorderPainted(false);
                add(deleteButton, BorderLayout.EAST);

                // "삭제" 버튼에 대한 ActionListener를 추가합니다.
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deleteSchedule(year, month, Integer.parseInt(day));
                    }
                });
            }

            // " + " 버튼을 만듭니다.
            CheckButton = new JButton(" + ");
            CheckButton.setFont(new Font("Arial-Black", Font.BOLD, 10));
            CheckButton.setBackground(Color.LIGHT_GRAY);
            CheckButton.setBorderPainted(false);
            add(CheckButton, BorderLayout.SOUTH);

            // " + " 버튼에 대한 ActionListener를 추가합니다.
            CheckButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createScheduleDialog(year, month + 1, day);
                }
            });
        }

        // 배경색을 설정합니다.
        setBackground(color);
    }

    // 일정을 삭제하는 메서드
    private void deleteSchedule(int year, int month, int day) {
        int response = JOptionPane.showConfirmDialog(
                null,
                "일정을 삭제하시겠습니까?",
                "일정 삭제",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            // 데이터베이스에서 삭제 수행
            AddDate.deleteUserSchedule(year, month + 1, day, LogInMenu.loggedInUsername);

            // UI를 업데이트하거나 필요한 작업 수행
            // 예를 들어, 현재 DateBox를 새로 고치거나 전체 달력을 새로 고칠 메서드를 구현해야 할 수 있습니다.
        }
    }

    // 일정 다이얼로그를 표시하는 메서드
    private void showScheduleDialog(int year, int month, int day) {
        JDialog dialog = new JDialog();
        dialog.setTitle(year + "년 " + (month + 1) + "월 " + day + "일 일정");

        JTextArea scheduleInfo = new JTextArea();
        scheduleInfo.setEditable(false);

        // 여기서 데이터베이스에서 해당 날짜의 일정을 가져와 scheduleInfo에 추가하는 로직을 구현
        ArrayList<Schedule> schedules = AddDate.fetchUserSchedules(year, month+1, day, LogInMenu.loggedInUsername);
        for (Schedule schedule : schedules) {
            scheduleInfo.append(schedule.getStartTime() + " ~ " + schedule.getEndTime() + " " + schedule.getDescription() + "\n");
        }

        dialog.add(new JScrollPane(scheduleInfo));
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // 일정 다이얼로그를 생성하는 메서드
    private void createScheduleDialog(int year, int month, String day) {
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 150);

        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(new GridLayout(3, 2));

        String[] timeOptions = new String[24];
        for (int i = 1; i <= 24; i++) {
            String hour = String.format("%02d", i);
            timeOptions[i - 1] = hour + ":00";
        }

        JComboBox<String> startTimeComboBox = new JComboBox<>(timeOptions);
        JComboBox<String> endTimeComboBox = new JComboBox<>(timeOptions);
        JTextField scheduleField = new JTextField();

        schedulePanel.add(new JLabel("일정:"));
        schedulePanel.add(scheduleField);
        schedulePanel.add(new JLabel("시작 시간:"));
        schedulePanel.add(startTimeComboBox);
        schedulePanel.add(new JLabel("종료 시간:"));
        schedulePanel.add(endTimeComboBox);

        JButton saveButton = new JButton("저장");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = scheduleField.getText();
                String startTime = (String) startTimeComboBox.getSelectedItem();
                String endTime = (String) endTimeComboBox.getSelectedItem();

                AddDate.addScheduleToDB(year, month, day, input, startTime, endTime, LogInMenu.loggedInUsername);
                dialog.dispose();

                // 스케줄이 추가된 후, 해당 DateBox를 업데이트

            }
        });

        dialog.add(schedulePanel, BorderLayout.CENTER);
        dialog.add(saveButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    //오버로딩
    public DateBox(String day, Color color, int width, int height) {
        this.day = day;
        this.color = color;
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());

        CheckButton = null; // 버튼을 없애기 위해 null로 설정
        setBackground(color);
    }

    // 일정 상자를 그리는 메서드
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(color);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.black);
        g.drawString(day, 10, 20);
    }
}
