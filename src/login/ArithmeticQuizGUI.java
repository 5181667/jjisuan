package login;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ArithmeticQuizGUI extends JFrame implements ActionListener {
    private JLabel questionCountLabel;
    private JTextField questionCountField;
    private JButton nextButton;
    private JButton submitButton;
    private JTable quizTable;
    private DefaultTableModel tableModel;
    private JLabel resultLabel;

    private int[] num1 = new int[100];
    private int[] num2 = new int[100];
    private char[] operator = new char[100];
    private int[] answer = new int[100];
    private int questionCount = 0;

    public ArithmeticQuizGUI() {
        super("四则运算练习");

        // 创建控件
        questionCountLabel = new JLabel("请输入题目数量：");
        questionCountField = new JTextField("10", 5);
        nextButton = new JButton("下一题");
        submitButton = new JButton("提交答案");
        resultLabel = new JLabel("");

        // 创建表格
        tableModel = new DefaultTableModel(new String[]{"题目", "你的答案", "正确答案"}, 0);
        quizTable = new JTable(tableModel);
        quizTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        quizTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        quizTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        quizTable.setDefaultEditor(Object.class, null); // 禁止编辑表格
        quizTable.setRowHeight(30);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        quizTable.getColumnModel().getColumn(0).setCellRenderer(renderer);
        quizTable.getColumnModel().getColumn(1).setCellRenderer(renderer);
        quizTable.getColumnModel().getColumn(2).setCellRenderer(renderer);
        quizTable.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
        JScrollPane scrollPane = new JScrollPane(quizTable);

        // 添加控件到界面
        JPanel panel = new JPanel();
        panel.add(questionCountLabel);
        panel.add(questionCountField);
        panel.add(nextButton);
        panel.add(submitButton);
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        // 注册事件处理器
        nextButton.addActionListener(this);
        submitButton.addActionListener(this);
    }

    // 处理按钮点击事件
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            // 点击下一题按钮，生成题目和答案
            try {
                questionCount = Integer.parseInt(questionCountField.getText());
                if (questionCount <= 0 || questionCount > 100) {
                    // 如果题目数量不合法，则弹出提示框
                    JOptionPane.showMessageDialog(this, "题目数量必须在1到100之间！");
                    return;
                }
            } catch (NumberFormatException ex) {
                //
                // 如果输入不是数字，则弹出提示框
                JOptionPane.showMessageDialog(this, "请输入数字！");
                return;
            }

            // 生成题目和答案
            Random random = new Random();
            for (int i = 0; i < questionCount; i++) {
                num1[i] = random.nextInt(100);
                num2[i] = random.nextInt(100);
                operator[i] = randomOperator();
                answer[i] = calculate(num1[i], num2[i], operator[i]);
                tableModel.addRow(new Object[]{String.format("%d %c %d =", num1[i], operator[i], num2[i]), "", ""});
            }
        } else if (e.getSource() == submitButton) {
            // 点击提交答案按钮，判断答案并标记表格
            int correctCount = 0;
            for (int i = 0; i < questionCount; i++) {
                try {
                    int userAnswer = Integer.parseInt((String) tableModel.getValueAt(i, 1));
                    if (userAnswer == answer[i]) {
                        // 答案正确，标记为绿色
                        tableModel.setValueAt(String.valueOf(userAnswer), i, 1);
                        tableModel.setValueAt(String.valueOf(answer[i]), i, 2);
                        quizTable.setSelectionBackground(Color.GREEN);
                        correctCount++;
                    } else {
                        // 答案错误，标记为红色
                        tableModel.setValueAt(String.valueOf(userAnswer), i, 1);
                        tableModel.setValueAt(String.valueOf(answer[i]), i, 2);
                        quizTable.setSelectionBackground(Color.RED);
                    }
                } catch (NumberFormatException ex) {
                    // 如果答案不是数字，则弹出提示框
                    JOptionPane.showMessageDialog(this, "请输入数字！");
                    return;
                }
            }
            // 显示总分
            int totalScore = 100 / questionCount * correctCount;
            resultLabel.setText(String.format("总分：%d分", totalScore));
        }
    }

    // 随机生成运算符
    private char randomOperator() {
        Random random = new Random();
        int n = random.nextInt(4);
        switch (n) {
            case 0:
                return '+';
            case 1:
                return '-';
            case 2:
                return '*';
            default:
                return '/';
        }
    }

    // 计算结果
    private int calculate(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return a / b;
        }
    }

    public static void main(String[] args) {
        ArithmeticQuizGUI quiz = new ArithmeticQuizGUI();
        quiz.setSize(600, 500);
        quiz.setLocationRelativeTo(null);
        quiz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        quiz.setVisible(true);
    }
}