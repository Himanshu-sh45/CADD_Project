
import java.util.Scanner;

public class D9_Project2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your age: ");
        int age = sc.nextInt();

        if(age > 0 && age < 18) {
            System.out.println("The person is not eligible to vote.");
        }
        else if(age>=18 && age<125) {
            System.out.println("The person is eligible to vote.");
        }
        else {
            System.out.println("Invalid age.");
        }
    }
}