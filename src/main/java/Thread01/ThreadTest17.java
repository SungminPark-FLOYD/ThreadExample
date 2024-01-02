package Thread01;


/*
    wait(), notify()메서드를 이용한 예제
    (두 쓰레드가 번갈아 한번씩 실행되는 예제)

    wait(), notify(), notifyAll()은 동기화 영역에서만 사용 가능하다
 */
public class ThreadTest17 {
    public static void main(String[] args) {
        WorkObject workObj = new WorkObject();

        ThreadA thA = new ThreadA(workObj);
        ThreadB thB = new ThreadB(workObj);

        thA.start();
        thB.start();
    }
}

//공통으로 사용할 객체
class WorkObject{
    public synchronized void method01() {
        System.out.println("method01()메서드 실행중...");

        //wait상태인 쓰레드 유무 상관없이 호출
        notify();

        try {
            wait();
        }catch (InterruptedException e) {

        }
    }
    public synchronized void method02() {
        System.out.println("method02()메서드 실행중...");

        //wait상태인 쓰레드 유무 상관없이 호출
        notify();

        try {
            wait();
        }catch (InterruptedException e) {

        }
    }
}

//TODO WorkObject(공통으로 사용하는 객체)의 method01()메서드만 호출하는 쓰레드
class ThreadA extends Thread {
    private WorkObject workObj;

    public ThreadA(WorkObject workObj) {
        this.workObj = workObj;
    }

    @Override
    public void run() {
        for(int i = 1; i <= 10; i++) {
            workObj.method01();
        }
        synchronized (workObj) {
            workObj.notify();
        }
    }


}

//TODO WorkObject의 method02()메서드만 호출하는 쓰레드
class ThreadB extends Thread {
    private WorkObject workObj;

    public ThreadB(WorkObject workObj) {
        this.workObj = workObj;
    }

    @Override
    public void run() {
        for(int i = 1; i <= 10; i++) {
            workObj.method02();
        }
        synchronized (workObj) {
            workObj.notify();
        }
    }
}








