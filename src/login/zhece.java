package login;
import javax.swing.*;
import java.awt.*;

public class zhece {
    public void zhucejiemian(){
        JFrame jFrame=new JFrame("注册界面");
        jFrame.setSize(900,556);
        jFrame.setLayout(null);
        JLabel aaa=new JLabel("注册");

        JLabel textuser=new JLabel("用户名：" );
        textuser.setForeground(new Color(0xFF0000));
        textuser.setFont(new Font("黑体",Font.PLAIN,30));
        textuser.setBounds(222,120,200,100);
        jFrame.add(textuser);

        JLabel textemail=new JLabel("邮  箱：" );
        textemail.setForeground(new Color(0xFF0000));
        textemail.setFont(new Font("黑体",Font.PLAIN,30));
        textemail.setBounds(222,180,200,100);
        jFrame.add(textemail);


        JLabel textpass=new JLabel("密  码：" );
        textpass.setForeground(new Color(0xFF0000));
        textpass.setFont(new Font("黑体",Font.PLAIN,30));
        textpass.setBounds(222,240,200,100);
        jFrame.add(textpass);

//用户名输入框
        JTextField user=new JTextField(20);
        user.setFont(new Font("黑体",Font.PLAIN,18));
        user.setSelectedTextColor(new Color(0xFF0000));
        user.setBounds(330,150,280,40);
        jFrame.add(user);
//邮箱输入框
        JTextField useremail=new JTextField(20);
        useremail.setFont(new Font("黑体",Font.PLAIN,18));
        useremail.setSelectedTextColor(new Color(0xFF0000));
        useremail.setBounds(330,210,280,40);
        jFrame.add(useremail);
//密码输入框
        JPasswordField pass=new JPasswordField(20);
        pass.setBounds(330,270,280,40);
        jFrame.add(pass);

        JButton register = new JButton("注册");
        register.setForeground(new Color(0x0029FF));
        register.setBackground(new Color(0xECA871));
        register.setFont(new Font("黑体", Font.PLAIN,20));
        register.setBorderPainted(false);
        register.setBounds(400,380,100,50);
        jFrame.add(register);

        register.addActionListener(e -> {
            loginpd lg=new loginpd();
            try {
                if(lg.zhuce(user.getText(), String.valueOf(pass.getPassword()),useremail.getText())==1){
                    JOptionPane.showMessageDialog(jFrame,"账号太受欢迎了，换一个吧！","提示",JOptionPane.INFORMATION_MESSAGE);
                    //置空输入框
                    user.setText("");
                    pass.setText("");
                    useremail.setText("");
                }else {
                    JOptionPane.showMessageDialog(jFrame,"注册成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                    jFrame.setVisible(false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
    }
}
