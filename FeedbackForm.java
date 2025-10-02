import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FeedbackForm extends JFrame implements ActionListener {
    private JRadioButton star1, star2, star3, star4, star5;
    private ButtonGroup ratingGroup;
    private JTextArea commentsArea;
    private JButton submitButton;

    public FeedbackForm() {
        setTitle("Online Feedback Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel for rating
        JPanel ratingPanel = new JPanel(new FlowLayout());
        ratingPanel.setBorder(BorderFactory.createTitledBorder("Rate Our Service"));

        star1 = new JRadioButton("★ 1");
        star2 = new JRadioButton("★ 2");
        star3 = new JRadioButton("★ 3");
        star4 = new JRadioButton("★ 4");
        star5 = new JRadioButton("★ 5");

        ratingGroup = new ButtonGroup();
        ratingGroup.add(star1);
        ratingGroup.add(star2);
        ratingGroup.add(star3);
        ratingGroup.add(star4);
        ratingGroup.add(star5);

        ratingPanel.add(star1);
        ratingPanel.add(star2);
        ratingPanel.add(star3);
        ratingPanel.add(star4);
        ratingPanel.add(star5);

        add(ratingPanel, BorderLayout.NORTH);

        // Comments area
        JPanel commentsPanel = new JPanel(new BorderLayout());
        commentsPanel.setBorder(BorderFactory.createTitledBorder("Comments"));

        commentsArea = new JTextArea(5, 30);
        commentsPanel.add(new JScrollPane(commentsArea), BorderLayout.CENTER);

        add(commentsPanel, BorderLayout.CENTER);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int rating = 0;
        if (star1.isSelected()) rating = 1;
        else if (star2.isSelected()) rating = 2;
        else if (star3.isSelected()) rating = 3;
        else if (star4.isSelected()) rating = 4;
        else if (star5.isSelected()) rating = 5;

        String comments = commentsArea.getText().trim();

        if (rating == 0) {
            JOptionPane.showMessageDialog(this, "Please select a rating.");
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Thank you for your feedback!\n" +
                "Rating: " + rating + " stars\n" +
                "Comments: " + (comments.isEmpty() ? "No comments" : comments));
        
        // Reset form
        ratingGroup.clearSelection();
        commentsArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeedbackForm().setVisible(true));
    }
}
