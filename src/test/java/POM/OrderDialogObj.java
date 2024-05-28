package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrderDialogObj {
    private WebDriver driver;

    //App_App
    private By mainApp = By.xpath(".//div[contains(@class,'App_App')]");
    //Имя
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //Фамилия
    private By secondNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Адрес
    private By adresField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Станция метро
    private By metroField = By.xpath(".//input[@class='select-search__input']");
    private By selectSearch = By.xpath(".//div[@class='select-search__select']");
    //Телефон
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка далее
    private By nextButton = By.xpath(".//div[contains(@class,'NextButton')]/button");
    //Дата * Когда привезти самокат
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //    //Срок * Срок аренды
    private By rentalPeriodDiv = By.xpath(".//div[@class='Dropdown-control']");
    private By rentalPeriodList = By.xpath(".//div[@class='Dropdown-menu']");


    //Цвет
    private By colorBlackField = By.id("black");
    private By colorGreyField = By.id("grey");
    //    //Комментарий
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //    //Кнопка Заказать
    private By orderButton = By.xpath(".//div[contains(@class,'Order_Buttons')]/button[text() = 'Заказать']");
    ;
    //    //Кнопка подтверждения заказа
    private By orderApllyButton = By.xpath(".//div[contains(@class,'Order_Modal')]/*/button[text()='Да']");
    //    //Текст формленного заказа Order_ModalHeader
    private By summaryOrderTextField = By.xpath(".//div[contains(@class,'Order_ModalHeader')]");


    public OrderDialogObj(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadDialog() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Content__bmtHS")));
    }

    public void waitForLoadSelectSearch() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(selectSearch));
    }

    public void waitForLoadPeriodList() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodList));
    }

    public void waitForLoadResultOrderModal() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(summaryOrderTextField));
    }

    public void fillDataFirstStep(
            String name, String secondName, String adress, String metro, String phone
    ) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(secondNameField).sendKeys(secondName);
        driver.findElement(adresField).sendKeys(adress);
        driver.findElement(metroField).click();
        waitForLoadSelectSearch();
        driver.findElement(selectSearch).findElement(By.xpath(".//div[contains(text(), '" + metro + "')]")).click();
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void fillDataSecondStep(String date, String period, List<String> colorField, String comment) {
        driver.findElement(dateField).sendKeys(date);
        // делаем миссклик на пустое место на форме (в данном случае есть div основного div) чтобы скрыть окно выбора даты
        driver.findElement(mainApp).click();
        driver.findElement(rentalPeriodDiv).click();
        waitForLoadPeriodList();
        driver.findElement(rentalPeriodList).findElement(
                By.xpath(".//div[contains(text(), '" + period + "')]")
        ).click();

        colorField.forEach(color -> {
            if (color.equals("Черный")) {
                driver.findElement(colorBlackField).click();
            } else if (color.equals("Серый")) {
                driver.findElement(colorGreyField).click();
            }
        });

        driver.findElement(commentField).sendKeys(comment);


    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickOrderApllyButton() {
        driver.findElement(orderApllyButton).click();
    }

    public boolean checkModalTextOrder() {
        String text = driver.findElement(summaryOrderTextField).getText();
        return text.contains("Заказ оформлен");
    }
}
