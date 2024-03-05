package Controller;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;


public class EmployeeController extends Component {
    public void addEmployee(int e_id, String name, String bday, String gender, String address, String contact) {
        try {
            Connection con = ConnectionClass.getCon();

            String sql = "INSERT INTO employeee (e_id, name, bday, gender, address, contact) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setInt(1, e_id);
            preparedStmt.setString(2, name);
            preparedStmt.setString(3, bday);
            preparedStmt.setString(4, gender);
            preparedStmt.setString(5, address);
            preparedStmt.setString(6, contact);

            int rowsInserted = preparedStmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Employee added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add Employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionClass.closeConnection();
        }
    }

    public void updateEmployee(int e_id, String name, String bday, String gender, String address, String contact) {
        try {
            Connection con = ConnectionClass.getCon();

            String sql = "UPDATE employeee SET name=?, bday=?, gender=?, address=?, contact=? WHERE e_id=?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);


            preparedStmt.setString(1, name);
            preparedStmt.setString(2, bday);
            preparedStmt.setString(3, gender);
            preparedStmt.setString(4, address);
            preparedStmt.setString(5, contact);
            preparedStmt.setInt(6, e_id);

            int rowsInserted = preparedStmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Employee updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update Employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionClass.closeConnection();
        }
    }

    public void deleteEmployee(int e_id) {
        try {
            Connection con = ConnectionClass.getCon();

            String sql = "DELETE FROM employeee WHERE e_id=?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setInt(1, e_id);

            int rowsDeleted = preparedStmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Employee deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete Employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionClass.closeConnection();
        }
    }

    public ArrayList<String[]>  getEmployeeData() {
        ArrayList<String[]> dataList = new ArrayList<>();

        try {
            Connection con = ConnectionClass.getCon();
            String sql = "SELECT e_id, name, bday, gender, address, contact FROM employeee";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            ResultSet rs = preparedStmt.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                String[] rowData = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = rs.getString(i + 1);
                }
                dataList.add(rowData);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionClass.closeConnection();
        }
        return dataList;
    }
}
