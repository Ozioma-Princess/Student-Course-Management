
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CourseManager {

    // A container for storing course objects
    private ArrayList<Course> courses = new ArrayList<>();

    // Method to add a new course
    public void addCourse(Scanner scanner) {

        System.out.println("\n--- ADD NEW COURSE ---");

        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine().trim().toUpperCase();

        // Check if the course already exists
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(code)) {
                System.out.println("This course already exists!");
                return;
            }
        }

        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine().trim();

        if (code.isEmpty() || title.isEmpty()) {
            System.out.println("Course code and title cannot be empty.");
            return;
        }

        int units;

        // Validate the credit units
        while (true) {
            System.out.print("Enter Credit Units: ");
            String input = scanner.nextLine().trim();

            try {
                units = Integer.parseInt(input);

                if (units > 0) {
                    break;
                }

                System.out.println("Units must be greater than zero.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a whole number.");
            }
        }

        // Create a new course object
        Course newCourse = new Course(code, title, units);

        // Add the object to the ArrayList
        courses.add(newCourse);

        System.out.println("Course added successfully!");
    }

    // Method to display all courses
    public void viewCourses() {

        System.out.println("\n--- ALL REGISTERED COURSES ---");

        if (courses.isEmpty()) {
            System.out.println("No courses have been added yet.");
            return;
        }

        for (Course course : courses) {
            System.out.println("-------------------------");
            course.displayCourse();
        }
    }

   
// Recursive method to search for a course
private Course findCourseRecursively(String code, int index) {

    // Base case: Stop when all courses have been checked
    if (index >= courses.size()) {
        return null;
    }

    // Get the course at the current position
    Course currentCourse = courses.get(index);

    // Check whether this is the course we are looking for
    if (currentCourse.getCourseCode().equalsIgnoreCase(code)) {
        return currentCourse;
    }

    // Recursive case: Check the next course
    return findCourseRecursively(code, index + 1);
}


// Method to search for a course using recursion
public void searchCourse(String code) {

    code = code.trim();

    Course foundCourse = findCourseRecursively(code, 0);

    if (foundCourse != null) {

        System.out.println("\nCourse Found!");
        foundCourse.displayCourse();

    } else {

        System.out.println("Course not found.");
    }
}

    // Method to calculate total credit units
    public void calculateTotalUnits() {

        int total = 0;

        for (Course course : courses) {
            total += course.getCreditUnits();
        }

        System.out.println("\nTotal Credit Units: " + total);
    }

    // Method to save courses to a file
    public void saveCourses() {

        try (FileWriter writer = new FileWriter("courses.txt")) {

            for (Course course : courses) {

                writer.write(
                    course.getCourseCode() + "," +
                    course.getCourseTitle() + "," +
                    course.getCreditUnits() + "\n"
                );
            }

            System.out.println("Courses saved successfully!");

        } catch (IOException e) {

            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    // Method to load courses from a file
    public void loadCourses() {

        ArrayList<Course> loadedCourses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader("courses.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] details = line.split(",", 3);

                if (details.length != 3) {
                    throw new IllegalArgumentException(
                        "Invalid course format in file."
                    );
                }

                String code = details[0].trim().toUpperCase();
                String title = details[1].trim();
                int units = Integer.parseInt(details[2].trim());

                if (code.isEmpty() || title.isEmpty() || units <= 0) {
                    throw new IllegalArgumentException(
                        "Invalid course information in file."
                    );
                }

                loadedCourses.add(new Course(code, title, units));
            }

            courses.clear();
            courses.addAll(loadedCourses);

            System.out.println("Courses loaded successfully!");

        } catch (IOException | IllegalArgumentException e) {

            System.out.println("Error loading courses: " + e.getMessage());
        }
    }
}
