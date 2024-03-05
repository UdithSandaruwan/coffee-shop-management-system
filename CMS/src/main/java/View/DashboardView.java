package View;

import Controller.EmployeeController;
import Controller.OrderController;
import Controller.ProductsController;
import Model.OrderModel;
import Model.ProductsModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class DashboardView extends JFrame implements ActionListener {
    private JLabel lblProducts;
    private JLabel lblEmployees;
    private JLabel lblProduct_id;
    private JLabel lblQty;
    private JTextField txtProduct_id;
    private JTextField txtQty;
    private JTextField txtDescription;
    private JButton btnAdd_product;
    private JButton btnUpdate_product;
    private JButton btnDelete_product;
    private  JButton btnAdd_employee;
    private JButton btnUpdate_employee;
    private JButton btnDelete_employee;
    private JButton btnCalculate;
    private JButton btnCalulate_2;
    private JButton btnLogout;
    private JTable tblEmployee;
    private JScrollPane spEplayee;
    private JScrollPane spProduct;
    ArrayList<OrderModel> cart = new ArrayList<>();


    public DashboardView(){

        lblProducts = new JLabel("Product Operations");
        lblProducts.setVerticalAlignment(JLabel.CENTER);
        lblProducts.setHorizontalAlignment(JLabel.CENTER);

        lblEmployees = new JLabel("Employee Operations");
        lblEmployees.setVerticalAlignment(JLabel.CENTER);
        lblEmployees.setHorizontalAlignment(JLabel.CENTER);

        lblProduct_id = new JLabel("Product Id");
        lblProduct_id.setVerticalAlignment(JLabel.CENTER);
        lblProduct_id.setHorizontalAlignment(JLabel.CENTER);

        lblQty = new JLabel("Quantity");
        lblQty.setVerticalAlignment(JLabel.CENTER);
        lblQty.setHorizontalAlignment(JLabel.CENTER);

        txtProduct_id = new JTextField(10);
        txtQty = new JTextField(10);
        txtDescription = new JTextField(10);
        addPlaceholder(txtDescription, "Description...");

        btnCalculate = new JButton("Add to Cart");
        btnCalulate_2 = new JButton("Calculate");

        btnAdd_product = new JButton("Add");
        btnUpdate_product = new JButton("Update");
        btnDelete_product = new JButton("Delete");

        btnAdd_employee = new JButton("Add");
        btnUpdate_employee = new JButton("Update");
        btnDelete_employee = new JButton("Delete");

        btnLogout = new JButton("LogOut");

        JPanel orderPanel = new JPanel(new GridLayout(7, 1));
        orderPanel.add(lblProduct_id);
        orderPanel.add(txtProduct_id);
        orderPanel.add(lblQty);
        orderPanel.add(txtQty);
        orderPanel.add(btnCalculate);
        orderPanel.add(txtDescription);
        orderPanel.add(btnCalulate_2);

        JPanel p1 = new JPanel(new GridLayout(4, 1));
        p1.add(lblProducts);
        p1.add(btnAdd_product);
        p1.add(btnUpdate_product);
        p1.add(btnDelete_product);

        JPanel p2 = new JPanel(new GridLayout(4, 1));
        p2.add(lblEmployees);
        p2.add(btnAdd_employee);
        p2.add(btnUpdate_employee);
        p2.add(btnDelete_employee);

        JPanel p3 = new JPanel(new GridLayout(2,1));
        p3.add(p1);
        p3.add(p2);

        //Products Table
        ProductsController productsController = new ProductsController();
        ArrayList<String[]> product_data = productsController.getProductsData();

        String[] pro_table_columns = {"Id", "Name", "Price (LKR)"};

        DefaultTableModel product_model = new DefaultTableModel(pro_table_columns, 0);
        for (String[] row : product_data) {
            product_model.addRow(row);
        }

        tblEmployee = new JTable(product_model);
        spProduct = new JScrollPane(tblEmployee);
        //end

        //Employee Table
        EmployeeController employeeController = new EmployeeController();
        ArrayList<String[]> employee_data = employeeController.getEmployeeData();

        String[] emp_tablr_columns = {"Id", "Name", "BirthDay", "Gender", "Address", "Contact"};

        DefaultTableModel employee_model = new DefaultTableModel(emp_tablr_columns, 0);
        for (String[] row : employee_data) {
            employee_model.addRow(row);
        }

        tblEmployee = new JTable(employee_model);
        spEplayee = new JScrollPane(tblEmployee);
        //end

        JPanel p4 = new JPanel(new GridLayout(1,1));
        p4.add(spEplayee);

        JPanel p5 = new JPanel(new GridLayout(4,1));
        p5.add(new JPanel());
        p5.add(new JPanel());
        p5.add(new JPanel());
        p5.add(btnLogout);

        JPanel p6 = new JPanel(new GridLayout(2,1));
        p6.add(p4);
        p6.add(p5);

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new GridLayout(1, 4));

        mainContainer.add(orderPanel);
        mainContainer.add(spProduct);
        mainContainer.add(p3);
        mainContainer.add(p6);

        btnCalculate.addActionListener(this);
        btnCalulate_2.addActionListener(this);

        btnAdd_product.addActionListener(this);
        btnUpdate_product.addActionListener(this);
        btnDelete_product.addActionListener(this);

        btnAdd_employee.addActionListener(this);
        btnUpdate_employee.addActionListener(this);
        btnDelete_employee.addActionListener(this);

        btnLogout.addActionListener(this);

        setVisible(true);
        setSize(1200,430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        DashboardView dashboardView = new DashboardView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OrderController orderController = new OrderController();
        if (e.getSource() == btnCalculate){
            Double price = orderController.getProductPrice(Integer.parseInt(txtProduct_id.getText()));

            OrderModel orderModel = new OrderModel(Integer.parseInt(txtProduct_id.getText()),Integer.parseInt(txtQty.getText()), price);
            cart.add(orderModel);
            JOptionPane.showMessageDialog(this, "Product Added to cart Successfully!");
            txtProduct_id.setText("");
            txtQty.setText("");
        }
        if (e.getSource() == btnCalulate_2){
            Double totalPrice = orderController.calculateTotalPrice(cart);
            JOptionPane.showMessageDialog(this, "Order Place Successfully!"+totalPrice);
            new OrderSuccessView(cart, totalPrice).setVisible(true);
            this.hide();
        }
        if (e.getSource() == btnAdd_product){
            new ProductAddView().setVisible(true);
            this.hide();
        }
        if (e.getSource() == btnUpdate_product){
            new ProductUpdateView().setVisible(true);
            this.hide();
        }
        if (e.getSource() == btnDelete_product){
            new ProductDeleteView().setVisible(true);
            this.hide();
        }
        if (e.getSource() == btnAdd_employee){
            new EmployeeAddView().setVisible(true);
            this.hide();
        }
        if (e.getSource() == btnUpdate_employee){
            new EmployeeUpdateView().setVisible(true);
            this.hide();
        }
        if (e.getSource() == btnDelete_employee){
            new EmployeeDeleteView().setVisible(true);
            this.hide();
        }

        if (e.getSource() == btnLogout){
            new WelcomeView().setVisible(true);
            this.hide();
        }
    }

    private static void addPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });
    }
}
