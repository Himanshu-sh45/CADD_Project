// Grid Master

import java.util.Scanner;

public class D9_Project {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of students: ");
        int numStudent = sc.nextInt();

        for(int i=0;i<numStudent;i++) {
            System.out.println("Enter the name of student: ");
            String name = sc.next();
            sc.nextLine();

            System.out.println("Enter student marks (out of 100): ");
            int marks = sc.nextInt();

            String grade;

            if (marks >= 90) {
                grade = "A";
            }
            else if (marks >= 80) {
                grade = "B+";
            }
            else if (marks >= 70) {
                grade = "B";
            }
            else if (marks >= 60) {
                grade = "C";
            }
            else {
                grade = "Fail";
            }

            System.out.println("Name of Student: "+name);
            System.out.println("Marks: "+marks);
            System.out.println("Grade: "+grade);

            if(grade.equals("A") || grade.equals("B+")) {
                System.out.println("Student performs well.");
            }
            else if(grade.equals("B") || grade.equals("C") ) {
                System.out.println("Good Performance.");
            }
            else if(grade.equals("Pass")) {
                System.out.println("Student needs improvement.");
            }
            else {
                System.out.println("Student needs improvement.");
            }
        }

        sc.close();
    }
}