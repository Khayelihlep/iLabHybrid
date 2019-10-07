package operation;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class UIOperation {

	WebDriver driver;
	public UIOperation(WebDriver driver){
		this.driver = driver;
	}
	public void perform(Properties p,String operation,String objectName,String objectType,String value) throws Exception{
		System.out.println("");
		switch (operation.toUpperCase()) {
		case "CLICK":
			//Perform click
			driver.findElement(this.getObject(p,objectName,objectType)).click();
			break;
		case "SETTEXT":
			//Set text on control
			driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
			
			break;
		case "SETNUM":
			//Set text on control
			driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
						
			String phone = String.format("083"+" %03d %04d",(int) Math.floor(999*Math.random()),(int) Math.floor(9999*Math.random()));
			driver.findElement(By.id("phone")).sendKeys(phone);
			
			break;
			
		case "NAVIGATE":
			//Get url of application
			driver.get(p.getProperty(value));
			break;
		case "CLOSE":
			//Close window
			driver.close();
			break;
			
		case "GETTEXT":
			//Get text of an element
		String actual=	driver.findElement(this.getObject(p,objectName,objectType)).getText();
			Assert.assertTrue(actual.contains("You need to upload at least one file."));
			
			driver.close();
			break;

		default:
			break;
		}
	}
	private By getObject(Properties p,String objectName,String objectType) throws Exception{
		//Find by xpath
		if(objectType.equalsIgnoreCase("XPATH")){
			
			return By.xpath(p.getProperty(objectName));
		}
		//find by class
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
			
			return By.className(p.getProperty(objectName));
			
		}
		//find by name
		else if(objectType.equalsIgnoreCase("NAME")){
			
			return By.name(p.getProperty(objectName));
			
		}
		//Find by css
		else if(objectType.equalsIgnoreCase("CSS")){
			
			return By.cssSelector(p.getProperty(objectName));
			
		}
		//find by link
		else if(objectType.equalsIgnoreCase("LINK")){
			
			return By.linkText(p.getProperty(objectName));
			
		}
		//find by partial link
		else if(objectType.equalsIgnoreCase("PARTIALLINK")){
			
			return By.partialLinkText(p.getProperty(objectName));
			
		}
		
		//find by partial id
		else if(objectType.equalsIgnoreCase("ID")){
					
			return By.id(p.getProperty(objectName));
					
		}
		else
		{
			throw new Exception("Wrong object type");
		}
	}
}
