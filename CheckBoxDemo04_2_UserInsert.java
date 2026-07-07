package day2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBoxDemo04_2_UserInsert {
    public static void main(String[] args) {
        JFrame frame = new JFrame("회원가입");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // 화면 중앙에 배치

        // 상단 패널: 제목 및 안내 메시지
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("관심있는 프로그래밍 언어를 선택하세요:"));

        // 중앙 패널: 체크박스들
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        
        JCheckBox javaCheckBox = new JCheckBox("Java");
        JCheckBox pythonCheckBox = new JCheckBox("Python");
        JCheckBox cppCheckBox = new JCheckBox("C++");
        
        centerPanel.add(javaCheckBox);
        centerPanel.add(pythonCheckBox);
        centerPanel.add(cppCheckBox);

        // 하단 패널: 버튼과 결과 표시
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        
        JButton confirmButton = new JButton("확인");
        JLabel resultLabel = new JLabel("선택한 항목: ");
        
        bottomPanel.add(confirmButton);
        bottomPanel.add(resultLabel);

        // 프레임에 패널 추가
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // 버튼 클릭 이벤트 처리
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder selectedItems = new StringBuilder("선택한 항목: ");
                boolean isSelected = false;
                
                if (javaCheckBox.isSelected()) {
                    selectedItems.append("Java ");
                    isSelected = true;
                }
                if (pythonCheckBox.isSelected()) {
                    selectedItems.append("Python ");
                    isSelected = true;
                }
                if (cppCheckBox.isSelected()) {
                    selectedItems.append("C++ ");
                    isSelected = true;
                }
                
                if (!isSelected) {
                    selectedItems.append("선택된 항목이 없습니다.");
                }
                
                resultLabel.setText(selectedItems.toString());
            }
        });

        frame.setVisible(true);
    }
}