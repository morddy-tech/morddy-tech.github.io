import java.util.*;

public class Student {

    // Private instance variables (Encapsulation)
    private String name;
    private String id;
    private Map<Course, Double> enrolledCourses;

    // Constructor
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new HashMap<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Course, Double> getEnrolledCourses() {
        return enrolledCourses;
    }

    // Instance method to enroll in course
    public void enrollStudent(Course course) {
        if (!enrolledCourses.containsKey(course)) {
            enrolledCourses.put(course, null);
            course.incrementEnrollment();
            System.out.println("Student enrolled successfully.");
        }
    }

    // Instance method to assign grade
    public void assignGrade(Course course, double grade) {
        if (enrolledCourses.containsKey(course)) {
            enrolledCourses.put(course, grade);
            System.out.println("Grade assigned successfully.");
        } else {
            System.out.println("Student not enrolled in this course.");
        }
    }
}