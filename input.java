import java.util.Scanner;

public class input {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Name: ");
        String name =sc.nextLine();
        System.out.print("Enter Your Thailand: ");
        Boolean isThai = sc.nextBoolean();
        System.out.print("Enter Your Age: ");
        int age = sc.nextInt();

        if (age > 18 &&  isThai == true) {
            System.out.println("-------Result-------");
            System.out.println("You can Vote");
        }
        else {
            System.out.println("-----Result-----");
            System.out.println("You Can't Vote: TT__TT");
        }
    }

}