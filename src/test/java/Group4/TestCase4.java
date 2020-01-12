package Group4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestCase4 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Parameters({"username", "password", "path"})
    @BeforeClass
    public void setup(String username, String password, String path) {
        // write your code here
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get("https://test-basqar.mersys.io");
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();
    }
    @Test
    public void main() {
        // to select "Budget", the first child of group items
        driver.findElement(By.cssSelector(".group-items > :nth-child(6)")).click();
        // to select "Setup", the first child of  the first child of group items
        driver.findElement(By.cssSelector(".group-items > :nth-child(6) > .children > :nth-child(1)")).click();
        // to select "budget groups ", the forth child of the first child of  the first child of group items
        driver.findElement(By.cssSelector(".group-items > :nth-child(6) > .children > :nth-child(1) > .children > :nth-child(1)")).click();
        driver.findElement( By.cssSelector( "[data-icon='plus']" ) ).click();
        // driver.findElement(By.xpath("//*[@class='mat-mini-fab mat-button-base mat-accent']")).click();

        String descriptionname="Group4";
        driver.findElement(By.xpath("//*[@id=\"mat-input-5\"]")).sendKeys(descriptionname);

        driver.findElement(By.cssSelector("#mat-select-2")).click();
        driver.findElement(By.xpath("//*[@id=\"mat-option-11\"]/span")).click();

        String startDate = "01/01/2020";
        driver.findElement(By.xpath("//*[@id=\"mat-input-7\"]")).sendKeys(startDate);

        String endDate="03/28/2020";
        driver.findElement(By.xpath("//*[@id=\"mat-input-8\"]")).sendKeys(endDate);

        driver.findElement(By.xpath("//*[@id=\"mat-input-6\"]")).sendKeys("comment");
        driver.findElement(By.xpath("//div[@class='mat-tab-label mat-ripple ng-star-inserted']")).click();
        driver.findElement(By.xpath("//input[@class='mat-input-element mat-form-field-autofill-control cdk-text-field-autofill-monitored ng-untouched ng-pristine ng-invalid']")).sendKeys("1");
        driver.findElement(By.xpath("//ms-decimal-field//input")).sendKeys("22112");
        driver.findElement(By.xpath("//button[@class='mat-raised-button mat-button-base mat-basic ng-star-inserted']")).click();
        WebElement saveIcon = driver.findElement( By.cssSelector( "mat-dialog-container [data-icon=\"save\"]" ) );
        saveIcon.click();
        try {
            wait.until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( "[aria-label='Budget Group successfully created']" ) ) );

        } catch( Exception e) {
            Assert.fail( "Delete failure", e );
        }

        String text = driver.findElement(By.xpath("//td[text()='Group4']")).getText();
        Assert.assertEquals(descriptionname,text);

        String text2 = driver.findElement(By.xpath("//td[text()='01/01/2020']")).getText();
        Assert.assertEquals(startDate,text2);

        String text3 = driver.findElement(By.xpath("//td[text()='03/28/2020']")).getText();
        Assert.assertEquals(endDate,text3);

    }
    @AfterClass
    public void quit(){
        driver.quit();
    }
}
