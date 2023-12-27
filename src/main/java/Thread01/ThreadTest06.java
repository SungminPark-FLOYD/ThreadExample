package Thread01;

import javax.swing.*;

public class ThreadTest06 {
    public static void main(String[] args) {

        Thread th1 = new DataInput();
        Thread th2 = new DataCountDown();
        th1.start();
        th2.start();
    }
}

//데이터를 입력하는 쓰레드 클래스
class DataInput extends Thread {
    //입력 여부를 확이하기 위한 변수 선언 - 쓰레드에서 공통으로 사용할 변수
    public static boolean inputChk = false;
    @Override
    public void run() {
        //사용자로부터 데이터 입력받기
        String str = JOptionPane.showInputDialog("아무거나 입력하세요");
        inputChk = true;
        System.out.println("입력한 값 : " + str);
    }
}

//카운트 다운을 진행하는 쓰레드 클래스
class DataCountDown extends Thread {
    @Override
    public void run() {

        for(int i=10; i  >= 1; i--) {
            //입력이 완료되었는지 여부를 검사해서 입력이 완료되었으면
            //카운트 다운 쓰레드를 종료시킨다
            if(DataInput.inputChk==true) {
                //run메서드가 종료되면 쓰레드도 종료된다
                return;

            }
            System.out.println(i);

            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {

            }
        }
        System.out.println("시간이 초과 되었습니다. 프로그램을 종료합니다.");
        //강제종료
        System.exit(0);
    }
}
