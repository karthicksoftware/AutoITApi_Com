package com.qa.pch.admins;

import com.qa.pch.utilities.EventFactory;

public class BlackjackAdmin extends EventFactory implements IAdminFeatures{
	public static final String username = "#j_username";
	public static final String password = "#j_password";
	public static final String loginButton = "#submit";

	public void enterUsername(String text) throws Exception {
		getElement(username).sendKeys(text);
	}

	public void enterPassword(String text) throws Exception {
		getElement(password).sendKeys(text);
	}

	public void clickLogin() throws Exception {
		clickElement(loginButton);
	}
}
