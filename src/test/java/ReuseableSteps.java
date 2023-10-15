import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReuseableSteps implements ElementsHelper{

    private WebDriver driver;

    @BeforeScenario
    public void getAll(){
        this.driver = DriverSettings.driver;
    }

    @Step("denemeXkkk")
    public void _01(){

        driver.findElement(By.id("APjFqb")).sendKeys("dasdasdasd");
        findElementWithKey("ahmet").click();

    }



}
