package Test;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.xml.xpath.XPath;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

	Connection con;

	Statement stmt;

	ResultSet rs;

	String FirstName;

	String LastName;

	String phone;
	String customerName;
	
	int randomID ;

	Random rand = new Random();

	@BeforeTest
	public void myTestSetup() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "saif55");
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

		for (int i = 2; i < AllData.size(); i = i + 3) {
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

	@Test(enabled = false)
	public void MouseHover() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1900)");
		Thread.sleep(2000);

		WebElement TheHoverElement = driver.findElement(By.id("mousehover"));
		Actions action = new Actions(driver);

		action.moveToElement(TheHoverElement).build().perform();

		// driver.findElement(By.linkText("Top")).click();
		driver.findElement(By.linkText("Reload")).click();

	}

	@Test(priority = 1, enabled = true)
	public void addData() throws SQLException {
		
	randomID = rand.nextInt(5353,6050);

		String QueryToAddData = "INSERT INTO customers (" + "customerNumber, " + "customerName, " + "contactLastName, "
				+ "contactFirstName, " + "phone, " + "addressLine1, " + "addressLine2, " + "city, " + "state, "
				+ "postalCode, " + "country, " + "salesRepEmployeeNumber, " + "creditLimit" + ") VALUES (" + randomID +","
				+ "'Future Tech Ltd.', " + "'Doe', " + "'John', " + "'+1 555 123 4567', " + "'123 Innovation Road', "
				+ "'Suite 300', " + "'New York', " + "'NY', " + "'10001', " + "'USA', " + "1370, " + "75000.00" + ");";

		stmt = con.createStatement();
		int rowInserted = stmt.executeUpdate(QueryToAddData); // int rowInserted used to insert
		System.out.println(rowInserted);
	}

	@Test(priority = 2, enabled = true)
	public void updateData() throws SQLException {

		String QueryToUpdate = "UPDATE customers\r\n" + "SET contactFirstName = 'Saif',\r\n"
				+ "contactLastName = 'Alhumsi'\r\n" + "WHERE customerNumber =" + randomID;

		stmt = con.createStatement();
		int rowInserted = stmt.executeUpdate(QueryToUpdate); // int rowInserted used to insert
		System.out.println(rowInserted);
	}
	


	@Test(priority = 3, enabled = true)
	public void Calender() throws InterruptedException, SQLException {

		driver.findElement(By.linkText("Booking Calendar")).click();
		Set<String> handles = driver.getWindowHandles();
		List<String> allTabs = new ArrayList<>(handles);

		driver.switchTo().window(allTabs.get(1));

		driver.findElement(By.linkText("25")).click();
		Thread.sleep(3000);

		int RandomID = rand.nextInt(144, 147);

		String QueryToRead = "select * from customers where customerNumber ="+randomID;

		stmt = con.createStatement();
		rs = stmt.executeQuery(QueryToRead); // the rs used for read only

		while (rs.next()) { // بتقرا الداتا
			FirstName = rs.getString("contactFirstName");
			LastName = rs.getString("contactLastName");
			phone = rs.getString("phone");
			customerName = rs.getString("customerName");

		}

		int RandomNumber = rand.nextInt(6000);
		driver.findElement(By.id("name1")).sendKeys(FirstName);
		driver.findElement(By.id("secondname1")).sendKeys(LastName);
		driver.findElement(By.id("email1")).sendKeys(FirstName + LastName + RandomNumber + "@gmail.com");
		driver.findElement(By.id("phone1")).sendKeys(phone);
		driver.findElement(By.id("details1")).sendKeys(customerName);
		System.out.println(randomID);

	}
	
	@Test(priority = 4, enabled = true)
	public void deleteData() throws SQLException {
		
		System.out.println(randomID);

		String QueryToDelete = "DELETE FROM customers\r\n"
				+ "WHERE customerNumber =" + randomID;

		stmt = con.createStatement();
		int rowInserted = stmt.executeUpdate(QueryToDelete); // int rowInserted used to insert
		System.out.println(rowInserted);
	}

	@Test (priority = 5, enabled = true)
	public void TakeAscreenShot () throws IOException, InterruptedException {
		
		Date timestamp = new Date();
		Thread.sleep(2000);

		System.out.println(timestamp);
		String newtimestamp = timestamp.toString().replace(":", "-");

		TakesScreenshot ts = (TakesScreenshot) driver;
		 JavascriptExecutor js = (JavascriptExecutor) driver ;
		File file = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("./ScreenShot_Folder/" + newtimestamp + ".jpg"));

		
		Date timestamp2 = new Date();
		Thread.sleep(2000);

		System.out.println(timestamp2);
		String newtimestamp2 = timestamp2.toString().replace(":", "-");
		
		
		js.executeScript("window.scrollTo(0,600)");
		File file2 = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file2, new File("./ScreenShot_Folder/" + newtimestamp2 + ".jpg"));

		
	}
}
