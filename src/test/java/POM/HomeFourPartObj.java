package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class HomeFourPartObj {

    private WebDriver driver;
    private By homeFAQElement = By.xpath("//div[contains(@class,'Home_FAQ')]");
    private By accordion = By.xpath(".//div[@class='accordion']");

    public HomeFourPartObj(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkQuestion(String question, String answer) {
        WebElement accordionItem = driver.findElement(homeFAQElement).findElement(accordion).findElement(By.xpath(".//*[contains(text(),'" + question + "')]"));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", accordionItem);

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[contains(text(),'" + question + "')]")));

        accordionItem.click();

        String findAnswer = driver.findElement(homeFAQElement).findElement(accordion).findElement(By.xpath(".//p[text()='" + answer + "']")).getText();

        return Objects.equals(answer, findAnswer);
    }
}
