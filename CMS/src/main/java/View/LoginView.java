package View;

import Controller.LoginController;
import Model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ActionListener {
    private JLabel lblWelcome;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnBack;
    public LoginView(){
        lblWelcome = new JLabel("Welcome Back");
        lblUsername = new JLabel("Username : ");
        lblPassword = new JLabel("Password : ");

        txtUsername = new JTextField(10);
        txtPassword = new JPasswordField(10);

        btnLogin = new JButton("Login");
        btnBack = new JButton("Back");

        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(lblWelcome);

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new GridLayout(4, 2));

        mainContainer.add(p1);
        mainContainer.add(new JPanel());

        mainContainer.add(lblUsername);
        mainContainer.add(txtUsername);

        mainContainer.add(lblPassword);
        mainContainer.add(txtPassword);

        mainContainer.add(btnBack);
        mainContainer.add(btnLogin);

        btnLogin.addActionListener(this);
        btnBack.addActionListener(this);

        setVisible(true);
        setSize(600,210);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            LoginController loginController = new LoginController();
            UserModel userModel = new UserModel();
            loginController.getUser(txtUsername.getText());

            String enteredUsername = txtUsername.getText();
            String enteredPassword = new String(txtPassword.getPassword());

            String storedUsername = loginController.tempUsername;
            String storedPassword = loginController.tempPassword;

            userModel.setUsername(storedUsername);

            if (storedUsername != null && storedPassword != null &&
                    storedUsername.equals(enteredUsername) && storedPassword.equals(enteredPassword)) {
                JOptionPane.showMessageDialog(this, "Success!!" + userModel.getUsername());
                new DashboardView().setVisible(true);
                this.hide();
            } else {
                JOptionPane.showMessageDialog(this, "Failed!!");
            }
        }


        if (e.getSource() == btnBack){
            new WelcomeView().setVisible(true);
            this.hide();
        }
    }
}
