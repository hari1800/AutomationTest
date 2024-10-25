package com.ecommerce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AutomationTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testECommerceFlow() {

        driver.get("https://www.amazon.in");

        WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys("iPhone 13");
        searchField.submit();

        List<WebElement> products = driver.findElements(By.cssSelector(".s-main-slot .s-result-item"));
        System.out.println("Total count of iPhone 13 devices listed on the first page: " + products.size());

      
        if (products.size() > 1) {
            products.get(1).click();
        }

     
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("variation_color_name")));
        WebElement colorOption = driver.findElement(By.id("variation_color_name"));
        colorOption.click();
        driver.findElement(By.xpath("//li[@data-asin='B09G9MZKHH']//span[contains(text(),'Red')]")).click(); 

        wait.until(ExpectedConditions.elementToBeClickable(By.id("storage_256GB")));
        driver.findElement(By.id("storage_256GB")).click();

        driver.findElement(By.id("add-to-cart-button")).click();

        driver.findElement(By.id("nav-cart")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sc-product-title")));
        driver.findElement(By.cssSelector(".sc-product-title")).click();

        driver.findElement(By.name("proceedToRetailCheckout")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Cash on Delivery')]")));
        driver.findElement(By.xpath("//span[contains(text(),'Cash on Delivery')]")).click();
        driver.findElement(By.name("continue")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".a-box-inner")));
        String deliveryAddress = driver.findElement(By.cssSelector(".a-box-inner")).getText();
        System.out.println("Delivery Address: " + deliveryAddress);

        driver.findElement(By.name("placeYourOrder1")).click();

        driver.findElement(By.id("nav-link-accountList")).click();

        driver.findElement(By.linkText("Your Orders")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".order-info")));
        List<WebElement> orders = driver.findElements(By.cssSelector(".order-info"));
        boolean orderFound = orders.stream().anyMatch(order -> order.getText().contains("iPhone 13") && order.getText().contains("Red") && order.getText().contains("256 GB"));
        
        Assert.assertTrue(orderFound, "The order for iPhone 13 Red 256 GB is not listed under Your Orders.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        // driver.quit();
    }
}
