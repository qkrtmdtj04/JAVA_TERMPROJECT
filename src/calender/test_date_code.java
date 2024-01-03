/*
package calender;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;

class test_date_code extends JPanel {
    String day;
    Color color;
    int width;
    int height;
    int year;
    int month;
    JButton CheckButton;

    public test_date_code(int year, int month, int dayOfMonth, Color color, int width, int height) {
        this.year = year;
        this.month = month;
        this.day = Integer.toString(dayOfMonth);
        this.color = color;
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());

        if (day.equals("")) {
            color = Color.DARK_GRAY;
            CheckButton = null;
        } else {
            CheckButton = new JButton(" + ");
            CheckButton.setFont(new Font("Arial-Black", Font.BOLD, 10));
            CheckButton.setBackground(Color.LIGHT_GRAY);
            CheckButton.setBorderPainted(false);
            add(CheckButton, BorderLayout.SOUTH);

            CheckButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    CheckButton.setBackground(Color.GRAY);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    CheckButton.setBackground(Color.LIGHT_GRAY);
                }
            });


            CheckButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createScheduleDialog();
                }
            });
        }

        setBackground(color);
    }

    private void createScheduleDialog() {
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

        schedulePanel.add(new JLabel("Schedule:"));
        schedulePanel.add(scheduleField);
        schedulePanel.add(new JLabel("시작 시간:"));
        schedulePanel.add(startTimeComboBox);
        schedulePanel.add(new JLabel("끝 시간:"));
        schedulePanel.add(endTimeComboBox);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = scheduleField.getText();
                String startTime = (String) startTimeComboBox.getSelectedItem();
                String endTime = (String) endTimeComboBox.getSelectedItem();

                System.out.printf("년도: %d, 월: %d, 일: %s, 일정: %s, 시작 시간: %s, 끝 시간: %s%n", year, month + 1, day, input, startTime, endTime);

                dialog.dispose();
            }
        });

        dialog.add(schedulePanel, BorderLayout.CENTER);
        dialog.add(saveButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public test_date_code(String day, Color color, int width, int height) {
        this.day = day;
        this.color = color;
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());

        CheckButton = null;
        setBackground(color);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(color);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.black);
        g.drawString(day, 10, 20);
    }
}
*/