package com.example.demo;

import org.junit.Test;

/**
 * @Author: liangjuzheng
 * @Date: 2018/11/16
 * @Description:
 */
public class MultiThreadTest {

    private class MyThreadPrinter implements Runnable {
        private String name;
        private Object prev;
        private Object self;
        private int count;

         MyThreadPrinter(String name, Object prev, Object self, int count) {

            this.name = name;
            this.prev = prev;
            this.self = self;
            this.count = count;

        }

        @Override
        public void run() {
            while (count > 0) {
                synchronized (prev) {

                    synchronized (self) {
                        System.out.print(name);
                        count--;
                        self.notifyAll();//唤醒下一个要执行的线程
                    }


                        try {
                            prev.wait();  //等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }



                }

            }


        }



    //开启三个线程打印ABC十次
    @Test
    public void waitMethodTest() throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        MyThreadPrinter t1 = new MyThreadPrinter("A", c, a, 10);
        MyThreadPrinter t2 = new MyThreadPrinter("B", a, b, 10);
        MyThreadPrinter t3 = new MyThreadPrinter("C", b, c, 10);
        new Thread(t1).start();
        Thread.sleep(100);
        new Thread(t2).start();
        Thread.sleep(100);
        new Thread(t3).start();
        Thread.sleep(100);
    }
}
