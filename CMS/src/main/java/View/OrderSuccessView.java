package View;

import Model.OrderModel;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderSuccessView extends JFrame implements ActionListener {
    private JButton btnBack;
    public ArrayList<OrderModel>productList = new ArrayList<>();
    public OrderSuccessView(ArrayList<OrderModel> productList, Double total){
        btnBack = new JButton("Back");

        this.productList = productList;

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (OrderModel product : productList) {

            listModel.addElement(String.valueOf("Product Id : " + product.getProductId()));
            listModel.addElement(String.valueOf("Product Qty : " + product.getQuantity()));
            listModel.addElement(String.valueOf("Product Price : (LKR)" + product.getPrice()));
        }
        listModel.addElement("Total is : (LKR)" + total);

        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new FlowLayout());
        mainContainer.add(scrollPane);
        mainContainer.add(btnBack);

        btnBack.addActionListener(this);

        setTitle(" Display List");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack){
            new DashboardView().setVisible(true);
            this.hide();
        }
    }
}
