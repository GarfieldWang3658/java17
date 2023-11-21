package com.hspedu.homework;

import java.util.Random;
import java.util.Scanner;

public class HomeWork01 {
    public static void main(String[] args) {
        A a = new A();
        a.start();
        B b = new B(a);//注意！
        b.start();

    }
}
class A extends Thread{
    private boolean loop = true;
    @Override
    public void run() {
        while (loop){
            System.out.println((int)(Math.random()*100+1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
class B extends Thread{
private A a;
private Scanner scanner = new Scanner(System.in);
    public B(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("コマンドを入力してください");
           char key = scanner.next().toUpperCase().charAt(0);
        if (key == 'Q'){
            //通知の方式でAスレッドを終了させる
            a.setLoop(false);
            System.out.println("Bスレッド終了");
            break;
        }
        }
    }
}
