package com.qa.pch.utilities;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class EventFactory extends TestBase{
	
	protected WebElement getElement(String locator) throws Exception {
		wait(3);
		waits(30, locator);
		waitForPresence(3, locator);
		return getDriver().findElement(getByType(locator));
	}
	
	protected List<WebElement> getElements(String locator) throws Exception{
		wait(3);
		waits(30, locator);
		return getDriver().findElements(getByType(locator));
	}

	protected void clickElement(String locator) throws Exception {
		waits(3, locator);
		waitForPresence(3, locator);
		getDriver().findElement(getByType(locator)).click();
	}
	
	protected void clickElement(WebElement element) throws Exception {
		waits(30, element);
		element.click();
	}
	
	protected void clickElementJS(String locator) throws Exception {
		System.out.println(waits(30, locator));
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click()", getDriver().findElement(getByType(locator)));
	}

	protected void clickElementJS(WebElement element) throws Exception {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click()",element);
	}
	
	protected String getText(String locator) throws Exception{
		return getElement(locator).getText().trim();
	}
	protected String getText(WebElement locator) throws Exception{
		return locator.getText().trim();
	}
	
	protected WebElement moveToWebElement(String locator) throws Exception{
		Actions action = new Actions(getDriver());
		action.moveToElement(getElement(locator));
		action.perform();
		return getElement(locator);
	}
	
	protected WebElement moveToWebElement(WebElement locator) throws Exception{
		Actions action = new Actions(getDriver());
		action.moveToElement(locator);
		action.perform();
		return locator;
	}
	protected void typeOnElement(String element, String text) throws Exception {
		waits(2, element);
		getElement(element).sendKeys(text);
	}
	protected void typeOnElement(WebElement element, String text) throws Exception {
		waits(2, element);
		element.sendKeys(text);
	}

}
