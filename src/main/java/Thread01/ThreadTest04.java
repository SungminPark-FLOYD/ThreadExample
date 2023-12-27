package Thread01;

/**
 *  1~20억가지의 합계를 구하는 프로그램을
 *  하나의 쓰레드가 단독으로 처리할 때와
 *  여러개의 쓰레드가 협력해서 처리할 때의 경과 시간을 비교해 보자
 */
public class ThreadTest04 {
    public static void main(String[] args) {
        //단독으로 처리하는 쓰레드 객체 생성
        sumThread sm = new sumThread(1L, 2_000_000_000L);

        //여럿이 협력해서 처리하는 쓰레드 객체 생성
        sumThread[] smArr = new sumThread[] {
          new sumThread(1L, 500_000_000L),
          new sumThread(500_000_001L, 1_000_000_000L),
          new sumThread(1_000_000_001L, 1_500_000_000L),
          new sumThread(1_500_000_001L, 2_000_000_000L)
        };

        //단독으로 처리하는 경우
        long startTime = System.currentTimeMillis();

        sm.start();
        try {
            sm.join();
        }catch (InterruptedException e) {

        }

        long endTime = System.currentTimeMillis();
        System.out.println("단독으로 처리할 때의 경과시간 : " + (endTime - startTime));
        System.out.println();

        //여러 쓰레드가 협력해서 처리할 경우
        startTime = System.currentTimeMillis();
        for(sumThread sm1 : smArr) {
            sm1.start();
        }
        for(sumThread sm1 : smArr) {
            try {
                sm1.join();
            }catch (InterruptedException e) {

            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("협력으로 처리할 때의 경과시간 : " + (endTime - startTime));
        System.out.println();
    }
}

class sumThread extends Thread {
    private  long start;
    private  long end;

    public sumThread(long start, long end) {
        super();
        this.start = start;
        this.end = end;
    }

    //생성자에서 시작값과 종료값을 초기화 한다
    @Override
    public void run() {
        long sum = 0L;
        //숫자 안에있는 '_' 은 생략되며 자릿수 판단할때 사용
        for(long i = start; i <= end; i++) {
            sum += i;
        }
        System.out.println(start + "부터 " + end + "까지의 합계 : " + sum);
    }
}
