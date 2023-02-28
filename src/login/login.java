package login;
import javax.swing.*;
import java.awt.*;
public class login {
    public static void main(String[] args) {
        JFrame jFrame=new JFrame("登陆界面");
        jFrame.setSize(900,556);
        jFrame.setLayout(null);
        JLabel textstudent=new JLabel("四则运算出题系统");
        textstudent.setForeground(new Color(0x0020FF));
        textstudent.setFont(new Font("黑体",Font.PLAIN,50));
        textstudent.setBounds(280,50,800,100);
        jFrame.add(textstudent);
        //标签->学生管理系统

        JLabel textuser=new JLabel("用户名：" );
        textuser.setForeground(new Color(0xFF0000));
        textuser.setFont(new Font("黑体",Font.PLAIN,30));
        textuser.setBounds(222,140,200,100);
        jFrame.add(textuser);

        JLabel textpass=new JLabel("密  码：" );
        textpass.setForeground(new Color(0xFF0000));
        textpass.setFont(new Font("黑体",Font.PLAIN,30));
        textpass.setBounds(222,200,200,100);
        jFrame.add(textpass);
//用户名输入框
        JTextField user=new JTextField(20);
        user.setFont(new Font("黑体",Font.PLAIN,18));
        user.setSelectedTextColor(new Color(0xFF0000));
        user.setBounds(330,170,280,40);
        jFrame.add(user);
//密码输入框
        JPasswordField pass=new JPasswordField(20);
        pass.setBounds(330,230,280,40);
        jFrame.add(pass);

        //登陆按钮
        //添加按钮【登录】
        JButton jButton = new JButton("登录");
        jButton.setForeground(new Color(0x023BF6));
        jButton.setBackground(new Color(0x38FF00));
        jButton.setFont(new Font("黑体", Font.PLAIN,20));
        jButton.setBorderPainted(false);
        jButton.setBounds(300,330,100,50);
        jFrame.add(jButton);

        //添加按钮【注册】
        JButton register = new JButton("注册");
        register.setForeground(new Color(0x0029FF));
        register.setBackground(new Color(0xECA871));
        register.setFont(new Font("黑体", Font.PLAIN,20));
        register.setBorderPainted(false);
        register.setBounds(500,330,100,50);
        jFrame.add(register);

        //对登陆按钮进行响应
        jButton.addActionListener((e->{

            loginpd lg=new loginpd();
            try {
                if(lg.jdba(user.getText(), String.valueOf(pass.getPassword()))==1){
                    JOptionPane.showMessageDialog(jFrame,"密码错误","提示",JOptionPane.INFORMATION_MESSAGE);
                    //将密码框置空
                    pass.setText("");
                }else if(lg.jdba(user.getText(), String.valueOf(pass.getPassword()))==0){
                    JOptionPane.showMessageDialog(jFrame,"账号不存在","提示",JOptionPane.INFORMATION_MESSAGE);
                    //将密码框置空
                    user.setText("");
                    pass.setText("");
                }
else{
                    jFrame.setVisible(false);//将登录界面设定为不可见
                    JOptionPane.showMessageDialog(jFrame,"登陆成功","提示",JOptionPane.INFORMATION_MESSAGE);
//                  new StudentManage().StudentMainInterface();

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));
        register.addActionListener(e -> {
            new zhece().zhucejiemian();

        });
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }
}
