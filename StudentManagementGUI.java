import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentManagementGUI {
    private StudentManagerImpl manager = new StudentManagerImpl();

    public static void main(String[] args) {
        new StudentManagementGUI().createGUI();
    }

    public void createGUI() {  
        /**
         * Frame components
         */
        // Frame
        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        JTextField studentIDField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField gradeField = new JTextField();

        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(studentIDField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton updateButton = new JButton("Update Student");
        JButton displayButton = new JButton("Display All Students");
        JButton calculateAvgButton = new JButton("Calculate Average");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(calculateAvgButton);

        // Output area
        JTextArea outputArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add components to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        /**
         * Button actions
         */

        // Add student
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentID = studentIDField.getText();
                String name = nameField.getText();
                String ageText = ageField.getText();
                String gradeText = gradeField.getText();

                // Validation
                if (studentID.isEmpty()) {
                    outputArea.setText("Add student: StudentID cannot be empty");
                    return;
                }
                if (name.isEmpty()) {
                    outputArea.setText("Add student: Name cannot be empty");
                    return;
                }
                if (ageText.isEmpty()) {
                    outputArea.setText("Add student: Age cannot be empty");
                    return;
                }
                if (gradeText.isEmpty()) {
                    outputArea.setText("Add student: Grade cannot be empty");
                    return;
                }

                int age = Integer.parseInt(ageText);
                double grade = Double.parseDouble(gradeText);

                if (age <= 0) {
                    outputArea.setText("Add student: Age must be greater than 0");
                    return;
                }
                if (grade < 0 || grade > 100) {
                    outputArea.setText("Add student: Grade must be between 0 and 100");
                    return;
                }

                String message = manager.addStudent(new Student(name, age, grade, studentID));
                outputArea.setText(message);
            }
        });

        // Remove student
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentID = studentIDField.getText();

                if (studentID.isEmpty()) {
                    outputArea.setText("Add student: StudentID cannot be empty");
                    return;
                }

                String message = manager.removeStudent(studentID);
                
                outputArea.setText(message);
            }
        });

        // Update student
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentID = studentIDField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double grade = Double.parseDouble(gradeField.getText());

                if (age <= 0) {
                    outputArea.setText("Add student: Age must be greater than 0");
                    return;
                }
                if (grade < 0 || grade > 100) {
                    outputArea.setText("Add student: Grade must be between 0 and 100");
                    return;
                }

                manager.updateStudent(studentID, new Student(name, age, grade, studentID));
                outputArea.setText("Student updated successfully!");
            }
        });

        // Display all students
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Student> students = manager.getAllStudents();
                StringBuilder sb = new StringBuilder();
                for (Student student : students) {
                    sb.append("Student ID: ").append(student.getStudentID()).append(", ")
                      .append("Name: ").append(student.getName()).append(", ")
                      .append("Age: ").append(student.getAge()).append(", ")
                      .append("Grade: ").append(student.getGrade()).append("\n");
                }
                outputArea.setText(sb.toString());
            }
        });

        // Calculate average grade
        calculateAvgButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double avg = manager.calculateAverageGrade();
                outputArea.setText("Average grade: " + avg);
            }
        });

        // Display the frame
        frame.setVisible(true);
    }
}
