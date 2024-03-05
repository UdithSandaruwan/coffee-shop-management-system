package View;

import Controller.EmployeeController;
import Model.EmployeeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDeleteView extends JFrame implements ActionListener {
    private JLabel lblDetails;
    private JLabel lblE_id;
    private JTextField txtE_id;
    private JButton btnExecute;
    private JButton btnBack;
    public EmployeeDeleteView() {
        lblDetails = new JLabel("Employee Details Handling");
        lblE_id = new JLabel("Id : ");

        txtE_id = new JTextField(10);

        btnExecute = new JButton("Delete");
        btnBack = new JButton("Back");

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new GridLayout(3, 2));

        mainContainer.add(lblDetails);
        mainContainer.add(new JPanel());
        mainContainer.add(lblE_id);
        mainContainer.add(txtE_id);

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
        EmployeeDeleteView employeeDeleteView = new EmployeeDeleteView();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExecute){
            EmployeeModel employeeModel = new EmployeeModel();
            EmployeeController employeeController = new EmployeeController();

            employeeModel.setE_id(Integer.parseInt(txtE_id.getText()));

            employeeController.deleteEmployee(employeeModel.getE_id());

            txtE_id.setText("");

        }
        if (e.getSource() == btnBack){
            new DashboardView().setVisible(true);
            this.hide();
        }
    }

}

