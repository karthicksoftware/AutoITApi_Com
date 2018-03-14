package com.qa.pch.utilities;

import java.io.File;

import com.jacob.com.LibraryLoader;

import autoitx4java.AutoItX;

public final class AutoITUtils {

	public static String jvmBitVersion() {
		return System.getProperty("sun.arch.data.model");
	}

	public static void startFileUpload(String path) throws InterruptedException {
		Thread.sleep(3000L);
		String jacobDllVersionToUse;
		if (jvmBitVersion().contains("32"))
			jacobDllVersionToUse = "jacob-1.18-x86.dll";
		else
			jacobDllVersionToUse = "jacob-1.18-x64.dll";
		File file = new File("libs", jacobDllVersionToUse);
		System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
		AutoItX x = new AutoItX();
		x.controlFocus("Open", "", "Edit1");
		Thread.sleep(2000L);
		x.ControlSetText("Open", "", "Edit1", path);
		Thread.sleep(2000L);
		x.controlClick("Open", "", "Button1");
		Thread.sleep(2000L);
	}
}
