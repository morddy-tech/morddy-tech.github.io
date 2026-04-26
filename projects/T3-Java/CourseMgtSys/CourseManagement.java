import java.util.*;

public class CourseManagement {

    // Private static variables
    private static List<Course> courses = new ArrayList<>();
    private static Map<Student, Double> overallGrades = new HashMap<>();

    // Add Course
    public static void addCourse(String code, String name, int capacity) {
        Course course = new Course(code, name, capacity);
        courses.add(course);
        System.out.println("Course added successfully.");
    }

    // Enroll student
    public static void enrollStudent(Student student, Course course) {
        if (course.getCurrentEnrollment() < course.getMaxCapacity()) {
            student.enrollStudent(course);
            System.out.println("Student Enrolled Successfully.");
        } else {
            System.out.println("Cannot enroll. Course capacity reached.");
        }
    }

    // Assign grade
    public static void assignGrade(Student student, Course course, double grade) {
        student.assignGrade(course, grade);
    }

    // Calculate overall grade
    public static void calculateOverallGrade(Student student) {
        double total = 0;
        int count = 0;

        for (Double grade : student.getEnrolledCourses().values()) {
            if (grade != null) {
                total += grade;
                count++;
            }
        }

        if (count > 0) {
            double average = total / count;
            overallGrades.put(student, average);
            System.out.println("Overall Grade: " + average);
        } else {
            System.out.println("No grades available.");
        }
    }

    public static List<Course> getCourses() {
        return courses;
    }
}