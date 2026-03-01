import java.util.*;

public class Student {

    private String name;
    private int group;
    private int course;
    private List<Integer> grades;

    public Student(String name, int group, int course, List<Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = new ArrayList<>(grades);
    }

    public String getName() {
        return name;
    }

    public int getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    public double averageGrade() {
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    public static void removeStudents(Set<Student> students) {
        students.removeIf(student -> student.averageGrade() < 3);
    }

    public static Set<Student> promoteStudents(Set<Student> students) {
        Set<Student> result = new HashSet<>();
        for (Student student : students) {
            if (student.averageGrade() >= 3) {
                result.add(new Student(student.getName(), student.getGroup(), student.getCourse() + 1, student.getGrades()));
            } else {
                result.add(student);
            }
        }
        return result;
    }

    public static void printStudents(Set<Student> students, int course) {
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }
}
