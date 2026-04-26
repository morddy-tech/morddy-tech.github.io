public class Course {

    // Private instance variables
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int currentEnrollment;

    // Static variable
    private static int totalEnrolledStudents = 0;

    // Constructor
    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
    }

    // Getters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    // Enrollment logic
    public void incrementEnrollment() {
        if (currentEnrollment < maxCapacity) {
            currentEnrollment++;
            totalEnrolledStudents++;
        } else {
            System.out.println("Course is full.");
        }
    }

    // Static method
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }
}