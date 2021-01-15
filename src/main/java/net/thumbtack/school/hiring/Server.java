package net.thumbtack.school.hiring;

import net.thumbtack.school.hiring.database.Database;
import net.thumbtack.school.hiring.service.EmployeeService;
import net.thumbtack.school.hiring.service.EmployerService;

import java.io.IOException;

public class Server {
    private Database database;
    private EmployeeService employeeService;
    private EmployerService employerService;

    public void startServer(String savedDataFileName) {
        //Производит всю необходимую инициализацию и запускает сервер.
        //savedDataFileName - имя файла, в котором было сохранено состояние сервера.  Если savedDataFileName == null, восстановление состояния не производится, сервер стартует “с нуля”.

        employeeService = new EmployeeService();
        employerService = new EmployerService();

        if (savedDataFileName != null) {

//            try (ObjectInputStream pObj = new ObjectInputStream(new FileInputStream(savedDataFileName))) {
//                database = (Database) pObj.readObject();
//                System.out.println("Sda");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


        } else {
            database = Database.getDatabase();
        }

    }

    public void stopServer(String savedDataFileName) throws IOException {
        //Останавливает сервер и записывает все его содержимое в файл сохранения с именем savedDataFileName. Если savedDataFileName == null, запись содержимого не производится.
        if (savedDataFileName != null) {
//            try (ObjectOutputStream pObj = new ObjectOutputStream(new FileOutputStream(savedDataFileName))) {
//                pObj.writeObject(Database.getDatabase());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } else {
            System.out.println("server off");
        }

    }

    public String registerEmployee(String stringJson) throws ServerException {
        return employeeService.registerEmployee(stringJson);
    }

    public String addSkillsEmployee(String stringJson) throws ServerException {
        return employeeService.addEmployeeSkills(stringJson);
    }

    public String deleteSkillsEmployee(String stringJson) throws ServerException {
        return employeeService.deleteEmployeeSkills(stringJson);
    }

    public String changeLevelSkillsEmployee(String stringJson) throws ServerException {
        return employeeService.changeEmployeeSkillsLevel(stringJson);
    }

    public String setProfileActiveEmployee(String stringJson) throws ServerException {
        return employeeService.setActiveProfile(stringJson);
    }

    public String setProfileNonActiveEmployee(String stringJson) throws ServerException {
        return employeeService.setNonActiveProfile(stringJson);
    }

    public String changeDataEmployee(String stringJson) throws ServerException {
        return employeeService.changeEmployeeData(stringJson);
    }

    public String getVacanciesSkillsAtRequirementLevel(String stringJson) throws ServerException {
        return employeeService.getVacanciesSkillsAtRequirementLevel(stringJson);
    }

    public String getVacanciesRequiredSkillsNotLowerRequiredLevel(String stringJson) throws ServerException {
        return employeeService.getVacanciesRequiredSkillsNotLowerRequiredLevel(stringJson);
    }

    public String getVacanciesAnyoneSkillsAnyLevel(String stringJson) throws ServerException {
        return employeeService.getVacanciesAnyoneSkillsAnyLevel(stringJson);
    }

    public String getVacanciesSortedByNumberOfSkills(String stringJson) throws ServerException {
        return employeeService.getVacanciesSortedByNumberOfSkills(stringJson);
    }

    public String registerEmployer(String stringJson) throws ServerException {
        return employerService.registerEmployer(stringJson);
    }

    public String changeDataEmployer(String stringJson) throws ServerException {
        return employerService.changeDataEmployer(stringJson);
    }

    public String addVacancy(String stringJson) throws ServerException {
        return employerService.addVacancy(stringJson);
    }

    public String delVacancy(String stringJson) throws ServerException {
        return employerService.delVacancy(stringJson);
    }

    public String addVacancySkill(String stringJson) throws ServerException {
        return employerService.addVacancySkill(stringJson);
    }

    public String removeVacancySkill(String stringJson) throws ServerException {
        return employerService.removeVacancySkill(stringJson);
    }

    public String changeVacancySkill(String stringJson) throws ServerException {
        return employerService.changeVacancySkill(stringJson);
    }

    public String acceptEmployeeToJob(String stringJson) throws ServerException {
        return employerService.acceptEmployeeToJob(stringJson);
    }

    public String setVacancyActive(String stringJson) throws ServerException {
        return employerService.setVacancyActive(stringJson);
    }

    public String setVacancyNonActive(String stringJson) throws ServerException {
        return employerService.setVacancyNonActive(stringJson);
    }

    public String getVacancyActive(String stringJson) throws ServerException {
        return employerService.getVacancyActive(stringJson);
    }

    public String getVacancyNonActive(String stringJson) throws ServerException {
        return employerService.getVacancyNonActive(stringJson);
    }

    public String getVacancyAll(String stringJson) throws ServerException {
        return employerService.getVacancyAll(stringJson);
    }

    public String getEmployeesSkillsAtRequirementLevel(String stringJson) throws ServerException {
        return employerService.getEmployeesSkillsAtRequirementLevel(stringJson);
    }

    public String getEmployeesRequiredSkillsNotLowerRequiredLevel(String stringJson) throws ServerException {
        return employerService.getEmployeesRequiredSkillsNotLowerRequiredLevel(stringJson);
    }

    public String getEmployeesAnyoneSkillsAnyLevel(String stringJson) throws ServerException {
        return employerService.getEmployeesAnyoneSkillsAnyLevel(stringJson);
    }

    public String getEmployeesOneSkillPerLevelRequirement(String stringJson) throws ServerException {
        return employerService.getEmployeesOneSkillPerLevelRequirement(stringJson);
    }

}
