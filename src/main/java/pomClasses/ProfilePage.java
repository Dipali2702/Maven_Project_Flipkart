package pomClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityClasses.Util;

public class ProfilePage extends Util{

	WebDriver driver;
	
	@FindBy(xpath="//div[@class='_1ruvv2']")
	private WebElement profileFullName;
	
	@FindBy(xpath="//div[text()='Manage Addresses']")
	private WebElement manageAddressText;
	
	@FindBy(xpath="//div[@class='_1QhEVk']")
	private WebElement addAddressBtn;
	
	@FindBy(xpath="//div[@class='_1lRtwc _1Jqgld']/input")
	private List<WebElement> addressDetails ;
	
	@FindBy(xpath="//textarea")
	private WebElement area;
	
	@FindBy(xpath="//button[text()='Save']")
	private WebElement saveAddress;
	
	@FindBy(xpath="//div[@class='_1CeZIA']")
	private List<WebElement> savedAddressCount;

	public ProfilePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public boolean getFullProfileName()
	{
		WebElement element = explicitWait(driver, profileFullName);
		String fullName = element.getText();
		
		if(fullName.contains("Dipali"))
		{
			return true;
		}
		
		return false;
	}
	
	public void clickOnManageAddress()
	{
		manageAddressText.click();
	}
	
	public void clickOnAddNewAddress()
	{
		addAddressBtn.click();
	}
	
	public void fillAddressDetails()
	{
		String[] k = {"Dipali Pagire","7350618607","411017","Rahatani,pune"};
		for(int i=0; i<4; i++)
		{
			addressDetails.get(i).sendKeys(k[i]);
		}
	}
	
	public void fillArea()
	{
		area.sendKeys("flat no.401 , Shantai Apartment , Sai sagar Colony 1, Rahatani,Pune");
	}
	
	public void clickOnSaveBtn()
	{
		saveAddress.click();
	}
	
	public int savedAddressCount()
	{
		return savedAddressCount.size();
	}
}
