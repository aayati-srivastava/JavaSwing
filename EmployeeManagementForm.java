import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class EmployeeManagementForm extends JFrame implements ActionListener {
    private JTextField nameField, deptField, salaryField;
    private JButton addButton;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public EmployeeManagementForm() {
        setTitle("Employee Management Form");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Department:"));
        deptField = new JTextField();
        inputPanel.add(deptField);

        inputPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        inputPanel.add(salaryField);

        addButton = new JButton("Add Employee");
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Name", "Department", "Salary"};
        tableModel = new DefaultTableModel(columns, 0);
        employeeTable = new JTable(tableModel);

        add(new JScrollPane(employeeTable), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String dept = deptField.getText().trim();
        String salaryText = salaryField.getText().trim();

        if (name.isEmpty() || dept.isEmpty() || salaryText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try {
            double salary = Double.parseDouble(salaryText);
            Object[] row = {name, dept, salary};
            tableModel.addRow(row);

            // Clear fields after adding
            nameField.setText("");
            deptField.setText("");
            salaryField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid salary. Please enter a number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeManagementForm().setVisible(true));
    }
}
