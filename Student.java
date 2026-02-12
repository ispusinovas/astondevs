import java.util.*;

public class Student {
    String name;
    int group;
    int course;
    List<Integer> grades;

    public Student(String name, int group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public static double averageGrade(Student student) {
        int sum = 0;
        for (int grade : student.grades) {
            sum += grade;
        }
        return (double) sum / student.grades.size();
    }

    public static void removeStudents(Set<Student> students) {
        students.removeIf(student -> averageGrade(student) < 3);
    }

    public static void promoteStudents(Set<Student> students) {
        for (Student student : students) {
            if (averageGrade(student) >= 3) {
                student.course++;
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        for (Student student : students) {
            if (student.course == course) {
                System.out.println(student.name);
            }
        }
    }
}