package com.qa.pch.admins;

import org.testng.Assert;

import com.qa.pch.utilities.EventFactory;

public class IWEAdmin extends EventFactory implements IAdminFeatures {

	public String username = "css=#j_username";
	public String password = "css=#j_password";
	public String loginButton = "css=#submit";
	public String devicesButton = "css=a #button-1018-btnInnerEl";
	public String deviceListItem = "css=[role='row']:nth-child(%d) td:nth-child(3)";
	public String gwyGroupDropdown = "name=giveawayGroupData.id";
	public String firstSuggestion = "css=ul[class='x-list-plain']>li:first-child";

	public void enterUsername(String text) throws Exception {
		getElement(username).sendKeys(text);
	}

	public void enterPassword(String text) throws Exception {
		getElement(password).sendKeys(text);
	}

	public void clickLogin() throws Exception {
		clickElement(loginButton);
		waitForPageToLoad();
	}

	public void clickOnDeviceTab() throws Exception {
		clickElementJS(devicesButton);
	}

	public void clickOnDeviceResults(int item) throws Exception {
		clickElement(String.format(deviceListItem, Integer.valueOf(item)));
	}

	public void typeOnGiveawayGroupDropdown(String text) throws Exception {
		waitForPresence(5, gwyGroupDropdown);
		getElement(gwyGroupDropdown).clear();
		typeOnElement(gwyGroupDropdown, text);
	}

	public void clickOnFirstListedOption() throws Exception {
		waitForPresence(5, firstSuggestion);
		clickElement(firstSuggestion);
	}

	public void validateSelectedOption(String expected) throws Exception {
		String actualValue = getElement(gwyGroupDropdown).getAttribute("value").trim();
		Assert.assertEquals(expected, actualValue);
	}
}
