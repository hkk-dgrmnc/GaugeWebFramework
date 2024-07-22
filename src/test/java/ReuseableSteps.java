import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ReuseableSteps implements ElementsHelper {

    private WebDriver driver;
    private ValueReader valueReader;

    @BeforeScenario
    public void getAll() {
        this.valueReader = DriverSettings.valueReader;
        this.driver = DriverSettings.driver;
    }

    @Step("Find element with key:<key> and click.")
    public void _findElementWithKeyClick(String key) {
        try {
            findElementWithKey(key).click();
        } catch (Exception e) {
            throw new AssertionError("Element could not be clicked ! " + key + "= " + elementTypeAndValue.getLocatorType(key) + ":" + elementTypeAndValue.getLocatorValue(key) + " ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find visible element with key:<key> and click.")
    public void _findVisibleElementWithKeyClick(String key) {
        try {
            findVisibleElementWithKey(key).click();
        } catch (Exception e) {
            throw new AssertionError("Element could not be clicked ! " + key + "= " + elementTypeAndValue.getLocatorType(key) + ":" + elementTypeAndValue.getLocatorValue(key) + " ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find presence element with key:<key> and click.")
    public void _findPresenceElementWithKeyClick(String key) {
        try {
            findPresenceElementWithKey(key).click();
        } catch (Exception e) {
            throw new AssertionError("Element could not be clicked ! " + key + "= " + elementTypeAndValue.getLocatorType(key) + ":" + elementTypeAndValue.getLocatorValue(key) + " ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find element with key:<key> and click(JS).")
    public void _findElementWithKeyClickJS(String key) {
        try {
            findElementWithKeyAndClickJS(key);
        } catch (Exception e) {
            throw new AssertionError("Element could not be clicked ! " + key + "= " + elementTypeAndValue.getLocatorType(key) + ":" + elementTypeAndValue.getLocatorValue(key) + " ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find visible element with key:<key> and click(JS).")
    public void _findVisibleElementWithKeyClickJS(String key) {
        try {
            findVisibleElementWithKeyAndClickJS(key);
        } catch (Exception e) {
            throw new AssertionError("Element could not be clicked ! " + key + "= " + elementTypeAndValue.getLocatorType(key) + ":" + elementTypeAndValue.getLocatorValue(key) + " ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find presence element with key:<key> and click(JS).")
    public void _findPresenceElementWithKeyClickJS(String key) {
        try {
            findPresenceElementWithKeyAndClickJS(key);
        } catch (Exception e) {
            throw new AssertionError("Element could not be clicked ! " + key + "= " + elementTypeAndValue.getLocatorType(key) + ":" + elementTypeAndValue.getLocatorValue(key) + " ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find element with key:<key> and send text:<text>.")
    public void _presenceElementSendKeys(String key, String text) {
        String endText = text;
        if (text.startsWith("Value_")) {
            endText = valueReader.getProperty(text);
        }
        try {
            findPresenceElementWithKey(key).sendKeys(endText);
        } catch (Exception e) {
            throw new AssertionError("Send text could not be made to the element ! " + key + "= " + elementTypeAndValue.getLocatorType(key) + ":" + elementTypeAndValue.getLocatorValue(key) + " ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find element with key:<key> and send text:<text>(JS).")
    public void _presenceElementSendKeysJS(String key, String text) {
        String endText = text;
        if (text.startsWith("Value_")) {
            endText = valueReader.getProperty(text);
        }
        try {
            sendKeysWithJS(findPresenceElementWithKey(key),endText);
        } catch (Exception e) {
            throw new AssertionError("Send text could not be made to the element (JS) ! " + key + "= " + elementTypeAndValue.getLocatorType(key) + ":" + elementTypeAndValue.getLocatorValue(key) + " ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find element with key:<key> and verify is displayed.")
    public void _isVisibleElement(String key) {
        try {
            findVisibleElementWithKey(key);
        } catch (Exception e) {
            throw new AssertionError("Element is not visible ! " + key + "= " + elementTypeAndValue.getLocatorType(key) + ":" + elementTypeAndValue.getLocatorValue(key) + " ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find presence element with text:<text>.")
    public void _findPresenceElementWithText(String text) {
        String endText = text;
        if (text.startsWith("Value_")) {
            endText = valueReader.getProperty(text);
        }
        try {
            findPresenceElementWithTextXpath(endText);
        } catch (Exception e) {
            throw new AssertionError("Element with text: " + endText + " not found !  ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find visible element with text:<text>.")
    public void _findVisibleElementWithText(String text) {
        String endText = text;
        if (text.startsWith("Value_")) {
            endText = valueReader.getProperty(text);
        }
        try {
            findVisibleElementWithTextXpath(endText);
        } catch (Exception e) {
            throw new AssertionError("Element with text: " + endText + " not found !  ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find presence element with contain text:<text>.")
    public void _findPresenceElementWithContainText(String text) {
        String endText = text;
        if (text.startsWith("Value_")) {
            endText = valueReader.getProperty(text);
        }
        try {
            findPresenceElementWithContainTextXpath(endText);
        } catch (Exception e) {
            throw new AssertionError("Element with contain text: " + endText + " not found !  ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find visible element with contain text:<text>.")
    public void _findVisibleElementWithContainText(String text) {
        String endText = text;
        if (text.startsWith("Value_")) {
            endText = valueReader.getProperty(text);
        }
        try {
            findVisibleElementWithContainTextXpath(endText);
        } catch (Exception e) {
            throw new AssertionError("Element with contain text: " + endText + " not found !  ==> Error message : " + e.getMessage());
        }
    }

    @Step("Find elements with key:<key> and select with index <index>.")
    public void _findElementsWithKeyChooseWithIndex(String key,String index) {
        int _index = Integer.parseInt(index);
        try {
            findElementsWithKey(key).get(_index).click();
        } catch (Exception e) {
            throw new AssertionError("Element could not be selected! " + key + "= " + elementTypeAndValue.getLocatorType(key) + ":" + elementTypeAndValue.getLocatorValue(key) + " ==> Error message : " + e.getMessage());
        }
    }

    @Step("Is the same text:<text> and text of the element with key:<key>?")
    public void _isSameTextAndTextOfElementWithKey(String text,String key) {
        String endText = text;
        if (text.startsWith("Value_")) {
            endText = valueReader.getProperty(text);
        }
        String actualText = findElementWithKey(key).getText();
        Assert.assertEquals(actualText,endText,"Expected text and actual text are not the same !!");
    }

    @Step("Find elements with key:<key> and scroll to it.")
    public void _findElementWithKeyAndScroll(String key) {
        try {
            scrollIntoElementJS(findElementWithKey(key));
        } catch (Exception e) {
            throw new AssertionError("Element not found and could not scroll ! " + key + "= " + elementTypeAndValue.getLocatorType(key) + ":" + elementTypeAndValue.getLocatorValue(key) + " ==> Error message : " + e.getMessage());
        }
    }

    @Step("Scroll all page DOWN.")
    public void _scrollAllTheWayDownJS() {
        try {
            scrollAllTheWayDownJS();
        } catch (Exception e) {
            throw new AssertionError("Could not scroll Down ! " + e.getMessage());
        }
    }

    @Step("Scroll all page UP.")
    public void _scrollAllTheWayUpJS() {
        try {
            scrollAllTheWayUpJS();
        } catch (Exception e) {
            throw new AssertionError("Could not scroll Up ! " + e.getMessage());
        }
    }






}
