package Group3;

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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCase3 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    @Parameters({"username", "password", "path"})
    public void init(String username, String password,String path) {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get("https://test-basqar.mersys.io/");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();

        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @AfterClass
    public void quit(){
        driver.quit();
    }

    @Test
    public void main() throws InterruptedException {

        //click on budget
        driver.findElement( By.xpath("//span[text()='Budget']")).click();

        //click on Setup
        driver.findElement(By.xpath("(//span[text()='Setup'])[5]")).click();

        //clicl on Cost Center
        driver.findElement(By.xpath("//span[text()='Cost Centers']")).click();

        //click on plus icon
        driver.findElement(By.xpath("//*[@tooltip='COST_CENTER.TITLE.ADD']")).click();

        //fill all the inputs
        WebElement fillTheName =driver.findElement(By.xpath("//ms-text-field[@placeholder='GENERAL.FIELD.NAME']//input"));
        String name = "ukulele"; //expected
        fillTheName.sendKeys(name);


        WebElement fillTheCode = driver.findElement(By.xpath("//ms-text-field[@placeholder='GENERAL.FIELD.CODE']//input"));
        String code = "6778uy"; //expected
        fillTheCode.sendKeys(code);


        WebElement clickTheType = driver.findElement(By.xpath("//div/span[text()='Type']"));
        clickTheType.click();


        //choose type from dropdown
        WebElement choiseOfDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=' Personal ']")));

        String type = choiseOfDropdown.getText();
        choiseOfDropdown.click();


        WebElement orderNumber = driver.findElement(By.xpath(" //ms-text-field[@placeholder='COST_CENTER.FIELD.ORDER_NO']//input")); //after I put input child it except to clear and give new value
        orderNumber.clear();
        String orderNum = "27"; //expected
        orderNumber.sendKeys(orderNum);

        //click on the constants

        driver.findElement(By.xpath("//div[text()='Constants']")).click();

        //fill the input on constants

        WebElement clickTheKey = driver.findElement(By.xpath("//ms-text-field[@placeholder='GENERAL.FIELD.KEY']//input"));
        String key = "kople";
        clickTheKey.sendKeys(key);


        WebElement clickTheValue = driver.findElement(By.xpath("//ms-text-field[@placeholder='GENERAL.FIELD.VALUE']//input"));
        String value = "hayrola";
        clickTheValue.sendKeys(value);

        //click on add button
        WebElement addButton =driver.findElement(By.xpath("//span[text()='Add']"));
        addButton.click();

        //click on save button
        WebElement save =driver.findElement(By.xpath("//span[contains(text(),'Save')]"));
        save.click();

        //verify all the inputs

        List<WebElement> listOfName = driver.findElements(By.cssSelector("table tbody td:nth-child(2)"));
        List<WebElement> listOfCode = driver.findElements(By.cssSelector("table tbody td:nth-child(3)"));
        List<WebElement> listOfType = driver.findElements(By.cssSelector("table tbody td:nth-child(4)"));
        List<WebElement> listOfOrderNo = driver.findElements(By.cssSelector("table tbody td:nth-child(5)"));


        for (int i = 1; i < listOfName.size(); i++) {
            String actualName;
            actualName = listOfName.get(i).getText();
            if (actualName.equals(name)) {
                String actualCode = listOfCode.get(i).getText();
                String actualType = listOfType.get(i).getText();
                String actualOrdNum = listOfOrderNo.get(i).getText();

                Assert.assertEquals(actualCode, code);
                Assert.assertEquals(actualType, type);
                Assert.assertEquals(actualOrdNum, orderNum);

            }


        }
        //click on delete Button
        WebElement deleteButton = wait.until( ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='" + name + "']//following-sibling::td//ms-delete-button/button")));
        deleteButton.click();

        //click on yes

        WebElement delete2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Yes ']")));
        delete2.click();

        try {
            wait.until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector("div[aria-label=\"Cost Center successfully deleted\"]") ) );
        } catch (Exception e){
            System.out.println( " => Delete Failure!");
        }



    }

}
