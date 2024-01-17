package Controller;

import Model.Student;
import Model.StudentList;
import View.Menu;
import View.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentManager extends Menu {
    private StudentList a;
    private static String[] mc = {"Create","Find and Sort","Update/Delete","Report","Exit"};
    
    public StudentManager() {
        super("\nWELCOME TO STUDENT MANAGEMENT",mc);
        a = new StudentList();
    }
    
    public static void main(String[] args) {
        new StudentManager().run();
    }
    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                createStudent();
                break;
                        
            case 2:
                findAndSortStudents();
                break;
            case 3:
                updateOrDeleteStudent();
                break;
            case 4:
                generateReport();
                break;
            case 5:
                System.out.println("Exiting Program. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please choose again!");
        }
        
    }
    
    public void createStudent(){
        int numberOfStudents = 0;
        do{
            int id = Utils.getInt("Enter Student ID");
            String studentName = Utils.getString("Enter Student Name");
            int semester = Utils.getInt("Enter Semester");
            String courseName = Utils.getString("Enter Course Name");
            Student student = new Student(id, studentName, semester,courseName);
            a.addStudent(student);
            numberOfStudents++;
            if(numberOfStudents >= 10){
                String n = Utils.getString("Do you want to continue (Y/N)?");
                if (!n.equalsIgnoreCase("Y")){
                    break;
                }
            }
        } while (true);
    }
    
    public void findAndSortStudents() {
        String searchName = Utils.getString("Enter Student Name or a part of Student Name");
        ArrayList<Student> students = a.getStudents();
        ArrayList<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getStudentName().toLowerCase().contains(searchName.toLowerCase())) {
                result.add(student);
            }
        }

        Collections.sort(result, Comparator.comparing(Student::getStudentName));
        Utils.printStudentList(result);
    }
    public void updateOrDeleteStudent(){
        int searchId = Utils.getInt("Enter Student Id");
        ArrayList<Student> students = a.getStudents();
        Student foundStudent = null;
        for(Student student : students){
            if (student.getId() == searchId){
                foundStudent = student;
                break;
            }
        }
        if (foundStudent != null) {
            String choice = Utils.getString("Do you want to update (U) or delete (D) this student?");

            if (choice.equalsIgnoreCase("U")) {
                updateStudent(foundStudent);
            } else if (choice.equalsIgnoreCase("D")) {
                a.deleteStudent(foundStudent);
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }
    
    private void updateStudent(Student student) {
        String newStudentName = Utils.getString("Enter new Student Name");
        int newSemester = Utils.getInt("Enter new Semester");
        String newCourseName = Utils.getString("Enter new Course Name");

        Student newStudent = new Student(student.getId(), newStudentName, newSemester, newCourseName);
        a.updateStudent(student, newStudent);
        System.out.println("Student updated successfully.");
    }

    public void generateReport() {
        System.out.println("Student Name\tCourse\tTotal Courses");

        ArrayList<Student> students = a.getStudents();
        ArrayList<String> uniqueCombinations = new ArrayList<>();

        for (Student student : students) {
            String combination = student.getStudentName() + "|" + student.getCourseName();

            if (!uniqueCombinations.contains(combination)) {
                int totalCourses = countTotalCourses(student);
                System.out.println(student.getStudentName() + "\t\t" + student.getCourseName() + "\t\t" + totalCourses);
                uniqueCombinations.add(combination);
            }
        }
    }

    private int countTotalCourses(Student student) {
        int totalCourses = 0;

        for (Student s : a.getStudents()) {
            if (s.getStudentName().equals(student.getStudentName()) && s.getCourseName().equals(student.getCourseName())) {
                totalCourses++;
            }
        }

        return totalCourses;
    }
}
