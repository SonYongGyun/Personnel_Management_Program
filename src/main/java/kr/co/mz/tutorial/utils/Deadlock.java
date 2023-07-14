package kr.co.mz.tutorial.utils;

public class Deadlock {

  record Friend(String name) {
    // = static class Friend 에서 record로 변경.
    // 불변객체, 외부클래스 참조 없이 정적멤버와 같은 방식으로 사용가능
    // 이거쓰면 인자로 필드를 받는다. 그럼 필드, 생성자, 게터 등이 다 포함되어있다; 14부터사용;;

    public synchronized void bow(Friend bower) {
      System.out.format("%s: %s"
              + "  has bowed to me!%n",
          this.name, bower.name());
      bower.bowBack(this);
    }

    public synchronized void bowBack(Friend bower) {
      System.out.format("%s: %s"
              + " has bowed back to me!%n",
          this.name, bower.name());
    }
  }

  public static void main(String[] args) {
    final Friend alphonse = new Friend("Alphonse");
    final Friend gaston = new Friend("Gaston");

    new Thread(() -> alphonse.bow(gaston)).start();
    new Thread(new Runnable() {
      public void run() {
//        1초 기다리면 둘 다 실행되긴한다. OS의 스케줄러는 이런걸 관리하지못한다근데.
        //OS가 스케줄러를 관리하는데 제어불가능한 요소와 예측불가능이 섞여있어서 이런 데드락이발생.
//        try {
//          Thread.sleep(1000);
//        } catch (InterruptedException e) {
//          throw new RuntimeException(e);
//        }
        gaston.bow(alphonse);
      }
    }).start();
  }
}