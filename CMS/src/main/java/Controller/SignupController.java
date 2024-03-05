package Controller;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupController extends Component {
    public void signupUser(String username, String password) {
        try {
            Connection con = ConnectionClass.getCon();

            String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, username);
            preparedStmt.setString(2, password);

            int rowsInserted = preparedStmt.executeUpdate();


            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "User Created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Create User.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionClass.closeConnection();
        }
    }

}
