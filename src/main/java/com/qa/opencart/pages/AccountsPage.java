package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByLinkText;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage 
{
	private WebDriver driver;
	private  ElementUtil eleUtil;
	
private By logout = By.linkText("Logout");
private By headers = By.cssSelector("div#content h2");
private By search = By.name("search");
private By searchIcon = By.cssSelector("div#search span");


public AccountsPage(WebDriver driver)
{
	this.driver=driver;
	eleUtil = new ElementUtil(driver);
}

public String getAccountsPageTitle()
{
	String title= eleUtil.waitForTitleContainsAndReturn(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
  
 System.out.println("Accounts page title:" +title);
	return title;
}


public boolean isLogoutLinkExist()
{
	 return eleUtil.isElementDisplayed(logout);
}

public int getTotalAccountsPageHeader()
{
	return eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_SHORT_TIME_OUT).size();
	
}

public List<String> getAccPageHeaders()
{
	     List<WebElement> headersList = eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
	     List<String> headersValueList = new ArrayList<String>();
	     for (WebElement e:headersList) 
	     {
	    	 String header =e.getText();
	    	 headersValueList.add(header);
	     }
	     return headersValueList;
}

public ResultsPage doSearch(String searchKey)
{
	WebElement searchEle= eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_SHORT_TIME_OUT);
	eleUtil.doSendKeys(searchEle, searchKey);
	eleUtil.doClick(searchIcon);
	return new ResultsPage(driver);
}

}
