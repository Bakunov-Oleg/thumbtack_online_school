package net.thumbtack.school.threads.ttschool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TraineeMap {
    private Map<Trainee, String> traineeMap;

    public TraineeMap() {
        //Создает TraineeMap с пустым Map.
        traineeMap = new HashMap<Trainee, String>();
    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        //Добавляет пару Trainee - String в Map. Если Map уже содержит информацию об этом Trainee, выбрасывает TrainingException с TrainingErrorCode.DUPLICATE_TRAINEE.
        if (traineeMap.put(trainee, institute) != null){
            throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        }
    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        //Если в Map уже есть информация о данном Trainee, заменяет пару Trainee - String в Map на новую пару, иначе выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND.
        if (traineeMap.replace(trainee, institute) == null){
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {
        //Удаляет информацию о Trainee из Map. Если Map не содержит информации о таком Trainee, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND.
        if (traineeMap.remove(trainee) == null){
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public int getTraineesCount() {
        //Возвращает количество элементов в Map, иными словами, количество студентов.
        return traineeMap.size();
    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {
        //Возвращает институт, в котором учится данный Trainee. Если Map не содержит информации о таком Trainee, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        if (!traineeMap.containsKey(trainee)){
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return traineeMap.get(trainee);
    }

    public Set<Trainee> getAllTrainees() {
        //Возвращает Set из всех имеющихся в Map Trainee.
        Set<Trainee> allTrainees = new HashSet<>();
        allTrainees.addAll(traineeMap.keySet());
        return allTrainees;
    }

    public Set<String> getAllInstitutes() {
        //Возвращает Set из всех институтов.
        Set<String> allInst = new HashSet<>();
            allInst.addAll(traineeMap.values());
        return allInst;
    }

    public boolean isAnyFromInstitute(String institute) {
        //Возвращает true, если хоть один студент учится в этом institute, иначе false.
        return traineeMap.values().contains(institute);
    }

}
