package singletonSession;

import factoryBrowser.FactoryBrowser;
import org.openqa.selenium.WebDriver;

import static helpers.ReadProperties.browser;

public class Session {
    private static Session session = null;
    private WebDriver driver;

    private Session(){
        this.driver= FactoryBrowser.make(browser).create();
    }

    public static Session getInstance(){
        if (session==null){
            session= new Session();
        }
        return session;
    }

    public void closeSession(){
        this.driver.quit();
        session=null;
    }
    public WebDriver getDriver() {
        return driver;
    }
}
