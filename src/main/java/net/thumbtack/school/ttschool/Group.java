package net.thumbtack.school.ttschool;

import java.util.*;

public class Group {

    //В этом классе должны быть поля “название группы” (текстовая строка) , название аудитории (текстовая строка, предполагается, что аудитория закреплена за группой постоянно)
    // и список студентов типа List<Trainee>. Название группы и номер аудитории не могут быть null или пустой строкой.

    private String name;
    private String room;
    private List<Trainee> traineeList;

    public Group(String name, String room) throws TrainingException {
        //Создает Group с указанными значениями полей и пустым списком студентов. Для недопустимых значений входных параметров выбрасывает TrainingException с соответствующим TrainingErrorCode
        setName(name);
        setRoom(room);
        traineeList = new ArrayList<>();
    }

    public String getName() {
        //Возвращает имя группы
        return name;
    }

    public void setName(String name) throws TrainingException {
        //Устанавливает имя группы. Для недопустимого значения входного параметра выбрасывает TrainingException с TrainingErrorCode.GROUP_WRONG_NAME
        // (здесь и далее добавляйте новые коды ошибок в TrainingErrorCode)
        chekName(name);
        this.name = name;
    }

    public String getRoom() {
        //Возвращает название  аудитории
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        //Устанавливает название  аудитории. Для недопустимого значения входного параметра выбрасывает TrainingException с TrainingErrorCode.GROUP_WRONG_ROOM
        chekRoom(room);
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        //Возвращает список учащихся.
        return traineeList;
    }

    public void addTrainee(Trainee trainee) {
        //Добавляет Trainee в группу.
        traineeList.add(trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        //Удаляет Trainee из группы. Если такого Trainee в группе нет, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        if (!traineeList.remove(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public void removeTrainee(int index) throws TrainingException {
        //Удаляет Trainee с данным номером в списке из группы. Если номер не является допустимым, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        try {
            traineeList.remove(index);
        } catch (Exception ex) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        //Возвращает первый найденный в списке группы Trainee, у которого имя равно firstName.
        // Если такого Trainee в группе нет, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        for (Trainee trainee : traineeList) {
            if (trainee.getFirstName().equals(firstName)) {
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        //Возвращает первый найденный в списке группы Trainee, у которого полное имя равно fullName.
        // Если такого Trainee в группе нет, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        for (Trainee trainee : traineeList) {
            if (trainee.getFullName().equals(fullName)) {
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void sortTraineeListByFirstNameAscendant() {
        //Сортирует список Trainee группы, упорядочивая его по возрастанию имени Trainee.
        traineeList.sort(Comparator.comparing(Trainee::getFirstName));
    }

    public void sortTraineeListByRatingDescendant() {
        //Сортирует список Trainee группы, упорядочивая его по убыванию оценки Trainee.
        traineeList.sort(Comparator.comparingInt(Trainee::getRating));
        reverseTraineeList();
    }

    public void reverseTraineeList() {
        //Переворачивает список Trainee группы, то есть последний элемент списка становится начальным, предпоследний - следующим за начальным и т.д..
        Collections.reverse(traineeList);
    }

    public void rotateTraineeList(int positions) {
        //Циклически сдвигает список Trainee группы на указанное число позиций. Для положительного значения positions сдвигает вправо, для отрицательного - влево на модуль значения positions.
        Collections.rotate(traineeList, positions);
    }

    public List<Trainee> getTraineesWithMaxRating() throws TrainingException {
        //Возвращает список тех Trainee группы , которые имеют наивысшую оценку.
        // Иными словами, если в группе есть Trainee с оценкой 5, возвращает список получивших оценку 5, если же таких нет, но есть Trainee с оценкой 4,
        // возвращает список получивших оценку 4 и т.д. Для пустого списка выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        if (traineeList == null || traineeList.size() == 0)
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        List<Trainee> topRating = new ArrayList<Trainee>();
        int maxRate;
        sortTraineeListByRatingDescendant();
        maxRate = traineeList.get(0).getRating();
        ListIterator<Trainee> tranieIterator = traineeList.listIterator();
        Trainee tempTrainee;
        while (tranieIterator.hasNext()) {
            tempTrainee = tranieIterator.next();
            if (tempTrainee.getRating() == maxRate) {
                topRating.add(tempTrainee);
            }
        }
        return topRating;
    }

    public boolean hasDuplicates() {
        //Проверяет, есть ли в группе хотя бы одна пара Trainee, для которых совпадают имя, фамилия и оценка.
        for (int i = 0; i < traineeList.size(); i++) {
            for (int j = i; j < traineeList.size(); j++) {
                if (i != j) {
                    if (traineeList.get(i).equals(traineeList.get(j))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void chekName(String name) throws TrainingException {
        if (name == null || name.equals(""))
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
    }

    private void chekRoom(String room) throws TrainingException {
        if (room == null || room.equals("")) throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
    }

    //Методы equals и hashCode
    //Не пишите эти методы сами, используйте средства IDEA.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(room, group.room) &&
                Objects.equals(traineeList, group.traineeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, traineeList);
    }
}
