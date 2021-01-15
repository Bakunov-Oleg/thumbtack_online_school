package net.thumbtack.school.database.model;

import java.util.*;

public class Group {
    private String name;
    private String room;
    private int id;
    private List<Subject> subjects;
    private List<Trainee> trainees;
    private int school_id;


    public Group() {
    }

    public Group(int id, String name, String room, List<Trainee> trainees, List<Subject> subjects) { //Конструктор, устанавливающий значения всех полей
        setName(name);
        setRoom(room);
        setId(id);
        setTrainees(trainees);
        setSubjects(subjects);
        setSchoolId(0);
    }

    public Group(int id, String name, String room) { //Конструктор, устанавливающий значения всех полей. Полям - спискам присваивается пустой список (не null!)
        this(id, name, room, new ArrayList<>(), new ArrayList<>());
    }

    public Group(String name, String room) { //Конструктор, устанавливающий значения всех полей. Полю id присваивается значение 0,  полям - спискам - пустые списки (не null!)
        this(0, name, room, new ArrayList<>(), new ArrayList<>());
    }

    public void addSubject(Subject subject) { //Добавляет Subject в Group
        subjects.add(subject);
    }

    public void removeSubject(Subject subject) { //Удаляет Subject из Group
        subjects.remove(subject);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void addTrainee(Trainee trainee) {
        trainees.add(trainee);
    }

    public void removeTrainee(Trainee trainee) {
        trainees.remove(trainee);
    }

    public void removeTrainee(int index) {
        trainees.remove(index);
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public Trainee getTraineeByFirstName(String firstName) {
        for (Trainee trainee : trainees) {
            if (trainee.getFirstName().equals(firstName)) {
                return trainee;
            }
        }
        return null;
    }

    public Trainee getTraineeByFullName(String fullName) {
        for (Trainee trainee : trainees) {
            if (trainee.getFullName().equals(fullName)) {
                return trainee;
            }
        }
        return null;
    }

    public void sortTraineeListByFirstNameAscendant() {
        //Сортирует список Trainee группы, упорядочивая его по возрастанию имени Trainee.
        trainees.sort(Comparator.comparing(Trainee::getFirstName));
    }

    public void sortTraineeListByRatingDescendant() {
        //Сортирует список Trainee группы, упорядочивая его по убыванию оценки Trainee.
        trainees.sort(Comparator.comparingInt(Trainee::getRating));
        reverseTraineeList();
    }

    public void reverseTraineeList() {
        Collections.reverse(trainees);
    }

    public void rotateTraineeList(int positions) {
        //Циклически сдвигает список Trainee группы на указанное число позиций. Для положительного значения positions сдвигает вправо, для отрицательного - влево на модуль значения positions.
        Collections.rotate(trainees, positions);
    }

    public List<Trainee> getTraineesWithMaxRating() {
        //Возвращает список тех Trainee группы , которые имеют наивысшую оценку.
        // Иными словами, если в группе есть Trainee с оценкой 5, возвращает список получивших оценку 5, если же таких нет, но есть Trainee с оценкой 4,
        // возвращает список получивших оценку 4 и т.д. Для пустого списка выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        List<Trainee> topRating = new ArrayList<Trainee>();
        int maxRate;
        sortTraineeListByRatingDescendant();
        maxRate = trainees.get(0).getRating();
        ListIterator<Trainee> tranieIterator = trainees.listIterator();
        Trainee tempTrainee;
        while (tranieIterator.hasNext()) {
            tempTrainee = tranieIterator.next();
            if (tempTrainee.getRating() == maxRate) {
                topRating.add(tempTrainee);
            }
        }
        return topRating;
    }

    public void setTrainees(List<Trainee> trainees) {
        this.trainees = trainees;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getSchoolId() {
        return school_id;
    }

    public void setSchoolId(int schoolId) {
        this.school_id = schoolId;
    }

    public boolean hasDuplicates() {
        //Проверяет, есть ли в группе хотя бы одна пара Trainee, для которых совпадают имя, фамилия и оценка.
        for (int i = 0; i < trainees.size(); i++) {
            for (int j = i; j < trainees.size(); j++) {
                if (i != j) {
                    if (trainees.get(i).equals(trainees.get(j))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return getId() == group.getId() &&
                getSchool_id() == group.getSchool_id() &&
                Objects.equals(getName(), group.getName()) &&
                Objects.equals(getRoom(), group.getRoom()) &&
                Objects.equals(getSubjects(), group.getSubjects()) &&
                Objects.equals(getTrainees(), group.getTrainees());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRoom(), getId(), getSubjects(), getTrainees(), getSchool_id());
    }
}
