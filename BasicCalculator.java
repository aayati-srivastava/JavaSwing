import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCalculator {
    private JTextField display;
    private String operator = "";
    private double num1 = 0;
    private boolean isNewCalculation = true;

    public BasicCalculator() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if ("0123456789".contains(command)) {
                if (isNewCalculation) {
                    display.setText(command);
                    isNewCalculation = false;
                } else {
                    display.setText(display.getText() + command);
                }
            } else if ("+-*/".contains(command)) {
                num1 = Double.parseDouble(display.getText());
                operator = command;
                isNewCalculation = true;
            } else if ("=".equals(command)) {
                double num2 = Double.parseDouble(display.getText());
                double result = 0;
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": result = num1 / num2; break;
                }
                display.setText(String.valueOf(result));
                isNewCalculation = true;
            } else if ("C".equals(command)) {
                display.setText("");
                num1 = 0;
                operator = "";
                isNewCalculation = true;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BasicCalculator::new);
    }
}