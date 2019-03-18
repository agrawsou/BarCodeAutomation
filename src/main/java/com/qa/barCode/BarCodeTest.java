package com.qa.barCode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarCodeTest {

	
	@Test
	public void barCode() throws IOException, NotFoundException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\USER\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://barcode.tec-it.com/en/Code128?data=Hi%20This%20is%20Sourabh%20Here");
		
		String barCodeURL = driver.findElement(By.tagName("img")).getAttribute("src");
		System.out.println(barCodeURL);
		
		URL url = new URL(barCodeURL);
		
		BufferedImage bufferImage = ImageIO.read(url);
		
		BufferedImageLuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferImage);
		
		BinaryBitmap bit = new BinaryBitmap(new HybridBinarizer(luminanceSource));
		
		Result result = new MultiFormatReader().decode(bit);
		
		System.out.println(result.getText());
	}
}
