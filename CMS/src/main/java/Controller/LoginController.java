package Controller;

import Model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public String tempUsername;
    public String tempPassword;
    public UserModel getUser(String username) {
        Connection con = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;
        UserModel userModel = null;

        try {
            con = ConnectionClass.getCon();

            String sql = "SELECT username, password FROM user WHERE username = ?";
            preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, username);

            resultSet = preparedStmt.executeQuery();

            if (resultSet.next()) {
                //userModel = new UserModel();
                tempUsername = resultSet.getString("username");
                tempPassword = resultSet.getString("password");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            ConnectionClass.closeConnection();
        }

        return userModel;
    }
}
