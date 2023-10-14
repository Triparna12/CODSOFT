import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Student Grade Calculator");

        System.out.print("Enter the total number of subjects : ");
        int numOfSubjects = scanner.nextInt();
        int totalMarks = 0;

        for (int i = 1; i <= numOfSubjects; i++) {
            System.out.print("Enter marks for subject " + i + " (out of 100) : ");
            int marks = scanner.nextInt();
            totalMarks += marks;
        }

        System.out.println("Total Marks : " + totalMarks);

        double percentage = (double) totalMarks / (numOfSubjects * 100) * 100;
        System.out.println("Percentage is : " + percentage + "%");

        String grade;
        if (percentage >= 90) {
            grade = "A+";
        } else if (percentage >= 80) {
            grade = "A";
        } else if (percentage >= 70) {
            grade = "B";
        } else if (percentage >= 60) {
            grade = "C";
        } else if (percentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("Student's grade : " + grade);

    }
}
