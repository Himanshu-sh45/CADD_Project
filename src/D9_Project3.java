
import java.util.Scanner;

public class D9_Project3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your age: ");
        int age = sc.nextInt();

        if(age>=18 && age< 25) {
            System.out.println("You are graduating.");
        }
        else if(age>=25 && age < 30) {
            System.out.println("You are Preparing for exam.");
        }
        else if(age>=30 && age<60) {
            System.out.println("You are working.");
        }
        else {
            System.out.println("You are retired.");
        }

    }
}