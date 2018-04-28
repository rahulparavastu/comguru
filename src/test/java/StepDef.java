import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class StepDef {

   public static WebDriver driver;

   @Before
   public void startBrowser(){
       WebDriverManager.chromedriver().setup();
       driver =new ChromeDriver();
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
   }

   @After
   public void stopBrowser(){
       driver.quit();
   }
    @Given("^as a User I am on HomePage$")
    public void as_a_User_I_am_on_HomePage() {

        driver.get("http://newtours.demoaut.com/");
    }

    @When("^I enter Username as \"([^\"]*)\"$")
    public void i_enter_Username_as(String arg1) {
       driver.findElement(By.name("userName")).sendKeys(arg1);

    }

    @When("^I enter password as \"([^\"]*)\"$")
    public void i_enter_password_as(String arg1) {
        driver.findElement(By.name("password")).sendKeys(arg1);


    }

    @When("^I click on \"([^\"]*)\"$")
    public void i_click_on(String arg1) {
        driver.findElement(By.name("login")).click();



    }

    @Then("^I can view \"([^\"]*)\" page$")
    public void i_can_view_page(String arg1){

        String expected=driver.getCurrentUrl();
        System.out.println(expected);
       // assertThat(expected,endsWith("9fb"));
        assertThat(expected,startsWith("http://newtours"));
    }




    @Given("^as a User I am on Flight Search Page$")
    public void as_a_User_I_am_on_Flight_Search_Page(){
        driver.get("http://newtours.demoaut.com/");
        this.i_enter_Username_as("eastham");
        this.i_enter_password_as("eastham");
        this.i_click_on("Sign-On");

    }




    @When("^I select journey type \"([^\"]*)\"$")
    public void i_select_journey_type(String arg1){
        List <WebElement> elements= driver.findElements(By.name("tripType"));
        for (WebElement element : elements){
            if(element.getText().equalsIgnoreCase(arg1)){
                element.click();
                break;
            }
        }

    }

    @When("^I select passengers \"([^\"]*)\"$")
    public void i_select_passengers(String arg1) {
        WebElement elements=driver.findElement(By.name("passCount"));
        Select selectpassenger=new Select(elements);
        selectpassenger.selectByValue(arg1);
    }

    @When("^i select  Departing From \"([^\"]*)\"$")
    public void i_select_Departing_From(String arg1) {
        WebElement elements = driver.findElement(By.name("fromPort"));
        Select selectPort= new Select(elements);
        selectPort.selectByValue(arg1);


    }

    @When("^I select Arriving In> \"([^\"]*)\"$")
    public void i_select_Arriving_In(String arg1) {
        WebElement elements=driver.findElement(By.name("toPort"));
        Select selectDesPort= new Select(elements);
        selectDesPort.selectByValue(arg1);
    }

    @When("^I select Service Class \"([^\"]*)\"$")
    public void i_select_Service_Class(String arg1) {
        List<WebElement> elements=driver.findElements(By.name("servClass"));
        for(WebElement ele4 :elements){
            if(ele4.getText().equalsIgnoreCase(arg1)){
                ele4.click();
                break;
            }
        }

    }

    @When("^I click on CONTINUE$")
    public void i_click_on_CONTINUE() {
        driver.findElement(By.name("findFlights")).click();


    }

    @Then("^I can view \"([^\"]*)\" selected$")
    public void i_can_view_selected(String arg1) {
       String expected=arg1;
       String actual=driver.findElement(By.cssSelector(".data_left>font>b")).getText();
       //Assert.assertTrue(expected, Boolean.parseBoolean(actual));
        Assert.assertEquals(expected,actual);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("/Users/kinjalpatel/IdeaProjects/mavenproject/abc.JPG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
