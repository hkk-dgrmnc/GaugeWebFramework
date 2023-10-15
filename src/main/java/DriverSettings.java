import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

public class DriverSettings {

    public static WebDriver driver;
    public static ConfigReader config;
    public static ElementsReader elementsReader;


    @BeforeSuite
    public static void setUp() throws IOException {

        config = new ConfigReader("src/main/resources/settings.yaml");
        elementsReader = new ElementsReader("src/main/resources/elements.properties");

        if(Objects.equals(config.getProperty("browserType"), "Chrome")){
            driver = new ChromeDriver();
        }
        else if (Objects.equals(config.getProperty("browserType"), "Firefox")) {
            driver = new FirefoxDriver();
        }
        else{
            throw new IllegalArgumentException("settings.yaml dosyasında geçersiz driverType türü: ");
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(config.getProperty("pageLoadTimeout"))));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicitlyWait"))));
        driver.get(config.getProperty("url"));
    }

    @AfterSuite
    public static void tearDown() {
        driver.quit();
    }


}
