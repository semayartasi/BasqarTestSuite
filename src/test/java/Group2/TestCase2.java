package Group2;
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
public class TestCase2 {
    public WebDriver driver;
    public WebDriverWait wait;

    //Navigate to https://test-basqar.mersys.io/
    //Enter username & passport click on sign in button
    @BeforeClass
    @Parameters({"username", "password", "path"})
    public void setUp(String username, String password,String path) {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get("https://test-basqar.mersys.io");
        driver.manage().window().maximize();
        // login info
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("a[class='cc-btn cc-dismiss']")).click();
    }
    @Test
    @Parameters({"exam", "regstart", "regend"})
    public void EntranceExams(String exam, String regstart,String regend) {
        //Click on Entrance Exams
        //Click on setup
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2)")).click();
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2) > .children > .nav-item:nth-child(1)")).click();
        //Click on Entrance Exams
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2) > .children > .nav-item:nth-child(1) > .children > .nav-item:nth-child(1)")).click();

        //Click on plus icon
        driver.findElement(By.cssSelector("svg[data-icon='plus']")).click();

        //Enter name , academic period , grade level , reg start , reg end
        driver.findElement(By.cssSelector("ms-text-field>input")).sendKeys(exam);

        //select academic period
        driver.findElement(By.cssSelector("mat-form-field mat-select[aria-label='Academic Period']")).click();

        WebElement gradeDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cdk-overlay-container mat-option:first-child")));
        gradeDropDown.click();
        //chose first-child
        WebElement gradeDropDown1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("mat-select[aria-label='Grade Level']")));
        gradeDropDown1.click();
       //click grade level
        //driver.findElement(By.cssSelector("mat-select[aria-label='Grade Level']")).click();
        //click bla bla bla
        driver.findElement(By.cssSelector(".cdk-overlay-container mat-option:first-child")).click();

        driver.findElement(By.cssSelector("input[placeholder='Reg.Start']")).sendKeys(regstart);
        driver.findElement(By.cssSelector("input[placeholder='Reg.End']")).sendKeys(regend);
        //Click on the document  type and choose any type then click on add
        //driver.findElement(By.cssSelector("mat-select[aria-label='Document Type']")).click();

       // driver.findElement(By.xpath("//span[contains(text(),'Report card')]")).click();
        //add
       // driver.findElement(By.cssSelector("div[fxflexalign='end center']")).click();

        //Click on save
        driver.findElement(By.cssSelector("div[fxlayoutalign='start stretch']>button:nth-child(2)>span")).click();


        //Click on Entrance Exams
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2)")).click();
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2) > .children > .nav-item:nth-child(1)")).click();
        //Click on Entrance Exams
        driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(2) > .children > .nav-item:nth-child(1) > .children > .nav-item:nth-child(1)")).click();
        //Verify all the inputs are as created from the table which is in the list
        WebElement text = driver.findElement(By.xpath(" //td[contains(text(),'Math')]"));
        String excepted = text.getText();
        Assert.assertEquals(exam, excepted);
    }
    @Test
    public void remove()  {
        //remove
        driver.findElement(By.cssSelector(" ms-table tbody tr  td ms-delete-button")).click();
        driver.findElement(By.cssSelector("mat-dialog-container [type='submit']")).click();
        try {
            wait.until( ExpectedConditions.visibilityOfElementLocated( By.xpath("//div[@class='toast-success ngx-toastr ng-trigger ng-trigger-flyInOut']" ) ) );
            System.out.println("Removed success!");
        } catch( Exception e) {
            Assert.fail("Removed failure",e);
        }
    }

    @AfterClass
    public void quit(){

       driver.quit();
    }
}
