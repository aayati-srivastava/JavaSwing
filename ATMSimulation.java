import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMSimulation extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Login panel
    private JPasswordField pinField;
    private JButton loginButton;
    private JLabel loginMessage;

    // Menu panel
    private JButton checkBalanceButton, depositButton, withdrawButton, logoutButton;
    private JTextArea outputArea;

    private int balance = 10000; // initial balance
    private final String correctPIN = "1234"; // demo PIN

    public ATMSimulation() {
        setTitle("ATM Simulation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Login panel
        JPanel loginPanel = new JPanel(new FlowLayout());
        loginPanel.add(new JLabel("Enter PIN:"));
        pinField = new JPasswordField(10);
        loginPanel.add(pinField);
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);
        loginMessage = new JLabel("");
        loginPanel.add(loginMessage);

        // Menu panel
        JPanel menuPanel = new JPanel(new FlowLayout());
        checkBalanceButton = new JButton("Check Balance");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        logoutButton = new JButton("Logout");

        checkBalanceButton.addActionListener(this);
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        logoutButton.addActionListener(this);

        menuPanel.add(checkBalanceButton);
        menuPanel.add(depositButton);
        menuPanel.add(withdrawButton);
        menuPanel.add(logoutButton);

        outputArea = new JTextArea(8, 30);
        outputArea.setEditable(false);
        menuPanel.add(new JScrollPane(outputArea));

        // Add panels to mainPanel
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(menuPanel, "Menu");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String enteredPIN = new String(pinField.getPassword());
            if (enteredPIN.equals(correctPIN)) {
                loginMessage.setText("");
                cardLayout.show(mainPanel, "Menu");
            } else {
                loginMessage.setText("Incorrect PIN. Try again.");
            }
        } else if (e.getSource() == checkBalanceButton) {
            outputArea.setText("Your Balance: ₹" + balance);
        } else if (e.getSource() == depositButton) {
            String amountStr = JOptionPane.showInputDialog(this, "Enter deposit amount:");
            try {
                int amount = Integer.parseInt(amountStr);
                if (amount > 0) {
                    balance += amount;
                    outputArea.setText("Deposited: ₹" + amount + "\nNew Balance: ₹" + balance);
                } else {
                    outputArea.setText("Invalid amount.");
                }
            } catch (Exception ex) {
                outputArea.setText("Invalid input.");
            }
        } else if (e.getSource() == withdrawButton) {
            String amountStr = JOptionPane.showInputDialog(this, "Enter withdraw amount:");
            try {
                int amount = Integer.parseInt(amountStr);
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    outputArea.setText("Withdrawn: ₹" + amount + "\nNew Balance: ₹" + balance);
                } else {
                    outputArea.setText("Insufficient balance or invalid amount.");
                }
            } catch (Exception ex) {
                outputArea.setText("Invalid input.");
            }
        } else if (e.getSource() == logoutButton) {
            pinField.setText("");
            outputArea.setText("");
            cardLayout.show(mainPanel, "Login");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMSimulation().setVisible(true));
    }
}
