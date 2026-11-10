
public class Course {

    // Attributes of a course
    private String courseCode;
    private String courseTitle;
    private int creditUnits;

    // Constructor
    public Course(String courseCode, String courseTitle, int creditUnits) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.creditUnits = creditUnits;
    }

    // Getters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getCreditUnits() {
        return creditUnits;
    }

    // Display course information
    public void displayCourse() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Title: " + courseTitle);
        System.out.println("Credit Units: " + creditUnits);
    }
}
