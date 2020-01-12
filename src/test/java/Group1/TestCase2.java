package Group1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCase2 {
    WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    @Parameters({"username", "password", "path"})
    public void setup(String username, String password,String path) {

        System.setProperty("webdriver.chrome.driver", path);

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://test-basqar.mersys.io");
        // login info
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys(username);

        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys(password);

        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
        // Navigate to Approvement
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2)")).click();
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2) > .children > .nav-item:nth-child(2)")).click();

    }

    @Test
    @Parameters({"firstName", "lastName"})
    public void verifying(String name, String lastName) {
        String expectedname = name + " " + lastName;

        List<WebElement> names = driver.findElements(By.xpath("//tbody//tr//td[3]"));
        Assert.assertNotEquals( names, null );
//        Assert.assertNotEquals( names.size(), 0 );

        boolean found = false;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().equals(expectedname)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue( found );
    }

    @Parameters({"gender"})
    @Test
    public void testingGender(String myGender) {
        List<WebElement> genders = driver.findElements(By.xpath("//tbody//tr//td[8]"));
        Assert.assertNotEquals( genders, null );
//        Assert.assertNotEquals( genders.size(), 0 );

        boolean found = false;
        for (int i = 0; i < genders.size(); i++) {
            if (genders.get( i ).getText().trim().equals(myGender)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue( found );


    }

    @BeforeMethod
    public  void waitForResults(){
        wait.until( ExpectedConditions.numberOfElementsToBeMoreThan( By.cssSelector( "tbody tr" ), 0 ) );
    }

    @AfterClass
    public void quit() {

        driver.quit();
    }
}
