package login;

import java.sql.*;
public class loginpd {
    String username,password;
    public int jdba(String userName,String passWord) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcjian", "root", "huchi123");
        Statement stat = com.createStatement();
        ResultSet rs=stat.executeQuery("select * from user");
        int f=0;
        while(rs.next()){
            if(rs.getString("username").equals(userName)){
                f++;
                if(rs.getString("password").equals(passWord)){
                    f++;
                }
            }
        }
        rs.close();
        stat.close();
        com.close();
        return f;
    }
    public String getemail(String username) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcjian", "root", "huchi123");
        Statement stat = com.createStatement();
        int f=0;
        ResultSet rs=stat.executeQuery("select * from user");
        while (rs.next()){
            if(rs.getString("username").equals(username)){
                return rs.getString("email");
            }
        }
        return "";
    }
    public int zhuce(String username,String password,String email) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/hcjian", "root", "huchi123");
        Statement stat = com.createStatement();
        int f=0;
        ResultSet rs=stat.executeQuery("select * from user");
        while(rs.next()){
            if(rs.getString("username").equals(username)){
                f++;
                return f;
            }
        }
            stat.executeUpdate("insert into user" + "(username,password,email)" + "values(" +"'"+ username + "'" +","+"'"+password+"'"+","+"'"+email+"'"+")");
            rs.close();
            stat.close();
            com.close();
        return 0;
    }
}
