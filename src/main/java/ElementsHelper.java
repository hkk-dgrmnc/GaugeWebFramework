import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Objects;


public interface ElementsHelper {

    ElementsReader elementTypeAndValue = new ElementsReader();
    WebDriverWait wait = new WebDriverWait(DriverSettings.driver, Duration.ofSeconds(Integer.parseInt(DriverSettings.config.getProperty("explicitWait"))));




    default WebElement findElementWithKey(String key){

        WebElement webElement;

        if(Objects.equals(elementTypeAndValue.getLocatorType(key), "id")){
            webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementTypeAndValue.getLocatorValue(key))));
        }
        else if (Objects.equals(elementTypeAndValue.getLocatorType(key), "css")) {
            webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementTypeAndValue.getLocatorValue(key))));
        }
        else if (Objects.equals(elementTypeAndValue.getLocatorType(key), "xpath")) {
            webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementTypeAndValue.getLocatorValue(key))));
        }
        else{
            throw new IllegalArgumentException("elements.properties dosyasında geçersiz locator type türü => " + key + ": " + elementTypeAndValue.getLocatorType(key));
        }
        return webElement;
    }

    default List<WebElement> findElementsWithKey(String key){

        List<WebElement> webElements;

        if(Objects.equals(elementTypeAndValue.getLocatorType(key), "id")){
            webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(elementTypeAndValue.getLocatorValue(key))));
        }
        else if (Objects.equals(elementTypeAndValue.getLocatorType(key), "css")) {
            webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(elementTypeAndValue.getLocatorValue(key))));
        }
        else if (Objects.equals(elementTypeAndValue.getLocatorType(key), "xpath")) {
            webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(elementTypeAndValue.getLocatorValue(key))));
        }
        else{
            throw new IllegalArgumentException("settings.yaml dosyasında geçersiz driverType türü => " + key + ": " + elementTypeAndValue.getLocatorType(key));
        }
        return webElements;
    }

    default WebElement findElementWithTextXpath(String text){

        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()="+"'"+text+"'"+"]")));
    }

    default WebElement findElementWithContainTextXpath(String text){

        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),"+"'"+text+"')"+"]")));
    }


}


