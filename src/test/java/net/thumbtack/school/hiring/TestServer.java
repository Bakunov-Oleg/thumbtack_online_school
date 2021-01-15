package net.thumbtack.school.hiring;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.request.employee.RegisterEmployeeDtoRequest;
import net.thumbtack.school.hiring.dto.response.employee.RegisterEmployeeDtoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

public class TestServer {

    @TempDir
    public Path tempDir;

    @Test
    public void startServerNoBackUp() {
        Server server = new Server();
        server.startServer(null);
    }

    @Test
    public void stopServerNoBackUp()  {
        Server server = new Server();
        try {
            server.stopServer(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void startServerOnBackUp()  {
//        //String fileName = tempDir.resolve("database.dat").toString();
//        String fileName = "C:\\database\\database.dat";
//        Server server = new Server();
//        server.startServer(fileName);
//        System.out.println("xxz");
//    }
//
//    @Test
//    public void stopServerOnBackUp() throws ServerException {
//        //String fileName = tempDir.resolve("database.dat").toString();
//        String fileName = "C:\\database\\database.dat";
//        Server server = new Server();
//
//        server.startServer(null);
//
//        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Sartov", "Igor", "Ivanovich", "8858@mail.ru", "Swer1", "zawderq112");
//        RegisterEmployeeDtoResponse resultResponce = new Gson().fromJson(server.registerEmployee(new Gson().toJson(employee1)), RegisterEmployeeDtoResponse.class);
//
//        try {
//            server.stopServer(fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("sasd");
//    }

}
