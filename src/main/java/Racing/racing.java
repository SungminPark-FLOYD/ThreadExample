package Racing;

import java.util.*;

/*
* 10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 말의 위치(int), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간 중간에 각 말들의 위치를 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수 순으로 출력한다.
* */
public class racing {
    public static void main(String[] args) {
        System.out.println("경기 시작");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Horse> hList = new ArrayList<Horse>();

        hList.add(new Horse("01번말"));
        hList.add(new Horse("02번말"));
        hList.add(new Horse("03번말"));
        hList.add(new Horse("04번말"));
        hList.add(new Horse("05번말"));
        hList.add(new Horse("06번말"));
        hList.add(new Horse("07번말"));
        hList.add(new Horse("08번말"));
        hList.add(new Horse("09번말"));
        hList.add(new Horse("10번말"));

        Thread p = new Position(hList);

        for (Thread th : hList) {
            th.start();
        }

        p.start();

        try {
            p.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collections.sort(hList);
        System.out.println("경기가 종료되었습니다.");

        for (Horse h : hList) {
            System.out.println(h);
        }
    }
}

class Horse extends Thread implements Comparable<Horse> {
    public static int currentRank; //현재순위
    private String hName; // 말이름
    private int rank; // 순위
    private int position;
    private Random random = new Random();

    public Horse(String name) {
        super();
        this.hName = name;
    }

    public String getHName() {
        return hName;
    }

    public void setHName(String hName) {
        this.hName = hName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    // this와 h의 랭크를 오름차순으로 정렬
    @Override
    public int compareTo(Horse h) {
        return Integer.compare(this.getRank(), h.getRank());
    }

    @Override
    public String toString() {
        return String.format("%d등 : %s", rank, hName);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            setPosition(i);
            try {
                Thread.sleep(random.nextInt(400));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setRank(++currentRank);
    }
}

//TODO 말의 위치를 출력
class Position extends Thread {
    private List<Horse> hs;

    public Position(List<Horse> hs) {
        super();
        this.hs = hs;
    }

    @Override
    public void run() {
        while (true) {
            if (Horse.currentRank == hs.size()) {
                break;
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println();
            }

            for (Horse h : hs) {
                System.out.print(h.getHName() + " : ");
                for (int i = 1; i <= 50; i++) {
                    if (h.getPosition() == i) {
                        System.out.print(">");
                    } else {
                        System.out.print("-");
                    }
                }
                if (h.getPosition() == 50) {
                    System.out.print("골인");
                }
                System.out.println();
            }


            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
