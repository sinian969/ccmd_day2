package day2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class CheckBoxDemo04_3_UserDelete {
    public static void main(String[] args) {
        JFrame frame = new JFrame("회원 관리 시스템");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        // 회원 데이터 준비 (10명)
        String[] columns = {"선택", "회원번호", "이름", "이메일", "전화번호"};
        Object[][] data = new Object[10][5];
        
        String[] names = {"김철수", "이영희", "박민수", "정지원", "최현우", 
                         "강서연", "조민준", "윤서현", "장도현", "임하은"};
        String[] emails = {"kim@email.com", "lee@email.com", "park@email.com", "jung@email.com", "choi@email.com",
                          "kang@email.com", "cho@email.com", "yoon@email.com", "jang@email.com", "lim@email.com"};
        String[] phones = {"010-1111-1111", "010-2222-2222", "010-3333-3333", "010-4444-4444", "010-5555-5555",
                          "010-6666-6666", "010-7777-7777", "010-8888-8888", "010-9999-9999", "010-0000-0000"};
        
        for (int i = 0; i < 10; i++) {
            data[i][0] = Boolean.FALSE;  // 선택 체크박스 초기값
            data[i][1] = i + 1;          // 회원번호
            data[i][2] = names[i];
            data[i][3] = emails[i];
            data[i][4] = phones[i];
        }

        // 테이블 모델 생성 (체크박스 지원)
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : String.class;
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // 선택 컬럼만 수정 가능
            }
        };
        
        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        
        // 스크롤 패널에 테이블 추가
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("회원 목록"));

        // 상단 패널: 제목 및 버튼들
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel titleLabel = new JLabel("회원 관리 시스템");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        topPanel.add(titleLabel);

        // 하단 패널: 전체 선택 및 삭제 버튼
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        JCheckBox selectAllCheckBox = new JCheckBox("전체 선택");
        selectAllCheckBox.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        
        JButton deleteButton = new JButton("선택 회원 삭제");
        deleteButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        deleteButton.setBackground(new Color(255, 80, 80));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        
        JButton deleteAllButton = new JButton("전체 삭제");
        deleteAllButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        deleteAllButton.setBackground(new Color(200, 50, 50));
        deleteAllButton.setForeground(Color.WHITE);
        deleteAllButton.setFocusPainted(false);

        bottomPanel.add(selectAllCheckBox);
        bottomPanel.add(deleteButton);
        bottomPanel.add(deleteAllButton);

        // 전체 선택 체크박스 이벤트
        selectAllCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSelected = selectAllCheckBox.isSelected();
                for (int i = 0; i < model.getRowCount(); i++) {
                    model.setValueAt(isSelected, i, 0);
                }
            }
        });

        // 선택 회원 삭제 버튼 이벤트
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedCount = 0;
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    if ((Boolean) model.getValueAt(i, 0)) {
                        model.removeRow(i);
                        selectedCount++;
                    }
                }
                
                if (selectedCount > 0) {
                    JOptionPane.showMessageDialog(frame, 
                        selectedCount + "명의 회원이 삭제되었습니다.", 
                        "삭제 완료", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    // 전체 선택 체크박스 해제
                    selectAllCheckBox.setSelected(false);
                    
                    // 삭제 후 남은 회원이 없으면 메시지 표시
                    if (model.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(frame, 
                            "모든 회원이 삭제되었습니다.", 
                            "알림", 
                            JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, 
                        "삭제할 회원을 선택해주세요.", 
                        "선택 오류", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // 전체 삭제 버튼 이벤트
        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                if (rowCount == 0) {
                    JOptionPane.showMessageDialog(frame, 
                        "삭제할 회원이 없습니다.", 
                        "알림", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int confirm = JOptionPane.showConfirmDialog(frame, 
                    "정말 모든 회원(" + rowCount + "명)을 삭제하시겠습니까?", 
                    "전체 삭제 확인", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    model.setRowCount(0); // 모든 행 삭제
                    selectAllCheckBox.setSelected(false);
                    JOptionPane.showMessageDialog(frame, 
                        "모든 회원이 삭제되었습니다.", 
                        "삭제 완료", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // 프레임에 컴포넌트 추가
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}