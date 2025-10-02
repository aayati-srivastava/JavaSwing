import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShoppingCartSimulation extends JFrame implements ActionListener {
    // Checkboxes for items
    JCheckBox laptop, phone, headphones;
    JButton generateBill;
    JTextArea billArea;

    public ShoppingCartSimulation() {
        // Frame settings
        setTitle("Shopping Cart Simulation");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create checkboxes with item names and prices
        laptop = new JCheckBox("Laptop - ₹50000");
        phone = new JCheckBox("Phone - ₹20000");
        headphones = new JCheckBox("Headphones - ₹3000");

        // Button to generate bill
        generateBill = new JButton("Generate Bill");
        generateBill.addActionListener(this);

        // Text area to display bill
        billArea = new JTextArea(10, 30);
        billArea.setEditable(false);

        // Add components to frame
        add(laptop);
        add(phone);
        add(headphones);
        add(generateBill);
        add(new JScrollPane(billArea));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int total = 0;
        StringBuilder bill = new StringBuilder("Items Selected:\n");

        if (laptop.isSelected()) {
            bill.append("Laptop - ₹50000\n");
            total += 50000;
        }
        if (phone.isSelected()) {
            bill.append("Phone - ₹20000\n");
            total += 20000;
        }
        if (headphones.isSelected()) {
            bill.append("Headphones - ₹3000\n");
            total += 3000;
        }

        bill.append("\nTotal: ₹").append(total);

        billArea.setText(bill.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShoppingCartSimulation().setVisible(true);
        });
    }
}
