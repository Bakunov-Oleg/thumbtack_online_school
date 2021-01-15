package net.thumbtack.school.threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Task18 {
    //Та же задача, что и в (17), но разработчики либо ставят в очередь Task, либо запускают нового разработчика, который делает то же самое, что и первичный разработчик.
    // Выбор, какое из этих двух действий сделать, производится случайным образом.

    public static void main(String[] args) throws InterruptedException {
        int count = 5;
        BlockingQueue<TaskOn18> queue = new LinkedBlockingQueue<>();
        BlockingQueue<TypeActionCase> queueActions = new LinkedBlockingQueue<>();
        ExecutorService executorServiceDev = Executors.newFixedThreadPool(count);
        ExecutorService executorServiceExec = Executors.newFixedThreadPool(count);
        TypeActionCase action;
        int endedDevs = 0;
        int addedTasks = 0;
        int endedTasks = 0;
        int startedDevs = 5;

        for (int i = 0; i < count; i++) {
            executorServiceDev.submit(new DeveloperOn18(queue, count, executorServiceDev, 0, queueActions));
            executorServiceExec.submit(new ExecutorOn18(queue, queueActions));
        }

        try {
            while (true) {
                action = queueActions.take();
                switch (action) {
                    case TASKEND:
                        endedTasks++;
                        break;
                    case TASKSADD:
                        addedTasks++;
                        break;
                    case DEVELOPEREND:
                        endedDevs++;
                        break;
                    case DEVELOPERSTART:
                        startedDevs++;
                    default:
                        break;
                }

                if (endedTasks == addedTasks && endedDevs == startedDevs) {
                    for (int i = 0; i < count; i++) {
                        queue.put(new TaskOn18(null, true));
                    }
                    break;
                }
            }
            executorServiceExec.shutdown();
            executorServiceExec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            executorServiceDev.shutdown();
            executorServiceDev.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class TaskOn18 {

    boolean poison;

    List<Executable> executables;

    public TaskOn18(List<Executable> executables) {
        this.executables = executables;
        this.poison = false;
    }

    public TaskOn18(List<Executable> executables, boolean poison) {
        this.executables = executables;
        this.poison = poison;
    }

    public boolean isPoison() {
        return poison;
    }

    public List<Executable> getExecutables() {
        return executables;
    }

    public void setExecutables(List<Executable> executables) {
        this.executables = executables;
    }

    synchronized Executable take() {
        return executables.remove(0);
    }
}

class DeveloperOn18 implements Runnable {

    BlockingQueue<TypeActionCase> queueActions;
    ExecutorService executorService;
    private BlockingQueue<TaskOn18> queue;
    private List<Executable> subtasks = Collections.synchronizedList(new ArrayList<>());
    private int count;
    private int numberDescendant;

    public DeveloperOn18(BlockingQueue<TaskOn18> queue, int count, ExecutorService executorService, int numberDescendant, BlockingQueue<TypeActionCase> queueActions) {
        this.queue = queue;
        this.count = count;
        this.queueActions = queueActions;
        this.numberDescendant = numberDescendant;
        this.executorService = executorService;

        for (int i = 0; i < 5; i++){
            subtasks.add(new Task());
        }
    }

    public void developerAction() throws InterruptedException {
        for (int i = 0; i < count; i++) {
            if (numberDescendant < 5) {
                if ((int) ((Math.random() * (3 - 1)) + 1) == 1) {
                    try {
                        for (int j = 0; j < count; j++) {
                            queue.put(new TaskOn18(subtasks));
                            queueActions.add(TypeActionCase.TASKSADD);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("InterruptedException caught");
                    }
                } else {
                    try {
                        executorService.submit(new DeveloperOn18(queue, count, executorService, numberDescendant, queueActions));
                        queueActions.add(TypeActionCase.DEVELOPERSTART);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                numberDescendant++;
            } else {
                for (int j = 0; j < count; j++) {
                    queue.put(new TaskOn18(subtasks));
                    queueActions.add(TypeActionCase.TASKSADD);
                }
            }
        }
        queueActions.add(TypeActionCase.DEVELOPEREND);
    }

    @Override
    public void run() {
        System.out.println("DeveloperOn18 run!");
        try {
            developerAction();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("DeveloperOn18 workout!");
    }
}


class ExecutorOn18 implements Runnable {

    BlockingQueue<TypeActionCase> queueActions;
    private BlockingQueue<TaskOn18> queue;

    public ExecutorOn18(BlockingQueue<TaskOn18> queue, BlockingQueue<TypeActionCase> queueActions) {
        this.queue = queue;
        this.queueActions = queueActions;
    }

    @Override
    public void run() {
        System.out.println("ExecutorOn18 run!");
        while (true) {
            try {
                TaskOn18 task = queue.take();
                if (task.isPoison() == true) {
                    break;
                }
                if (task.getExecutables().isEmpty()) {
                    queueActions.put(TypeActionCase.TASKEND);
                } else {
                    task.take().execute();
                    queue.put(task);
                    System.out.println("ExecutorOn18 returned task: " + task + " to queue");
                    System.out.println("task: " + task + " size: " + task.getExecutables().size() + " returned to queue");
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }

        System.out.println("ExecutorOn18 workout! ");
    }
}