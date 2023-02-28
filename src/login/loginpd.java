package login;

import java.sql.*;
public class loginpd {
    String username,password;
    public int jdba(String userName,String passWord) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/competition", "root", "huchi123");
        Statement stat = com.createStatement();
        ResultSet rs=stat.executeQuery("select * from userr");
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
    public int zhuce(String username,String password) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/competition", "root", "huchi123");
        Statement stat = com.createStatement();
        int f=0;
        ResultSet rs=stat.executeQuery("select * from userr");
        while(rs.next()){
            if(rs.getString("username").equals(username)){
                f++;
                return f;
            }
        }
            stat.executeUpdate("insert into userr" + "(username,password)" + "values(" +"'"+ username + "'" +","+"'"+password+"'"+")");
            rs.close();
            stat.close();
            com.close();
        return 0;
    }
}
