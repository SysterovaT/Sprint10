package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderObj {

    private WebDriver driver;

    // Кнопка заказать
    private By orderHeaderButton = By.xpath(".//div[contains(@class,'Header_Nav')]/button[contains(text(),'Заказать')]");
    private By orderPageButton = By.xpath(".//div[contains(@class,'Home_FinishButton')]/button[contains(text(),'Заказать')]");


    public HeaderObj(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadHeader(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(orderHeaderButton));
    }

    //Нажать кнопку заказать в заголовке
    public void clickOrderButton() {
        driver.findElement(orderHeaderButton).click();
    }

    public void clickOrderPageButton() {
        WebElement element = driver.findElement(orderPageButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(orderPageButton).click();
    }

}
