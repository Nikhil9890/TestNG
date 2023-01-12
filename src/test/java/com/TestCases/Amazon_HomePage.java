package com.TestCases;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.Zip;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;
import org.testng.annotations.Parameters;

public class Amazon_HomePage {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@class='a-cardui-footer']/a[contains (text(),'Shop now')]")).click();
		driver.findElement(
				By.xpath("//a[@class='a-color-base a-link-normal']/span[contains (text(),'Home & Kitchen')]")).click();
		driver.findElement(
				By.xpath("//a[@class='a-link-normal s-navigation-item']/span[contains (text(),'Kitchen & Dining')]"))
				.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(
				"//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']/span[contains (text(),'AmazonBasics Enameled Heavy Duty Cast Iron Dutch Oven / Cooking Pan with Lid- 4.1 Liters (5.07 Kgs), Blue')]"))
				.click();
		Thread.sleep(4000);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
	}
	// }

	@Parameters("browser-name")
	@BeforeMethod
	public void driverinitialization() throws java.net.SocketException {
		// TODO Auto-generated method stub
		System.out.println("Launching Chrome Browser");
		driver = new ChromeDriver();
		// utility.main(null);

	}

	@AfterMethod
	public void closebrowser() {
		driver.quit();
	}

	@Test
	public void signin() throws InterruptedException {

		String Expectedtitle = "Amazon Sign In";
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@id='nav-tools']/a[2]")).click();
		String Actualtitle = driver.getTitle();
		Assert.assertEquals(Actualtitle, Expectedtitle);
	}

	@Test
	public void actualLogin() throws InterruptedException {
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//div[@id='nav-tools']/a[2]")).click();
		Thread.sleep(10000);
		String Expectedtitle = "Your password is incorrect";
		driver.findElement(By.xpath("//div[@class='a-row a-spacing-base']/input[@name='email']"))
				.sendKeys("9890826508");
		driver.findElement(By.xpath("//span[@class='a-button-inner']/input")).click();
		driver.findElement(By.xpath("//div[@class='a-section a-spacing-large']/input")).sendKeys("7821886597");
		driver.findElement(By.xpath("//span[@class='a-button-inner']/input[@id='signInSubmit']")).click();
		String ActualTitle = driver.findElement(By.xpath("//span[@class='a-list-item']")).getText();
		Assert.assertEquals(ActualTitle, Expectedtitle);
	}

	@Test
	public void verifyhomepagetitle() {
		String Expectedtitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		String Actualtitle = driver.getTitle();
		Assert.assertEquals(Actualtitle, Expectedtitle);
	}

	@Test
	public void verifynavigationmenutext() {
		List l = new ArrayList();
		l.add("Best Sellers");
		l.add("Mobiles");
		l.add("Today's Deals");
		l.add("Customer Service");
		l.add("Electronics");
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		List<WebElement> linktext = driver.findElements(By.xpath("//div[@id='nav-xshop']/a"));
		System.out.println(linktext.size());
		List<String> st = new ArrayList<String>();
		for (WebElement string : linktext) {
			st.add(string.getText());
		}
		System.out.println(st);
		System.out.println(st.containsAll(l));
	}

	@Test(dependsOnMethods = "verifynavigationmenutext")
	public void dropdowntextcount() {
		int ExpectedCount = 43;
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath(
				"//select[@class='nav-search-dropdown searchSelect nav-progressive-attrubute nav-progressive-search-dropdown']"))
				.click();
		List<WebElement> dropDownBox = driver.findElements(By.xpath("//select[@name='url']/option"));
		int ActualCount = dropDownBox.size();
		System.out.println(ActualCount);
		Assert.assertEquals(ActualCount, ExpectedCount);
	}

	/*
	 * Select Kitchen Product AmazonBasics Enameled Heavy Duty Cast Iron Dutch Oven
	 * / Cooking Pan with Lid- 4.1 Liters (5.07 Kgs), Blue Under Category Home &
	 * Kitchen and Kitchen & Dining which is given under Upgrade your home | Amazon
	 * Brands & more element And add to cart
	 */
	@Test
	public void addtocart() throws InterruptedException {
		Amazon_HomePage.main(null);

		String Expectedtitle = "Amazon.in Shopping Cart";
		String ActualTitle = driver.getTitle();
		Assert.assertEquals(ActualTitle, Expectedtitle);
		String title = driver.getTitle();
		System.out.println(title);
		String URL = driver.getCurrentUrl();
	}

	@Test
	public void deleteproductincart() throws InterruptedException {
		Amazon_HomePage.main(null);
		driver.manage().window().maximize();
		String ExpectedTitle = "Amazon.in Shopping Cart";
		driver.findElement(
				By.xpath("//div[@class='layoutToolbarPadding']/a[@class='nav-a nav-a-2 nav-progressive-attribute']"))
				.click();
		driver.findElement(By.xpath(
				"//input[@aria-label='Delete AmazonBasics Enameled Heavy Duty Cast Iron Dutch Oven / Cooking Pan with Lid- 4.1 Liters (5.07 Kgs), Blue']"))
				.click();
		String Actualtitle = driver.getTitle();
		Assert.assertEquals(Actualtitle, ExpectedTitle);
		System.out.println("This is deleteproductincart");
		Reporter.log(Actualtitle);
	}

	@Test
	public void verifytitleoftodaysdeal() {
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div/a[@data-csa-c-content-id='nav_cs_gb']")).click();
		String ExpectedTitle = "Amazon.in - Today's Deals";
		String ActualTitle = driver.getTitle();
		Assert.assertEquals(ExpectedTitle, ActualTitle);
	}

	/*
	 * upcoming price 5000&above with 25%off Average customer Review 4.5 for all
	 * deal Product Wireless Video Doorbell from Hero Group | Instant Visitor Video
	 * Call on Phone
	 */
	@Test
	public void doesitsatisfyRequirement() throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div/a[@data-csa-c-content-id='nav_cs_gb']")).click();
		driver.findElement(By.xpath("//a[@role='button']/span[contains (text(),'Upcoming')]")).click();
		driver.findElement(By.xpath("//a[@role='button']/span[contains (text(),'₹5,000 and Above')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='a-row']/a/span[contains (text(),'25% off or more')]")).click();
		driver.findElement(By.xpath("//a[@role='button']//i[@class='a-icon a-icon-star a-star-4']")).click();
		Thread.sleep(10000);
		driver.findElement(
				By.xpath("//div[@class='Grid-module__gridDisplayGrid_2X7cDTY7pjoTwwvSRQbt9Y']//child::div[15]"))
				.click();
		String ExpectedTitle = "Wake Up 12-inch Medium Firm Queen Size Pocket Spring Mattress (84x66x12)";
		WebElement product_title = driver.findElement(By.xpath("//h1[@id='title']"));
		String Actualtitle = product_title.getText();
		Assert.assertEquals(Actualtitle, ExpectedTitle);
	}

	@Test
	public void MouseHoverMethod() {
		Actions action = new Actions(driver);
		driver.get("https://www.amazon.in/");
		WebElement hovr = driver.findElement(By.xpath("//div[@id='nav-tools']/a[2]"));
		action.moveToElement(hovr);
		String[] elements = new String[] { "Sign in", "New customer?", "Start here.", "Your Lists",
				"Create a Wish List", "Wish from Any Website", "Baby Wishlist", "Discover Your Style",
				"Explore Showroom", "Your Account", "Your Account", "Your Orders", "Your Wish List",
				"Your Recommendations", "Your Prime Membership", "Your Prime Video", "Your Subscribe & Save Items",
				"Memberships & Subscriptions", "Your Amazon Business Account", "Your Seller Account",
				"Manage Your Content and Devices" };
		List list = new ArrayList(Arrays.asList(elements));
		List<WebElement> hoverelement = driver.findElements(By.xpath("//div/div/a[@class='nav-link nav-item']"));
		List<String> st = new ArrayList<String>();
		for (WebElement strig : hoverelement) {
			st.add(strig.getText());
		}
		System.out.println(list.contains(hoverelement));
	}

	public void verifyhomeagetitlecount() {
		int ExpectedTitleCount = 21;
		driver.get("https://www.amazon.in/");
		List<WebElement> Titles = driver.findElements(By.xpath("//div[@class='a-cardui-header']"));
		int ActualTitleCount = Titles.size();
		Assert.assertEquals(ActualTitleCount, ExpectedTitleCount);
	}

}
