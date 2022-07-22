package assessment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BookMyShow {

	public static void main(String[] args) throws IOException, InterruptedException {
		//call WDM for Chrome
		WebDriverManager.chromedriver().setup();
		//To disable notifications
		/*ChromeDriver options = new ChromeDriver();
		options.addArguments("--disabled-notifications");*/
		//launch Chrome
		ChromeDriver driver = new ChromeDriver();
		//ChromeDriver driver = new ChromeDriver(options);

		//load the driver
		driver.get("https://in.bookmyshow.com/");

		//Maximize the browser
		driver.manage().window().maximize();

		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));		

		//03) Type the city as "Hyderabad" in Select City
		driver.findElement(By.xpath("//img[@alt='HYD']")).click();
		
		//04) Confirm the URL has got loaded with Hyderabad
		String currentUrl = driver.getCurrentUrl();
		
		if(currentUrl.contains("Hyderabad")) {
			System.out.println("URL loaded for Hyderabad");
		}
		else {
			System.out.println("URL not loaded for Hyderabad");
		}		
			
		//05) Search for your favorite movie 
		driver.findElement(By.xpath("//span[text()='Search for Movies, Events, Plays, Sports and Activities']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search for Movies, Events, Plays, Sports and Activities']")).sendKeys("RRR");;
		driver.findElement(By.xpath("(//div[@class='sc-bOCYYb eRsEwz'])[1]")).click();
		
		//06) Click Book Tickets
		driver.findElement(By.xpath("//span[text()='Book tickets']")).click();
		
		//07) Print the name of the theater displayed first
		String theatreName = driver.findElement(By.xpath("//a[@class='__venue-name']")).getText();
		
		System.out.println(theatreName);
		
		//08) Click on the info of the theater
		driver.findElement(By.xpath("//a[@class='__venue-name']")).click();
		driver.findElement(By.xpath("(//div[@class='chevron-icon'])[1]")).click();
		
		//09) Confirm if there is a parking facility in the theater
		String parkText = driver.findElement(By.xpath("//div[@class='venue-facility-item']/div/following-sibling::div")).getText();
		
		System.out.println(parkText);
		
		if (parkText.equals("Parking Facility")) {
			System.out.println("Parking facility available");
		}
		else {
			System.out.println("Parking facility not available");
		}
		
		//11) Click on the first green (available) timing
		driver.findElement(By.xpath("//div[@class='_available showtime-pill-container']/a/div/div")).click();
		
		//12) Click Accept
		driver.findElement(By.xpath("//div[@id='btnPopupAccept']")).click();
		
		//13) Choose 1 Seat and Click Select Seats
		driver.findElement(By.xpath("(//div[@id='dTktQtySelVehiclesIcons']/following-sibling::div//li)[1]")).click();
		driver.findElement(By.xpath("//div[@id='proceed-Qty']")).click();
		
		//14) Choose any available ticket and Click Pay		
		driver.findElement(By.xpath("//div[@class='wzrk-button-container']/button[@class='No thanks']")).click();		
		driver.findElement(By.xpath("//div[@class='seatI']/a[@class='_available']")).click();
		driver.findElement(By.xpath("//a[text()='Pay ']/span")).click();
		

		//15) Print the sub total
		String subTotal = driver.findElement(By.xpath("//div[contains(text(),'Sub total')]/following::div[1]/span")).getText();
		
		System.out.println(subTotal);
		
		//16) Take screenshot and close browser
		File source = driver.getScreenshotAs(OutputType.FILE);
		
		File dest = new File("./bookmyshow.png");
		
		FileUtils.copyFile(source, dest);
				
	}
	

}
