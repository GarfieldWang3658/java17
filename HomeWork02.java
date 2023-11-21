package com.hspedu.homework;

public class HomeWork02 {
    public static void main(String[] args) {
        Coustom coustom = new Coustom();
        Thread thread1 = new Thread(coustom);
        thread1.setName("coustom1");
        Thread thread2 = new Thread(coustom);
        thread2.setName("coustom2");
        thread1.start();
        thread2.start();

    }
}
//1、因为设计到多个线程共享资源，所以使用实现Runnable方式
//複数のスレッドで共有されるリソースがあるため、Runnable インターフェースを実装する方法を使用しています。
//2、当多个线程执行到这里时，就会争夺this对象锁。
//複数のスレッドがここに到達すると、this オブジェクトのロックを競合します。
//3、哪个线程夺到（获取）this对象锁，就执行synchronized 代码块
//this オブジェクトのロックを獲得したスレッドが、synchronized コードブロックを実行します。
//4、争夺不到this对象锁，就blocked，准备继续争夺
//this オブジェクトのロックを獲得できない場合、ブロックされ、再度ロックを獲得するために待機します。
//5、this对象锁是非公平锁
//this オブジェクトのロックは、公平ではないロックです。株式會社
class Coustom implements Runnable{
    private int money = 20000;
    private boolean loop = true;
    @Override
    public void run() {
        while (loop) {
            synchronized (this) {
                if (money <= 500) {
                    loop = false;
                    System.out.println("残高不足");
                    break;
                }
                System.out.println(Thread.currentThread().getName() +
                        "は1000円を引き出した後の残高は" + (money -= 1000) + "円です。");
            }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

