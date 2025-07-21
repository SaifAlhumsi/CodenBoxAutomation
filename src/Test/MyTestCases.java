package Test;

import java.awt.Window;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();
	String theURL = "https://codenboxautomationlab.com/practice/";

	@BeforeTest
	public void myTestSetup() {

		driver.get(theURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(enabled = false)
	public void RadioButton() {

		WebElement ContainerRadioButton = driver.findElement(By.xpath("//div[@id='radio-btn-example']//fieldset"));
		ContainerRadioButton.findElements(By.tagName("input")).get(0).click();

	}

	@Test(enabled = false)
	public void DynamicDropDownList() throws InterruptedException {

		String[] Countries = { "Jor", "Unit", "ira" };

		WebElement CountryInput = driver.findElement(By.id("autocomplete"));
		CountryInput.sendKeys(Countries[1]);

		Thread.sleep(2000);
		CountryInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER));

	}

	@Test(enabled = false)
	public void Select_Tag() {

		WebElement mySelectTag = driver.findElement(By.id("dropdown-class-example"));
		Select mySelect = new Select(mySelectTag);

		// mySelect.selectByIndex(1);
		// mySelect.selectByVisibleText("API");
		mySelect.selectByValue("option3");

	}

	@Test(enabled = false)
	public void CheckBox() {

		WebElement CheckBoxContainer = driver.findElement(By.xpath("//div[@id='checkbox-example']//fieldset"));

		List<WebElement> AllCheckBoxes = CheckBoxContainer.findElements(By.tagName("input"));

		for (int i = 0; i < AllCheckBoxes.size(); i++) {
			AllCheckBoxes.get(i).click();

		}
	}

	@Test(enabled = false)
	public void openWindow() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,700)");
		driver.findElement(By.id("openwindow")).click();

		Set<String> handles = driver.getWindowHandles();
		List<String> allTabs = new ArrayList<>(handles);

		driver.switchTo().window(allTabs.get(1));

		driver.findElement(By.xpath("//*[@id=\"menu-item-9680\"]/a")).click();

		driver.switchTo().window(allTabs.get(0));
		System.out.println(driver.getTitle());

	}
	
	@Test(enabled = false)
	public void openTab() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,700)");
		driver.findElement(By.id("opentab")).click();

		Set<String> handles = driver.getWindowHandles();
		List<String> allTabs = new ArrayList<>(handles);

		driver.switchTo().window(allTabs.get(1));
		Thread.sleep(2000);

		driver.switchTo().window(allTabs.get(0));
		Thread.sleep(2000);

		System.out.println(driver.getTitle());
		
	}

	@Test(enabled = false)
	public void AlertAndConfirm() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,800)");
		
		Thread.sleep(1000);
		driver.findElement(By.id("name")).sendKeys("Saif");

		driver.findElement(By.id("alertbtn")).click();
		Thread.sleep(1000);
		
		boolean Actual = driver.switchTo().alert().getText().contains("Saif");
		
		Assert.assertEquals(Actual, true);
		
		Thread.sleep(1000);
		
		driver.switchTo().alert().accept();
		
		
	}
	
	@Test(enabled = false)
	public void theTable() {
		
		WebElement TheTable = driver.findElement(By.id("product"));
		
		List<WebElement> AllData = TheTable.findElements(By.tagName("td"));
		
		for (int i = 2; i < AllData.size(); i = i+3) {
			System.out.println(AllData.get(i).getText());
			
		}
	}
		
		@Test(enabled = false)
		public void HideAndShow() throws InterruptedException {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0,1500)");
			Thread.sleep(2000);
			
			WebElement TheElementWeNeedToHide = driver.findElement(By.id("displayed-text"));
			WebElement theButtonToHide = driver.findElement(By.id("hide-textbox"));
			theButtonToHide.click();
			
			System.out.println(TheElementWeNeedToHide.isDisplayed());
			
			WebElement ShowButton = driver.findElement(By.id("show-textbox"));
			ShowButton.click();
			System.out.println(TheElementWeNeedToHide.isDisplayed());
		
	}
	
		@Test(enabled = false)
		public void EnableAndDisable() {
			
			driver.findElement(By.id("disabled-button")).click();
			System.out.println(driver.findElement(By.id("enabled-example-input")).isEnabled());
			
			driver.findElement(By.id("enabled-button")).click();
			System.out.println(driver.findElement(By.id("enabled-example-input")).isEnabled());
			
		}
		
		@Test(enabled = true)
		public void MouseHover() throws InterruptedException {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0,1900)");
			Thread.sleep(2000);
			
			WebElement TheHoverElement = driver.findElement(By.id("mousehover"));
			Actions action = new Actions(driver); 
			
			action.moveToElement(TheHoverElement).build().perform();
			
			//driver.findElement(By.linkText("Top")).click();
			driver.findElement(By.linkText("Reload")).click();

			
			
		}
		
}
