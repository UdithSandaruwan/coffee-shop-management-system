package View;

import Controller.SignupController;
import Model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupView extends JFrame implements ActionListener {
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnCreate;
    private JButton btnBack;
    public SignupView(){
        lblUsername = new JLabel("Username : ");
        lblPassword = new JLabel("Password : ");

        txtUsername = new JTextField(10);
        txtPassword = new JPasswordField(10);

        btnCreate = new JButton("Create");
        btnBack = new JButton("Back");

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new GridLayout(3, 2));

        mainContainer.add(lblUsername);
        mainContainer.add(txtUsername);
        mainContainer.add(lblPassword);
        mainContainer.add(txtPassword);
        mainContainer.add(btnBack);
        mainContainer.add(btnCreate);

        btnCreate.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
        setSize(600,170);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SignupView signupView = new SignupView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCreate){
            SignupController signupController = new SignupController();
            UserModel userModel = new UserModel();
            userModel.setUsername(txtUsername.getText());
            userModel.setPassword(txtPassword.getText());

            signupController.signupUser(userModel.getUsername(), userModel.getPassword());

            txtUsername.setText("");
            txtPassword.setText("");
        }
        if (e.getSource() == btnBack){
            new WelcomeView().setVisible(true);
            this.hide();
        }
    }
}
