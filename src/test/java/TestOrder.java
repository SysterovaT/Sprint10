import POM.HeaderObj;
import POM.OrderDialogObj;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class TestOrder {
  
  private WebDriver driver;
  
  @Before
  public void startUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
    driver = new ChromeDriver(options);
  }
  
  
  @Test
  public void testHeadButtonOrder() {
    
    driver.get("https://qa-scooter.praktikum-services.ru/");
    HeaderObj headerObj = new HeaderObj(driver);
    headerObj.clickOrderButton();
    boolean result = testOrderForm();
    
    assertTrue(result);
    
  }
  
  @Test
  public void testPageButtonOrder() {
    
    driver.get("https://qa-scooter.praktikum-services.ru/");
    HeaderObj headerObj = new HeaderObj(driver);
    headerObj.clickOrderPageButton();
    boolean result = testOrderForm();
    
    assertTrue(result);
    
  }
  
  private boolean testOrderForm() {
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
  
  @After
  public void tearDown() {
    driver.quit();
  }
  
}
