package View;

import Controller.ProductsController;
import Model.ProductsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductAddView extends JFrame implements ActionListener {
    private JLabel lblDetails;
    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblPrice;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtPrice;
    private JButton btnExecute;
    private JButton btnBack;
    public ProductAddView() {
        lblDetails = new JLabel("Product Details Handling");
        lblId = new JLabel("Id : ");
        lblName = new JLabel("Name : ");
        lblPrice = new JLabel("Price : LKR");

        txtId = new JTextField(10);
        txtName = new JTextField(10);
        txtPrice = new JTextField(10);

        btnExecute = new JButton("Save");
        btnBack = new JButton("Back");

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new GridLayout(5, 2));

        mainContainer.add(lblDetails);
        mainContainer.add(new JPanel());
        mainContainer.add(lblId);
        mainContainer.add(txtId);
        mainContainer.add(lblName);
        mainContainer.add(txtName);
        mainContainer.add(lblPrice);
        mainContainer.add(txtPrice);
        mainContainer.add(btnBack);
        mainContainer.add(btnExecute);

        btnExecute.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
        setSize(600,260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ProductAddView productsAddView = new ProductAddView();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExecute){
            ProductsModel productsModel = new ProductsModel();
            ProductsController productsController = new ProductsController();

            productsModel.setP_id(Integer.parseInt(txtId.getText()));
            productsModel.setName(txtName.getText());
            productsModel.setPrice(Double.parseDouble(txtPrice.getText()));

            productsController.addProduct(productsModel.getP_id(), productsModel.getName(), productsModel.getPrice());

            txtId.setText("");
            txtName.setText("");
            txtPrice.setText("");

        }
        if (e.getSource() == btnBack){
            new DashboardView().setVisible(true);
            this.hide();
        }
    }
}
