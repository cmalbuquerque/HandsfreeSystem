/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionDB;

/**
 *
 * @author Carolina
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {

    public static boolean validate(String email, String pass) throws ClassNotFoundException {
        Connection con = null;

        try {
            con = DataConnect.getConnection();
            System.out.println("-----------------------------");
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from pessoa where password = crypt('" + pass + "', password);");
            
            while(rs.next()) {
                System.out.println(rs.getString(3));
                if(rs.getString(3).equals(email) ){
                    System.out.print(rs.getString(3) + ":" + rs.getString(4));
                    return true;
                }  
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }
}
