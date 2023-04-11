package login;

import login.login.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.io.*;
import java.util.Vector;

public class ArithmeticQuizGUI  extends JFrame implements ActionListener {
    public static String addColor(String msg, Color color) {
        String hexColor = String.format("#%06X",  (0xFFFFFF & color.getRGB()));
        String colorMsg =" <FONT COLOR=hexColor>" + msg +"</FONT>";
        return colorMsg;
    }
    private JLabel questionCountLabel;
    private JTextField questionCountField;
    private JButton nextButton;
    private JButton submitButton;
    private JButton Button1;
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
        nextButton = new JButton("生成题目");
        submitButton = new JButton("提交答案");
        Button1 =new JButton("发送错题到邮箱");
        resultLabel = new JLabel("");
        // 创建表格
        tableModel = new DefaultTableModel(new String[]{"题目", "你的答案", "正确答案","系统批改"}, 0);
        quizTable = new JTable(tableModel);
        quizTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        quizTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        quizTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        quizTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        quizTable.setDefaultEditor(Object.class, null); // 禁止编辑表格
        quizTable.setRowHeight(30);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        quizTable.getColumnModel().getColumn(0).setCellRenderer(renderer);
        quizTable.getColumnModel().getColumn(1).setCellRenderer(renderer);
        quizTable.getColumnModel().getColumn(2).setCellRenderer(renderer);
        quizTable.getColumnModel().getColumn(3).setCellRenderer(renderer);
        quizTable.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()));
        JScrollPane scrollPane = new JScrollPane(quizTable);

        // 添加控件到界面
        JPanel panel = new JPanel();
        panel.add(questionCountLabel);
        panel.add(questionCountField);
        panel.add(nextButton);
        panel.add(submitButton);
        panel.add(Button1);
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        // 注册事件处理器
        nextButton.addActionListener(this);
        submitButton.addActionListener(this);
        Button1.addActionListener(this);
    }

    // 处理按钮点击事件
//    public void  actionPerformed1 (ActionEvent e)  {
//        if(e.getSource() == Button1){
//            String url="jdbc:mysql://localhost:3306/NewCreate";
//            String username="root";
//            String password="password";
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                Connection connection = DriverManager.getConnection(url, username, password);
//                Statement stmt = connection.createStatement();
//                String sql = "select * from wrongset";
//                ResultSet resultSet = stmt.executeQuery(sql);
//                FileWriter fw = new FileWriter("title.txt");
//                BufferedWriter bw = new BufferedWriter(fw);
//                while (resultSet.next()) {
//                    String title = resultSet.getString("title");
//                    bw.write(title);
//                    bw.newLine();
//                }
//                bw.close();
//                fw.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
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
                num1[i] = random.nextInt(10000);
                num2[i] = random.nextInt(10000);
                operator[i] = randomOperator();
                answer[i] = calculate(num1[i], num2[i], operator[i]);
                tableModel.addRow(new Object[]{String.format("%d %c %d =", num1[i], operator[i], num2[i]), "", ""});
            }
        } else if (e.getSource() == submitButton) {
            // 点击提交答案按钮，判断答案并标记表格
            int correctCount = 0;
            String str="正确",str1="错误";
            for (int i = 0; i < questionCount; i++) {
                try {
                    int userAnswer = Integer.parseInt((String) tableModel.getValueAt(i, 1));
                    if (answer[i] == userAnswer) {
                        // 答案正确，标记为绿色
                        tableModel.setValueAt(String.valueOf(userAnswer), i, 1);
                        tableModel.setValueAt(String.valueOf(answer[i]), i, 2);
                        tableModel.setValueAt(String.valueOf(str), i, 3);
                        //quizTable.setSelectionBackground(Color.GREEN);
                        correctCount++;
                    } else {
                        // 答案错误，标记为红色
                        tableModel.setValueAt(String.valueOf(userAnswer), i, 1);
                        tableModel.setValueAt(String.valueOf(answer[i]), i, 2);
                        tableModel.setValueAt(String.valueOf(str1), i, 3);
                        //quizTable.setSelectionBackground(Color.RED);
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection com = null;
                        com = DriverManager.getConnection("jdbc:mysql://localhost:3306/NewCreate", "root", "huchi123");
                        Statement stat = com.createStatement();
                        stat.executeUpdate("insert into wrongset" + "(wrongtitle,wrongan,truean)" + "values(" +"'"+ num1[i]+" "+operator[i]+" "+num2[i]+" "+" = "+"'"+","+"'"+ userAnswer +"'"+","+"'"+ answer[i] +"'"+")");
                        stat.close();
                        com.close();
                    }
                }
            catch (NumberFormatException ex) {
                    // 如果答案不是数字，则弹出提示框
                    JOptionPane.showMessageDialog(this, "确定交卷吗？");
                    break;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
            // 显示总分
            int totalScore = 100 / questionCount * correctCount;
            resultLabel.setText(String.format("总分：%d分", totalScore));
        }
        else if(e.getSource()==Button1){
            String url="jdbc:mysql://localhost:3306/NewCreate";
            String username="root";
            String password="huchi123";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement stmt = connection.createStatement();
                String sql = "select * from wrongset";
                ResultSet resultSet = stmt.executeQuery(sql);
                FileWriter fw = new FileWriter("title.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                String str0=" ";
                while (resultSet.next()) {
                    String title = resultSet.getString("wrongtitle");
                    String wa=resultSet.getString("wrongan");
                    String ac=resultSet.getString("truean");
                    addColor(wa,Color.RED);
                    addColor(ac,Color.green);
                    str0=str0+title+" "+ac+" "+"您的错误答案为:"+wa+"<br>";
                }
                bw.close();
                fw.close();
                final Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.host", "smtp.qq.com");

                // 发件人的账号
                props.put("mail.user", "2424742061@qq.com");
                //发件人的密码
                props.put("mail.password", "asmujlzffgpgdiea");
                String email = login.email;
                 // 构建授权信息，用于进行SMTP进行身份验证
                Authenticator authenticator = new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        // 用户名、密码
                        String userName = props.getProperty("mail.user");
                        String password = props.getProperty("mail.password");
                        return new PasswordAuthentication(userName, password);
                    }
                };
                // 使用环境属性和授权信息，创建邮件会话
                Session mailSession = Session.getInstance(props, authenticator);
                // 创建邮件消息
                MimeMessage message = new MimeMessage(mailSession);
                // 设置发件人
                String username1 = props.getProperty("mail.user");
                InternetAddress form = new InternetAddress(username1);
                message.setFrom(form);

                // 设置收件人
                InternetAddress toAddress = new InternetAddress(email);
                message.setRecipient(Message.RecipientType.TO, toAddress);

                // 设置邮件标题
                message.setSubject("错题本");

                // 设置邮件的内容体
                message.setContent(str0, "text/html;charset=UTF-8");
                // 发送邮件

                Transport.send(message);




            }catch (Exception e1){
                e1.printStackTrace();
            }
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
//        try{
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}

