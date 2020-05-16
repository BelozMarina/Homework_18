import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class NewTestPrice {

    static WebDriver driver;
    static String browser = "opera";

    public static void main(String[] args) throws InterruptedException {


        if (browser.equals("opera")) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        } else if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            System.out.println("You input incorrect browser");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://price.ua");

        driver.findElement(By.cssSelector("#SearchForm_searchPhrase")).sendKeys("Кавомашина");
        driver.findElement(By.cssSelector("#search-block-submit")).submit();

         WebDriverWait wait = new WebDriverWait(driver, 10);

        // filter by price
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("price_min_"))).sendKeys("8000");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("price_max_"))).sendKeys("12000");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='btn btn-purple ga_cat_filter btn-filters-submit btn-ok']")));
        element.click();
        element.click();

        // filter by producer
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Delonghi']"))).click();






    }
}
