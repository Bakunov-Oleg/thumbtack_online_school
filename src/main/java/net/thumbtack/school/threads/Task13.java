package net.thumbtack.school.threads;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task13 {
    //Написать класс Formatter, с методом format(Date date), форматирующим дату-время.
    //Для форматирования возьмите класс SimpleDateFormat.
    //В основном потоке создать единственный экземпляр класса Formatter и 5 потоков, каждому потоку передается при инициализации этот экземпляр.
    //Потоки должны корректно форматировать дату-время, синхронизация не разрешается. Использовать ThreadLocal.

    public static void main(String[] args) {
        Formatter formatter = new Formatter(new Date());

        MyThread thread1 = new MyThread(formatter);
        MyThread thread2 = new MyThread(formatter);
        MyThread thread3 = new MyThread(formatter);
        MyThread thread4 = new MyThread(formatter);
        MyThread thread5 = new MyThread(formatter);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {

        private Formatter formatter;

        public MyThread(Formatter formatter) {
            this.formatter = formatter;
        }

        @Override
        public void run() {
            String date = formatter.format(formatter.getDate());
            System.out.println("Date input: " + formatter.getDate() + "                 date output: " + date);
        }
    }

    static class Formatter {

        private Date date;
        private ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<>();

        public Formatter(Date date) {
            this.date = date;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String format(Date date) {
            SimpleDateFormat simpleDateFormat = getThreadLocalSimpleDateFormat();
            return simpleDateFormat.format(date);
        }


        private SimpleDateFormat getThreadLocalSimpleDateFormat() {
            SimpleDateFormat simpleDateFormat = simpleDateFormatThreadLocal.get();
            if (simpleDateFormat == null) {
                simpleDateFormat = new SimpleDateFormat();
                simpleDateFormatThreadLocal.set(simpleDateFormat);
            }
            return simpleDateFormat;
        }
    }
}
