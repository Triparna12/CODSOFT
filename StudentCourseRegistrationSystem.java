import java.util.ArrayList;
import java.util.List;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean isFull() {
        return enrolledStudents >= capacity;
    }

    public void enrollStudent() {
        if (!isFull()) {
            enrolledStudents++;
        } else {
            System.out.println("Course " + code + " is already full.");
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            if (course.isFull()) {
                System.out.println("Course " + course.getCode() + " is full. Cannot register.");
            } else {
                registeredCourses.add(course);
                course.enrollStudent();
            }
        } else {
            System.out.println("You are already registered for this course.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
        } else {
            System.out.println("You are not registered for this course.");
        }
    }
}

public class StudentCourseRegistrationSystem{
    public static void main(String[] args) {
        Course course1 = new Course("CS101", "Introduction to Programming", "Learn programming basics", 30, "MWF 10:00 AM");
        Course course2 = new Course("MATH201", "Calculus I", "Fundamental calculus concepts", 25, "TTH 2:00 PM");

        Student student1 = new Student(1001, "Alice");
        Student student2 = new Student(1002, "Bob");

        student1.registerForCourse(course1);
        student1.registerForCourse(course2);

        student2.registerForCourse(course1);

        student1.dropCourse(course1);

        displayCourseDetails(course1);
        displayCourseDetails(course2);

        displayStudentCourses(student1);
        displayStudentCourses(student2);
    }

    public static void displayCourseDetails(Course course) {
        System.out.println("Course Code: " + course.getCode());
        System.out.println("Title: " + course.getTitle());
        System.out.println("Description: " + course.getDescription());
        System.out.println("Capacity: " + course.getCapacity());
        System.out.println("Enrolled Students: " + course.getEnrolledStudents());
        System.out.println("Schedule: " + course.getSchedule());
        System.out.println();
    }

    public static void displayStudentCourses(Student student) {
        System.out.println("Student ID: " + student.getStudentID());
        System.out.println("Name: " + student.getName());
        System.out.println("Registered Courses:");
        for (Course course : student.getRegisteredCourses()) {
            System.out.println(course.getCode() + " - " + course.getTitle());
        }
        System.out.println();
    }
}
