package net.thumbtack.school.threads.ttschool;

import java.util.LinkedList;
import java.util.Queue;

public class TraineeQueue {

    Queue traineeQueue = new LinkedList<Trainee>();


    public TraineeQueue() {
        //Создает TraineeQueue с пустой Queue.
    }

    public void addTrainee(Trainee trainee) {
        //Добавляет студента в очередь.
        traineeQueue.add(trainee);
    }
    public Trainee removeTrainee() throws TrainingException {
        //Удаляет студента из очереди. Метод возвращает удаленного Trainee. Если в очереди никого нет, выбрасывает TrainingException с TrainingErrorCode.EMPTY_TRAINEE_QUEUE.
        if(traineeQueue.isEmpty()){
            throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        }

        return (Trainee)traineeQueue.remove();
    }
    public boolean isEmpty() {
        //Возвращает true, если очередь пуста, иначе false
        return traineeQueue.isEmpty();
    }

}
