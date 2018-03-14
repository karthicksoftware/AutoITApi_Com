package com.qa.pch.admins;

import com.qa.pch.utilities.EventFactory;

public class PCHAdmin extends EventFactory implements IAdminFeatures{

	public static final String username = "name=username";
	public static final String password = "name=passwd";
	public static final String loginButton = "css=.button1";
	
	public String allCategories = "//div[@class='icon']//*[text()='%s']";
	public String filterSearch = "name=filter_search";
	public String searchButton = "css=[class^='filter-search'] [type='submit']";
	public String clearButton = "css=[class^='filter-search'] [type='button']";
	public String nthSearchResults = "css=[class^='row']:nth-child(%d)  td:nth-child(2) a:first-child";
	public String zipUploadButton = "pch_zip_upload";

	public void enterUsername(String text) throws Exception {
		getElement(username).sendKeys(text);
	}

	public void enterPassword(String text) throws Exception {
		getElement(password).sendKeys(text);
	}

	public void clickLogin() throws Exception {
		clickElement(loginButton);
	}

	public void clickOnQuickIcons(String category) throws Exception{
		clickElement(String.format(allCategories, category));
	}
	
	public void searchForArticle(String text) throws Exception{
		typeOnElement(filterSearch, text);
	}
	
	public void clickOnSearchButton() throws Exception{
		clickElement(searchButton);
	}
	
	public void clickOnSearchResults(int index) throws Exception{
		clickElement(String.format(nthSearchResults, index));
	}
	
	public void clickOnZipUploadButton() throws Exception{
		moveToWebElement(zipUploadButton);
		clickElement(zipUploadButton);
	}
}
