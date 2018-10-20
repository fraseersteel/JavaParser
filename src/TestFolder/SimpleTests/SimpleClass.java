package TestFolder.SimpleTests;

public class SimpleClass
{

    public SimpleClass(){
        int a = 3;
        int b =5;

        for(int i = 0;i<5;i++){
            b = 2;
            int y =2;
            String hello = "hello";
        }
}

    public static void main(String[] args) {
        printOne(1,2,5,2,6,7);
        printTwo();
    }

    public static void printOne(int g, int u,int t,int z,int b,int p) {
        System.out.println("Hello World");
    }

    public static void printTwo() {
        int i = 0;
        int a = 0;
        i = 10;
        i = 12;
        a = i;
        i = a;
        a = i;
        i = a;
        a = i;
        a = i;
        i = a;
        a = i;
        printOne(1,2,5,7,4,2);

        if(i<10){
            i = 12012001;
            System.out.println("blah");
        }else{
            System.out.println("help");
        }
    }

}