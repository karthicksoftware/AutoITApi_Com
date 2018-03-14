package com.qa.pch.tests;

import org.testng.annotations.Test;
import com.qa.pch.admins.IWEAdmin;
import com.qa.pch.admins.PCHAdmin;
import com.qa.pch.utilities.AutoITUtils;
import com.qa.pch.utilities.TestBase;

public class PCH_RefactorScenarios extends TestBase{

	private IWEAdmin iweAdmin = new IWEAdmin();
	private PCHAdmin pchAdmin = new PCHAdmin(); 
	
	/**
	 * @author karunachalam
	 * @throws Exception
	 */
	@Test  (enabled = false)
	public void selectItemsFromDropdown() throws Exception {
		launchApp(getData("iwe.applicationUrl"));
		iweAdmin.enterUsername(getData("iwe.username"));
		iweAdmin.enterPassword(getData("iwe.password"));
		iweAdmin.clickLogin();
		iweAdmin.clickOnDeviceTab();
		iweAdmin.clickOnDeviceResults(1);
		iweAdmin.typeOnGiveawayGroupDropdown(getData("dropdownExpValue"));
		iweAdmin.clickOnFirstListedOption();
		iweAdmin.validateSelectedOption(getData("dropdownExpValue"));
	}

	/**
	 * @author karunachalam
	 * @throws Exception
	 */
	@Test
	public void uploadFileUsingAutoIT() throws Exception{
		launchApp(getData("pchadmin.applicationUrl"));
		pchAdmin.enterUsername(getData("pchadmin.username"));
		pchAdmin.enterPassword(getData("pchadmin.password"));
		pchAdmin.clickLogin();
		pchAdmin.clickOnQuickIcons("Article Manager");
		pchAdmin.searchForArticle("Hungary");
		pchAdmin.clickOnSearchButton();
		pchAdmin.clickOnSearchResults(1);
		pchAdmin.clickOnZipUploadButton();
		AutoITUtils.startFileUpload(getData("file.path"));
	}
	
	
	
}
