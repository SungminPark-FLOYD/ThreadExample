package Thread01;

//은행의 입출금을 쓰레드로 처리하는 예제(동기화 처리 예제)
public class ThreadTest15 {
    private int balance;    //잔액이 저장될 변수
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    //입금을 처리하는 메서드
    public  void deposit(int money) {
        balance += money;
    }

    //출금을 처리하는 메서드 (출금 성공 : true, 출금 실패 : false_
//    public synchronized boolean withdraw(int money) { 방법 1
    public boolean withdraw(int money) {
        synchronized (this) {   //방법2
            if(balance >= money) {
                for(int i=1; i<=10000000; i++) {    //시간 지연용 반복문(오류발생하기위해)
                    int k = i;
                }
                //출금 여부 검사
                balance -= money;

                System.out.println("메서드 안에서 balance = " + getBalance());
                return true;
            }else {
                return false;
            }
        }
    }
    public static void main(String[] args) {
        //은행업무 처리
        ThreadTest15 acount = new ThreadTest15();
        acount.setBalance(10000);   //잔액 설정

        //익명 구현체로 쓰레드 구현
        Runnable test = new Runnable() {
            @Override
            public void run() {
                //6000원 출금
                boolean result = acount.withdraw(6000);
                System.out.println("쓰레드에서 result= " + result
                        + ", balance = " + acount.getBalance());

            }
        };

        Thread th1 = new Thread(test);
        Thread th2 = new Thread(test);

        th1.start();
        th2.start();
    }
}
