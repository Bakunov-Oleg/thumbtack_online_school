package net.thumbtack.school.threads;

public class Task3 {
    //Создать 3 новых потока и дождаться окончания их всех в первичном потоке.
    //Для каждого потока создать свой собственный экземпляр класса.

    public static void main(String args[]) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        MyThread thread3 = new MyThread();

        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("MyThread1 isAlive: " + thread1.isAlive());
        System.out.println("MyThread2 isAlive: " + thread2.isAlive());
        System.out.println("MyThread3 isAlive: " + thread3.isAlive());

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }

        System.out.println("MyThread1 isAlive: " + thread1.isAlive());
        System.out.println("MyThread2 isAlive: " + thread2.isAlive());
        System.out.println("MyThread3 isAlive: " + thread3.isAlive());

        System.out.println("Main thread exiting.");
    }

    static class MyThread extends Thread {
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
    }
}
