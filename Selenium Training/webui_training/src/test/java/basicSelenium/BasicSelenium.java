package basicSelenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.indexeddb.model.DataEntry;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BasicSelenium {
    ChromeDriver driver;
    @BeforeEach
    public void setup(){
        String path=new File("").getAbsolutePath()+"\\src\\test\\resources\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);


        driver = new ChromeDriver();
        // implicit wait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://todo.ly/");

    }

    @AfterEach
    public void cleanup() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }


    @Test
    public void verifyPasswordChange() throws InterruptedException{
        // Cambiar en cada ejecución
        String oldPwd = "123456";
        String newPwd = "12345";


        // click login
        driver.findElement(By.xpath("//img[@src='/Images/design/pagelogin.png']")).click();
        // set email
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("training@training2021.com");
        // set pwd
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys(oldPwd);
        // click login
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();

        //SETTINGS
        driver.findElement(By.xpath("//div[@id='ctl00_HeaderTopControl1_PanelHeaderButtons']/a[1]")).click();
        // Wait until it's visible
        Thread.sleep(3000);
        //Enter old pwd
        driver.findElement(By.id("TextPwOld")).sendKeys(oldPwd);
        //Enter new pwd
        driver.findElement(By.id("TextPwNew")).sendKeys(newPwd);
        // Save changes
        driver.findElement(By.xpath("//div[9]/div[2]/div/button[1]")).click();
        Thread.sleep(3000);
        // Logout
        driver.findElement(By.id("ctl00_HeaderTopControl1_LinkButtonLogout")).click();
        Thread.sleep(5000);

        //Login with new credentials
        // click login
        driver.findElement(By.xpath("//img[@src='/Images/design/pagelogin.png']")).click();
        // set email
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("training@training2021.com");
        // set pwd
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys(newPwd);
        // click login
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        // verificacion
        Assertions.assertTrue(driver.findElement(By.id("ctl00_HeaderTopControl1_LinkButtonLogout")).isDisplayed(),"ERROR!! no se pudo hacer login");
    }

}
