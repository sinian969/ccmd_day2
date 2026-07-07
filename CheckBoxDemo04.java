package day2;
//4. 체크박스 (JCheckBox)
import javax.swing.*;
public class CheckBoxDemo04 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("체크박스");
        JPanel panel = new JPanel();
        panel.add(new JCheckBox("Java"));
        panel.add(new JCheckBox("Python"));
        panel.add(new JCheckBox("C++"));
        frame.add(panel);
        frame.setSize(600, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}