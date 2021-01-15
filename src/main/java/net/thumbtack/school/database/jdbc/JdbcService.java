package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcService {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void insertTrainee(Trainee trainee) throws SQLException {
        String insertQuery = "insert into trainee values(?, ?, ?, ?, ?)";

        try (PreparedStatement prepareStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setNull(1, Types.INTEGER);
            prepareStatement.setString(2, trainee.getFirstName());
            prepareStatement.setString(3, trainee.getLastName());
            prepareStatement.setInt(4, trainee.getRating());
            prepareStatement.setNull(5, Types.INTEGER);
            prepareStatement.executeUpdate();
            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    trainee.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static void updateTrainee(Trainee trainee) throws SQLException {
        String updateQuery = "update trainee set firstname = ?, lastname = ?, rating = ? where id = ?";
        try (PreparedStatement prepareStatement = connection.prepareStatement(updateQuery)) {
            prepareStatement.setString(1, trainee.getFirstName());
            prepareStatement.setString(2, trainee.getLastName());
            prepareStatement.setInt(3, trainee.getRating());
            prepareStatement.setInt(4, trainee.getId());
            prepareStatement.executeUpdate();
        }
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        String selectQuery = "select * from trainee where id = " + traineeId;
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                int rating = resultSet.getInt("rating");
                return new Trainee(id, firstName, lastName, rating);
            }
            return null;
        }
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        String selectQuery = "select * from trainee where id = " + traineeId;
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int rating = resultSet.getInt(4);
                return new Trainee(id, firstName, lastName, rating);
            }
            return null;
        }
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        String selectQuery = "SELECT * FROM ttschool.trainee";
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                int rating = resultSet.getInt("rating");
                trainees.add(new Trainee(id, firstName, lastName, rating));
            }
            return trainees;
        }
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        List<Trainee> trainees = new ArrayList<>();
        String selectQuery = "SELECT * FROM ttschool.trainee";
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                int rating = resultSet.getInt(4);
                trainees.add(new Trainee(id, firstName, lastName, rating));
            }
            return trainees;
        }
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        String selectQuery = "delete from trainee where id =  ?";
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            prepareStatement.setInt(1, trainee.getId());
            prepareStatement.executeUpdate();
        }
    }

    public static void deleteTrainees() throws SQLException {
        String delQuery = "DELETE FROM ttschool.trainee";
        connection = new JdbcUtils().getConnection();
        statement = connection.createStatement();
        try (PreparedStatement prepareStatement = connection.prepareStatement(delQuery)) {
            prepareStatement.executeUpdate();
        }
    }

    public static void insertSubject(Subject subject) throws SQLException {
        String insertQuery = "insert into subject values(?, ?)";

        try (PreparedStatement prepareStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setNull(1, Types.INTEGER);
            prepareStatement.setString(2, subject.getName());
            prepareStatement.executeUpdate();
            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subject.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        String selectQuery = "select * from subject where id = " + subjectId;
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new Subject(id, name);
            }
            return null;
        }
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        String selectQuery = "select * from subject where id = " + subjectId;
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                return new Subject(id, name);
            }
            return null;
        }
    }

    public static void deleteSubjects() throws SQLException {
        //Удаляет все Subject из базы данных.
        String selectQuery = "DELETE FROM ttschool.subject";
        connection = new JdbcUtils().getConnection();
        statement = connection.createStatement();
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            prepareStatement.executeUpdate();
        }
    }

    public static void insertSchool(School school) throws SQLException {
        String insertQuery = "insert into school values(?, ?, ?)";

        try (PreparedStatement prepareStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setNull(1, Types.INTEGER);
            prepareStatement.setString(2, school.getName());
            prepareStatement.setInt(3, school.getYear());
            prepareStatement.executeUpdate();
            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    school.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        String selectQuery = "select * from school where id = " + schoolId;
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int year = resultSet.getInt("year");
                return new School(id, name, year);
            }
            return null;
        }
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        String selectQuery = "select * from school where id = " + schoolId;
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int year = resultSet.getInt(3);
                return new School(id, name, year);
            }
            return null;
        }
    }

    public static void deleteSchools() throws SQLException {
        //Удаляет все School из базы данных. Если список Group в School не пуст, удаляет все Group для каждой School.
        String selectQuery = "DELETE FROM ttschool.school";
        connection = new JdbcUtils().getConnection();
        statement = connection.createStatement();
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            prepareStatement.executeUpdate();
        }
        selectQuery = "DELETE FROM ttschool.`group`";
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            prepareStatement.executeUpdate();
        }
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        //Добавляет Group в базу данных, устанавливая ее принадлежность к школе School.
        String insertQuery = "insert into ttschool.`group` values(?, ?, ?, ?)";

        try (PreparedStatement prepareStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setNull(1, Types.INTEGER);
            prepareStatement.setString(2, group.getName());
            prepareStatement.setString(3, group.getRoom());
            prepareStatement.setInt(4, school.getId());
            prepareStatement.executeUpdate();
            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    group.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        //Получает School по ее ID вместе со всеми ее Group из базы данных. Если School с таким ID нет, возвращает null. Метод получения (по именам или номерам полей) - на Ваше усмотрение.
        School school = new School(0, null, 0);
        String selectQuery = "SELECT ttschool.`school`.*, ttschool.`group`.* FROM school INNER JOIN ttschool.`group` where school_id = " + id;

        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                if (school.getId() != id) {
                    school.setId(resultSet.getInt(1));
                    school.setName(resultSet.getString(2));
                    school.setYear(resultSet.getInt(3));
                }
                school.addGroup(new Group(resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6)));
            }
            return school;
        }
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        //Получает список всех School вместе со всеми их Group из базы данных. Если ни одной  School в БД нет,  возвращает пустой список. Метод получения (по именам или номерам полей) - на Ваше усмотрение.
        List<School> schools = new ArrayList<>();
        Map<Integer, School> schoolById = new HashMap<>();
        School school;
        String selectQuery = "SELECT ttschool.`school`.*, ttschool.`group`.* FROM school INNER JOIN ttschool.`group`";

        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                if (schoolById.get(resultSet.getInt(1)) == null) {
                    school = new School(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                    schoolById.put(resultSet.getInt(1), school);
                }
                if (resultSet.getInt(1) == resultSet.getInt(7)) {
                    schoolById.get(resultSet.getInt(1)).addGroup(new Group(resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6)));
                }
            }
        }
        schools.addAll(schoolById.values());
        return schools;
    }

}
