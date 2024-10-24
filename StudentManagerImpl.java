import java.sql.*;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager {
  private Connection connection;

  private Connection connect() {
    Connection conn = null;

    String url = "jdbc:postgresql://localhost:5432/student_management?user=postgres&password=Tomek1029";

    try {
      Class.forName("org.postgresql.Driver");

      conn = DriverManager.getConnection(url);
    } catch (ClassNotFoundException e) {
        System.out.println("PostgreSQL Driver not found: " + e.getMessage());
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }

    return conn;
  }

  /**
   * CRUD operations
   */
  // Create Student
  public String addStudent(Student student) {
    String sql = "INSERT INTO students(studentID, name, age, grade) VALUES(?, ?, ?, ?)";

    try (
      Connection conn = this.connect();
      PreparedStatement pstmt = conn.prepareStatement(sql)
    ) {
      // Insert
      pstmt.setString(1, student.getStudentID());
      pstmt.setString(2, student.getName());
      pstmt.setInt(3, student.getAge());
      pstmt.setDouble(4, student.getGrade());
      pstmt.executeUpdate();

      conn.close();
      return "Student added successfully!";

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  // Remove Student
  public String removeStudent(String studentID) {
    String sql = "DELETE FROM students WHERE studentID = ?";

    try (Connection conn = this.connect();
      PreparedStatement pstmt = conn.prepareStatement(sql)
    ) {
      pstmt.setString(1, studentID);
      pstmt.executeUpdate();

      conn.close();
      return "Student removed successfully!";
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  // Update Student
  public void updateStudent(String studentID, Student updatedStudent) {
    String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";

    try (Connection conn = this.connect();
      PreparedStatement pstmt = conn.prepareStatement(sql)
    ) {
      pstmt.setString(1, updatedStudent.getName());
      pstmt.setInt(2, updatedStudent.getAge());
      pstmt.setDouble(3, updatedStudent.getGrade());
      pstmt.setString(4, studentID);
      pstmt.executeUpdate();

      conn.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  // Get all students
  public ArrayList<Student> getAllStudents() {
    String sql = "SELECT * FROM students";
    ArrayList<Student> students = new ArrayList<>();

    try (Connection conn = this.connect();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql)
    ) {
      while (rs.next()) {
        students.add(new Student(rs.getString("name"),
                                 rs.getInt("age"),
                                 rs.getDouble("grade"),
                                 rs.getString("studentID")));
      }

      conn.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return students;
  }

  public double calculateAverageGrade() {
    String sql = "SELECT AVG(grade) AS avg_grade FROM students";
    double avgGrade = 0.0;

    try (Connection conn = this.connect();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql)
    ) {

      if (rs.next()) {
        avgGrade = rs.getDouble("avg_grade");
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return avgGrade;
  }
}
