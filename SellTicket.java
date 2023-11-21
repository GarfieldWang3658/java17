package com.hspedu.syn;



public class SellTicket {
    public static void main(String[] args) {
//        SellTicket01 sellTicket01 = new SellTicket01();
//
//        SellTicket01 sellTicket02 = new SellTicket01();
//        SellTicket01 sellTicket03 = new SellTicket01();
//
//        //オーバーする事があります。
//
//        sellTicket01.start();
//        sellTicket02.start();
//        sellTicket03.start();
        //System.out.println("实现接口的泛格式售票");
        System.out.println("测试同步");
        SellTicket03 sellTicket03 = new SellTicket03();
        new Thread(sellTicket03).start();//スレッド-1
        new Thread(sellTicket03).start();//スレッド-2
        new Thread(sellTicket03).start();//スレッド-3



    }
}
//实现接口方式，使用synchronized实现线程同步
// インターフェースの実装およびsynchronizedを使用してスレッド同期を実現する
class SellTicket03 implements Runnable{
    private static int ticketnum =100;//共有する
    private boolean loop = true;
    Object object = new Object();

        //同步方法，在同一时刻，只能有一个线程来执行run方法。
        // 同期メソッドでは、同時に1つのスレッドだけが run メソッドを実行できます。
    //同步方法（静态的）的锁为当前类本身
    //1、クラスに対するロック
    //2、静的メソッドの中に同期されたコードブロックを実装する場合はクラス名.classを使用します。
    /*
           synchronized (SellTicket03.class){
            System.out.println("m2");
     */
//    new SellTicket01().start();
//    new SellTicket01().start();
//
//    class SellTicket01 extends Thread{
//        private static int ticketnum =100;
//        public  void m1(){
//            synchronized (this){
//                System.out.println("hello");
//            }
//        }
//    }

    public synchronized static void m1(){}
    public static void m2(){
        synchronized (SellTicket03.class){
            System.out.println("m2");
        }
    }


    //1、public synchronized void sell() {}=>同期メソッド
    //2、this オブジェクトに対するロック
    //3、可以在代码块上写 synchronized，同步代码块
    //「synchronized」は、コードブロックに書くことができ、これは「同期化されたコードブロック」を意味します。
    public /* synchronized */ void sell() {
        synchronized (/* this */ object ) {
            if (ticketnum <= 0) {
                System.out.println("販売終了");
                loop = false;
                return;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("窓口" + Thread.currentThread().getName() + "チケット販売" +
                    "" + "残チケット＝" + (--ticketnum));
        }
    }

    @Override
    public void run() {
        while (loop){
            sell();//同期メソッド
    }
}
}
//Thread継承
//class SellTicket01 extends Thread{
//    private static int ticketnum =100;//共有する
//    @Override
//    public void run() {
//       while (true){
//           if (ticketnum<=0){
//               System.out.println("販売終了");
//               break;
//           }
//           try {
//               Thread.sleep(50);
//           } catch (InterruptedException e) {
//               throw new RuntimeException(e);
//           }
//           System.out.println("窓口"+Thread.currentThread().getName()+"チケット販売" +
//                   ""+"残チケット＝"+(--ticketnum));
//       }
//    }
//}
//class SellTicket02 implements Runnable{
//    private static int ticketnum =100;//共有する
//    @Override
//    public void run() {
//        while (true){
//            if (ticketnum<=0){
//                System.out.println("販売終了");
//                break;
//            }
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("窓口"+Thread.currentThread().getName()+"チケット販売" +
//                    ""+"残チケット＝"+(--ticketnum));
//        }
//    }
//}