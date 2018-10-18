package javaParser;

import java.io.File;
import java.util.Scanner;


public class javaParser {

    public static void main(String[] args) {
        displayMenu();
        console();
    }


    private static void console() {

        LongParameterList lpl = new LongParameterList();
        LongMethod lm = new LongMethod();
        LargeClass lc = new LargeClass();
        MiddleMan mm = new MiddleMan();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 9) {

            System.out.println("Repeat list of options - 5");
            System.out.print("Enter choice : ");


            File dir = new File("src/TestFolder/SimpleTests");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    try {

//                    System.out.println(child.getName());


                        choice = scanner.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println("Testing for Large parameter list.");
                                lpl.run(child);
                                break;
                            case 2:
                                System.out.println("Testing for Large method.");
                                lm.run(child);
                                break;
                            case 3:
                                System.out.println("Testing for Large class");
                                lc.run(child);
                                break;

                            case 4:
                                System.out.println("Testing for Middle man");

                                break;
                            case 5:
                                displayMenu();
                                break;
                            case 9:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Unexpected input: please try again.");
                        }




                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println();
        }
    }


    private static void displayMenu() {

        System.out.println();
        System.out.println("Choose which code smell you would like to test for");
        System.out.println();
        System.out.println("1 - Testing for large Parameter list.");
        System.out.println("2 - Testing for large method.");
        System.out.println("3 - Testing for large class.");
        System.out.println("9 - Exit");
        System.out.println();

    }
}

