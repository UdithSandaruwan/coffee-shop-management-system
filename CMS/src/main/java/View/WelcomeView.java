package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeView extends JFrame implements ActionListener {
    private JLabel lblWelcome;
    private JButton btnLogin;
    private JButton btnSigniup;
    public WelcomeView(){
        lblWelcome = new JLabel("Welcome To CMS of Our Cofee");

        btnLogin = new JButton("Login");
        btnSigniup = new JButton("Signiup");

        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(lblWelcome);

        JPanel p2 = new JPanel(new GridLayout(2, 1));
        p2.add(btnLogin);
        p2.add(btnSigniup);

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new GridLayout(3, 3));

        mainContainer.add(new JPanel());
        mainContainer.add(p1);
        mainContainer.add(new JPanel());

        mainContainer.add(new JPanel());
        mainContainer.add(p2);
        mainContainer.add(new JPanel());

        mainContainer.add(new JPanel());
        mainContainer.add(new JPanel());
        mainContainer.add(new JPanel());

        btnLogin.addActionListener(this);
        btnSigniup.addActionListener(this);

        setVisible(true);
        setSize(600,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        WelcomeView welcomeView=new WelcomeView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            new LoginView().setVisible(true);
            this.hide();
        }

        if (e.getSource() == btnSigniup) {
            new SignupView().setVisible(true);
            this.hide();
        }
    }
}
