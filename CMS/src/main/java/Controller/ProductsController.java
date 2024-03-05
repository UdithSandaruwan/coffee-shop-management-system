package Controller;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ProductsController extends Component {
    public void addProduct(int p_id, String name, Double price) {
        try {
            Connection con = ConnectionClass.getCon();

            String sql = "INSERT INTO products (p_id, name, price) VALUES (?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setInt(1, p_id);
            preparedStmt.setString(2, name);
            preparedStmt.setDouble(3, price);

            int rowsInserted = preparedStmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Product added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add product.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionClass.closeConnection();
        }
    }

    public void updateProduct(int p_id, String name, Double price) {
        try {
            Connection con = ConnectionClass.getCon();

            String sql = "UPDATE products SET name=?, price=? WHERE p_id=?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setString(1, name);
            preparedStmt.setDouble(2, price);
            preparedStmt.setInt(3, p_id);

            int rowsInserted = preparedStmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Product updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update product.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionClass.closeConnection();
        }
    }

    public void deleteProduct(int p_id) {
        try {
            Connection con = ConnectionClass.getCon();

            String sql = "DELETE FROM products WHERE p_id=?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);

            preparedStmt.setInt(1, p_id);

            int rowsDeleted = preparedStmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Product deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete product.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionClass.closeConnection();
        }
    }
    public ArrayList<String[]> getProductsData() {
        ArrayList<String[]> dataList = new ArrayList<>();

        try {
            Connection con = ConnectionClass.getCon();
            String sql = "SELECT p_id, name, price FROM products";
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
