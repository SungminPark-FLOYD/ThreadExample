package Thread01;

import javax.swing.*;
import java.util.Random;

/*
        컴퓨터와 가위 바위 보를 진행하는 프로그램 작성

        컴퓨터의 가위 바위 보는 난수를 이용해서 구하고
        사용자의 입력은 showInputDialog()메서드를 이용해서 입력받는다

        입력시간은 5초로 제한하고 카운트 다운을 한다
        5초안에 입력이 없으면 게임에 진것으로 처리한다
        5초안에 입력이 완료되면 승패를 구해서 출력한다.

        결과 예시)
        1) 5초 안에 입려이 없을때
            -- 결 과 --
           시간초과로 당신이 졌습니다.
       2) 5초안에 입력을 완료했을 때
            -- 결 과--
            컴퓨터 : 가위
            사용자 : 바위
            결 과 : 승리
 */
public class ThreadTest07 {
    public static void main(String[] args) {
        Thread th1 = new UserInput();
        Thread th2 = new Computer();
        th1.start();
        th2.start();
    }
}

//데이터를 입력하는 쓰레드 클래스 -> main도 하나의 쓰레드이기때문에 main에서 처리해도 됨
class UserInput extends Thread {
    //입력 여부를 확이하기 위한 변수 선언 - 쓰레드에서 공통으로 사용할 변수
    public static boolean inputChk = false;
    public static String str;
    @Override
    public void run() {
        while(!inputChk) {
            //사용자로부터 데이터 입력받기
            str = JOptionPane.showInputDialog("가위 바위 보 게임");
            if(str != null) {
                if(str.isEmpty()) {
                    System.out.println("잘못된 입력입니다.");
                }

                else if(str.equals("가위") ||  str.equals("바위") || str.equals("보")) {
                    inputChk = true;
                }
                else System.out.println("잘못된 입력입니다.");
            }
            else {
                System.out.println("시스템 종료...");
                //강제종료
                System.exit(0);
            }
        }

    }
}

//카운트 다운을 진행하는 쓰레드 클래스
class Computer extends Thread {
    @Override
    public void run() {
        Random ran = new Random();
        //int index = (int)(Math.random()*3 + 1) //1부터3까지 난수 생성
        int r = ran.nextInt(3) + 1;
        String result;

        if(r == 1) result = "가위";
        else if(r == 2) result = "바위";
        else result = "보";

        for (int i = 10; i >= 1; i--) {
            //입력이 완료되었는지 여부를 검사해서 입력이 완료되었으면
            //카운트 다운 쓰레드를 종료시킨다
            if (UserInput.inputChk) {

                System.out.println("-- 결 과 --");
                System.out.println("컴퓨터 : " + result);
                System.out.println("사용자 :"  + UserInput.str);
                if(UserInput.str.equals(result)) System.out.println("결과 : 비겼습니다.");
                else if(UserInput.str.equals("가위")) {
                    if(r == 2) System.out.println("결과 : 당신이 졌습니다.");
                    else if(r == 3) System.out.println("결과 : 당신이 이겼습니다.");
                }
                else if(UserInput.str.equals("바위")) {
                    if(r == 3) System.out.println("결과 : 당신이 졌습니다.");
                    else if(r == 1) System.out.println("결과 : 당신이 이겼습니다.");
                }
                else if(UserInput.str.equals("보")) {
                    if(r == 1) System.out.println("결과 : 당신이 졌습니다.");
                    else if(r == 2) System.out.println("결과 : 당신이 이겼습니다.");
                }
                //run메서드가 종료되면 쓰레드도 종료된다
                return;

            }
            System.out.println(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
        System.out.println("시간이 초과 되었습니다. 당신이 졌습니다.");
        //강제종료
        System.exit(0);
    }

}
