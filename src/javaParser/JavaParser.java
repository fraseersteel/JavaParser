package javaParser;

import java.io.File;
import java.util.Scanner;


public class JavaParser {

    public static void main(String[] args) {
        displayMenu();
        console();
    }


    private static void console() {

        LongParameterList lpl = new LongParameterList();
        LongMethod lm = new LongMethod();
        LargeClass lc = new LargeClass();
        PrimitiveObsession po = new PrimitiveObsession();
        PrimitiveObssession2 pO2 = new PrimitiveObssession2();
        DataClass dc = new DataClass();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        //**** change file path to chose which directory you would like to work in ****//
        File dir = new File("src/TestFolder/Dispensibles");
        if (dir.isDirectory()) {
        while (choice != 9) {

                System.out.println("Folder under investigation is " + dir.toString());
                System.out.println("Repeat list of options - 8");
                System.out.print("Enter choice : ");
                System.out.println();
                choice = scanner.nextInt();
                File[] directoryListing = dir.listFiles();
                if (directoryListing != null) {
                    for (File child : directoryListing) {
                        if (child.isDirectory()) {
                            System.out.println("file is directory. Looking at next file");
                            continue;
                        }
                        try {

                            System.out.println("---------------------------------------------------------------");

                            switch (choice) {
                                case 1:
                                    System.out.println("Testing for Large parameter list in class - " + child.getName());
                                    lpl.run(child);
                                    break;
                                case 2:
                                    System.out.println("Testing for Long method -" + child.getName());
                                    lm.run(child);
                                    break;
                                case 3:
                                    System.out.println("Testing for Large class - " + child.getName());
                                    lc.run(child);
                                    break;
                                case 4:
                                    System.out.println("Testing for Primitive Obession -" + child.getName());
                                    pO2.run(child);
                                    break;
                                case 5:
                                    System.out.println("Testing for Data Class -" + child.getName());
                                    dc.run(child);
                                    break;
                                case 8:
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

                        System.out.println();
                    }
                }
            }

        }
        else{
            System.out.println(dir.getName() + " is not a directory. Please change the pathname in javaParserClass");
        }
    }


    private static void displayMenu() {

        System.out.println();
        System.out.println("Choose which code smell you would like to MethodStatements for");
        System.out.println();
        System.out.println("1 - Testing for large Parameter list.");
        System.out.println("2 - Testing for long method.");
        System.out.println("3 - Testing for large class.");
        System.out.println("4 - Testing for Primitve Obession");
        System.out.println("5 - Testing for Data class.");
        System.out.println("9 - Exit");
        System.out.println();

    }

}

