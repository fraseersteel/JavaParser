package TestFolder.SimpleTests;

public class ThirdTest {

    int x = 0;

    public int helloWorld(){
        int i = 0;
        int a = 2;
        int b=3;

        a = i;
        a = i*a;
        b= a*i;
        return a;
    }

    public void test(){
        int y =1;
        System.out.println(helloWorld()*y);

        if(y==1){
            int i = 0;
            int a = 2;
        }

        while(y==1){
            int i = 0;
            int a = 2;
        }
    }
}
