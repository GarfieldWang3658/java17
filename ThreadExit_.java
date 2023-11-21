package com.hspedu.exit_;

public class ThreadExit_ {
    public static void main(String[] args) {
        T t1 = new T();
        t1.start();

        //T１をメインスレッドでコントロールし、終了させる場合は、loopを修正しなければなりません。
        //runメソッドから退出し、T１のスレッドを終了させる方式は「通知方式」です。

        //メインスレッドを10秒スリープさせます,その後は退出します
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t1.setLoop(false);
    }
}
class T extends Thread {
    private int count=0;
    //制御変数を設置します
    private boolean loop = true;
    @Override
    public void run() {
        while (loop){
            System.out.println("T 运行中。。。。"+(++count));

            try {
                Thread.sleep(50 );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}