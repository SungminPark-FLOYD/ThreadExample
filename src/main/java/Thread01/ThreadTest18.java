package Thread01;

import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;
import java.time.chrono.IsoChronology;

public class ThreadTest18 {
    public static void main(String[] args) {
        DataBox dataBox = new DataBox();

        ProducerThread th1 = new ProducerThread(dataBox);
        ConsumerThread th2 = new ConsumerThread(dataBox);

        th1.start();
        th2.start();
    }
}

//데이터를 공통으로 사용하는 클래스
class DataBox {
    private String data;

    //data변수의 값이 null이면 data변수에 문자열이 채워질 때까지 기다리고,
    //data변수에 값이 있으면 해당 문자열을 반환한다.
    //반환 후에는 data변수를 null로 만든다.
    public synchronized String getData() {
        if (data == null) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }//if문 끝..
        String temp = data;
        System.out.println("쓰레드가 읽은 데이터 : " + temp);
        data = null;
        notify();
        return temp;
    }

    //data변수에 값이 있으면 data변수가 null이 될 때까지 기다린다.
    //data변수가 null이 되면 새로운 데이터값을 저장한다.
    public synchronized void setData(String data) {
        if(this.data != null) {
            try{
                wait();
            }catch (InterruptedException e) {
            }
        }//if문 끝..
        this.data = data;   //새로운 데이터 저장
        System.out.println("Thread에서 새로 저장한 데이터 : " + data);
        notify();
    }
}

//데이터를 공급해주는 쓰레드
class ProducerThread extends Thread {
    private DataBox dataBox;
    public ProducerThread(DataBox dataBox) {
        this.dataBox = dataBox;
    }
    @Override
    public void run() {
        String[] nameArr = {"홍길동", "이순신", "강감찬", "박성민"};

        for(int i=0; i<nameArr.length; i++) {
            if(dataBox != null)
                dataBox.setData((nameArr[i]));
        }
    }
}

//데이터를 꺼내서 사용하는 쓰레드
class ConsumerThread extends Thread {
    private DataBox dataBox;

    public ConsumerThread(DataBox dataBox) {
        this.dataBox = dataBox;
    }

    @Override
    public void run() {
        for(int i =0; i < 4; i++) {
            String data = dataBox.getData();
        }
    }
}