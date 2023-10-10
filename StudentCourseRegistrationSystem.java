import java.util.ArrayList;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private int capacity;
    private int availableSpots;

    public Course(String code, String title, int capacity) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
        this.availableSpots = capacity;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public boolean enrollStudent() {
        if (availableSpots > 0) {
            availableSpots--;
            return true;
        }
        return false;
    }

    public void dropStudent() {
        availableSpots++;
    }

    @Override
    public String toString() {
        return code + " - " + title + " (Available Spots: " + availableSpots + ")";
    }
}

class Student {
    private String id;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerForCourse(Course course) {
        if (course.enrollStudent() && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.dropStudent();
            registeredCourses.remove(course);
        }
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}

public class StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Course Registration System");
            System.out.println("1. Add Course");
            System.out.println("2. List Available Courses");
            System.out.println("3. Register Student");
            System.out.println("4. List Registered Students");
            System.out.println("5. Enroll Student");
            System.out.println("6. Drop Student from Course");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addCourse(courses);
                    break;
                case 2:
                    listAvailableCourses(courses);
                    break;
                case 3:
                    registerStudent(students);
                    break;
                case 4:
                    listRegisteredStudents(students);
                    break;
                case 5:
                    enrollStudent(students, courses);
                    break;
                case 6:
                    dropStudentFromCourse(students, courses);
                    break;
                case 7:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addCourse(ArrayList<Course> courses) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter course code: ");
        String code = scanner.nextLine();

        System.out.print("Enter course title: ");
        String title = scanner.nextLine();

        System.out.print("Enter course capacity: ");
        int capacity = scanner.nextInt();

        Course course = new Course(code, title, capacity);
        courses.add(course);

        System.out.println("Course added successfully.");
    }

    private static void listAvailableCourses(ArrayList<Course> courses) {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void registerStudent(ArrayList<Student> students) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        Student student = new Student(id, name);
        students.add(student);

        System.out.println("Student registered successfully.");
    }

    private static void listRegisteredStudents(ArrayList<Student> students) {
        System.out.println("Registered Students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void enrollStudent(ArrayList<Student> students, ArrayList<Course> courses) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = findStudentByID(students, studentId);
        Course course = findCourseByCode(courses, courseCode);

        if (student == null || course == null) {
            System.out.println("Student or course not found.");
            return;
        }

        if (student.registerForCourse(course)) {
            System.out.println("Enrolled student " + student.getName() + " in course " + course.getCode());
        } else {
            System.out.println("Unable to enroll student in the course.");
        }
    }

    private static void dropStudentFromCourse(ArrayList<Student> students, ArrayList<Course> courses) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = findStudentByID(students, studentId);
        Course course = findCourseByCode(courses, courseCode);

        if (student == null || course == null) {
            System.out.println("Student or course not found.");
            return;
        }

        student.dropCourse(course);
        System.out.println("Dropped student " + student.getName() + " from course " + course.getCode());
    }

    private static Student findStudentByID(ArrayList<Student> students, String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(ArrayList<Course> courses, String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }
}
