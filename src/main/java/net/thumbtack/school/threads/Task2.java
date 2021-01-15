package net.thumbtack.school.threads;

public class Task2 {
    //Создать новый поток и дождаться его окончания в первичном потоке.

    public static void main(String args[]) {
        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("`myThread` is alive: " + myThread.isAlive());

        try {
            myThread.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
        System.out.println("`myThread` is alive: " + myThread.isAlive());

        System.out.println("`MainThread` exiting.");
    }

    static class MyThread extends Thread {
        public void run() {
            try {
                System.out.println("`MainThread` is running");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            System.out.println("`MainThread` exit.");
        }
    }
}
