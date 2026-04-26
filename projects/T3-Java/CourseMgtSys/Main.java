import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n===== Course Management System =====");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. Enroll Student");
            System.out.println("4. Assign Grade");
            System.out.println("5. Calculate Overall Grade");
            System.out.println("6. View Total Enrollments");
            System.out.println("7. Exit");

            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Course Code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Max Capacity: ");
                    int cap = scanner.nextInt();
                    CourseManagement.addCourse(code, name, cap);
                    break;

                case 2:
                    System.out.print("Student Name: ");
                    String sName = scanner.nextLine();
                    System.out.print("Student ID: ");
                    String sId = scanner.nextLine();
                    students.add(new Student(sName, sId));
                    break;

                case 3:
                    if (students.isEmpty() || CourseManagement.getCourses().isEmpty()) {
                        System.out.println("Add student and course first.");
                        break;
                    }
                    CourseManagement.enrollStudent(students.get(0),
                            CourseManagement.getCourses().get(0));
                    break;

                case 4:
                    System.out.print("Enter grade: ");
                    double grade = scanner.nextDouble();
                    CourseManagement.assignGrade(students.get(0),
                            CourseManagement.getCourses().get(0), grade);
                    break;

                case 5:
                    CourseManagement.calculateOverallGrade(students.get(0));
                    break;

                case 6:
                    System.out.println("Total Enrolled Students: "
                            + Course.getTotalEnrolledStudents());
                    break;

                case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
            
        scanner.close();
        
        }
    }
}