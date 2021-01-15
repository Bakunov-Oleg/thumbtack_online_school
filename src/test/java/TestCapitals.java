import net.thumbtack.school.capitals.CapitalsService;
import net.thumbtack.school.capitals.jdbcUtilsCapitals;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({jdbcUtilsCapitals.class, CapitalsService.class})

public class TestCapitals {

    private static final String createTablesSQL =
            "CREATE TABLE `capitalcity`( \n" +
                    "`id` INT(11)NOT NULL AUTO_INCREMENT, \n" +
                    "`name` VARCHAR(50)NOT NULL, \n" +
                    "UNIQUE KEY capitalcity(`name`), \n" +
                    "PRIMARY KEY(`id`))\n";


    private static JdbcDataSource ds = new JdbcDataSource();
    private static Connection connection;

    @BeforeClass()
    public static void setUp() throws SQLException {
        ds.setURL("jdbc:h2:mem:~/capitals");
        ds.setUser("test");
        ds.setPassword("test");
        connection = ds.getConnection();
        try (PreparedStatement stmt = connection.prepareStatement(createTablesSQL)) {
            stmt.executeUpdate();
        }
    }


    @Test
    public void testGetCapitals() throws Exception {
        CapitalsService capServ = new CapitalsService();

        PowerMockito.mockStatic(jdbcUtilsCapitals.class);
        when(jdbcUtilsCapitals.getConnection()).thenReturn(connection);

        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO capitalcity(`name`)\n" +
                "VALUES ('Yerevan')")) {
            prepareStatement.executeUpdate();
        }

        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO capitalcity(`name`)\n" +
                "VALUES ('Brussels')")) {
            prepareStatement.executeUpdate();
        }

        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO capitalcity(`name`)\n" +
                "VALUES ('Gaborone')")) {
            prepareStatement.executeUpdate();
        }

        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO capitalcity(`name`)\n" +
                "VALUES ('Brasilia')")) {
            prepareStatement.executeUpdate();
        }

        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO capitalcity(`name`)\n" +
                "VALUES ('Ottawa')")) {
            prepareStatement.executeUpdate();
        }

        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO capitalcity(`name`)\n" +
                "VALUES ('Bratislava')")) {
            prepareStatement.executeUpdate();
        }

        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO capitalcity(`name`)\n" +
                "VALUES ('Ljubljana')")) {
            prepareStatement.executeUpdate();
        }

        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO capitalcity(`name`)\n" +
                "VALUES ('Warsaw')")) {
            prepareStatement.executeUpdate();
        }

        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO capitalcity(`name`)\n" +
                "VALUES ('Moscow')")) {
            prepareStatement.executeUpdate();
        }


        List<String> capital = new ArrayList<>();
        List<String> capitalsList = capServ.getCapitals();

        InputStream stream1 = new ByteArrayInputStream(
                "[{\"name\":\"Armenia\",\"topLevelDomain\":[\".am\"],\"alpha2Code\":\"AM\",\"alpha3Code\":\"ARM\",\"callingCodes\":[\"374\"],\"capital\":\"Yerevan\",\"altSpellings\":[\"AM\",\"Hayastan\",\"Republic of Armenia\",\"Հայաստանի Հանրապետություն\"],\"region\":\"Asia\",\"subregion\":\"Western Asia\",\"population\":2994400,\"latlng\":[40.0,45.0],\"demonym\":\"Armenian\",\"area\":29743.0,\"gini\":30.9,\"timezones\":[\"UTC+04:00\"],\"borders\":[\"AZE\",\"GEO\",\"IRN\",\"TUR\"],\"nativeName\":\"Հայաստան\",\"numericCode\":\"051\",\"currencies\":[{\"code\":\"AMD\",\"name\":\"Armenian dram\",\"symbol\":null}],\"languages\":[{\"iso639_1\":\"hy\",\"iso639_2\":\"hye\",\"name\":\"Armenian\",\"nativeName\":\"Հայերեն\"},{\"iso639_1\":\"ru\",\"iso639_2\":\"rus\",\"name\":\"Russian\",\"nativeName\":\"Русский\"}],\"translations\":{\"de\":\"Armenien\",\"es\":\"Armenia\",\"fr\":\"Arménie\",\"ja\":\"アルメニア\",\"it\":\"Armenia\",\"br\":\"Armênia\",\"pt\":\"Arménia\",\"nl\":\"Armenië\",\"hr\":\"Armenija\",\"fa\":\"ارمنستان\"},\"flag\":\"https://restcountries.eu/data/arm.svg\",\"regionalBlocs\":[{\"acronym\":\"EEU\",\"name\":\"Eurasian Economic Union\",\"otherAcronyms\":[\"EAEU\"],\"otherNames\":[]}],\"cioc\":\"ARM\"}]"
                        .getBytes()
        );


        InputStream stream2 = new ByteArrayInputStream(
                "[{\"name\":\"Belgium\",\"topLevelDomain\":[\".be\"],\"alpha2Code\":\"BE\",\"alpha3Code\":\"BEL\",\"callingCodes\":[\"32\"],\"capital\":\"Brussels\",\"altSpellings\":[\"BE\",\"België\",\"Belgie\",\"Belgien\",\"Belgique\",\"Kingdom of Belgium\",\"Koninkrijk België\",\"Royaume de Belgique\",\"Königreich Belgien\"],\"region\":\"Europe\",\"subregion\":\"Western Europe\",\"population\":11319511,\"latlng\":[50.83333333,4.0],\"demonym\":\"Belgian\",\"area\":30528.0,\"gini\":33.0,\"timezones\":[\"UTC+01:00\"],\"borders\":[\"FRA\",\"DEU\",\"LUX\",\"NLD\"],\"nativeName\":\"België\",\"numericCode\":\"056\",\"currencies\":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}],\"languages\":[{\"iso639_1\":\"nl\",\"iso639_2\":\"nld\",\"name\":\"Dutch\",\"nativeName\":\"Nederlands\"},{\"iso639_1\":\"fr\",\"iso639_2\":\"fra\",\"name\":\"French\",\"nativeName\":\"français\"},{\"iso639_1\":\"de\",\"iso639_2\":\"deu\",\"name\":\"German\",\"nativeName\":\"Deutsch\"}],\"translations\":{\"de\":\"Belgien\",\"es\":\"Bélgica\",\"fr\":\"Belgique\",\"ja\":\"ベルギー\",\"it\":\"Belgio\",\"br\":\"Bélgica\",\"pt\":\"Bélgica\",\"nl\":\"België\",\"hr\":\"Belgija\",\"fa\":\"بلژیک\"},\"flag\":\"https://restcountries.eu/data/bel.svg\",\"regionalBlocs\":[{\"acronym\":\"EU\",\"name\":\"European Union\",\"otherAcronyms\":[],\"otherNames\":[]}],\"cioc\":\"BEL\"}]"
                        .getBytes()
        );


        InputStream stream3 = new ByteArrayInputStream(
                "[{\"name\":\"Botswana\",\"topLevelDomain\":[\".bw\"],\"alpha2Code\":\"BW\",\"alpha3Code\":\"BWA\",\"callingCodes\":[\"267\"],\"capital\":\"Gaborone\",\"altSpellings\":[\"BW\",\"Republic of Botswana\",\"Lefatshe la Botswana\"],\"region\":\"Africa\",\"subregion\":\"Southern Africa\",\"population\":2141206,\"latlng\":[-22.0,24.0],\"demonym\":\"Motswana\",\"area\":582000.0,\"gini\":61.0,\"timezones\":[\"UTC+02:00\"],\"borders\":[\"NAM\",\"ZAF\",\"ZMB\",\"ZWE\"],\"nativeName\":\"Botswana\",\"numericCode\":\"072\",\"currencies\":[{\"code\":\"BWP\",\"name\":\"Botswana pula\",\"symbol\":\"P\"}],\"languages\":[{\"iso639_1\":\"en\",\"iso639_2\":\"eng\",\"name\":\"English\",\"nativeName\":\"English\"},{\"iso639_1\":\"tn\",\"iso639_2\":\"tsn\",\"name\":\"Tswana\",\"nativeName\":\"Setswana\"}],\"translations\":{\"de\":\"Botswana\",\"es\":\"Botswana\",\"fr\":\"Botswana\",\"ja\":\"ボツワナ\",\"it\":\"Botswana\",\"br\":\"Botsuana\",\"pt\":\"Botsuana\",\"nl\":\"Botswana\",\"hr\":\"Bocvana\",\"fa\":\"بوتسوانا\"},\"flag\":\"https://restcountries.eu/data/bwa.svg\",\"regionalBlocs\":[{\"acronym\":\"AU\",\"name\":\"African Union\",\"otherAcronyms\":[],\"otherNames\":[\"الاتحاد الأفريقي\",\"Union africaine\",\"União Africana\",\"Unión Africana\",\"Umoja wa Afrika\"]}],\"cioc\":\"BOT\"}]"
                        .getBytes()
        );

        HttpURLConnection http = mock(HttpURLConnection.class);
        Mockito.when(http.getContent()).thenReturn(stream1);
        URL url = mock(URL.class);
        Mockito.when(url.openConnection()).thenReturn(http);
        PowerMockito.whenNew(URL.class).withAnyArguments().thenReturn(url);

        Map<String, String> response1 = new CapitalsService().downloadPage("Yerevan");

        Mockito.when(http.getContent()).thenReturn(stream2);
        Map<String, String> response2 = new CapitalsService().downloadPage("Brussels");

        Mockito.when(http.getContent()).thenReturn(stream3);
        Map<String, String> response3 = new CapitalsService().downloadPage("Gaborone");

        capital.add("Yerevan");
        capital.add("Brussels");
        capital.add("Gaborone");
        capital.add("Brasilia");
        capital.add("Ottawa");
        capital.add("Bratislava");
        capital.add("Ljubljana");
        capital.add("Warsaw");
        capital.add("Moscow");

        assertAll(
                () -> Assert.assertTrue(capital.containsAll(capitalsList)),
                () -> assertEquals(response1.get("name"), "Armenia"),
                () -> assertEquals(response1.get("currencies"), "AMD"),
                () -> assertEquals(response2.get("name"), "Belgium"),
                () -> assertEquals(response2.get("currencies"), "EUR"),
                () -> assertEquals(response3.get("name"), "Botswana"),
                () -> assertEquals(response3.get("currencies"), "BWP")
        );


    }

    @PrepareForTest(CapitalsService.class)
    @Test
    public void testUrl() throws Exception {
        HttpURLConnection http = mock(HttpURLConnection.class);
        URL url = mock(URL.class);
        when(url.openConnection()).thenReturn(http);
        PowerMockito.whenNew(URL.class).withAnyArguments().thenReturn(url);


        try {
            new CapitalsService().downloadPage("Yerevan");
        } catch (NullPointerException e) {
        }

        PowerMockito.verifyNew(URL.class).withArguments("http://restcountries.eu/rest/v2/capital/Yerevan");
    }

    @PrepareForTest(CapitalsService.class)
    @Test
    public void testDownloadPage() throws Exception {

        InputStream stream1 = new ByteArrayInputStream(
                "[{\"name\":\"Armenia\",\"topLevelDomain\":[\".am\"],\"alpha2Code\":\"AM\",\"alpha3Code\":\"ARM\",\"callingCodes\":[\"374\"],\"capital\":\"Yerevan\",\"altSpellings\":[\"AM\",\"Hayastan\",\"Republic of Armenia\",\"Հայաստանի Հանրապետություն\"],\"region\":\"Asia\",\"subregion\":\"Western Asia\",\"population\":2994400,\"latlng\":[40.0,45.0],\"demonym\":\"Armenian\",\"area\":29743.0,\"gini\":30.9,\"timezones\":[\"UTC+04:00\"],\"borders\":[\"AZE\",\"GEO\",\"IRN\",\"TUR\"],\"nativeName\":\"Հայաստան\",\"numericCode\":\"051\",\"currencies\":[{\"code\":\"AMD\",\"name\":\"Armenian dram\",\"symbol\":null}],\"languages\":[{\"iso639_1\":\"hy\",\"iso639_2\":\"hye\",\"name\":\"Armenian\",\"nativeName\":\"Հայերեն\"},{\"iso639_1\":\"ru\",\"iso639_2\":\"rus\",\"name\":\"Russian\",\"nativeName\":\"Русский\"}],\"translations\":{\"de\":\"Armenien\",\"es\":\"Armenia\",\"fr\":\"Arménie\",\"ja\":\"アルメニア\",\"it\":\"Armenia\",\"br\":\"Armênia\",\"pt\":\"Arménia\",\"nl\":\"Armenië\",\"hr\":\"Armenija\",\"fa\":\"ارمنستان\"},\"flag\":\"https://restcountries.eu/data/arm.svg\",\"regionalBlocs\":[{\"acronym\":\"EEU\",\"name\":\"Eurasian Economic Union\",\"otherAcronyms\":[\"EAEU\"],\"otherNames\":[]}],\"cioc\":\"ARM\"}]"
                        .getBytes()
        );


        InputStream stream2 = new ByteArrayInputStream(
                "[{\"name\":\"Belgium\",\"topLevelDomain\":[\".be\"],\"alpha2Code\":\"BE\",\"alpha3Code\":\"BEL\",\"callingCodes\":[\"32\"],\"capital\":\"Brussels\",\"altSpellings\":[\"BE\",\"België\",\"Belgie\",\"Belgien\",\"Belgique\",\"Kingdom of Belgium\",\"Koninkrijk België\",\"Royaume de Belgique\",\"Königreich Belgien\"],\"region\":\"Europe\",\"subregion\":\"Western Europe\",\"population\":11319511,\"latlng\":[50.83333333,4.0],\"demonym\":\"Belgian\",\"area\":30528.0,\"gini\":33.0,\"timezones\":[\"UTC+01:00\"],\"borders\":[\"FRA\",\"DEU\",\"LUX\",\"NLD\"],\"nativeName\":\"België\",\"numericCode\":\"056\",\"currencies\":[{\"code\":\"EUR\",\"name\":\"Euro\",\"symbol\":\"€\"}],\"languages\":[{\"iso639_1\":\"nl\",\"iso639_2\":\"nld\",\"name\":\"Dutch\",\"nativeName\":\"Nederlands\"},{\"iso639_1\":\"fr\",\"iso639_2\":\"fra\",\"name\":\"French\",\"nativeName\":\"français\"},{\"iso639_1\":\"de\",\"iso639_2\":\"deu\",\"name\":\"German\",\"nativeName\":\"Deutsch\"}],\"translations\":{\"de\":\"Belgien\",\"es\":\"Bélgica\",\"fr\":\"Belgique\",\"ja\":\"ベルギー\",\"it\":\"Belgio\",\"br\":\"Bélgica\",\"pt\":\"Bélgica\",\"nl\":\"België\",\"hr\":\"Belgija\",\"fa\":\"بلژیک\"},\"flag\":\"https://restcountries.eu/data/bel.svg\",\"regionalBlocs\":[{\"acronym\":\"EU\",\"name\":\"European Union\",\"otherAcronyms\":[],\"otherNames\":[]}],\"cioc\":\"BEL\"}]"
                        .getBytes()
        );


        InputStream stream3 = new ByteArrayInputStream(
                "[{\"name\":\"Botswana\",\"topLevelDomain\":[\".bw\"],\"alpha2Code\":\"BW\",\"alpha3Code\":\"BWA\",\"callingCodes\":[\"267\"],\"capital\":\"Gaborone\",\"altSpellings\":[\"BW\",\"Republic of Botswana\",\"Lefatshe la Botswana\"],\"region\":\"Africa\",\"subregion\":\"Southern Africa\",\"population\":2141206,\"latlng\":[-22.0,24.0],\"demonym\":\"Motswana\",\"area\":582000.0,\"gini\":61.0,\"timezones\":[\"UTC+02:00\"],\"borders\":[\"NAM\",\"ZAF\",\"ZMB\",\"ZWE\"],\"nativeName\":\"Botswana\",\"numericCode\":\"072\",\"currencies\":[{\"code\":\"BWP\",\"name\":\"Botswana pula\",\"symbol\":\"P\"}],\"languages\":[{\"iso639_1\":\"en\",\"iso639_2\":\"eng\",\"name\":\"English\",\"nativeName\":\"English\"},{\"iso639_1\":\"tn\",\"iso639_2\":\"tsn\",\"name\":\"Tswana\",\"nativeName\":\"Setswana\"}],\"translations\":{\"de\":\"Botswana\",\"es\":\"Botswana\",\"fr\":\"Botswana\",\"ja\":\"ボツワナ\",\"it\":\"Botswana\",\"br\":\"Botsuana\",\"pt\":\"Botsuana\",\"nl\":\"Botswana\",\"hr\":\"Bocvana\",\"fa\":\"بوتسوانا\"},\"flag\":\"https://restcountries.eu/data/bwa.svg\",\"regionalBlocs\":[{\"acronym\":\"AU\",\"name\":\"African Union\",\"otherAcronyms\":[],\"otherNames\":[\"الاتحاد الأفريقي\",\"Union africaine\",\"União Africana\",\"Unión Africana\",\"Umoja wa Afrika\"]}],\"cioc\":\"BOT\"}]"
                        .getBytes()
        );

        HttpURLConnection http = mock(HttpURLConnection.class);
        Mockito.when(http.getContent()).thenReturn(stream1);
        URL url = mock(URL.class);
        Mockito.when(url.openConnection()).thenReturn(http);
        PowerMockito.whenNew(URL.class).withAnyArguments().thenReturn(url);

        Map<String, String> result = new CapitalsService().downloadPage("Yerevan");
        assertAll(
                () -> assertEquals(result.get("name"), "Armenia"),
                () -> assertEquals(result.get("currencies"), "AMD")
        );
    }


}
