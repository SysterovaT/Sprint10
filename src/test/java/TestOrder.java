import POM.HeaderObj;
import POM.OrderDialogObj;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestOrder {

    private WebDriver driver;

    @Test
    public void testHeadButtonOrder() {

        //Инициализируем браузер Chrome
        ChromeOptions options = new ChromeOptions();
//        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);

        driver.get("https://qa-scooter.praktikum-services.ru/");

        HeaderObj headerObj = new HeaderObj(driver);
        headerObj.clickOrderButton();

        boolean result = testOrderForm(driver);
        driver.quit();
        assertTrue(result);

    }

    @Test
    public void testPageButtonOrder() {

        //Инициализируем браузер Chrome
        ChromeOptions options = new ChromeOptions();
//        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
//        driver = new FirefoxDriver(options);

        driver.get("https://qa-scooter.praktikum-services.ru/");

        HeaderObj headerObj = new HeaderObj(driver);

        headerObj.clickOrderPageButton();

        boolean result = testOrderForm(driver);

        driver.quit();
        assertTrue(result);

    }

    private boolean testOrderForm (WebDriver driver) {
        OrderDialogObj orderDialogObj = new OrderDialogObj(driver);
        orderDialogObj.waitForLoadDialog();
        orderDialogObj.fillDataFirstStep(
                "Имя", "Фамилия", "Адрес", "Балтийская", "89333000145"
        );
        orderDialogObj.clickNextButton();
        orderDialogObj.fillDataSecondStep("24.05.2024", "трое суток", List.of("Черный"), "Тестовый заказ");
        orderDialogObj.clickOrderButton();
        orderDialogObj.clickOrderApllyButton();
        orderDialogObj.waitForLoadResultOrderModal();
        return orderDialogObj.checkModalTextOrder();
    }

}
