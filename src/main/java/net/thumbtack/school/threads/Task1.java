package net.thumbtack.school.threads;

public class Task1 {
    //Напечатать все свойства текущего потока.

    public static void main(String args[]) {
        Thread thread = Thread.currentThread();

        System.out.println("Properties of thread");
        System.out.println("Name: " + thread.getName());
        System.out.println("Id: " + thread.getId());
        System.out.println("Priority: " + thread.getPriority());
        System.out.println("State: " + thread.getState());
        System.out.println("IsAlive: " + thread.isAlive());
    }
}
