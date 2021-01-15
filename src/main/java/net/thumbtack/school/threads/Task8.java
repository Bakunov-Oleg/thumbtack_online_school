package net.thumbtack.school.threads;

import java.util.concurrent.Semaphore;

public class Task8 {

    //Система читатель-писатель

    static Semaphore semReader = new Semaphore(0);
    static Semaphore semWriter = new Semaphore(1);

    public static void main(String args[]) {
        Book book = new Book();
        new Reader(book).start();
        new Writer(book).start();
    }


    static class Writer extends Thread {
        private Book book;

        public Writer(Book book) {
            this.book = book;
        }

        public void run() {
            for (int i = 0; i < 100; i++)
                book.write(i);
        }
    }

    static class Reader extends Thread {
        private Book book;

        public Reader(Book book) {
            this.book = book;
        }

        public void run() {
            for (int i = 0; i < 100; i++)
                book.read();
        }
    }

    static class Book {
        private int step;

        public void read() {
            try {
                semReader.acquire();
                System.out.println("Read book on step: " + step);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            } finally {
                semWriter.release();
            }
        }

        public void write(int step) {
            try {
                semWriter.acquire();
                this.step = step;
                System.out.println("Write book on step: " + step);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            } finally {
                semReader.release();
            }
        }
    }

}
