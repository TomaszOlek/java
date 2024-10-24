import java.util.ArrayList;

public interface StudentManager {
  String addStudent(Student student);
  String removeStudent(String studentID);
  void updateStudent(String studentID, Student updatedStudent);
  ArrayList<Student> getAllStudents();
  double calculateAverageGrade();
}