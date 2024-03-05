package View;

import Controller.EmployeeController;
import Controller.ProductsController;
import Model.EmployeeModel;
import Model.ProductsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeAddView extends JFrame implements ActionListener {
    private JLabel lblDetails;
    private JLabel lblE_id;
    private JLabel lblName;
    private JLabel lblBday;
    private JLabel lblGender;
    private JLabel lblAddress;
    private JLabel lblContact;
    private JTextField txtE_id;
    private JTextField txtName;
    private JTextField txtBday;
    String[] genders = {"","Male", "Female", "Other"};
    private JComboBox cbGender;
    private JTextField txtAddress;
    private JTextField txtContact;
    private JButton btnExecute;
    private JButton btnBack;
    public EmployeeAddView() {
        lblDetails = new JLabel("Employee Details Handling");
        lblE_id = new JLabel("Id : ");
        lblName = new JLabel("Name : ");
        lblBday = new JLabel("Birthday");
        lblGender = new JLabel("Gender : ");
        lblAddress = new JLabel("Address : ");
        lblContact = new JLabel("Contact : ");

        txtE_id = new JTextField(10);
        txtName = new JTextField(10);
        txtBday = new JTextField(10);
        txtAddress = new JTextField(10);
        txtContact = new JTextField(10);

        cbGender = new JComboBox(genders);

        btnExecute = new JButton("Save");
        btnBack = new JButton("Back");

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new GridLayout(8, 2));

        mainContainer.add(lblDetails);
        mainContainer.add(new JPanel());
        mainContainer.add(lblE_id);
        mainContainer.add(txtE_id);
        mainContainer.add(lblName);
        mainContainer.add(txtName);
        mainContainer.add(lblBday);
        mainContainer.add(txtBday);
        mainContainer.add(lblGender);
        mainContainer.add(cbGender);
        mainContainer.add(lblAddress);
        mainContainer.add(txtAddress);
        mainContainer.add(lblContact);
        mainContainer.add(txtContact);

        mainContainer.add(btnBack);
        mainContainer.add(btnExecute);

        btnExecute.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
        setSize(600,416);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EmployeeAddView employeeAddView = new EmployeeAddView();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExecute){
            EmployeeModel employeeModel = new EmployeeModel();
            EmployeeController employeeController = new EmployeeController();

            employeeModel.setE_id(Integer.parseInt(txtE_id.getText()));
            employeeModel.setName(txtName.getText());
            employeeModel.setBday(txtBday.getText());
            employeeModel.setGender((String) cbGender.getSelectedItem());
            employeeModel.setAddress(txtAddress.getText());
            employeeModel.setContact(txtContact.getText());


            employeeController.addEmployee(employeeModel.getE_id(), employeeModel.getName(), employeeModel.getBday(),
                    employeeModel.getGender(), employeeModel.getAddress(), employeeModel.getContact());

            txtE_id.setText("");
            txtName.setText("");
            txtBday.setText("");
            txtAddress.setText("");
            txtContact.setText("");
            cbGender.setSelectedIndex(0);

        }
        if (e.getSource() == btnBack){
            new DashboardView().setVisible(true);
            this.hide();
        }
    }
}
