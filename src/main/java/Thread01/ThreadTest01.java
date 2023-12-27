package Thread01;

public class ThreadTest01 {
    public static void main(String[] args) {
        // '*'문자 200개 출력, '$'문자 200개 출력

        for(int i=1; i <= 200; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println();

        for(int i=1; i <= 200; i++) {
            System.out.print("$");
        }
    }
}
