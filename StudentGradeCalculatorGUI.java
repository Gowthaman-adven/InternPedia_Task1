import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculatorGUI extends JFrame {
    private JTextField[] markFields;
    private JLabel resultLabel;
    private JButton calculateButton;
    private int numSubjects = 5; // Define the number of subjects here

    public StudentGradeCalculatorGUI() {
        setTitle("Student Grade Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(numSubjects + 1, 2, 10, 10));

        // Create input fields for each subject
        markFields = new JTextField[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            inputPanel.add(new JLabel("Enter marks for subject " + (i + 1) + ":"));
            markFields[i] = new JTextField();
            inputPanel.add(markFields[i]);
        }

        // Add input panel to frame
        add(inputPanel, BorderLayout.CENTER);

        // Calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        add(calculateButton, BorderLayout.SOUTH);

        // Result label
        resultLabel = new JLabel("Total Marks, Average Percentage, and Grade will appear here");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel, BorderLayout.NORTH);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int totalMarks = 0;

                // Calculate total marks by summing up inputs
                for (JTextField markField : markFields) {
                    int mark = Integer.parseInt(markField.getText());
                    totalMarks += mark;
                }

                // Calculate average percentage
                double averagePercentage = (double) totalMarks / numSubjects;

                // Determine grade
                char grade;
                if (averagePercentage >= 90) {
                    grade = 'A';
                } else if (averagePercentage >= 80) {
                    grade = 'B';
                } else if (averagePercentage >= 70) {
                    grade = 'C';
                } else if (averagePercentage >= 60) {
                    grade = 'D';
                } else if (averagePercentage >= 50) {
                    grade = 'E';
                } else {
                    grade = 'F';
                }

                // Display results
                resultLabel.setText(String.format("Total Marks: %d, Average: %.2f%%, Grade: %c", totalMarks, averagePercentage, grade));

            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numbers for all subjects.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeCalculatorGUI frame = new StudentGradeCalculatorGUI();
            frame.setVisible(true);
        });
    }
}
