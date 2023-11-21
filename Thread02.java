package com.hspedu.threaduse;

public class Thread02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        //dog.start();//不能调用start
        //创建Thread对象，把dog对象（实现Runnable）放入Thread
        Thread thread = new Thread(dog);
        thread.start();
        //底层使用了设计模式【代理模式】
        //底層でデザインパターンが使用されており、そのパターンはプロキシパターンです。

        Tiger tiger = new Tiger();
        Proxy proxy = new Proxy(tiger);//模拟了线程代理
        proxy.start();

    }
}
class Animal{}
class Tiger extends Animal implements Runnable{
    @Override
    public void run() {
        System.out.println("老虎嗷嗷叫。。。");
    }
}
//线程代理，模拟了一个极简的Thread；
class Proxy implements Runnable{//可与把Proxy类当作
    private Runnable target=null;//属性，类型是Runnable

    @Override
    public void run() {
    if (target!=null){
        target.run();//动态绑定，绑定target的运行类型（当前为Tiger）
    }
    }

    public Proxy(Runnable target) {
        this.target = target;
    }
    public void start(){
        start0();
    }
    public void start0(){
        run();
    }
}
class Dog implements Runnable{//通过实现runnable接口开发线程类

    int count = 0;
    @Override
    public void run() {
        while (true){
            System.out.println("小狗汪汪叫。。。hi"+(++count)+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (count == 10){
                break;
            }
        }
    }
}
