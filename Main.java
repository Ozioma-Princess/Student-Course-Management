
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CourseManager manager = new CourseManager();
        
        // Automatically load previously saved courses
    manager.loadCourses();

        int choice = 0;

        // Keep displaying the menu until the user exits
        while (choice != 7) {

            System.out.println("\n========================================");
            System.out.println("   STUDENT COURSE MANAGEMENT SYSTEM");
            System.out.println("========================================");

            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course by Code");
            System.out.println("4. Compute Total Units");
            System.out.println("5. Save Courses to File");
            System.out.println("6. Load Courses from File");
            System.out.println("7. Exit Program");

            System.out.println("========================================");
            System.out.print("Enter your choice: ");

            try {

                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {

                    case 1:
                        manager.addCourse(scanner);
                        break;

                    case 2:
                        manager.viewCourses();
                        break;

                    case 3:
                        System.out.print("Enter Course Code: ");
                        String code = scanner.nextLine();
                        manager.searchCourse(code);
                        break;

                    case 4:
                        manager.calculateTotalUnits();
                        break;

                    case 5:
                        manager.saveCourses();
                        break;

                    case 6:
                        manager.loadCourses();
                        break;

                    case 7:
                        System.out.println("Thank you for using the system!");
                        break;

                    default:
                        System.out.println("Invalid choice! Select between 1 and 7.");
                        break;
                }

            } catch (NumberFormatException e) {

                System.out.println("Invalid input! Please enter a number.");
            }
        }

        scanner.close();
    }
}
