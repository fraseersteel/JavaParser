package TestFolder.SimpleTests;

public class SimpleClass
{
    public static void main(String[] args) {
        printOne(1,2,5,2,6,7);
        printTwo();
    }

    public static void printOne(int g, int u,int t,int z,int b,int p) {
        System.out.println("Hello World");
    }

    public static void printTwo() {
        int i = 0;
        i = 10;
        printOne(1,2,5,7,4,2);
    }
}