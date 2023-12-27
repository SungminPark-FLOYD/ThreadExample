package Thread01;

public class ThreadTest03 {
    public static void main(String[] args) {
        //스레드가 수행되는 시간을 체크해보기
        Thread th = new Thread(new MyRunner());
        long startTime = System.currentTimeMillis();

        th.start();
        try {
            //현재 위치에서 대상이 되는 쓰레드(변수 th)가 실행이 끝날대 가지 기다린다.
            th.join();
        }catch (InterruptedException e) {

        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}

class MyRunner implements Runnable {

    @Override
    public void run() {
        long sum = 0L;
        //숫자 안에있는 '_' 은 생략되며 자릿수 판단할때 사용
        for(long i = 1L; i <= 1_000_000_000L; i++) {
            sum += i;
        }
        System.out.println("합계 : " + sum);

    }
}
