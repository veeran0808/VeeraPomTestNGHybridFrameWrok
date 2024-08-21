package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

/**
 * The ProductInfoPage class represents the product information page of the OpenCart application.
 * It provides methods to interact with and retrieve information from the product page.
 */
public class ProductInfoPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    private By productHeader = By.cssSelector("div#content h1");
    private By productImagesCount = By.cssSelector("div#content a.thumbnail");
    private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

    private Map<String, String> productMap;

    /**
     * Constructor to initialize the ProductInfoPage class with a WebDriver instance.
     * 
     * @param driver The WebDriver instance to interact with the browser.
     */
    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    /**
     * Gets the product header text from the page.
     * 
     * @return The product header text.
     */
    public String getProductHeader() {
        String header = eleUtil.doGetText(productHeader);
        System.out.println("product header ===" + header);
        return header;
    }

    /**
     * Gets the count of product images on the page.
     * 
     * @return The count of product images.
     */
    public int getProductImagesCount() {
        int imagesCount =
                eleUtil.waitForVisibilityOfElemenetsLocated(productImagesCount, TimeUtil.DEFAULT_MEDIUM_TIME).size();
        System.out.println("total images ==" + imagesCount);
        return imagesCount;
    }

    /**
     * Retrieves the product information as a map of key-value pairs.
     * 
     * @return A map containing product information.
     */
    public Map<String, String> getProductInfoMap() {
        productMap = new HashMap<String, String>();

        productMap.put("productname", getProductHeader());
        productMap.put("productimagescount", String.valueOf(getProductImagesCount()));

        getProductMetaData();
        getProductPriceData();
        return productMap;
    }

    /**
     * Retrieves and parses the product metadata (e.g., brand, product code) from the page.
     */
    private void getProductMetaData() {
        List<WebElement> metaList = eleUtil.getElements(productMetaData);
        for(WebElement e : metaList) {
            String metaData = e.getText();
            String meta[] = metaData.split(":");
            String metaKey = meta[0].trim();
            String metaValue = meta[1].trim();
            productMap.put(metaKey, metaValue);
        }
    }

    /**
     * Retrieves and parses the product price data (e.g., price, ex-tax price) from the page.
     */
    private void getProductPriceData() {
        List<WebElement> priceList = eleUtil.getElements(productPriceData);
        String productPrice = priceList.get(0).getText();
        String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
        productMap.put("productprice", productPrice);
        productMap.put("exTaxPrice", exTaxPrice);
    }

}
