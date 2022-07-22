package assessment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonBag {

	public static void main(String[] args) throws InterruptedException, IOException {
		//call WDM for Chrome
		WebDriverManager.chromedriver().setup();
		
		//launch Chrome
		ChromeDriver driver = new ChromeDriver();
		
		//load the driver
		driver.get("https://www.amazon.in");
		
		//Maximize the browser
		driver.manage().window().maximize();
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
		
		//Type "Bags" in the Search box
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("bags");
		
		Thread.sleep(3000);
		
		//Choose the third displayed item in the result (bags for boys)
		driver.findElement(By.xpath("//div[@class='s-suggestion-container']/div[text()='bags']/span[text()=' for boys']")).click();

		//Print the total number of results (like 30000)
		String text = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span")).getText();
		
		//System.out.println(text.substring(13)); 
		String str1 = text.substring(13);
		String str = " results for";
		String result1=str1.replaceAll(str, "");
		System.out.println(result1);	
		
		
		//Select the first 2 brands in the left menu
		driver.findElement(By.xpath("(//ul[@aria-labelledby='p_89-title']/li)[1]/span/a/div/label/i")).click();
		driver.findElement(By.xpath("(//ul[@aria-labelledby='p_89-title']/li)[3]/span/a/div/label/i")).click();
		
		String text1 = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span")).getText();
		//System.out.println(text.substring(13)); 
		String str2 = text1.substring(13);		
		String result2=str2.replaceAll(str, "");
		System.out.println(result2);
		
		//08) Choose New Arrivals (Sort)
		driver.findElement(By.xpath("//span[text()='Sort by:']")).click();
		driver.findElement(By.xpath("//a[text()='Newest Arrivals']")).click();
		
		//09) Print the first resulting bag info (name, discounted price)
		String bagName = driver.findElement(By.xpath("(//h5[@class='s-line-clamp-1'])[1]/span")).getText();	
		String[] words = bagName.split(" ");
		String finalCount = words[3].replace(",","");
		System.out.println(bagName);		
		String discPrice = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();		
		System.out.println(discPrice);
		
		driver.findElement(By.xpath("(//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2'])[1]/a")).click();

		//10) Confirm the color of the 'Deal of the day' is in kind of Red
		System.out.println("No deal of the day");
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		
		File dest = new File("./snap1.png");
		
		FileUtils.copyFile(source, dest);
		
	}

}
