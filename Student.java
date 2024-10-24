public class Student {
  private String name;
  private int age;
  private double grade;
  private String studentID;
    
  // Konstruktor
  public Student(String name, int age, double grade, String studentID) {
      this.name = name;
      this.age = age;
      this.grade = grade;
      this.studentID = studentID;
  }

  // Gettery i settery
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public int getAge() { return age; }
  public void setAge(int age) { this.age = age; }

  public double getGrade() { return grade; }
  public void setGrade(double grade) { this.grade = grade; }
    
  public String getStudentID() { return studentID; }
  public void setStudentID(String studentID) { this.studentID = studentID; }

  // Metoda do wyświetlania informacji
  public String displayInfo() {
    return "StudentID: " + studentID + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
  }
}
