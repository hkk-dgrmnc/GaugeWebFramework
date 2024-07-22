import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Objects;


public interface ElementsHelper {

    ElementsReader elementTypeAndValue = new ElementsReader();
  //  WebDriverWait wait = new WebDriverWait(DriverSettings.driver, Duration.ofSeconds(Integer.parseInt(DriverSettings.config.getProperty("explicitWait"))));
    FluentWait<WebDriver> wait = new FluentWait<>(DriverSettings.driver)
            .withTimeout(Duration.ofSeconds(Integer.parseInt(DriverSettings.config.getProperty("fluentMaxWait"))))
            .pollingEvery(Duration.ofMillis(Integer.parseInt(DriverSettings.config.getProperty("pollingWait"))))
            .ignoring(NoSuchElementException.class);


    default WebElement findElementWithKey(String key){

        WebElement webElement;

        if(Objects.equals(elementTypeAndValue.getLocatorType(key), "id")){
            webElement = DriverSettings.driver.findElement(By.id(elementTypeAndValue.getLocatorValue(key)));
        }
        else if (Objects.equals(elementTypeAndValue.getLocatorType(key), "css")) {
            webElement = DriverSettings.driver.findElement(By.cssSelector(elementTypeAndValue.getLocatorValue(key)));
        }
        else if (Objects.equals(elementTypeAndValue.getLocatorType(key), "xpath")) {
            webElement = DriverSettings.driver.findElement(By.xpath(elementTypeAndValue.getLocatorValue(key)));
        }
        else{
            throw new AssertionError("elements.properties dosyasında geçersiz locator type => " + key + ": " + elementTypeAndValue.getLocatorType(key));
        }
        return webElement;
    }

    default WebElement findVisibleElementWithKey(String key){

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
            throw new AssertionError("elements.properties dosyasında geçersiz locator type => " + key + ": " + elementTypeAndValue.getLocatorType(key));
        }
        return webElement;
    }

    default WebElement findPresenceElementWithKey(String key){

        WebElement webElement;

        if(Objects.equals(elementTypeAndValue.getLocatorType(key), "id")){
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementTypeAndValue.getLocatorValue(key))));
        }
        else if (Objects.equals(elementTypeAndValue.getLocatorType(key), "css")) {
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elementTypeAndValue.getLocatorValue(key))));
        }
        else if (Objects.equals(elementTypeAndValue.getLocatorType(key), "xpath")) {
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementTypeAndValue.getLocatorValue(key))));
        }
        else{
            throw new AssertionError("elements.properties dosyasında geçersiz locator type => " + key + ": " + elementTypeAndValue.getLocatorType(key));
        }
        return webElement;
    }

    default List<WebElement> findElementsWithKey(String key){

        List<WebElement> webElements;

        if (Objects.equals(elementTypeAndValue.getLocatorType(key), "css")) {
            webElements = DriverSettings.driver.findElements(By.cssSelector(elementTypeAndValue.getLocatorValue(key)));
        }
        else if (Objects.equals(elementTypeAndValue.getLocatorType(key), "xpath")) {
            webElements = DriverSettings.driver.findElements(By.xpath(elementTypeAndValue.getLocatorValue(key)));
        }
        else{
            throw new AssertionError("settings.yaml dosyasında geçersiz driverType türü => " + key + ": " + elementTypeAndValue.getLocatorType(key));
        }
        return webElements;
    }

    default WebElement findPresenceElementWithTextXpath(String text){

        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()="+"'"+text+"'"+"]")));
    }

    default WebElement findVisibleElementWithTextXpath(String text){

        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()="+"'"+text+"'"+"]")));
    }

    default WebElement findVisibleElementWithContainTextXpath(String text){

        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),"+"'"+text+"')"+"]")));
    }

    default WebElement findPresenceElementWithContainTextXpath(String text){

        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),"+"'"+text+"')"+"]")));
    }

    default void findElementWithKeyAndClickJS(String key) {
        WebElement element = findElementWithKey(key);
        ((JavascriptExecutor) DriverSettings.driver).executeScript("arguments[0].click();", element);
    }

    default void findVisibleElementWithKeyAndClickJS(String key) {
        WebElement element = findVisibleElementWithKey(key);
        ((JavascriptExecutor) DriverSettings.driver).executeScript("arguments[0].click();", element);
    }

    default void findPresenceElementWithKeyAndClickJS(String key) {
        WebElement element = findPresenceElementWithKey(key);
        ((JavascriptExecutor) DriverSettings.driver).executeScript("arguments[0].click();", element);
    }

    default void scrollAllTheWayDownJS() {
        JavascriptExecutor js = (JavascriptExecutor) DriverSettings.driver;
        boolean check = true;
        int time = 0;
        while(check) {
            js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
            Long scrollHeight = (Long) js.executeScript("return document.body.scrollHeight;");
            Long scrollPosition = (Long) js.executeScript("return window.pageYOffset + window.innerHeight;");
            time++;
            if(scrollHeight.equals(scrollPosition) || time >= 3){
                check = false;
            }
        }
    }
    default void scrollAllTheWayUpJS() {
        JavascriptExecutor js = (JavascriptExecutor) DriverSettings.driver;
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }

    default void scrollIntoElementJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverSettings.driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    default void sendKeysWithJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) DriverSettings.driver;
        js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
    }











}


