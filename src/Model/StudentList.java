package Model;

import java.util.ArrayList;

public class StudentList {
    private ArrayList<Student> students = new ArrayList<>();
    
    public void addStudent(Student student){
        students.add(student);
    }
    public ArrayList<Student> getStudents(){
        return students;
    }
    public void updateStudent(Student oldStudent, Student newStudent){
        int index = students.indexOf(oldStudent);
        students.set(index, newStudent);
    }
    public void deleteStudent (Student student){
        students.remove(student);
    }
}
