package com.hspedu.threaduse;

public class Thread01 {
    public static void main(String[] args) throws InterruptedException {
        //创建cat对象，可以当作线程使用
        Cat cat = new Cat();
        /*
        （1）
           public void start() {
        synchronized (this) {
            // zero status corresponds to state "NEW".
            if (holder.threadStatus != 0)
                throw new IllegalThreadStateException();
            start0();
        }
    }
        （2）
        start0()是本地方法，是JVM调用的，底层是C/C++实现的
        //真正实现多线程效果的是start0()方法，而不是run
         private native void start0();
         */
        cat.start();
        cat.run();//run方法就是一个普通的方法，并没有真正的启动一个线程
        //当main线程启动一个子thread-0主线程不会阻塞，会继续执行。
        //主线程和子线程交替执行。
        System.out.println("主线程序执行"+Thread.currentThread().getName());//默认叫main
        for (int i = 0; i < 60; i++) {
            System.out.println(+i);
            Thread.sleep(1000);
        }

    }
}
//1、当一个类继承了 thread类，该类就可以当作线程使用了
//クラスがThreadクラスを継承していると、そのクラスはスレッドとして使用できます
//2、重写run方法，并写上自己的业务代码
//run メソッドをオーバーライドします。
//3、run Thread 类 实现了 Runnable 结构的 run 方法
//Thread クラスを実装して Runnable インターフェースの構造にした run メソッドを実装する
/*
public viod run(){
if(target!=null){
target.run();
}}
 */
class Cat extends Thread {


        @Override
        public void run () {
            int times = 0;

            while(true) {
        System.out.println("喵喵我是一只小猫咪"+(++times)+Thread.currentThread().getName()
        );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (times == 8){
            break;
                }
    }
    }
}
