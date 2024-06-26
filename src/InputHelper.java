import java.util.Scanner;

public class InputHelper {
        static String inputString(String printString, Scanner sc) {
            sc.skip("\n");
            System.out.print(printString);
            return sc.nextLine();
        }

        static double inputDouble(String printString, Scanner sc) {
            System.out.print(printString);
            return sc.nextDouble();
        }

        static float inputFloat(String printString, Scanner sc) {
            System.out.print(printString);
            return sc.nextFloat();
        }

        static int inputInt(String printString, Scanner sc) {
            System.out.print(printString);
            return sc.nextInt();
        }

        static boolean inputYesNoQuestion(String printString, Scanner sc) {
            sc.skip("\n");
            System.out.print(printString);
            String aws =  sc.nextLine();
            return aws.equals("y");
        }

}
