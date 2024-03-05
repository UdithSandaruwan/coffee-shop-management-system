package View;

import Controller.ProductsController;
import Model.ProductsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductDeleteView extends JFrame implements ActionListener {
    private JLabel lblDetails;
    private JLabel lblId;
    private JTextField txtId;
    private JButton btnExecute;
    private JButton btnBack;
    public ProductDeleteView() {
        lblDetails = new JLabel("Product Details Handling");
        lblId = new JLabel("Id : ");

        txtId = new JTextField(10);

        btnExecute = new JButton("Delete");
        btnBack = new JButton("Back");

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new GridLayout(3, 2));

        mainContainer.add(lblDetails);
        mainContainer.add(new JPanel());
        mainContainer.add(lblId);
        mainContainer.add(txtId);
        mainContainer.add(btnBack);
        mainContainer.add(btnExecute);

        btnExecute.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
        setSize(600,180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ProductDeleteView productDeleteClass = new ProductDeleteView();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExecute) {
            ProductsModel productsModel = new ProductsModel();
            ProductsController productsController = new ProductsController();

            productsModel.setP_id(Integer.parseInt(txtId.getText()));

            productsController.deleteProduct(productsModel.getP_id());
            txtId.setText("");

        }
        if (e.getSource() == btnBack){
            new DashboardView().setVisible(true);
            this.hide();
        }
    }
}
