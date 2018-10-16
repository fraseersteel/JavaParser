package javaParser;

import java.io.File;


public class javaParser {

    public static void main(String[] args) {
        LongParameterList lpl = new LongParameterList();
        LargeClass lc = new LargeClass();
        File dir = new File("src/TestFolder/SimpleTests");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                try {
                    System.out.println("File - " + child.getName());
                   lc.run(child);
                    lpl.run(child);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
             else{
                System.out.println("This is not a directory");
            }
        }

    }

