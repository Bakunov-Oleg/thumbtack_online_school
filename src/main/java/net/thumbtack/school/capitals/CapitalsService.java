package net.thumbtack.school.capitals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CapitalsService {
    private static Connection connection;
    private static ResultSet resultSet;

    public CapitalsService() {

    }


    public List<String> getCapitals() throws SQLException {
        connection = new jdbcUtilsCapitals().getConnection();
        List<String> capitalsList = new ArrayList<>();
        String selectQuery = "SELECT * FROM capitalcity";
        try (PreparedStatement prepareStatement = connection.prepareStatement(selectQuery)) {
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                capitalsList.add(name);
            }
            return capitalsList;
        }
    }

    public Map<String, String> downloadPage(String cityName) throws IOException {
        String name = "";
        String currencies = "";

        Map<String, String> response = new HashMap<>();

        URL url = new URL("http://restcountries.eu/rest/v2/capital/" + cityName);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        try (InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
             BufferedReader buff = new BufferedReader(in)) {
            String line = buff.readLine();

            int indexOfStartName = line.indexOf("name\":\"") + 6;
            while (line.charAt(indexOfStartName + 1) != '"') {
                name += line.charAt(indexOfStartName + 1);
                indexOfStartName++;
            }

            int indexOfStartCurrencies = line.indexOf("currencies\":[{\"code\":\"") + 21;
            while (line.charAt(indexOfStartCurrencies + 1) != '"') {
                currencies += line.charAt(indexOfStartCurrencies + 1);
                indexOfStartCurrencies++;
            }
            response.put("name", name);
            response.put("currencies", currencies);
        }
        return response;
    }


}
