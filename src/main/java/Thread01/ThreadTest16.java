package Thread01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
    Vector, Hashtable등의 예전 부터 존재하던 Collection 객체들은
    내부에 동기화 처리가 되어 있다.

    그런데 새롭게 구성된 Collection 들은 동기화 처리가 되어 있지 않다
    그래서, 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면
    동기화 처리를 한 후에 사용해야 한다.

    동기화 처리를 해 주는 메서드
    - Collections.synchronizedList() => List동기화 처리
    - Collections.synchronizedMap() => Map동기화 처리
    - Collections.synchronizedSet() => Set동기화 처리

 */
public class ThreadTest16 {
    public static Vector<Integer> vec = new Vector<>();
    //동기화 처리가 되지 않은 List
    public static List<Integer> list1 = new ArrayList<>();

    //동기화 처리를 한 List
    public static List<Integer> list2 =
            Collections.synchronizedList(new ArrayList<Integer>());

    public static void main(String[] args) {
        //익명 구현체로 쓰레드 구현
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i < 10000; i++) {
//                    vec.add(i);
//                    list1.add(i);
                    list2.add(i);
                }
            }
        };
        Thread[] thArr = new Thread[] {
                new Thread(r),
                new Thread(r),
                new Thread(r),
                new Thread(r),
                new Thread(r)
        };

        for(Thread th : thArr) {
            th.start();
        }
        for(Thread th : thArr) {
            try {
                th.join();
            }catch (InterruptedException e) {

            }
        }

//        System.out.println("vec의 개수 : " + vec.size());
        System.out.println("list1의 개수 : " + list2.size());



    }

}
