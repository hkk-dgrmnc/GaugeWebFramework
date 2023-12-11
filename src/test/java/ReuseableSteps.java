import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ReuseableSteps implements ElementsHelper{

    private WebDriver driver;
    private ValueReader valueReader;

    @BeforeScenario
    public void getAll(){
        this.valueReader = DriverSettings.valueReader;
        this.driver = DriverSettings.driver;
    }

    @Step("Find element with key:<key> and click.")
    public void _01(String key){
        findElementWithKey(key).click();
    }

    @Step("Find element with key:<key> and send text:<text>.")
    public void _02(String key,String text){
        String endText= text;
        if (text.startsWith("Value_")) {
           endText= valueReader.getProperty(text);
        }
        findElementWithKey(key).sendKeys(endText);
    }

    @Step("Find element with key:<key> and verify is displayed.")
    public void _03(String key) {
        try {findElementWithKey(key);}
        catch (Exception e) { throw new AssertionError("Element is not displayed ! " + key + "= " + elementTypeAndValue.getLocatorType(key)+":"+elementTypeAndValue.getLocatorValue(key)); }
    }

}
