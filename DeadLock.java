package com.hspedu.syn;

public class DeadLock {
    public static void main(String[] args) {
        //デットロックを模擬します
        DeadLockDemo A = new DeadLockDemo(true);
        A.setName("Aスレッド");
        DeadLockDemo B = new DeadLockDemo(false);
        B.setName("Bスレッド");

        A.start();
        B.start();

    }
}

class DeadLockDemo extends Thread{
    static Object o1 = new Object();//オブジェクトを共有するために、staticを使用します。
    static Object o2 = new Object();
    boolean flag;
    public DeadLockDemo(boolean flag){//コンストラクタ
        this.flag=flag;
    }
    public void run(){
        //1、如果flag为T线程就会先得到/持有o1对象锁，然后尝试去获取o2对象锁
        //2、如果线程A得不到o2，对象锁就会Blocked
        //3、如果flag为F，线程B 就会得到/持有o2 对象锁，尝试会去o1对象锁
        //4、如果线程B得不到o1对象锁，就会Blocked
        if (flag){
            synchronized (o1){//相互排他ロック,下記コードは同期します
                System.out.println(Thread.currentThread().getName()+"进入1");
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName()+"进入2");
                }
            }
        }else {
            synchronized (o2){
                System.out.println(Thread.currentThread().getName()+"进入3");
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName()+"进入4");
                }
            }
        }
    }
}
