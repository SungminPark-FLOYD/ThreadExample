package Thread01;


/*
* 쓰레드에서 객체를 공통으로 사용하는 예쩨
*
*   - 원주율을 계산하는 쓰레드와
*     계산된 원주율을 출력하는 쓰레드가 있다.
*
*   */
public class ThreadTest13 {
    public static void main(String[] args) {
        ShareData sd = new ShareData();

        CalcPIThread c = new CalcPIThread();
        c.setSd(sd);
        PrintPIThread p = new PrintPIThread(sd);

        c.start();
        p.start();
    }
}

//TODO 공통으로 사용할 객체   =>   원주율을 관리하는 클래스
class ShareData {
    public double result; //계산된 원주율이 저장될 변수
    public boolean isOk = false;
}

//TODO 원주율을 계산하는 쓰레드
class CalcPIThread extends Thread {
    private ShareData sd;

    //setter를 이용하여 공통으로 사용할 객체를 주입한다.
    public void setSd(ShareData sd) {
        this.sd = sd;
    }

    @Override
    public void run() {
        /*
        원주율 = (1/1 - 1/3 - 1/5 - 1/7 + 1/9 - ...)*4
         */

        double sum = 0.0;
        for(int i = 1; i <= 1000000000; i+=2) {
            if((i/2)%2 == 0) {  //2로 나눈 몫이 짝수 일때
                sum += 1.0/i;
            }
            else  {
                sum -= 1.0 / i;
            }
        }

        //계산이 완료된 결과를 공통객체에 저장한다.
        sd.result = sum*4;
        sd.isOk = true;
    }
}

//TODO 계산된 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread {
    private ShareData sd;

    //생성자를 이용해서 공통으로 사용할 객체를 주입한다
    public PrintPIThread(ShareData sd) {
        this.sd = sd;
    }

    @Override
    public void run() {
        while (true) {
            //계산이 완료되었는지 여부를 검사한다.
            if(sd.isOk) {
                break;
            }
            else {
                //양보
                Thread.yield();
            }
        }

        System.out.println();
        System.out.println("결과 : " + sd.result);
        System.out.println("PI : " + Math.PI);
    }
}