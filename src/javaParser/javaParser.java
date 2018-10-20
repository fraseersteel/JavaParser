package javaParser;

import sun.nio.ch.sctp.SctpNet;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
        PrimitiveObession po = new PrimitiveObession();
        MiddleMan mm = new MiddleMan();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 9) {

            //**** change file path to chose which directory you would like to work in ****//

            File dir = new File("/Users/aaa/Documents/Uni/4th year/CS409/cs409-courseworkFinal/src/TestFolder/Abusers/Switch");
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
//                    System.out.println(child.getName());

                        switch (choice) {
                            case 1:
                                System.out.println("Testing for Large parameter list in class - " + child.getName());
                                lpl.run(child);
                                break;
                            case 2:
                                System.out.println("Testing for Large method." + child.getName());
                                lm.run(child);
                                break;
                            case 3:
                                System.out.println("Testing for Large class - " + child.getName());
                                lc.run(child);
                                break;
                            case 4:
                                System.out.println("Testing for Middle man");
                                mm.run(child);
                                break;
                            case 6:
                                System.out.println("Testing for Primitive Obession");
                                po.run(child);
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
        System.out.println("6 - Testing for Primitve Obession");
        System.out.println("9 - Exit");
        System.out.println();

    }

//    private static File chooseDirectory(File file) {
//
//        Scanner scanner = new Scanner(System.in);
//        int choice = -1;
//
//        ArrayList<File> files = new ArrayList<File>(Arrays.asList(file.listFiles()));
//
//        while (choice != 9) {
//
//            choice = scanner.nextInt();
//            if(choice==9){
//                break;
//            }
//            System.out.println("choose which directory to work with: ");
//            for (int i = 1; i > files.size(); i++) {
//                if(file.isDirectory()) {
//                    if (file.isDirectory()) {
//                        System.out.println(i + " " + file.listFiles());
//                    }
//                }else{
//
//                }
//            }
//        }
//
//        return files.get(choice);
//
//    }

}

