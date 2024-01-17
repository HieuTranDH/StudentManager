package Model;

public class Student implements Comparable <Student> {
    private int id;
    private String studentName;
    private int semester;
    private String courseName;

    public Student(int id, String studentName, int semester, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getSemester() {
        return semester;
    }

    public String getCourseName() {
        return courseName;
    }
    
    @Override
    public int compareTo(Student otherStudent) {
        return this.getStudentName().compareTo(otherStudent.getStudentName());
    }
}
