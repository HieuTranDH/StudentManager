package View;

import Model.Student;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public static String getString(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.println(msg + ": ");
        return sc.nextLine().trim();
    }

    public static int getInt(String msg) {
        return Integer.parseInt(getString(msg));
    }
    public static double getDouble(String msg) {
        return Double.parseDouble(getString(msg));
    }
    
    public static void printStudentList(ArrayList<Student> students){
        System.out.println("Student Name\tSemester\tCourse Name");
        for (Student student : students) {
            System.out.println(student.getStudentName() + "\t\t" + student.getSemester() + "\t\t" + student.getCourseName());
        }
    }
}
