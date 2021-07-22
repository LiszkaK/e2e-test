package Listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import regresiontests.BaseTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TestListener implements ITestListener {
    private static final String SOURCE_FOLDER_SCREENSHOT = "target/screenshots/";

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPngForAllure(WebDriver webDriver) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test success  : " + getClassAndTestNameFromTest(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test failed  : " + getClassAndTestNameFromTest(iTestResult));
        Object currentClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) currentClass).getWebDriver();
        takeScreenShot(webDriver, iTestResult);
        saveScreenshotPngForAllure(webDriver);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test skipped  : " + getClassAndTestNameFromTest(iTestResult));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private void takeScreenShot(WebDriver webDriver, ITestResult iTestResult) {
        System.out.println("Taking a screenshot");

        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        String screenName = SOURCE_FOLDER_SCREENSHOT + getClassAndTestNameFromTest(iTestResult) + ".png";
        File outputFile = new File(screenName);
        try {
            BufferedImage bufferedImage = ImageIO.read(scrFile);
            ImageIO.write(bufferedImage, "png", outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("screenshot captured: " + outputFile.getAbsolutePath());
    }

    private String getClassAndTestNameFromTest(ITestResult iTestResult) {
        return iTestResult.getInstanceName() + "." + iTestResult.getName();
    }
}
